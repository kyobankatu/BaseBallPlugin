package main.java.PlayerInfo;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

import static main.java.PlayerInfo.Pitches.getBall;
import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;
import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;

public class PitcherInfo extends PlayerInfo{

    private int kyui;
    private int seikyu;
    private int stamina;
    private List<PitchInfo> pitchesInfoList=new ArrayList<>();

    public PitcherInfo(Position pos,int kyui,int seikyu,int stamina,int hokyu,int suroing,int kata,PitchInfo... pitches){
        this.seikyu=seikyu;
        this.kyui=kyui;
        this.stamina=stamina;
        this.hokyu=hokyu;
        this.suroing=suroing;
        this.kata=kata;
        this.pos=pos;
        pitchesInfoList.addAll(Arrays.asList(pitches));
        getPlayerInfoList().addInfo(this);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(ChatColor.GREEN);
        sb.append("-<投手能力>--------");
        sb.append("\n");
        sb.append("球威:");
        sb.append(kyui);
        sb.append("\n");
        sb.append("制球:");
        sb.append(seikyu);
        sb.append("\n");
        sb.append("スタミナ:");
        sb.append(stamina);
        sb.append("\n");
        sb.append("-<守備能力>--------");
        sb.append("\n");
        sb.append("ポジション:");
        sb.append(pos.toString());
        sb.append("\n");
        sb.append("捕球:");
        sb.append(hokyu);
        sb.append("\n");
        sb.append("スローイング:");
        sb.append(suroing);
        sb.append("\n");
        sb.append("肩:");
        sb.append(kata);
        return sb.toString();
    }

    public void setPitches(Player player){
        Inventory inv=player.getInventory();
        pitchesInfoList.forEach(pitchinfo->inv.addItem(getBall(pitchinfo.getPitches())));
    }

    public int getHenka(Pitches pitches){
        for(PitchInfo info:pitchesInfoList){
            if(info.getPitches()==pitches)
                return info.getHenka();
        }
        return -1;
    }

    public int getSpeed(Pitches pitches){
        for(PitchInfo info:pitchesInfoList){
            if(info.getPitches()==pitches)
                return info.getSpeed();
        }
        return -1;
    }

    public int getKyui(Pitches pitches){
        for(PitchInfo info:pitchesInfoList){
            if(info.getPitches()==pitches)
                return info.getKyui();
        }
        return -1;
    }

    public int getKyui() {
        return kyui;
    }

    public int getSeikyu() {
        return seikyu;
    }

    public int getStamina() {
        return stamina;
    }
}
