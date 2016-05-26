import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;

/**
 * The Player class manages the movement of the player-controlled sprite that is used to navigate the maze. It is
 * responsible for storing and updating the location of the player within the maze as well as coordinating the
 * player-controlled sprite.
 */
public class Player extends Observable {
	private int x;
	private int y;
	private int collectibleCount;
	private List<BufferedImage> sprites = new ArrayList<BufferedImage>();
	private final int UP = 0; private final int DOWN = 1;
	private final int LEFT = 2; private final int RIGHT = 3;
	private int direction;

	/**
	 * Constructor for a Player.
	 * A Player stores its location and updates the appearance of the sprite based on the user's keyboard input.
	 */
	public Player() {
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

	/**
	 * Gets the position of the player along the maze's x-axis.
	 * @return The x-coordinate of the player's position
     */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the position of the player along the maze's y-axis.
	 * @return The y-coordinate of the player's position
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the position of the player along the maze's x-axis
	 * @param position - The x-coordinate of the player's position to be set
     */
	public void setX(int position) {
		x = position;
	}

	/**
	 * Sets the position of the player along the maze's y-axis
	 * @param position - The y-coordinate of the player's position to be set
	 */
	public void setY(int position) {
		y = position;
	}

	/**
	 * Moves the player one cell in the up, down, left or right direction if there is no wall in the way. Also updates
	 * the sprite to face the direction of movement.
	 * @param direction - The direction in which the player requests to move
	 * @param maze - The maze representation
     */
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
			CollectibleState cState = (CollectibleState) maze.getState(x, y);
			if (!cState.checkCollected()) {
				cState.collect();
				collectibleCount--;
			}
		}
		setChanged();
		if (x == maze.getSize()-1 && y == maze.getSize()-1 && collectibleCount == 0) notifyObservers(true);
		else notifyObservers(getCoords());
	}

	/**
	 * Resets the position of the player to its starting position.
	 * @param collectibleCount - The number of collectibles remaining in the maze
     */
	public void reset(int collectibleCount) {
		x = 0;
		y = 0;
		this.collectibleCount = collectibleCount;
		direction = DOWN;
		setChanged();
		notifyObservers("Create");
	}

	/**
	 * Gets the coordinates of the player's current position.
	 * @return The x and y coordinates of the player's position in an array.
     */
	public int[] getCoords() {
		int[] coords = {x,y};
		return coords;
	}

	/**
	 * Gets the direction that the sprite is facing in.
	 * @return The sprite's direction.
     */
	public Image getSprite() {
		return sprites.get(direction);
	}

	/**
	 * Gets the number of collectibles that the player has not yet collected.
	 * @return The number of collectibles in the maze
     */
	public int getNumLeft() {
		return collectibleCount;
	}
}
