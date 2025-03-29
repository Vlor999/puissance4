package table;

import Player.player;

public class table {
    private int numberLine;
    private int numberColumn;
    private player[][] currentTable;

    public table(int numberLine, int numberColumn) {
        this.numberLine = numberLine;
        this.numberColumn = numberColumn;
        this.currentTable = new player[numberLine][numberColumn];
    }

    public String toString() {
        String sb = "";
        for (int i = 0; i < numberLine; i++) {
            for (int j = 0; j < numberColumn; j++) {
                if (currentTable[i][j] == null) {
                    sb += ". ";
                } else {
                    sb += currentTable[i][j].getNumeroPLayeur() + " ";
                }
            }
            sb += "\n";
        }
        return sb;
    }

    public int getNumberLine() {
        return this.numberLine;
    }

    public int getNumberColumn() {
        return this.numberColumn;
    }

    public player[][] getCurrentTable() {
        return this.currentTable;
    }

    public player getInformation(int x, int y) {
        return this.currentTable[x][y];
    }

    public boolean setInformation(int x, int y, player currentPlayer) {
        player prevPlayer = this.currentTable[x][y];
        if (prevPlayer != null) {
            return false;
        }
        this.currentTable[x][y] = currentPlayer;
        return true;
    }
}
