import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Collectible {
	private int x;
	private int y;
	private BufferedImage icon; 
	private BufferedImage bwIcon;
	private boolean collected = false;
	private int index;
	
	/**
	 * Constructs an instance of the Collectible Class
	 * Creates a new collectible object which will be used in the "collectible" game-mode. This object
	 * includes the images and location of the collectible. A collectible will be placed in a different
	 * maze column than previously constructed collectibles.
	 * @param coords - An array containing the x-Coordinates of all previously created collectibles.
	 * @param size - The size of the maze (width/height).
	 * @param mode - The difficulty of the current game Maze
	 * @param index - An index number signifying the order in which this collectible was constructed.
	 */
	public Collectible (int[] coords, int size, int mode, int index) {
		this.index = index;
		Random generator = new Random();
		while (true) {
			this.x = generator.nextInt(size-2) + 1;
			this.y = generator.nextInt(size-2) + 1;
			boolean flag = true;
			for (int i = 0; i < index; i++) {
				if (coords[i] == this.x) flag = false;
			}
			if (flag == true) break;
		}
		coords[index] = this.x;
		setImage(mode, index);
	} 
	
	/**
	 * A helper function to assign the respective sprite image to a constructed collectible
	 * Given some basic information about the collectible - this function will assign the new object
	 * a sprite for it's GUI generation.
	 * @param mode - The difficulty of the current game Maze
	 * @param index - An index number signifying the order in which this collectible was constructed.
	 */
	private void setImage(int mode, int index) {
		try {
		if (mode == 0) {
			icon = ImageIO.read(new File("images/key.png")); bwIcon = ImageIO.read(new File("images/keyBlack.png"));
		} else if (mode == 1) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/F.png")); bwIcon = ImageIO.read(new File("images/FBlack.png")); break;
			case 1: icon = ImageIO.read(new File("images/U.png")); bwIcon = ImageIO.read(new File("images/UBlack.png")); break;
			case 2: icon = ImageIO.read(new File("images/N.png")); bwIcon = ImageIO.read(new File("images/NBlack.png")); break;
			}
		} else if (mode == 2) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/L.png")); bwIcon = ImageIO.read(new File("images/LBlack.png")); break;
			case 1: icon = ImageIO.read(new File("images/E.png")); bwIcon = ImageIO.read(new File("images/EBlack.png")); break;
			case 2: icon = ImageIO.read(new File("images/A.png")); bwIcon = ImageIO.read(new File("images/ABlack.png")); break;
			case 3: icon = ImageIO.read(new File("images/R.png")); bwIcon = ImageIO.read(new File("images/RBlack.png")); break;
			case 4: icon = ImageIO.read(new File("images/N.png")); bwIcon = ImageIO.read(new File("images/NBlack.png")); break;
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the image sprite of a collectible
	 * @return The image sprite of a collectible
	 */
	public BufferedImage getCollectedImage() {
		return icon;
	}
	
	/**
	 * Returns the x and y coordinates of a collectible
	 * This function returns the x and y coorindates of a collectible for use of the front-end GUI.
	 * (0,0) in terms of coordinates refers to the top left hand corner of the maze. 
	 * @return The two co-ords are returned in an array, with the x position stored in the 0th position and the y position stored in the 1st array space.
	 */
	public int[] getCoords() {
		if (collected == true) {return null;}
		int[] coords = {x,y};
		return coords;
	}
	
	/**
	 * Returns whether a collectible has been collected by the player or not
	 * @return A boolean signifying whether a collectible has already been collected
	 */
	public boolean returnStatus() {
		return collected;
	}

	/**
	 * Returns the sprite for the Black/White version of the collectible sprite.
	 * This sprite is to be used by the CollectedPanel
	 * @return The Black/White version of the collectible sprite
	 */
	public BufferedImage getBWImage() {
		return bwIcon;
	}
	
	/**
	 * Changes the status of a collectible to collected
	 * This will signal to the Maze that a collectible has been collected and is no longer on the Maze board.
	 */
	public void collect() {
		collected = true;
	}
	
	/**
	 * Returns the order/place in which a collectible was constructed
	 * @return The indexed order in which a collectible was constructed
	 */
	public int getIndex() {
		return index;
	}
}
