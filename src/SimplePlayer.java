import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Observable;

public class SimplePlayer extends Observable implements Player {
	private int x;
	private int y;
	private Maze maze;
	
	public SimplePlayer() {
		this.x = 0;
		this.y = 0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setMaze(Maze m) {
		this.maze = m;
	}
	
	public void move(int direction) {
		switch(direction) {
		case KeyEvent.VK_UP:
			y = (maze.isConnected(x, y, "UP")) ? y-1 : y; break;
		case KeyEvent.VK_DOWN:
			y = (maze.isConnected(x, y, "DOWN")) ? y+1: y; break;
		case KeyEvent.VK_LEFT:
			x = (maze.isConnected(x, y, "LEFT")) ? x-1 : x; break;
		case KeyEvent.VK_RIGHT:
			x = (maze.isConnected(x, y, "RIGHT")) ? x+1: x; break;
		}
		setChanged();
		notifyObservers();
	}

	public void draw(Graphics g, int intervalx, int intervaly) {
		g.setColor(Color.black);
		g.drawOval(x*intervalx, y*intervaly, intervalx, intervaly);
	}

	@Override
	public void reset() {
		x = 0;
		y = 0;
	}
}
