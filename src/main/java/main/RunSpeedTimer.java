package main.java.main;

import static main.java.main.Main.*;
import main.java.PlayerInfo.BatterInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RunSpeedTimer {
    private BukkitTask task;
    private BukkitTask loctask;
    private Player p;
    public RunSpeedTimer(Player player,Location stoplocation,float plusspeed){
        this.p=player;
        runtimer.put(p, this);
        float speed=(float) ((BatterInfo)EachPlayerInfo.getPlayerInfo(p)).getSpeed()/465;
        player.setWalkSpeed(speed);
        task=new BukkitRunnable(){
            @Override
            public void run() {
                float newspeed=player.getWalkSpeed();
                if(!player.isSprinting())
                    newspeed=newspeed-speed*2;
                else
                    newspeed+=plusspeed;
                if(newspeed<=speed)
                    newspeed=speed;
                else if(newspeed>=speed+0.1)
                    newspeed=speed+0.1F;
                player.setWalkSpeed(newspeed);
            }
        }.runTaskTimer(Main.plugin,20,3);
        if(stoplocation!=null)
            Locationstop(player,stoplocation);
    }

    private void Locationstop(Player p,Location location){
        loctask=new BukkitRunnable(){
            @Override
            public void run() {
                if(p.getLocation().getBlock().getLocation().equals(location.getBlock().getLocation())) {
                    stop();
                    cancel();
                }
            }
        }.runTaskTimer(Main.plugin,0,1);
    }

    public void stop(){
        if(loctask!=null)
            loctask.cancel();
        task.cancel();
        p.setWalkSpeed(0.2F);
        runtimer.remove(p);
    }
}
