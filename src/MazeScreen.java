import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeScreen extends JFrame implements Observer, Screen {

	@Override
	public JPanel getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JComponent> getMazeListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
