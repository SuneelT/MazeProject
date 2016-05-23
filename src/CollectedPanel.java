import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	private int nCollectables = 0;
	private Hashtable<Observable, BufferedImage> collectables = new Hashtable<Observable, BufferedImage>();

	public CollectedPanel() {
		this.setVisible(false);
	}
	
	/*@Override
	public void paintComponent(Graphics g) {
		
	}*/
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1.equals(new String("Create"))) {
			collectables.put(arg0, ((CollectableState) arg0).getUncollectedSprite());
			this.setLayout(new GridLayout(++nCollectables, 1, 50, 50));
			if (!this.isVisible()) this.setVisible(true);
		}
		if (arg1.equals(new String("Collect"))) {
			
		}
		//repaint();
		
	}

}
