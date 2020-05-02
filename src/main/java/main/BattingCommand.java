package main.java.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static main.java.main.Main.plugin;

public class BattingCommand implements CommandExecutor {

    private int count=0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==3){
            try {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            ((Player) sender).performCommand("throw " + args[0]);
                            if (++count >= Integer.parseInt(args[2])) {
                                count=0;
                                cancel();
                            }
                        }catch (Exception e){
                            Bukkit.broadcastMessage(ChatColor.RED+"Exception");
                            cancel();
                        }
                    }
                }.runTaskTimer(plugin,0,Integer.parseInt(args[1]));
            }catch (Exception e){
                Bukkit.broadcastMessage(ChatColor.RED+"Exception");
            }
        }
        return false;
    }
}
