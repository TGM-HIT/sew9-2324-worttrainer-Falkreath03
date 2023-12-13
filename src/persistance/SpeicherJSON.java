package persistance;

import model.Rechtschreibtrainer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SpeicherJSON implements Speicher{
    private static final String path = "C:\\Users\\zihrl\\OneDrive\\Dokumente\\TGM\\SEW\\Worttrainer\\src\\data\\save.json";
    @Override
    public void save(Rechtschreibtrainer rechtschreibtrainer) {
        JSONObject jsonTrainer = new JSONObject();
        JSONObject jsonCurrent = new JSONObject();
        jsonCurrent.put("wort", rechtschreibtrainer.getCurrent().getWort());
        jsonCurrent.put("bild", rechtschreibtrainer.getCurrent().getBild());
        JSONObject jsonStats = new JSONObject();
        jsonStats.put("richtig", rechtschreibtrainer.getStats().getRichtig());
        jsonStats.put("falsch", rechtschreibtrainer.getStats().getFalsch());
        JSONArray jsonPaare = new JSONArray();
        for (int i = 0; rechtschreibtrainer.getPaare().size() > i; i++){
            JSONObject jsonPaar = new JSONObject();
            jsonPaar.put("wort", rechtschreibtrainer.getPaare().get(i).getWort());
            jsonPaar.put("bild", rechtschreibtrainer.getPaare().get(i).getBild());
            jsonPaare.put(jsonPaar);
        }
        jsonTrainer.put("current",jsonCurrent);
        jsonTrainer.put("stats",jsonStats);
        jsonTrainer.put("paare",jsonPaare);
        jsonTrainer.put("last", rechtschreibtrainer.getLast());

        try(FileWriter fileWriter = new FileWriter(path)){
            fileWriter.write(jsonTrainer.toString());
            System.out.println("Hat funktioniert :)");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Rechtschreibtrainer load() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            System.out.println(json);
            JSONObject jsonObject = new JSONObject(json);
            Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer(jsonObject);
            return rechtschreibtrainer;
        }catch (Exception e){
            e.printStackTrace();
            return new Rechtschreibtrainer();
        }
    }
}
