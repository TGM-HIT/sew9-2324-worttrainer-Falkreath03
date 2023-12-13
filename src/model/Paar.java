package model;

public class Paar {
    private String wort;
    private String bild;

    public Paar(String wort, String bild) {
        this.wort = wort;
        this.bild = bild;
        if(!this.check()){
            throw new IllegalArgumentException("Bild-URL oder Wort null.");
        }
    }

    public String getWort() {
        return wort;
    }

    public String getBild() {
        return bild;
    }

    public boolean check(){
        return (this.getBild()!=null && this.getWort()!=null);
    }

    public boolean prüfen(String wort){
        return this.wort.equals(wort);
    }
}
