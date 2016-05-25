import java.awt.*;
import java.util.Observer;

import javax.swing.*;

/**
 * Used to create the GUI that the player interacts with.
 * Is responsible for the different screens that need to be shown, as well as the different components to be drawn in the frame
 * Stores the model which contains all the data relevant to the maze itself
 */
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel cards = new JPanel(new CardLayout());
	private Model model;
	private final int WIDTH = 840;
	private final int HEIGHT = 680;
 
	/**
	 * This main function begins by adding to queue of runnable threads our GUI. This synchronises the GUI and prevents it from doing things like
	 * hang unresponsively. The GUI that is created extends JFrame which is necessary to put JPanels on top of. After the creation of the top-level
	 * class, the various components of the GUI are bootstrapped, as well as the model, and the window is created on the desktop. It should be
	 * exitable from the top taskbar
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		    public void run() {
		        new GUI();
		    }
		});
	}
	
	/**
	 * Constructor for the GUI
	 * Creates a model used to represent data relevant to the actual maze
	 * Adds a number of screens to cards: a JPanel which houses the different screens that need to be shown
	 * Adds the screens to the content pane
	 * Sets the default behaviour for when the close button is pressed to exiting the application
	 * Makes the GUI visible, and sets the window size (which cannot be changed)
	 * Changes the screen to the menu screen
	 */
	public GUI() {
	    model = new Model();
		cards.add(new MazeScreen(this), "Maze");
		cards.add(new MenuScreen(this), "Menu");
		cards.add(new HelpScreen(this), "Help");
		cards.add(new WinScreen(this), "Win");
		getContentPane().add(cards);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true); setSize(WIDTH, HEIGHT); setResizable(false);
        switchScreen("Menu");
	}
	
	/**
	 * Changes from one screen to another
	 * @param switchTo a string referring to the name of the screen being switched to
	 */
	public void switchScreen(String switchTo) {
		((CardLayout) cards.getLayout()).show(cards, switchTo);		
		this.setTitle("Maze - "+switchTo);
	}
	
	/**
	 * gets the model
	 * @return the GUI's model representing the maze data
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Adds an observer for the player
	 * @param o the observer being added
	 */
	public void setPlayerObserver(Observer o) {
		model.setPlayerObserver(o);
	}

	/**
	 * Adds an observer for the collectables
	 * @param collected the observer being added
	 */
	public void setCollectableObserver(Observer collected) {
		model.setCollectableOberver(collected);
	}
}
