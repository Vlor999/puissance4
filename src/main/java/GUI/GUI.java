package GUI;

import java.awt.*;
import javax.swing.*;

import Player.player;
import table.table;

public class GUI{
    private JFrame mainFrame;
    private boolean isAvailable;
    private boolean isVisible;
    private String frameName;
    private int width;
    private int height;

    public GUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        this.frameName = "Puissance 4";
        this.width = screenWidth;
        this.height = screenHeight;
        this.isVisible = true;
        initializeFrame();
    }

    public GUI(String name, int width, int height) {
        this.frameName = name;
        this.width = width;
        this.height = height;
        this.isVisible = true;
        initializeFrame();
    }

    private void initializeFrame() {
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle(this.frameName);
        this.mainFrame.setSize(this.width, this.height);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(this.isVisible);
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.isAvailable = true;
    }

    public void destroyMainFrame() {
        if (this.isAvailable) {
            this.mainFrame.dispose();
            this.isAvailable = false;
            System.out.println("Window: " + this.frameName + " is destroyed");
        } else {
            System.out.println("The window: " + this.frameName + " is no longer available");
        }
    }

    public void ajoutTexteFrame(String sentence) {
        JLabel label = new JLabel(sentence);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.mainFrame.getContentPane().add(label, BorderLayout.CENTER);
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    public void drawTable(table myTable) {
        int nombreLigne = myTable.getNumberLine();
        int nombreColonne = myTable.getNumberColumn();
    
        JPanel gridPanel = new JPanel(new GridLayout(nombreLigne, nombreColonne));
        gridPanel.setPreferredSize(new Dimension(this.width, this.height));
    
        for (int l = 0; l < nombreLigne; l++) {
            for (int c = 0; c < nombreColonne; c++) {
                player currentPlayer = myTable.getInformation(l, c);
                JPanel cell = new JPanel();
    
                if (currentPlayer != null) {
                    cell.setBackground(currentPlayer.getColor());
                } else {
                    cell.setBackground(Color.WHITE);
                }
    
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(cell);
            }
        }
    
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(gridPanel, BorderLayout.CENTER);
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }
    
    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public String getFrameName() {
        return this.frameName;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setWidth(int width) {
        this.width = width;
        this.mainFrame.setSize(this.width, this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        this.mainFrame.setSize(this.width, this.height);
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        this.mainFrame.setVisible(this.isVisible);
    }
}
