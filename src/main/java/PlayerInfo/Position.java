package main.java.PlayerInfo;

public class Position {
    public static Position STARTER=new Position();
    public static Position SETUPPER=new Position();
    public static Position CLOSER=new Position();
    public static Position CATCHER=new Position();
    public static Position FIRST=new Position();
    public static Position SECOND=new Position();
    public static Position THIRD=new Position();
    public static Position SHORT=new Position();
    public static Position LEFT=new Position();
    public static Position CENTER=new Position();
    public static Position RIGHT=new Position();

    private Position(){}

    public String toString(){
        if(this==STARTER)
            return "STARTER";
        else if(this==SETUPPER)
            return "SETUPPER";
        else if(this==CLOSER)
            return "CLOSER";
        else if(this==CATCHER)
            return "CATCHER";
        else if(this==FIRST)
            return "FIRST";
        else if(this==SECOND)
            return "SECOND";
        else if(this==THIRD)
            return "THIRD";
        else if(this==SHORT)
            return "SHORT";
        else if(this==LEFT)
            return "LEFT";
        else if(this==CENTER)
            return "CENTER";
        else if(this==RIGHT)
            return "RIGHT";
        return null;
    }
}
