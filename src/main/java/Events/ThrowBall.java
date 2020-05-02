package main.java.Events;

import main.java.PlayerInfo.PitcherInfo;
import main.java.PlayerInfo.Pitches;
import main.java.main.CatchTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import main.java.pitches.Curve;
import main.java.pitches.SliderORSinking;
import main.java.pitches.VerticalSlider;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static main.java.main.Main.*;
import static main.java.main.Methods.randomforce;
import static main.java.main.Methods.rotateAroundAxisY;
import static main.java.main.Methods.rotateY;
import static main.java.main.Methods.roteAroundAxisAnyVector;

public class ThrowBall implements Listener {
    @EventHandler
    public void ThrowBall(ProjectileLaunchEvent e){
        if(e.getEntity().getType()== EntityType.SNOWBALL&&e.getEntity().getShooter()!=null&&e.getEntity().getShooter() instanceof Player) {
            logger.info(((Player)e.getEntity().getShooter()).getUniqueId()+"：Throw");
            Player p = (Player) e.getEntity().getShooter();
            ItemMeta ballmeta=p.getInventory().getItemInMainHand().getItemMeta();
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
            World w=e.getEntity().getWorld();
            Location loc=e.getEntity().getLocation();
            e.getEntity().remove();
            Snowball ball = (Snowball) w.spawnEntity(loc,EntityType.SNOWBALL);
            Vector vec;
            double force;
            double seikyutmp;
            double seikyuy;
            double seikyuxz;
            double kyui=0;
            boolean ispitcher=false;
            if(EachPlayerInfo.getPlayerInfo(p) instanceof PitcherInfo){
                ispitcher=true;
                seikyutmp = ((double) 100 - ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSeikyu()) / 15;
                seikyuy = Math.random() * seikyutmp - seikyutmp / 2;
                seikyutmp = ((double) 100 - ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSeikyu()) / 15;
                seikyuxz = Math.random() * seikyutmp - seikyutmp / 2;
                kyui=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui()-70;
                if(ballmeta.equals(fastballm)) {
                    ball.setGravity(false);
                    vec=p.getLocation().getDirection();
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.FASTBALL);
                    force = randomforce(speed,1);
                }else if(ballmeta.equals(sliderm)) {
                    ball.setGravity(false);
                    int ball_kyui=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.SLIDER);
                    kyui+=(ball_kyui-35)*0.5;
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.SLIDER);
                    vec = rotateAroundAxisY(p.getLocation().getDirection(), (double)henka/20);
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.SLIDER);
                    force = randomforce(speed,2.5);
                    new SliderORSinking(ball,(-henka-ball_kyui*1.5)/300,(long) ((double)ball_kyui/(double) speed*800+(double)(35-henka)*1.5),20,force);
                }else if(ballmeta.equals(sinkingm)) {
                    ball.setGravity(false);
                    int ball_kyui=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.HARD_SINKER);
                    kyui+=(ball_kyui-35)*0.5;
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.HARD_SINKER);
                    vec = rotateAroundAxisY(p.getLocation().getDirection(), (double)-henka/40);
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.HARD_SINKER);
                    force = randomforce(speed,2.5);
                    new SliderORSinking(ball,(henka+ball_kyui*1.5)/450,(long) ((double)ball_kyui/(double) speed*800+(double)(35-henka)*1.5),20,force);
                }else if(ballmeta.equals(changem)) {
                    ball.setGravity(false);
                    int ball_kyui=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.CHANGEUP);
                    kyui+=(ball_kyui-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.CHANGEUP);
                    force = randomforce(speed,1);
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.CHANGEUP);
                    final Vector[] axis = new Vector[1];
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            axis[0] =rotateAroundAxisY(ball.getVelocity(),90).normalize();
                        }
                    },10);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run()  {
                            ball.setVelocity(ball.getVelocity().multiply((double) (200-henka-ball_kyui)/180));
                        }
                    },(long) ((double)ball_kyui/(double) speed*1000 +(double)(35-henka)*1.5)-10);

                    new Timer().schedule(new TimerTask(){
                        @Override
                        public void run() {
                            ball.setVelocity(roteAroundAxisAnyVector(ball.getVelocity(), axis[0],(henka+ball_kyui*2.0)/1500));
                        }
                    }, (long) ((double)ball_kyui/(double) speed*1000 +(double)(35-henka)*1.5),20);
                }else if(ballmeta.equals(cutm)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.CUT)-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.CUT);
                    force = randomforce(speed, 1.25);
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.CUT);
                    rotateY(ball, (double) -henka/50, 7, force);
                }else if(ballmeta.equals(verticalm)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.VERTICAL_SLIDER)-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.VERTICAL_SLIDER);
                    force = randomforce(speed, 1.25);
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.VERTICAL_SLIDER);
                    new VerticalSlider(ball,henka*0.0008,0,4,force);
                }else if(ballmeta.equals(forkm)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.FORK)-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.FORK);
                    force = randomforce(speed, 1.5);
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.FORK);
                    new VerticalSlider(ball,henka*0.00015,3,1,force);
                }else if(ballmeta.equals(slowcurvem)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.SLOW_CURVE)-35)*0.5;
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.SLOW_CURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY()+(double) henka/500), (double)henka/5);
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.SLOW_CURVE);
                    force = randomforce(speed, 2.5);
                    new Curve(ball,henka*0.0007,-henka*0.035,0,2,force);
                }else if(ballmeta.equals(curvem)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.CURVE)-35)*0.5;
                    int henka=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.CURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY()+(double) henka/500),(double)henka/10);
                    int speed=((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.CURVE);
                    force = randomforce(speed, 2);
                    new Curve(ball, henka*0.0005, (double)-henka/50, 0, 2, force);
                }else if(ballmeta.equals(powercurvem)||ballmeta.equals(spikem)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.SPIKE_CURVE) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.SPIKE_CURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + henka * 0.003), (double) henka / 100);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.SPIKE_CURVE);
                    force = randomforce(speed, 2.5);
                    new Curve(ball, henka * 0.0006, -henka * 0.015, 0, 2, force);
                }else if(ballmeta.equals(sffm)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.SFF)-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.SFF);
                    force = randomforce(speed, 1.5);
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.SFF);
                    new VerticalSlider(ball, henka * 0.0005, 9, 1, force);
                }else if(ballmeta.equals(oneseamm)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.ONE_SEAM)-35)*0.5;
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.ONE_SEAM);
                    force = randomforce(speed, 1.25);
                    rotateY(ball, 1, 6, force);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Vector axis = rotateAroundAxisY(ball.getVelocity(), 90).setY(0);
                            ball.setVelocity(roteAroundAxisAnyVector(ball.getVelocity(), axis, 1));
                        }
                    }.runTaskLater(plugin, 6);
                }else if(ballmeta.equals(slurvem)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.SLURVE)-35)*0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.SLURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + henka * 0.001), (double) henka / 25);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.SLURVE);
                    force = randomforce(speed, 2);
                    new Curve(ball, henka * 0.0005, -henka * 0.035, 0, 2, force);
                }else if(ballmeta.equals(dcurvem)) {
                    ball.setGravity(false);
                    kyui+=(((PitcherInfo)EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.DROPCURVE)-35)*0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.DROPCURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + henka * 0.0015), (double) henka / 100);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.DROPCURVE);
                    force = randomforce(speed, 2.5);
                    new Curve(ball, henka * 0.0005, -henka * 0.01, 0, 2, force);
                }else if(ballmeta.equals(twoseamm)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.TWO_SEAM) - 35) * 0.5;
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.TWO_SEAM);
                    force = randomforce(speed, 1.25);
                    rotateY(ball, 1, 6, force);
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.TWO_SEAM);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Vector axis = rotateAroundAxisY(ball.getVelocity(), 90).setY(0);
                            ball.setVelocity(roteAroundAxisAnyVector(ball.getVelocity(), axis, 1 + (double) henka / 120));
                        }
                    }.runTaskLater(plugin, 6);
                }else if(ballmeta.equals(fastsliderm)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.FAST_SLIDER) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.FAST_SLIDER);
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.FAST_SLIDER);
                    force = randomforce(speed, 1.5);
                    new SliderORSinking(ball, (double) -henka / 150, 2, 2, force);
                }else if(ballmeta.equals(fasthardsinkerm)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.FAST_HARD_SINKER) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.FAST_HARD_SINKER);
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.FAST_HARD_SINKER);
                    force = randomforce(speed, 1.5);
                    new SliderORSinking(ball, (double) henka / 150, 2, 2, force);
                }else if(ballmeta.equals(movem)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.MOVINGFAST) - 35) * 0.5;
                    vec = p.getLocation().getDirection();
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.MOVINGFAST);
                    force = randomforce(speed, 1.25);
                    int dir = new Random().nextInt(2) * 2 - 1;
                    rotateY(ball, dir, 6, force);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Vector axis = rotateAroundAxisY(ball.getVelocity(), 90).setY(0);
                            ball.setVelocity(roteAroundAxisAnyVector(ball.getVelocity(), axis, 1));
                        }
                    }.runTaskLater(plugin, 6);
                }else if(ballmeta.equals(fastscrewm)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.FAST_SCREW) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.FAST_SCREW);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + (double) henka / 500), (double) -henka / 10);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.FAST_SCREW);
                    force = randomforce(speed, 2);
                    new Curve(ball, henka * 0.00055, (double) henka / 45, 0, 2, force);
                }else if(ballmeta.equals(sinkerm)) {
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.CURVE) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.CURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + (double) henka / 500), (double) henka / 10);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.CURVE);
                    force = randomforce(speed, 2);
                    new Curve(ball, henka * 0.00055, (double) henka / 40, 0, 2, force);
                }else if(ballmeta.equals(fastsinkerm)){
                    ball.setGravity(false);
                    kyui += (((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getKyui(Pitches.CURVE) - 35) * 0.5;
                    int henka = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getHenka(Pitches.CURVE);
                    vec = rotateAroundAxisY(p.getLocation().getDirection().setY(p.getLocation().getDirection().getY() + (double) henka / 500), (double) henka / 10);
                    int speed = ((PitcherInfo) EachPlayerInfo.getPlayerInfo(p)).getSpeed(Pitches.CURVE);
                    force = randomforce(speed, 2);
                    new Curve(ball, henka * 0.0005, (double) henka / 50, 0, 2, force);

                }else{
                    vec=p.getLocation().getDirection();
                    force=(double) EachPlayerInfo.getPlayerInfo(p).getSuroing()/30;
                    seikyutmp = ((double) 100 - EachPlayerInfo.getPlayerInfo(p).getSuroing()) / 30;
                    seikyuy = Math.random() * seikyutmp - seikyutmp / 2;
                    seikyutmp = ((double) 100 - EachPlayerInfo.getPlayerInfo(p).getSuroing()) / 30;
                    seikyuxz = Math.random() * seikyutmp - seikyutmp / 2;
                }
            }else {
                vec=p.getLocation().getDirection();
                force=(double) EachPlayerInfo.getPlayerInfo(p).getKata()/30;
                seikyutmp = ((double) 100 - EachPlayerInfo.getPlayerInfo(p).getSuroing()) / 15;
                seikyuy = Math.random() * seikyutmp - seikyutmp / 2;
                seikyutmp = ((double) 100 - EachPlayerInfo.getPlayerInfo(p).getSuroing()) / 15;
                seikyuxz = Math.random() * seikyutmp - seikyutmp / 2;
            }
            vec=rotateAroundAxisY(vec,seikyuxz);
            Vector axis=rotateAroundAxisY(vec,90).setY(0);
            vec=roteAroundAxisAnyVector(vec,axis,seikyuy);
            ball.setGlowing(true);
            ball.setVelocity(vec.normalize().multiply(force));
            ball.teleport(loc);
            ball.setMetadata("force",new FixedMetadataValue(plugin,force));
            ball.setMetadata("kyui",new FixedMetadataValue(plugin,kyui));
            Bukkit.broadcastMessage(ChatColor.BLUE+"Speed:"+(force));
            catchmap.put(ball,new CatchTimer(ball,false,ispitcher));
        }else if(e.getEntity().getType()==EntityType.SNOWBALL&&e.getEntity().getShooter()!=null){
            Snowball ball=(Snowball) e.getEntity();
            ball.setGlowing(true);
            catchmap.put(ball,new CatchTimer(ball,false,false));
        }
    }
}
