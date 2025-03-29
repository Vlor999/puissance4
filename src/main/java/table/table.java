package table;

import Player.player;

public class table {
    private int height;
    private int width;
    private player[][] currentTable;

    table(int height, int width){
        this.height = height;
        this.width = width;
        this.currentTable = new player[height][width];
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (currentTable[i][j] == null) {
                    sb.append(". ");
                } else {
                    sb.append(currentTable[i][j].getNumeroPLayeur()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public player[][] getCurrentTable(){
        return this.currentTable;
    }
}
