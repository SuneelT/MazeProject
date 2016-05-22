import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Collectable {
	private int x;
	private int y;
	private Image icon; 
	private boolean collected;
	
	public Collectable (int size, int mode, int index) {
		Random generator = new Random();
		this.x = generator.nextInt(size-1);
		this.y = generator.nextInt(size-1);
		this.collected = false;
		setImage(mode, index);
	} 
	
	public void setImage(int mode, int index) {
		try {
		if (mode == 1) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/F.png")); break;
			case 1: icon = ImageIO.read(new File("images/U.png")); break;
			case 2: icon = ImageIO.read(new File("images/N.png")); break;
			}
		} else if (mode == 2) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/L.png")); break;
			case 1: icon = ImageIO.read(new File("images/E.png")); break;
			case 2: icon = ImageIO.read(new File("images/A.png")); break;
			case 3: icon = ImageIO.read(new File("images/R.png")); break;
			case 4: icon = ImageIO.read(new File("images/N.png")); break;
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return icon;
	}
	
	public int[] getCoords() {
		if (collected == true) {return null;}
		int[] coords = {x,y};
		return coords;
	}
	
	public void possibleCollect(int x, int y) {
		if (x == this.x && y == this.y) {
			collected = true;
		}
	}
	
	public boolean returnStatus() {
		return collected;
	}
}
