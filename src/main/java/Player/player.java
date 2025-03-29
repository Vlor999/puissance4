package Player;

import java.awt.Color;

public class player {
    private static int nombrePlayeur = 0;

    private Color myColor;
    private String name;
    private int numeroPlayeur;

    player(Color myColor, String name){
        this.name = name;
        this.myColor = myColor;
        this.numeroPlayeur = nombrePlayeur;
        nombrePlayeur++;
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
}
