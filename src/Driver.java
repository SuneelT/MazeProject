import java.util.LinkedList;
import java.util.List;

public class Driver {
	private Screen currScreen;
	private List<Screen> screens = new LinkedList<Screen>();	
	private Maze maze;
	private final int MAZE = 0;
	private final int MENU = 1;
	private final int PAUSE = 2;

	public static void main(String[] args) {
		Driver sys = new Driver();
		sys.begin();
	}
	
	public Driver () {
		init_GUI();
		init_Maze();
	}
	
	private void begin() {
		// TODO Auto-generated method stub
		
	}

	private void init_Maze() {
		// TODO Auto-generated method stub
		
	}

	private void init_GUI() {
		screens.add(new MazeScreen());
		screens.add(new MenuScreen());
		screens.add(new PauseScreen());
		currScreen = screens.get(MAZE);
		
	}
}
