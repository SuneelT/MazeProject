import java.awt.event.KeyAdapter;
import java.util.Observable;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	private MazePanel mazePanel = new MazePanel();

	public MazeScreen(GUI gui) {
		setGUI(gui);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private class ThisWillBeMovedIntoAFieldWhenMoreWorkIsDone extends KeyAdapter {
		
	}

}
