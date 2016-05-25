import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * Used for the display of the various collectables available in the maze
 */
public class CollectedTile extends JPanel {
		private static final long serialVersionUID = 1L;
		private BufferedImage sprite;
		
		/**
		 * CollectedTile constructor
		 * Creates an instance of a CollectedTile.
		 * Sets it as not-opaque, letting the background be seen through the transparent sections of the sprite
		 */
		public CollectedTile() {
			this.setOpaque(false);
		}
		
		/**
		 * Sets the sprite of the collectable
		 * @param s the image that the tile is to be set to
		 */
		public void setSprite(BufferedImage s) {
			sprite = s; 
			repaint();
		}
		
		/**
		 * Calls the JPanel paintComponent method which in turn calls the UI component's paint method
		 * The graphics object is copied and passed through to be drawn
		 * @param g the Graphics object to be protected and drawn
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(sprite, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}