import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Collectable {
	private int x;
	private int y;
	private BufferedImage icon; 
	private BufferedImage bwIcon;
	private boolean collected = false;
	private int index;
	
	public Collectable (int size, int mode, int index) {
		this.index = index;
		Random generator = new Random();
		this.x = generator.nextInt(size-1);
		this.y = generator.nextInt(size-1);
		setImage(mode, index);
	} 
	
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
	
	public BufferedImage getCollectedImage() {
		return icon;
	}
	
	public int[] getCoords() {
		if (collected == true) {return null;}
		int[] coords = {x,y};
		return coords;
	}
	
	public boolean returnStatus() {
		return collected;
	}

	public BufferedImage getBWImage() {
		return bwIcon;
	}
	
	public void collect() {
		collected = true;
	}
	
	public int getIndex() {
		return index;
	}
}
