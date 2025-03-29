package table;

import Player.player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    private table gameTable;
    private player player1;
    private player player2;

    @BeforeEach
    public void setUp() {
        player.resetPlayers();
        gameTable = new table(6, 7);
        player1 = new player(Color.RED, "Test Player 1");
        player2 = new player(Color.YELLOW, "Test Player 2");
    }

    @Test
    public void testTableCreation() {
        assertEquals(6, gameTable.getNumberLine());
        assertEquals(7, gameTable.getNumberColumn());
        
        // Check that the table is initially empty
        for (int i = 0; i < gameTable.getNumberLine(); i++) {
            for (int j = 0; j < gameTable.getNumberColumn(); j++) {
                assertNull(gameTable.getInformation(i, j));
            }
        }
    }
    
    @Test
    public void testSetAndGetInformation() {
        // Set a player at a position
        assertTrue(gameTable.setInformation(5, 3, player1));
        
        // Verify the player is at that position
        assertEquals(player1, gameTable.getInformation(5, 3));
        
        // Try to set a player at an occupied position
        assertFalse(gameTable.setInformation(5, 3, player2));
        
        // Verify the original player is still there
        assertEquals(player1, gameTable.getInformation(5, 3));
    }
    
    @Test
    public void testFindLowestEmptyRow() {
        // Initially all columns have their lowest row at 5
        assertEquals(5, gameTable.findLowestEmptyRow(0));
        
        // Place a piece in column 0, row 5
        gameTable.setInformation(5, 0, player1);
        
        // Now the lowest empty row should be 4
        assertEquals(4, gameTable.findLowestEmptyRow(0));
        
        // Fill the column
        gameTable.setInformation(4, 0, player1);
        gameTable.setInformation(3, 0, player1);
        gameTable.setInformation(2, 0, player1);
        gameTable.setInformation(1, 0, player1);
        gameTable.setInformation(0, 0, player1);
        
        // Now the column should be full
        assertEquals(-1, gameTable.findLowestEmptyRow(0));
    }
    
    @Test
    public void testHorizontalWin() {
        // Place 4 pieces in a horizontal row
        gameTable.setInformation(5, 0, player1);
        gameTable.setInformation(5, 1, player1);
        gameTable.setInformation(5, 2, player1);
        gameTable.setInformation(5, 3, player1);
        
        assertTrue(gameTable.checkWin(5, 3));
        
        // Reset and test with non-winning pattern
        setUp();
        gameTable.setInformation(5, 0, player1);
        gameTable.setInformation(5, 1, player1);
        gameTable.setInformation(5, 2, player1);
        gameTable.setInformation(5, 3, player2); // Different player
        
        assertFalse(gameTable.checkWin(5, 3));
    }
    
    @Test
    public void testVerticalWin() {
        // Place 4 pieces in a vertical column
        gameTable.setInformation(5, 0, player1);
        gameTable.setInformation(4, 0, player1);
        gameTable.setInformation(3, 0, player1);
        gameTable.setInformation(2, 0, player1);
        
        assertTrue(gameTable.checkWin(2, 0));
    }
    
    @Test
    public void testDiagonalWin() {
        // Create a diagonal win pattern (/)
        gameTable.setInformation(5, 0, player1);
        
        gameTable.setInformation(5, 1, player2);
        gameTable.setInformation(4, 1, player1);
        
        gameTable.setInformation(5, 2, player2);
        gameTable.setInformation(4, 2, player2);
        gameTable.setInformation(3, 2, player1);
        
        gameTable.setInformation(5, 3, player2);
        gameTable.setInformation(4, 3, player2);
        gameTable.setInformation(3, 3, player2);
        gameTable.setInformation(2, 3, player1);
        
        assertTrue(gameTable.checkWin(2, 3));
        
        // Test diagonal win pattern (\)
        setUp();
        gameTable.setInformation(5, 3, player1);
        
        gameTable.setInformation(5, 2, player2);
        gameTable.setInformation(4, 2, player1);
        
        gameTable.setInformation(5, 1, player2);
        gameTable.setInformation(4, 1, player2);
        gameTable.setInformation(3, 1, player1);
        
        gameTable.setInformation(5, 0, player2);
        gameTable.setInformation(4, 0, player2);
        gameTable.setInformation(3, 0, player2);
        gameTable.setInformation(2, 0, player1);
        
        assertTrue(gameTable.checkWin(2, 0));
    }
    
    @Test
    public void testToString() {
        gameTable.setInformation(5, 0, player1);
        gameTable.setInformation(4, 1, player2);
        
        String tableString = gameTable.toString();
        assertNotNull(tableString);
        assertTrue(tableString.contains("."));
    }
}
