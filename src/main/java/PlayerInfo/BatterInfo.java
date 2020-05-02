package main.java.PlayerInfo;

import org.bukkit.ChatColor;

import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;
import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;

public class BatterInfo extends PlayerInfo {

    private int mito;
    private int power;
    private int speed;
    private int area;
    private Dandou dandou;

    public BatterInfo(int mito,int power,int speed,Dandou dandou,int hokyu,int suroing,int kata,int area,Position pos){
        this.mito=mito;
        this.power=power;
        this.speed=speed;
        this.hokyu=hokyu;
        this.suroing=suroing;
        this.kata=kata;
        this.pos=pos;
        this.dandou=dandou;
        this.area=area;
        getPlayerInfoList().addInfo(this);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(ChatColor.GREEN);
        sb.append("-<打撃能力>--------");
        sb.append("\n");
        sb.append("弾道:");
        sb.append(dandou.toString());
        sb.append("\n");
        sb.append("ミート:");
        sb.append(mito);
        sb.append("\n");
        sb.append("パワー:");
        sb.append(power);
        sb.append("\n");
        sb.append("走力:");
        sb.append(speed);
        sb.append("\n");
        sb.append("-<守備能力>--------");
        sb.append("\n");
        sb.append("ポジション:");
        sb.append(pos.toString());
        sb.append("\n");
        sb.append("守備適性:");
        sb.append(area);
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

    public int getMito() {
        return mito;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public Dandou getDandou() {
        return dandou;
    }

    public int getArea() {
        return area;
    }
}
