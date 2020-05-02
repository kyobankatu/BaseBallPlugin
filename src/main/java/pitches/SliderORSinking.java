package main.java.pitches;

import org.bukkit.Bukkit;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.NumberConversions;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

import static main.java.main.Methods.rotateAroundAxisY;
import static main.java.main.Methods.setScale;

public class SliderORSinking {
    private boolean start_flg=false;

    public SliderORSinking(){}

    public SliderORSinking(Snowball ball, double angle, long delay,long period, double force) {
        Bukkit.broadcastMessage(angle+","+delay+","+period+","+force);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(start_flg)
                    cancel();
                ball.setVelocity(rotateAroundAxisY(ball.getVelocity(), angle/10).normalize().multiply(force));
            }
        },20,period);

        new Timer().schedule(new TimerTask() {
            public void run() {
                start_flg=true;
                if(ball.isDead())
                    cancel();
                ball.setVelocity(rotateAroundAxisY(ball.getVelocity(), angle).normalize().multiply(force));
            }
        },delay,period);
    }
}
