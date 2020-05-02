package main.java.PlayerInfo;

public abstract class PlayerInfo {
    protected int hokyu;
    protected int suroing;
    protected int kata;
    protected Position pos;

    abstract public String toString();

    public int getHokyu(){
        return hokyu;
    }

    public int getSuroing() {
        return suroing;
    }

    public int getKata() {
        return kata;
    }
}
