import java.awt.Color;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	private int nCollectables = 0;
	private Hashtable<Observable, JLabel> collectables = new Hashtable<Observable, JLabel>();

	public CollectedPanel() {
		this.setVisible(false); this.setBackground(new Color(73, 201, 18, 90));
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (!this.isVisible()) this.setVisible(true);
		JLabel tmp;
		switch((String) arg1) {
			case "First":
				this.removeAll();
				nCollectables = 0;
			case "Create":
				this.setLayout(new GridLayout(++nCollectables, 1, 10, 10));
				tmp = new JLabel(new ImageIcon(((CollectableState) arg0).getUncollectedSprite()));
				collectables.put(arg0, tmp);
				this.add(tmp);
				break;
			case "Collect":
				tmp = collectables.get(arg0);
				tmp.setIcon(new ImageIcon(((CollectableState) arg0).getCollectedSprite()));
				break;
		}
	}

}
