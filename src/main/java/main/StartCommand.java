package main.java.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartCommand implements CommandExecutor {

    private int inning=1;
    private boolean back=false;
    private List<Player> backseat=new ArrayList<>();
    static Player pitcher;
    private Player catcher;
    private Player first;
    private Player second;
    private Player third;
    private Player shortstop;
    private Player left;
    private Player center;
    private Player right;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p=(Player) sender;
        if(sender.isOp()) {
            setInning();
        }else{
            sender.sendMessage(ChatColor.RED+"OPでは無い為コマンドを実行出来ません");
        }
        return false;
    }

    private void setInning(){
        backseat.addAll(Bukkit.getOnlinePlayers());
        String back="表";
        if(this.back) {
            back = "裏";
            this.back=false;
        }
        for(Player p:Bukkit.getOnlinePlayers()){
            p.sendTitle(ChatColor.GREEN+String.valueOf(inning)+"回"+back,"",10,70,20);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2F, 1F);
        }
        setPosition("P");
        setPosition("C");
        setPosition("1");
        setPosition("2");
        setPosition("3");
        setPosition("S");
        setPosition("L");
        setPosition("CE");
        setPosition("R");
        backseat.forEach(player -> player.sendMessage(ChatColor.GREEN+"あなたはベンチです"));
    }

    private void setPosition(String pos){
        Collections.shuffle(backseat);
        Player p = backseat.get(0);
        if(pos.equalsIgnoreCase("P")){
            pitcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはピッチャーです");
        }else if(pos.equalsIgnoreCase("C")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはキャッチャーです");
        }else if(pos.equalsIgnoreCase("1")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはファーストです");
        }else if(pos.equalsIgnoreCase("2")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはセカンドです");
        }else if(pos.equalsIgnoreCase("3")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはサードです");
        }else if(pos.equalsIgnoreCase("S")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはショートです");
        }else if(pos.equalsIgnoreCase("L")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはレフトです");
        }else if(pos.equalsIgnoreCase("CE")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはセンターです");
        }else if(pos.equalsIgnoreCase("R")){
            catcher=p;
            p.sendMessage(ChatColor.GREEN+"あなたはライトです");
        }
        backseat.remove(p);
    }
}
