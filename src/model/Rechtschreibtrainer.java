package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rechtschreibtrainer {

    private List<Paar> paare;
    private Paar current;
    private Stats stats;
    private boolean last;

    public Rechtschreibtrainer() {
        this.paare = new ArrayList<>(Arrays.asList(
                new Paar("panzerkampfwagen viii maus", "https://flakhobby.com/wp-content/uploads/2022/07/5f4c6301e01a2.jpg"),
                new Paar("hund", "https://hips.hearstapps.com/clv.h-cdn.co/assets/17/29/2048x1152/hd-aspect-1500566326-gettyimages-512366437-1.jpg"),
                new Paar("katze", "https://hips.hearstapps.com/clv.h-cdn.co/assets/17/29/2048x1152/hd-aspect-1500566326-gettyimages-512366437-1.jpg")
        ));
        this.current = null;
        this.stats = new Stats();
        this.check();
    }

    public Rechtschreibtrainer(JSONObject jsonObject) {
        JSONObject jsonCurrent = jsonObject.getJSONObject("current");
        JSONArray jsonPaare = jsonObject.getJSONArray("paare");
        JSONObject jsonStats = jsonObject.getJSONObject("stats");
        this.paare = new ArrayList<>();
        for (int i = 0; jsonPaare.length() > i; i++) {
            JSONObject jsonTemp = jsonPaare.getJSONObject(i);
            Paar temp = new Paar(jsonTemp.getString("wort"), jsonTemp.getString("bild"));
            this.paare.add(temp);
        }
        this.current = new Paar(jsonCurrent.getString("wort"), jsonCurrent.getString("bild"));
        this.stats = new Stats(jsonStats.getInt("richtig"), jsonStats.getInt("falsch"));
        this.last = jsonObject.getBoolean("last");
        this.check();
    }


    public boolean getLast() {
        System.out.println(last);
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public Paar getCurrent() {
        return current;
    }

    public void setCurrent(int i) {
        if (i < 0 || i > paare.size()) {
            return;
        }
        this.current = paare.get(i);
    }

    public void setCurrent() {
        setCurrent((int) (Math.random() * paare.size()));
    }

    public boolean check() {
        if (paare == null) {
            throw new NullPointerException("Paare ist null.");
        }
        if (paare.size() == 0) {
            throw new IllegalArgumentException("Paare ist leer.");
        }
        if (current != null) {
            if (!current.check()) {
                throw new NullPointerException("Bild-URL oder Wort null.");
            }
        }
        if (paare == null) {
            throw new NullPointerException("Stats ist null.");
        }
        if (!stats.check()) {
            throw new IllegalArgumentException("Stats sind kleiner als 0.");
        }
        return true;
    }

    public boolean prüfen(String wort) {
        if (current.prüfen(wort)) {
            stats.increaseRichtig();
            current = null;
            last = true;
            return true;
        }
        stats.increaseFalsch();
        last = false;
        return false;
    }

    public List<Paar> getPaare() {
        return paare;
    }

    public void setPaare(List<Paar> paare) {
        this.paare = paare;
    }

    public void setCurrent(Paar current) {
        this.current = current;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public boolean isLast() {
        return last;
    }
}
