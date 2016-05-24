import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;

import javax.swing.*;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	private int nCollectables = 0;
	private Hashtable<Observable, CollectedTile> collectables = new Hashtable<Observable, CollectedTile>();
	private PriorityQueue<CollectableState> tmpList = new PriorityQueue<CollectableState>();

	public CollectedPanel() {
		this.setVisible(false); this.setOpaque(false);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (!this.isVisible()) this.setVisible(true);
		CollectedTile tile;
		switch((String) arg1) {
			case "Create":
				this.setLayout(new GridLayout(++nCollectables, 1));
				tmpList.add(((CollectableState)arg0));
				break;
			case "Done":
				for (CollectableState s: tmpList) {
					tile = new CollectedTile();
					tile.setSprite(s.getUncollectedSprite());
					collectables.put((Observable) s, tile);
					this.add(tile);
				}
				break;
			case "Collect":
				tile = collectables.get(arg0);
				tile.setSprite(((CollectableState) arg0).getCollectedSprite());
				break;
		}
	}
	
	public void reset() {
		nCollectables = 0;
		this.removeAll();
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
