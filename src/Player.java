import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
	private int x;
	private int y;
	
	public Player(int xStart, int yStart) {
		this.x = xStart;
		this.y = yStart;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void move(int direction) {
		switch(direction) {
		case KeyEvent.VK_UP:
			y++; break;
		case KeyEvent.VK_DOWN:
			y--; break;
		case KeyEvent.VK_LEFT:
			x--; break;
		case KeyEvent.VK_RIGHT:
			x++; break;
		}
	}

	public void draw(Graphics g, int width, int height) {
		g.drawOval(x, y, width, height);
	}
}
