import GUI.GUI;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            GUI guiInterface = new GUI("Puissance - 4", 800, 400);
            guiInterface.ajoutTexteFrame("Puissance 4");
            guiInterface.setVisible(true);
        });

        

    }
}