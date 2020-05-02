package main.java.PlayerInfo;

public class PitchInfo {

    private int henka;
    private int speed;
    private int kyui;
    private Pitches pitches;

    public PitchInfo(Pitches pitches,int kyui,int henka,int speed){
        this.kyui=kyui;
        this.henka=henka;
        this.speed=speed;
        this.pitches=pitches;
    }

    public int getHenka() {
        return henka;
    }

    public int getSpeed() {
        return speed;
    }

    public int getKyui() {
        return kyui;
    }

    public Pitches getPitches() {
        return pitches;
    }
}
