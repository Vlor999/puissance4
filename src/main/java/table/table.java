package table;

import Player.player;

public class table {
    private int numberLine;
    private int numberColumn;
    private player[][] currentTable;
    private static final int WIN_COUNT = 4; // Nombre de jetons à aligner pour gagner

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

    /**
     * Trouve la rangée la plus basse vide dans une colonne
     * @param column La colonne à vérifier
     * @return L'index de la rangée la plus basse vide, ou -1 si la colonne est pleine
     */
    public int findLowestEmptyRow(int column) {
        if (column < 0 || column >= numberColumn) {
            return -1; // Colonne invalide
        }
        
        // Commence par le bas de la colonne et remonte
        for (int row = numberLine - 1; row >= 0; row--) {
            if (currentTable[row][column] == null) {
                return row;
            }
        }
        
        return -1; // La colonne est pleine
    }
    
    /**
     * Vérifie s'il y a une victoire à partir d'une position
     * @param row Ligne du dernier jeton placé
     * @param col Colonne du dernier jeton placé
     * @return true si une victoire est détectée
     */
    public boolean checkWin(int row, int col) {
        player p = currentTable[row][col];
        if (p == null) return false;
        
        // Directions à vérifier: horizontal, vertical, diagonal descendante, diagonal montante
        int[][] directions = {
            {0, 1},  // Horizontale
            {1, 0},  // Verticale
            {1, 1},  // Diagonale descendante
            {1, -1}  // Diagonale montante
        };
        
        for (int[] direction : directions) {
            int count = 1; // Le jeton placé compte déjà pour 1
            
            // Vérifier dans une direction
            count += countConsecutive(row, col, direction[0], direction[1], p);
            
            // Vérifier dans la direction opposée
            count += countConsecutive(row, col, -direction[0], -direction[1], p);
            
            if (count >= WIN_COUNT) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Compte le nombre de jetons consécutifs du même joueur dans une direction
     */
    private int countConsecutive(int startRow, int startCol, int dRow, int dCol, player p) {
        int count = 0;
        int row = startRow + dRow;
        int col = startCol + dCol;
        
        while (row >= 0 && row < numberLine && col >= 0 && col < numberColumn && 
               currentTable[row][col] != null && currentTable[row][col].getNumeroPLayeur() == p.getNumeroPLayeur()) {
            count++;
            row += dRow;
            col += dCol;
        }
        
        return count;
    }
}
