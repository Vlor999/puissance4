package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Player.player;
import table.table;

public class GUI{
    private JFrame mainFrame;
    private boolean isAvailable;
    private boolean isVisible;
    private String frameName;
    private int width;
    private int height;
    private table currentTable;
    private JPanel gridPanel;
    private JLabel statusLabel;
    private boolean headless;

    public GUI() {
        this.headless = GraphicsEnvironment.isHeadless();
        
        Dimension screenSize = this.headless ? 
            new Dimension(800, 600) : Toolkit.getDefaultToolkit().getScreenSize();
        
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        this.frameName = "Puissance 4";
        this.width = screenWidth;
        this.height = screenHeight;
        this.isVisible = true;
        initializeFrame();
    }

    public GUI(String name, int width, int height) {
        this.headless = GraphicsEnvironment.isHeadless();
        this.frameName = name;
        this.width = width;
        this.height = height;
        this.isVisible = true;
        initializeFrame();
    }

    private void initializeFrame() {
        if (this.headless) {
            // In headless mode, don't create actual GUI components
            this.isAvailable = false;
            return;
        }
        
        this.mainFrame = new JFrame();
        this.mainFrame.setTitle(this.frameName);
        this.mainFrame.setSize(this.width, this.height);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(this.isVisible);
        this.mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.isAvailable = true;
    }

    public void destroyMainFrame() {
        if (this.isAvailable && !this.headless) {
            this.mainFrame.dispose();
            this.isAvailable = false;
            System.out.println("Window: " + this.frameName + " is destroyed");
        } else {
            System.out.println("The window: " + this.frameName + " is no longer available or in headless mode");
        }
    }

    public void ajoutTexteFrame(String sentence) {
        if (this.headless) return;
        
        JLabel label = new JLabel(sentence);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.mainFrame.getContentPane().add(label, BorderLayout.CENTER);
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    public void drawTable(table myTable) {
        if (this.headless) return;
        
        this.currentTable = myTable;
        int nombreLigne = myTable.getNumberLine();
        int nombreColonne = myTable.getNumberColumn();
    
        gridPanel = new JPanel(new GridLayout(nombreLigne, nombreColonne));
        gridPanel.setPreferredSize(new Dimension(this.width, this.height - 50));

        statusLabel = new JLabel("C'est au tour du joueur " + (player.getCurrentPlayerIndex() + 1));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(statusLabel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
    
        for (int li = 0; li < nombreLigne; li++) {
            for (int co = 0; co < nombreColonne; co++) {
                final int finalLi = li;
                final int finalCo = co;
                
                JPanel cellPanel = new JPanel();
                cellPanel.setLayout(new BorderLayout());
                cellPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
                JPanel cell = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        
                        player currentPlayer = myTable.getInformation(finalLi, finalCo);
                        if (currentPlayer != null) {
                            g2d.setColor(currentPlayer.getColor());
                        } else {
                            g2d.setColor(Color.WHITE);
                        }
                        
                        g2d.fillOval(2, 2, getWidth() - 4, getHeight() - 4);
                        
                        g2d.setColor(new Color(0, 0, 139));
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawOval(2, 2, getWidth() - 4, getHeight() - 4);
                        
                        g2d.dispose();
                    }
                };
                
                cell.setBackground(new Color(0, 0, 205));
                int finalC = co;
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleColumnClick(finalC);
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    }
                });
                
                cellPanel.add(cell, BorderLayout.CENTER);
                gridPanel.add(cellPanel);
            }
        }
    
        this.mainFrame.getContentPane().removeAll();
        this.mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }
    
    private void handleColumnClick(int column) {
        if (this.headless) return;
        
        player currentPlayer = player.getPlayingPlayer();
        int row = currentTable.findLowestEmptyRow(column);
        
        if (row != -1) {
            boolean success = currentTable.setInformation(row, column, currentPlayer);
            
            if (success) {
                statusLabel.setText("C'est au tour du joueur " + (player.getCurrentPlayerIndex() + 1));
                
                // Redraw the table
                drawTable(currentTable);

                if (currentTable.checkWin(row, column)) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Le joueur " + currentPlayer.getName() + " a gagné !", 
                        "Victoire", JOptionPane.INFORMATION_MESSAGE);
                    // Restart the game : TODO
                }
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, 
                "Cette colonne est pleine. Choisissez une autre colonne.", 
                "Colonne pleine", JOptionPane.WARNING_MESSAGE);
        }
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
        if (!this.headless && this.mainFrame != null) {
            this.mainFrame.setSize(this.width, this.height);
        }
    }

    public void setHeight(int height) {
        this.height = height;
        if (!this.headless && this.mainFrame != null) {
            this.mainFrame.setSize(this.width, this.height);
        }
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        if (!this.headless && this.mainFrame != null) {
            this.mainFrame.setVisible(this.isVisible);
        }
    }
    
    public boolean isHeadless() {
        return this.headless;
    }
}
