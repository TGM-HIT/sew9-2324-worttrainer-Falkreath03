package persistance;

import model.Rechtschreibtrainer;

public interface Speicher {

    public void save(Rechtschreibtrainer rechtschreibtrainer);

    public Rechtschreibtrainer load();
}
