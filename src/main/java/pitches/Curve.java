package main.java.pitches;

import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

import java.util.Timer;
import java.util.TimerTask;


import static main.java.main.Methods.rotateAroundAxisY;

public class Curve {
    private boolean start_flg=false;

    public Curve(Snowball ball, double minusYvector, double angle, int delay, int period, double force){

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(start_flg)
                    cancel();
                Vector vec=rotateAroundAxisY(ball.getVelocity(), angle/10);
                ball.setVelocity(vec.setY(vec.getY()-minusYvector/10).normalize().multiply(force));
            }
        },20,period);

        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                start_flg=true;
                if(ball.isDead())
                    cancel();
                Vector vec=rotateAroundAxisY(ball.getVelocity(), angle);
                ball.setVelocity(vec.setY(vec.getY()-minusYvector).normalize().multiply(force));
            }
        },delay,period);
    }
}
