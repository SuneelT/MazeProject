import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel cards = new JPanel(new CardLayout());
	private Model model;
	private String lastScreen;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;

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
	
	public GUI() {
		/*Add all the different screens to the card layot for ease of switching*/
		cards.add(new MazeScreen(this), "Maze"); cards.getComponent(0).setName("Maze");
		cards.add(new MenuScreen(this), "Menu"); cards.getComponent(1).setName("Menu");
		cards.add(new PauseScreen(this), "Pause"); cards.getComponent(2).setName("Pause");
		cards.add(new HelpScreen(this), "Help"); cards.getComponent(3).setName("Help");
		cards.add(new SettingsScreen(this), "Settings"); cards.getComponent(4).setName("Settings");
		getContentPane().add(cards);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true); setSize(WIDTH, HEIGHT);
        switchScreen("Menu");
        model = new Model();
		model.createPlayer(((Screen) cards.getComponent(0)).getMazeComponents());
	}
	
	public void switchScreen(String switchTo) {
		for (Component c: cards.getComponents()) {
			if (c.isVisible()) lastScreen = c.getName();
		}
		((CardLayout) cards.getLayout()).show(cards, switchTo);		//to understand this code go look at java.awt.CardLayout
		this.setTitle("Maze - "+switchTo);
	}
	
	public Model getModel() {
		return model;
	}

	public void switchToLastScreen() {
		switchScreen(lastScreen);
	}
}
