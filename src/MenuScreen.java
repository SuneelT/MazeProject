import java.awt.event.MouseAdapter;
import java.util.Observable;

public class MenuScreen extends Screen {
	private static final long serialVersionUID = -1725872337351590898L;

	public MenuScreen(GUI gui) {
		setGUI(gui);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	private class ThisClassWillBeMovedWhenMoreWorkDone extends MouseAdapter {
		
	}
}
