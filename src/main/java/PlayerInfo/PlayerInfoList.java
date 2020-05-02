package main.java.PlayerInfo;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerInfoList {

    private static PlayerInfoList PlayerInfoList=new PlayerInfoList();
    private ArrayList<BatterInfo> batterInfos=new ArrayList<>();
    private ArrayList<PitcherInfo> pitcherInfos=new ArrayList<>();

    private PlayerInfoList(){};

    public static PlayerInfoList getPlayerInfoList(){
        return PlayerInfoList;
    }

    public void addInfo(BatterInfo info){
        PlayerInfoList.batterInfos.add(info);
    }

    public void addInfo(PitcherInfo info){
        PlayerInfoList.pitcherInfos.add(info);
    }

    public BatterInfo getRandomBatterInfo(){
        if(PlayerInfoList.batterInfos.isEmpty())
            return null;
        Collections.shuffle(PlayerInfoList.batterInfos);
        return PlayerInfoList.batterInfos.get(0);
    }

    public PitcherInfo getRandomPitcherInfo(){
        if(PlayerInfoList.pitcherInfos.isEmpty())
            return null;
        Collections.shuffle(PlayerInfoList.pitcherInfos);
        return PlayerInfoList.pitcherInfos.get(0);
    }
}
