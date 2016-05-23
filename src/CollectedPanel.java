import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	private int nCollectables = 0;
	private Hashtable<Observable, CollectedTile> collectables = new Hashtable<Observable, CollectedTile>();

	public CollectedPanel() {
		this.setVisible(false); this.setOpaque(false);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (!this.isVisible()) this.setVisible(true);
		CollectedTile tmp;
		switch((String) arg1) {
			case "First":
				this.removeAll();
				nCollectables = 0;
			case "Create":
				this.setLayout(new GridLayout(++nCollectables, 1, 10, 10));
				tmp = new CollectedTile();
				tmp.setSprite(((CollectableState) arg0).getUncollectedSprite());
				collectables.put(arg0, tmp);
				this.add(tmp);
				break;
			case "Collect":
				tmp = collectables.get(arg0);
				tmp.setSprite(((CollectableState) arg0).getCollectedSprite());
				break;
		}
	}
	
	private class CollectedTile extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage sprite;
		public CollectedTile() {this.setOpaque(false);}
		public void setSprite(BufferedImage s) {sprite = s; repaint();}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(sprite, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

}
