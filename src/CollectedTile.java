import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CollectedTile extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage sprite;
		
		public CollectedTile() {
			this.setOpaque(false);
		}
		
		public void setSprite(BufferedImage s) {
			sprite = s; 
			repaint();
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(sprite, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}