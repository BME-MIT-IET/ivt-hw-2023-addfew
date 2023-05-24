package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.Component;
import java.awt.Container;
import com.log.timmy.Game;
import com.log.timmy.Display;

import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JFrame;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void startGameTest() throws Exception {
        // Call the start method
        Method startMethod = Game.class.getDeclaredMethod("start");
        startMethod.setAccessible(true);
        startMethod.invoke(game);

        // Wait for a short period to allow the game thread to start
        Thread.sleep(100);

        // Get the value of the "working" field using reflection
        Field workingField = Game.class.getDeclaredField("working");
        workingField.setAccessible(true);
        boolean working = (boolean) workingField.get(game);

        // Assert that the "working" field is set to true
        assertTrue("Game should be working", working);

        // Call the stop method
        Method stopMethod = Game.class.getDeclaredMethod("stop");
        stopMethod.setAccessible(true);
        stopMethod.invoke(game);

        // Wait for a short period to allow the game thread to stop
        Thread.sleep(100);

        // Assert that the "working" field is set to false
        working = (boolean) workingField.get(game);
        assertFalse("Game should be stopped", working);
    }

    @Test
    public void displayTest() throws Exception {
        int width = 800;
        int height = 600;
        Display display = new Display(width, height, game);

        // Get the window field using reflection
        Field windowField = Display.class.getDeclaredField("window");
        windowField.setAccessible(true);
        JFrame window = (JFrame) windowField.get(display);

        // Assert that the window object is not null
        assertNotNull("Window object should not be null", window);

        // Get the content pane of the window
        Container contentPane = window.getContentPane();

        // Get the game component added to the content pane
        Component[] components = contentPane.getComponents();
        assertEquals("Number of components in the content pane should be 1", 1, components.length);
        Component gameComponent = components[0];

        // Get the preferred size of the game component
        Dimension preferredSize = gameComponent.getPreferredSize();

        // Assert that the preferred size matches the specified width and height
        assertEquals("Preferred width should match", width, (int) preferredSize.getWidth());
        assertEquals("Preferred height should match", height, (int) preferredSize.getHeight());
    }
}
