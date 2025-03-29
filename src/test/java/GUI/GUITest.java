package GUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;


import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    private GUI gui;
    
    @BeforeAll
    public static void setUpHeadlessMode() {
        // Set up for headless mode in CI environment
        System.setProperty("java.awt.headless", "true");
    }
    
    @AfterEach
    public void tearDown() {
        if (gui != null) {
            gui.destroyMainFrame();
        }
    }
    
    @Test
    @DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
    public void testDefaultConstructor() {
        gui = new GUI();
        assertNotNull(gui);
        assertEquals("Puissance 4", gui.getFrameName());
        assertTrue(gui.isVisible());
    }
    
    @Test
    @DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
    public void testCustomConstructor() {
        gui = new GUI("Test Window", 800, 600);
        assertNotNull(gui);
        assertEquals("Test Window", gui.getFrameName());
        assertEquals(800, gui.getWidth());
        assertEquals(600, gui.getHeight());
        assertTrue(gui.isVisible());
    }
    
    @Test
    @DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
    public void testSetDimensions() {
        gui = new GUI("Test Window", 800, 600);
        
        gui.setWidth(1000);
        assertEquals(1000, gui.getWidth());
        
        gui.setHeight(700);
        assertEquals(700, gui.getHeight());
    }
    
    @Test
    @DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
    public void testSetVisibility() {
        gui = new GUI("Test Window", 800, 600);
        
        gui.setVisible(false);
        assertFalse(gui.isVisible());
        
        gui.setVisible(true);
        assertTrue(gui.isVisible());
    }
    
    @Test
    @DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
    public void testGetMainFrame() {
        gui = new GUI("Test Window", 800, 600);
        assertNotNull(gui.getMainFrame());
    }
    
    // Tests that are safe to run in headless mode
    @Test
    public void testFrameNameGetter() {
        gui = new GUI("Test Window", 0, 0);
        assertEquals("Test Window", gui.getFrameName());
    }
    
    @Test
    public void testWidthHeightGetters() {
        gui = new GUI("Test", 800, 600);
        assertEquals(800, gui.getWidth());
        assertEquals(600, gui.getHeight());
    }
}
