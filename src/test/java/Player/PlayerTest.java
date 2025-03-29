package Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @BeforeEach
    public void resetPlayers() {
        player.resetPlayers();
    }
    
    @Test
    public void testPlayerCreation() {
        player p1 = new player(Color.RED, "Player 1");
        assertEquals("Player 1", p1.getName());
        assertEquals(Color.RED, p1.getColor());
        assertEquals(0, p1.getNumeroPLayeur());
        
        player p2 = new player(Color.YELLOW, "Player 2");
        assertEquals("Player 2", p2.getName());
        assertEquals(Color.YELLOW, p2.getColor());
        assertEquals(1, p2.getNumeroPLayeur());
    }
    
    @Test
    public void testPlayerRotation() {
        player p1 = new player(Color.RED, "Player 1");
        player p2 = new player(Color.YELLOW, "Player 2");
        
        // First player should be p1
        assertEquals(p1, player.getPlayingPlayer());
        assertEquals(1, player.getCurrentPlayerIndex()); // Index should now be 1
        
        // Second player should be p2
        assertEquals(p2, player.getPlayingPlayer());
        assertEquals(0, player.getCurrentPlayerIndex()); // Index should be back to 0
        
        // Back to first player
        assertEquals(p1, player.getPlayingPlayer());
        assertEquals(1, player.getCurrentPlayerIndex());
    }
    
    @Test
    public void testPlayerReset() {
        new player(Color.RED, "Player 1");
        new player(Color.YELLOW, "Player 2");
        
        // Get the first player
        player.getPlayingPlayer();
        
        // Reset players
        player.resetPlayers();
        
        // Create new players
        player newP1 = new player(Color.GREEN, "New Player 1");
        
        // The player number should start from 0 again
        assertEquals(0, newP1.getNumeroPLayeur());
        
        // And current player index should be reset
        assertEquals(0, player.getCurrentPlayerIndex());
    }
    
    @Test
    public void testToString() {
        player p1 = new player(Color.RED, "Test Player");
        String playerString = p1.toString();
        
        assertTrue(playerString.contains("Test Player"));
        assertTrue(playerString.contains("color"));
    }
}
