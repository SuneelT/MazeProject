import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	
	public CollectedPanel() {
		this.setLayout(new GridLayout(3, 1, 30, 30));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
