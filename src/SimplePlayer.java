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
			y = (maze.isConnected(x, y, 0)) ? y-1 : y; break;
		case KeyEvent.VK_DOWN:
			y = (maze.isConnected(x, y, 1)) ? y+1: y; break;
		case KeyEvent.VK_LEFT:
			x = (maze.isConnected(x, y, 2)) ? x-1 : x; break;
		case KeyEvent.VK_RIGHT:
			x = (maze.isConnected(x, y, 3)) ? x+1: x; break;
		}
		setChanged();
		if (x == maze.getSize()-1 && y == maze.getSize()-1) notifyObservers(true);
		else notifyObservers();
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
	
	@Override
	public int[] getCoords() {
		int[] coords = {x,y};
		return coords;
	}
}
