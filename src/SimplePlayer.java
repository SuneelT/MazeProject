import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

public class SimplePlayer extends Observable implements Player {
	private int x;
	private int y;
	private int collectableCount; 
	private List<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private final int UP = 0; private final int DOWN = 1;
	private final int LEFT = 2; private final int RIGHT = 3;
	private int direction;
	
	public SimplePlayer() {
		this.x = 0;
		this.y = 0;
			try {
				sprites.add(ImageIO.read(new File("images/player_up.png")));
				sprites.add(ImageIO.read(new File("images/player_down.png")));
				sprites.add(ImageIO.read(new File("images/player_left.png")));
				sprites.add(ImageIO.read(new File("images/player_right.png")));
			} catch (IOException e) {e.printStackTrace();}
		direction = DOWN;
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
			y = (maze.isConnected(x, y, 0)) ? y-1 : y; this.direction = UP; break;
		case KeyEvent.VK_DOWN:
			y = (maze.isConnected(x, y, 1)) ? y+1: y; this.direction = DOWN; break;
		case KeyEvent.VK_LEFT:
			x = (maze.isConnected(x, y, 2)) ? x-1 : x; this.direction = LEFT; break;
		case KeyEvent.VK_RIGHT:
			x = (maze.isConnected(x, y, 3)) ? x+1: x; this.direction = RIGHT; break;
		}
		if (!maze.isClassic()) {
			CollectableState cState = (CollectableState) maze.getState(x, y);
			if (!cState.checkCollected()) {
				cState.collect();
				collectableCount--;
			}
		}
		setChanged();
		if (x == maze.getSize()-1 && y == maze.getSize()-1 && collectableCount == 0) notifyObservers(true);
		else notifyObservers(getCoords());
	}

	@Override
	public void reset(int collectableCount) {
		x = 0;
		y = 0;
		this.collectableCount = collectableCount;
		direction = DOWN;
		setChanged();
		notifyObservers("Create");
	}
	
	@Override
	public int[] getCoords() {
		int[] coords = {x,y};
		return coords;
	}

	@Override
	public Image getSprite() {
		return sprites.get(direction);
	}

	@Override
	public int getNumLeft() {
		return collectableCount;
	}
}
