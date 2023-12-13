package model;

public class Stats {
    public Stats(int richtig, int falsch) {
        this.richtig = richtig;
        this.falsch = falsch;
    }

    private int richtig;
    private int falsch;

    public Stats() {
        this.richtig = 0;
        this.falsch = 0;
    }

    public int getGesamt() {
        return richtig + falsch;
    }

    public int getRichtig() {
        return richtig;
    }

    public int getFalsch() {
        return falsch;
    }

    public void increaseRichtig(){
        this.richtig ++;
    }

    public void increaseFalsch(){
        this.falsch ++;
    }

    public boolean check(){
        return (this.richtig >= 0 && this.falsch >= 0);
    }

    @Override
    public String toString() {
        return "Statistik: " +
                "Richtig: " + richtig +
                " | Falsch: " + falsch +
                " | Versuche: " + getGesamt();
    }
}
