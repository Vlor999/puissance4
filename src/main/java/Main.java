import GUI.GUI;
import Player.player;
import table.table;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int NOM_CAMEL = 0;
        
        SwingUtilities.invokeLater(() -> {
            table gameTable = new table(6, 7);
            
            new player(Color.RED, "Joueur 1");
            new player(Color.YELLOW, "Joueur 2");
            
            GUI guiInterface = new GUI("Puissance 4", 700, 600);
            guiInterface.drawTable(gameTable);
            guiInterface.setVisible(true);
        });
    }
}