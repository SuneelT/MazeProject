import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Container mainFrame = new JPanel();
	private List<Screen> screens = new LinkedList<Screen>();	
	private Maze maze;
	private final int MAZE = 0;
	private final int MENU = 1;
	private final int PAUSE = 2;

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
	((CardLayout) mainFrame.getLayout()).show(mainFrame, switchTo);
	}

	private void init_Maze() {
		// TODO Auto-generated method stub
		
	}

	private void init_GUI() {
		screens.add(new MazeScreen());
		screens.add(new MenuScreen());
		screens.add(new PauseScreen());
		mainFrame.setLayout(new CardLayout());
		mainFrame.add(screens.get(MAZE), "MAZE");
		mainFrame.add(screens.get(MENU), "MENU");
		mainFrame.add(screens.get(PAUSE), "PAUSE");
	}
}
