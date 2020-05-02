package main.java.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static main.java.main.Main.*;


public class AreaCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        switch (args.length) {
            case 1:
                if (args[0].equalsIgnoreCase("stand"))
                    p.getInventory().addItem(standbarrier.clone());
                else if (args[0].equalsIgnoreCase("foul"))
                    p.getInventory().addItem(foulbarrier.clone());
                break;
        }
        return false;
    }

    //エリア
    @EventHandler
    public void place(BlockPlaceEvent e){
        if(e.getItemInHand().getItemMeta()==standbarrier.getItemMeta())
            e.getBlockPlaced().setMetadata("stand",new FixedMetadataValue(plugin,0));
        else if(e.getItemInHand().getItemMeta()==foulbarrier.getItemMeta())
            e.getBlockPlaced().setMetadata("foul",new FixedMetadataValue(plugin,0));
    }
}
