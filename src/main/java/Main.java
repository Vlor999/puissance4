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
        
        SwingUtilities.invokeLater(() -> {
            // Create a new 6x7 table (standard Puissance 4 dimensions)
            table gameTable = new table(6, 7);
            
            // Create players
            player player1 = new player(Color.RED, "Joueur 1");
            player player2 = new player(Color.YELLOW, "Joueur 2");
            
            // Create and configure GUI
            GUI guiInterface = new GUI("Puissance 4", 700, 600);
            guiInterface.drawTable(gameTable);
            guiInterface.setVisible(true);
        });
    }
}