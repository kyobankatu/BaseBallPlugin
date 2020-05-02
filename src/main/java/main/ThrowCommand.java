package main.java.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import main.java.pitches.*;

import java.util.Random;

import static main.java.main.Methods.*;

public class ThrowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Snowball ball = (Snowball) ((Player) sender).getWorld().spawnEntity(new Location(((Player) sender).getWorld(), 1309.5, 5.25, -681.5), EntityType.SNOWBALL);
                double force;
                Vector vec;
                if (args.length == 0) {
                    vec = new Vector(1, 1, -1);
                    force = 0.475;
                } else if (args[0].equalsIgnoreCase("fastball")) {
                    ball.setGravity(false);
                    vec = new Vector(1, 0, -1);
                    force = randomforce(155, 1);
                } else if (args[0].equalsIgnoreCase("slider")) {
                    ball.setGravity(false);
                    vec = rotateAroundAxisY(new Vector(1, 0, -1), 2.5);
                    force = randomforce(133, 2.5);
                    new SliderORSinking(ball,-1,400,20,force);
                } else if (args[0].equalsIgnoreCase("hardsinker")) {
                    ball.setGravity(false);
                    vec = rotateAroundAxisY(new Vector(1, 0, -1), -2.5);
                    force = randomforce(144, 2.5);
                    new SliderORSinking(ball,1,2,2,force);
                } else if (args[0].equalsIgnoreCase("change")) {
                    ball.setGravity(false);
                    vec = new Vector(1, 0, -1);
                    force = randomforce(130, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            ball.setGravity(true);
                            ball.setVelocity(ball.getVelocity().multiply(0.8));
                        }
                    }.runTaskLater(Main.plugin, 7);
                } else if (args[0].equalsIgnoreCase("cut")) {
                    ball.setGravity(false);
                    vec = new Vector(1, 0, -1);
                    force = randomforce(143, 1.25);
                    rotateY(ball, 2, 7, force);
                    rotateY(ball, -2, 12, force);
                } else if (args[0].equalsIgnoreCase("verticalslider")) {
                    ball.setGravity(false);
                    vec = new Vector(1, 0.1, -1);
                    force = randomforce(133, 1.25);
                    new VerticalSlider(ball,0.04,0,4,force);
                } else if (args[0].equalsIgnoreCase("fork")) {
                    ball.setGravity(false);
                    vec = new Vector(1, 0, -1);
                    force = randomforce(135, 1.5);
                    new VerticalSlider(ball,0.015,3,1,force);
                } else if (args[0].equalsIgnoreCase("slowcurve")) {
                    ball.setGravity(false);
                    vec = rotateAroundAxisY(new Vector(1, 0.4, -0.9), 10);
                    force = randomforce(103, 1.5);
                    new Curve(ball, 0.035, -1.75, 0, 2, force);
                } else if (args[0].equalsIgnoreCase("curve")) {
                    ball.setGravity(false);
                    vec = rotateAroundAxisY(new Vector(1, 0.1, -1), 5);
                    force = randomforce(121, 1.5);
                    new Curve(ball, 0.025, -1, 0, 2, force);
                }else if(args[0].equalsIgnoreCase("powercurve")){
                    ball.setGravity(false);
                    vec = rotateAroundAxisY(new Vector(1, 0, -1), 1);
                    force = randomforce(130, 1.3);
                    new Curve(ball, 0.02, -0.5, 5, 2, force);
                } else {
                    vec = new Vector(1, 1, -1);
                    force = 0.475;
                }
                ball.setGlowing(true);
                ball.setVelocity(vec.multiply(force));
                ball.setMetadata("force", new FixedMetadataValue(Main.plugin, force));
                Bukkit.broadcastMessage(ChatColor.BLUE + "Speed:" + force);
                Main.catchmap.put(ball, new CatchTimer(ball, false, false));
            }
        }.runTaskLater(Main.plugin, 20);
        return false;
    }
}
