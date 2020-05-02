package main.java.PlayerInfo;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class EachPlayerInfo{

    private HashMap<Player,PitcherInfo> pitchinfomap=new HashMap<>();
    private HashMap<Player,BatterInfo> batterinfomap=new HashMap<>();
    private static EachPlayerInfo infos=new EachPlayerInfo();

    private EachPlayerInfo(){};

    public static EachPlayerInfo getEachPlayerInfo(){
        return infos;
    }

    public void addInfo(Player player,PitcherInfo info){
        infos.pitchinfomap.put(player,info);
        info.setPitches(player);
    }

    public void addInfo(Player player,BatterInfo info){
        infos.batterinfomap.put(player,info);
    }

    public PlayerInfo getPlayerInfo(Player player){
        if(infos.pitchinfomap.containsKey(player))
            return infos.pitchinfomap.get(player);
        else if(infos.batterinfomap.containsKey(player))
            return infos.batterinfomap.get(player);
        return null;
    }
}
