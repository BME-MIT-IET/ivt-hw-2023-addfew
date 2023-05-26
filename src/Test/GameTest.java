package Test;
import java.awt.AWTException;
import java.awt.Robot;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Component;
import java.awt.Container;
import com.log.timmy.Game;
import com.log.timmy.Identity;

import com.log.timmy.Mouse;

import com.log.timmy.Particle;

import com.log.timmy.Display;


import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


import javax.swing.JFrame;
import javax.swing.JPanel;

//Import necessary packages and classes

public class GameTest {
 private Game game;
 private Robot robot;

 @Before
 public void setUp() throws AWTException {
     // Create a new instance of the Game class and a Robot for input simulation
     game = new Game();
     robot = new Robot();
     robot.setAutoDelay(50); // Set the delay between robot actions
 }

 @After
 public void tearDown() {
     // Clean up by setting objects to null
     game = null;
     robot = null;
 }

 @Test
 public void startGameTest() throws Exception {
     // Call the start method of the Game class using reflection
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

     // Call the stop method of the Game class using reflection
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

     // Create a new instance of the Display class with specified width, height, and game instance
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

 @Test
 public void testMousePressed() {
     // Create a new instance of the Game class
     Game game = new Game();

     // Set the initial state of the game
     game.idn = Identity.START;
     game.scores = false;

     // Create a new instance of the Mouse class
     Mouse mouse = new Mouse();
     MouseEvent event;

     // Test clicking in the start area
     event = createMouseEvent(Game.WIDTH / 2, 225);
     mouse.mousePressed(event);
     assertEquals(Identity.START, game.idn);

     // Test clicking in the scores area
     event = createMouseEvent(Game.WIDTH / 2, 320);
     mouse.mousePressed(event);
     assertTrue(game.scores);

     // Test clicking outside the defined areas
     event = createMouseEvent(Game.WIDTH / 2, 480);
     mouse.mousePressed(event);
     assertEquals(Identity.START, game.idn);
 }

 private MouseEvent createMouseEvent(int x, int y) {
     // Create a new MouseEvent with specified coordinates
     return new MouseEvent(new JPanel(), MouseEvent.MOUSE_PRESSED, System.currentTimeMillis(), 0, x, y, 1, false);
 }

 @Test
 public void testGetBounds() {
     // Create an instance of Particle with the desired position and dimensions
     Particle particle = new Particle(0.0f, 0.0f, 40, 40, Identity.PARTICLE);

     // Get the bounds of the particle
     Rectangle bounds = particle.getBounds();

     // Assert the expected bounds
     assertEquals(0, bounds.x);
     assertEquals(0, bounds.y);
     assertEquals(40, bounds.width);
     assertEquals(40, bounds.height);
 }
}
