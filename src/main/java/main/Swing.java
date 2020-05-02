package main.java.main;

import main.java.PlayerInfo.BatterInfo;
import main.java.PlayerInfo.Dandou;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import java.util.Collection;
import java.util.List;

import static main.java.main.Methods.rotateAroundAxisY;

public class Swing {

    private float angle;
    private double y1;
    private double y2;

    public Swing(Player batter){
        Arrow ar = batter.getWorld().spawnArrow(batter.getEyeLocation(), batter.getLocation().getDirection().multiply(1), 1, 0);
        Vector vec = batter.getLocation().getDirection();
        y1=vec.getY();
        new BukkitRunnable() {
            @Override
            public void run() {
                Vector svec = batter.getLocation().getDirection();
                y2=svec.getY();
                double upper=y2-y1;
                if(upper<=0)
                    upper=0;
                angle = svec.angle(vec);
                BatterInfo info=(BatterInfo) Main.EachPlayerInfo.getPlayerInfo(batter);
                float mito=info.getMito()/60F;
                int power=info.getPower();
                double kyui=0;
                Dandou dandou=info.getDandou();
                Collection<Entity> list = ar.getNearbyEntities(mito*1.1, mito*0.6, mito*1.1);
                for (Entity e : list) {
                    if (e.getType() == EntityType.SNOWBALL) {
                        Entity ball = batter.getWorld().spawnEntity(e.getLocation(), EntityType.SNOWBALL);
                        new RunSpeedTimer(batter, new Location(batter.getWorld(), 1325.5, 4,-695.5),0.001875F);
                        Vector newvec;
                        double speedforce = 0;
                        List<MetadataValue> values = e.getMetadata("force");
                        for (MetadataValue v : values) {
                            // 名前を比較して同じプラグインか確認
                            if (v.getOwningPlugin().getName().equals(Main.plugin.getName())) {
                                // 同じなら値をセットしてループ抜ける
                                speedforce = v.asDouble()/10;
                                break;
                            }
                        }
                        List<MetadataValue> kyuivalues = e.getMetadata("kyui");
                        for (MetadataValue v : kyuivalues) {
                            // 名前を比較して同じプラグインか確認
                            if (v.getOwningPlugin().getName().equals(Main.plugin.getName())) {
                                // 同じなら値をセットしてループ抜ける
                                kyui=(v.asDouble()-70)*0.0075;
                                break;
                            }
                        }
                        double force = angle*0.75 + speedforce;
                        double distance = ar.getLocation().distance(StartCommand.pitcher.getLocation()) - e.getLocation().distance(StartCommand.pitcher.getLocation());
                        double rote = -distance * 40;
                        double forceadjustment=(mito-Math.abs(distance))/5;
                        force-=kyui;
                        force+=forceadjustment;
                        force+=(power-70)*0.005;
                        double dandouy;
                        if(dandou==Dandou.GROUNDER)
                            dandouy=-0.1;
                        else if(dandou==Dandou.LOW)
                            dandouy = 0;
                        else if(dandou==Dandou.LINE_DRIVE) {
                            dandouy = 0.15;
                            force-=0.15;
                        }else if(dandou==Dandou.MEDIUM) {
                            dandouy = 0.25;
                            force-=0.225;
                        }else if(dandou==Dandou.HIGH) {
                            dandouy = 0.3;
                            force-=0.25;
                        }else if(dandou==Dandou.POWER) {
                            dandouy = 0.6;
                            force-=0.5;
                            new BukkitRunnable(){
                                @Override
                                public void run() {
                                    Vector vec=ball.getVelocity();
                                    if(vec.getY()<=0)
                                        ball.setVelocity(new Vector(vec.getX(),0,vec.getZ()).multiply(0.9).setY(vec.getY()));
                                    else
                                        ball.setVelocity(vec.multiply(0.9));
                                }
                            }.runTaskTimer(Main.plugin,10,10);
                        }else {
                            dandouy = 0.6;
                            force-=0.5;
                        }
                        if(force<=0)
                            force=0;
                        double ydistance = e.getLocation().getY() - ar.getLocation().getY();
                        Vector vec = e.getVelocity();
                        e.remove();
                        double newvecy=svec.subtract(vec).multiply(force).getY();
                        newvec = rotateAroundAxisY(svec.subtract(vec).multiply(force), rote);
                        newvec=newvec.setY(newvecy + ydistance*0.5 + upper*0.5 + dandouy);
                        ball.setVelocity(newvec);
                        ball.setGlowing(true);
                        Bukkit.broadcastMessage(ChatColor.GREEN + "force:"+force);
                        Bukkit.broadcastMessage(ChatColor.RED + "angle:" +angle);
                        Bukkit.broadcastMessage(ChatColor.BLUE + "speedforce:"+speedforce);
                        Bukkit.broadcastMessage(ChatColor.AQUA + "forceadjustment:"+forceadjustment);
                        Bukkit.broadcastMessage(ChatColor.GOLD + "distance:"+distance);
                        Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Ydistance:"+ydistance);
                        Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Upper:"+upper);
                        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Yvector:"+newvec.getY());
                        CatchTimer timer=new CatchTimer((Snowball) ball,false,false);
                        Main.catchmap.put((Snowball) ball, timer);
                        break;
                    }
                }
                ar.remove();
            }
        }.runTaskLater(Main.plugin, 1);
    }
}
