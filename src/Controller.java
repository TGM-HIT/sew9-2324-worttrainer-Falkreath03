import model.Paar;
import model.Rechtschreibtrainer;
import persistance.Speicher;
import persistance.SpeicherJSON;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Controller {
    public static void main(String[] args) {
        Speicher speicher = new SpeicherJSON();
        Rechtschreibtrainer rechtschreibtrainer = speicher.load();
        String input = "";
        while (true){
            rechtschreibtrainer.setCurrent();
            try {
                URL url = new URL(rechtschreibtrainer.getCurrent().getBild());
                Image image = ImageIO.read(url);
                JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH)));
                imageLabel.setSize(new Dimension(200,200));
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(imageLabel, BorderLayout.CENTER);
                String statsAusgabe = rechtschreibtrainer.getStats().toString() + (rechtschreibtrainer.getStats().getGesamt()!=0?" | Letzte Antwort war " + (rechtschreibtrainer.getLast()?"richtig!":"falsch."):"");
                JLabel stats = new JLabel(statsAusgabe);
                panel.add(stats,BorderLayout.PAGE_START);
                input = JOptionPane.showInputDialog(null, panel, "Was ist das?");
                if(input == null || input.equals("")){
                    break;
                }else {
                    input.toLowerCase();
                }
                rechtschreibtrainer.prüfen(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        speicher.save(rechtschreibtrainer);
    }
}
