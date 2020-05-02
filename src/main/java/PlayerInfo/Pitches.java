package main.java.PlayerInfo;

import org.bukkit.inventory.ItemStack;
import static main.java.main.Main.*;

public class Pitches {
    public static Pitches FASTBALL=new Pitches();
    public static Pitches SLIDER=new Pitches();
    public static Pitches HARD_SINKER=new Pitches();
    public static Pitches CHANGEUP=new Pitches();
    public static Pitches CUT=new Pitches();
    public static Pitches VERTICAL_SLIDER=new Pitches();
    public static Pitches SLOW_CURVE=new Pitches();
    public static Pitches CURVE=new Pitches();
    public static Pitches POWER_CURVE=new Pitches();
    public static Pitches FORK=new Pitches();
    public static Pitches SFF=new Pitches();
    public static Pitches ONE_SEAM=new Pitches();
    public static Pitches TWO_SEAM=new Pitches();
    public static Pitches SLURVE=new Pitches();
    public static Pitches DROPCURVE=new Pitches();
    public static Pitches FAST_SLIDER=new Pitches();
    public static Pitches FAST_HARD_SINKER=new Pitches();
    public static Pitches MOVINGFAST=new Pitches();
    public static Pitches SPIKE_CURVE=new Pitches();
    public static Pitches FAST_SCREW=new Pitches();
    public static Pitches SINKER=new Pitches();
    public static Pitches FAST_SINKER=new Pitches();

    private Pitches(){}

    public static ItemStack getBall(Pitches pitch){
        if(pitch==FASTBALL)
            return fastballi.clone();
        else if(pitch==SLIDER)
            return slideri.clone();
        else if(pitch==HARD_SINKER)
            return sinkingi.clone();
        else if(pitch==CHANGEUP)
            return changei.clone();
        else if(pitch==CUT)
            return cuti.clone();
        else if(pitch==VERTICAL_SLIDER)
            return verticali.clone();
        else if(pitch==SLOW_CURVE)
            return slowcurvei.clone();
        else if(pitch==CURVE)
            return curvei.clone();
        else if(pitch==FORK)
            return forki.clone();
        else if(pitch==POWER_CURVE)
            return powercurvei.clone();
        else if(pitch==SFF)
            return sffi.clone();
        else if(pitch==ONE_SEAM)
            return oneseami.clone();
        else if(pitch==SLURVE)
            return slurvei.clone();
        else if(pitch==DROPCURVE)
            return dcurvei.clone();
        else if(pitch==TWO_SEAM)
            return twoseami.clone();
        else if(pitch==FAST_SLIDER)
            return fastslideri.clone();
        else if(pitch==FAST_HARD_SINKER)
            return fasthardsinkeri.clone();
        else if(pitch==MOVINGFAST)
            return movei.clone();
        else if(pitch==SPIKE_CURVE)
            return spikei.clone();
        else if(pitch==FAST_SCREW)
            return fastscrewi.clone();
        else if(pitch==SINKER)
            return sinkeri.clone();
        else if(pitch==FAST_SINKER)
            return fastsinkeri.clone();
        return null;
    }
}
