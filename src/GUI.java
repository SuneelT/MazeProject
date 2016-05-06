import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Container mainFrame = new JPanel();
	private List<Screen> screens = new LinkedList<Screen>();	
	private Maze maze;
	private final int MAZE = 0;
	private final int MENU = 1;
	private final int PAUSE = 2;

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
		        GUI ui = new GUI();
		        ui.init_GUI(); ui.init_Maze();					//init till the end...I see stars reference
		        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		        ui.setVisible(true);
		    }
		});
	}
	
	public void switchScreen(String switchTo) {
	((CardLayout) mainFrame.getLayout()).show(mainFrame, switchTo);		//to understand this code go look at java.awt.CardLayout
	}

	private void init_Maze() {
		maze = new GraphMaze(3, 3, 3, 3, null);	//what does this mean??
		for (Screen s: screens) {
			for (Observer j: s.getMazeComponents()) ((GraphMaze) maze).addObserver(j);
		}
		
	}

	private void init_GUI() {
		screens.add(new MazeScreen(this));			//the screen constructors need (this) because without it they can't call switchScreen().
		screens.add(new MenuScreen(this));
		screens.add(new PauseScreen(this));
		mainFrame.setLayout(new CardLayout());
		mainFrame.add(screens.get(MAZE), "MAZE");	//add the various screens to the main JPanel frame, for ease of switching.
		mainFrame.add(screens.get(MENU), "MENU");
		mainFrame.add(screens.get(PAUSE), "PAUSE");
	}
}
