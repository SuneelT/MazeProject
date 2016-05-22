import java.awt.event.KeyEvent;
import java.util.Observable;

public class SimplePlayer extends Observable implements Player {
	private int x;
	private int y; 
	
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
	
	public void setX(int position) {
		x = position;
	}
	
	public void setY(int position) {
		y = position;
	}
	
	public void move(int direction, Maze maze) {
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
		else notifyObservers(getCoords());
	}

	@Override
	public void reset() {
		x = 0;
		y = 0;
		setChanged();
		notifyObservers("Create");
	}
	
	@Override
	public int[] getCoords() {
		int[] coords = {x,y};
		return coords;
	}
}
