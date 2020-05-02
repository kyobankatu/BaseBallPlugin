package main.java.main;

import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.math.BigDecimal;
import java.util.Random;

public final class Methods {
    public static Vector rotateAroundAxisY(Vector v, double angle) {
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, y,z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        y=v.getY();
        return new Vector(x,y,z);
    }

    public static Vector roteAroundAxisAnyVector(Vector v,Vector axis,double angle){
        angle = -angle;
        angle = Math.toRadians(angle);
        double x, y,z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x =v.getX()*(cos+Math.pow(axis.getX(),2)*(1-cos))+v.getY()*(axis.getX()*axis.getY()*(1-cos)-axis.getZ()*sin)+v.getZ()*(axis.getX()*axis.getZ()*(1-cos)+axis.getY()*sin);
        y=v.getX()*(axis.getX()*axis.getY()*(1-cos)+axis.getZ()*sin)+v.getY()*(cos+Math.pow(axis.getY(),2)*(1-cos))+v.getZ()*(axis.getY()*axis.getZ()*(1-cos)-axis.getX()*sin);
        z = v.getX()*(axis.getX()*axis.getZ()*(1-cos)-axis.getY()*sin)+v.getY()*(axis.getY()*axis.getZ()*(1-cos)+axis.getX()*sin)+v.getZ()*(cos+Math.pow(axis.getZ(),2)*(1-cos));
        return new Vector(x,y,z);
    }

    public static double setScale(double d,int scale){
        return new BigDecimal(d).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void rotateY(Snowball ball, double angle, int delay, double force) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ball.setVelocity(rotateAroundAxisY(ball.getVelocity(), angle).normalize().multiply(force));
            }
        }.runTaskLater(Main.plugin, delay);
    }

    public static double randomforce(double speed, double maxdifferpercent) {
        double dif = speed * 0.008 * (maxdifferpercent /100);
        return speed*0.008 - dif + new Random().nextDouble()*dif*2;
    }
}
