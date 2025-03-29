import GUI.GUI;
import Player.player;
import table.table;

import javax.swing.UIManager;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        GUI guiInterface = new GUI("Puissance - 4", 800, 400);

        table maTable = new table(8, 8);
        player player1 = new player(Color.BLUE, "Willem");
        maTable.setInformation(0, 0, player1);
        System.out.println(maTable);

        guiInterface.drawTable(maTable);

    }
}