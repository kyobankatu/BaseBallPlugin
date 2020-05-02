package main.java.PlayerInfo;

public class Dandou {
    public static Dandou GROUNDER=new Dandou();
    public static Dandou LOW=new Dandou();
    public static Dandou LINE_DRIVE=new Dandou();
    public static Dandou MEDIUM=new Dandou();
    public static Dandou HIGH=new Dandou();
    public static Dandou POWER=new Dandou();
    public static Dandou ARTISTE=new Dandou();

    private Dandou(){}

    public String toString(){
        if(this==GROUNDER)
            return "GROUNDER";
        else if(this==LOW)
            return "LOW";
        else if(this==LINE_DRIVE)
            return "LINE_DRIVE";
        else if(this==MEDIUM)
            return "MEDIUM";
        else if(this==HIGH)
            return "HIGH";
        else if(this==POWER)
            return "POWER";
        else if(this==ARTISTE)
            return "ARTISTE";
        return null;
    }
}
