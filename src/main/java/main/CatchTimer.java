package main.java.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static main.java.main.Main.*;

import java.util.Collection;

public class CatchTimer {

    private BukkitTask task;

    public CatchTimer(Snowball ball,boolean line,boolean ispitcher){
        if(!ispitcher) {
            task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (line)
                        getline(ball.getLocation(), ball.getLocation().getBlock().getType());
                    Collection<Entity> entities = ball.getNearbyEntities(0.5, 0.5, 0.5);
                    for (Entity ent : entities) {
                        if (ent.getType() == EntityType.PLAYER) {
                            ball.remove();
                            Player p=(Player) ent;
                            (p).getInventory().addItem(new ItemStack(Material.SNOWBALL, 1));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,Integer.MAX_VALUE,9,false,false));
                            hokyulist.add(p);
                            new BukkitRunnable(){
                                @Override
                                public void run() {
                                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                                    hokyulist.remove(p);
                                }
                            }.runTaskLater(plugin,(long)((100-EachPlayerInfo.getPlayerInfo(p).getHokyu())*0.3));
                            cancel();
                            break;
                        }
                    }
                }
            }.runTaskTimer(Main.plugin, 3, 1);
        }
    }

    private void getline(Location loc,Material material){
        new BukkitRunnable(){
            @Override
            public void run() {
                loc.getBlock().setType(Material.RED_WOOL);
                removeline(loc,material);
            }
        }.runTaskLater(Main.plugin,20);
    }

    private void removeline(Location loc,Material material){
        new BukkitRunnable(){
            @Override
            public void run() {
                loc.getBlock().setType(material);
            }
        }.runTaskLater(Main.plugin,100);
    }

    public void stop(){
        if(task!=null)
            task.cancel();
    }
}
