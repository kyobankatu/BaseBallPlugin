package main.java.pitches;

import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static main.java.main.Main.plugin;

public class VerticalSlider {
    public VerticalSlider(Snowball ball, double minusYvector, int delay,int period ,double force){
        new BukkitRunnable(){
            @Override
            public void run() {
                if(ball.isDead())
                    cancel();
                Vector vec=ball.getVelocity();
                ball.setVelocity(new Vector(vec.getX(),vec.getY()-minusYvector,vec.getZ()).normalize().multiply(force));
            }
        }.runTaskTimer(plugin,delay,period);
    }
}
