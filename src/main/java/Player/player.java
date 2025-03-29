package Player;

import java.awt.Color;

public class player {
    private static int nombrePlayeur = 0;
    private static int currentPlayer = 0;
    private static player[] listPlayer;

    private Color myColor;
    private String name;
    private int numeroPlayeur;

    public player(Color myColor, String name){
        this.name = name;
        this.myColor = myColor;
        this.numeroPlayeur = nombrePlayeur;
        nombrePlayeur++;
        if (listPlayer == null) {
            listPlayer = new player[10]; // Assuming a maximum of 10 players
        }
        listPlayer[nombrePlayeur - 1] = this;
    }

    public String toString(){
        return "PLayeur : " + this.name + ", with the color : " + this.myColor.toString();
    }

    public Color getColor(){
        return this.myColor;
    }

    public String getName(){
        return this.name;
    }

    public int getNumeroPLayeur(){
        return this.numeroPlayeur;
    }

    public static player getPlayingPlayer(){
        player returnPlayeur = listPlayer[currentPlayer];
        currentPlayer = (currentPlayer + 1) % nombrePlayeur;
        return returnPlayeur;
    }

    public static int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public static void resetPlayers() {
        nombrePlayeur = 0;
        currentPlayer = 0;
        listPlayer = null;
    }

}
