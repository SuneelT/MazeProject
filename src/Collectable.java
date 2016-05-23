import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

import javax.imageio.ImageIO;

public class Collectable implements Comparator<Collectable> {
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
	
	public void setImage(int mode, int index) {
		try {
		if (mode == 0) {
			icon = ImageIO.read(new File("images/key.png")); bwIcon = ImageIO.read(new File("images/keyBlack.PNG"));
		} else if (mode == 1) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/F.png")); bwIcon = ImageIO.read(new File("images/FBlack.PNG")); break;
			case 1: icon = ImageIO.read(new File("images/U.png")); bwIcon = ImageIO.read(new File("images/UBlack.PNG")); break;
			case 2: icon = ImageIO.read(new File("images/N.png")); bwIcon = ImageIO.read(new File("images/NBlack.PNG")); break;
			}
		} else if (mode == 2) {
			switch (index) {
			case 0: icon = ImageIO.read(new File("images/L.png")); bwIcon = ImageIO.read(new File("images/LBlack.PNG")); break;
			case 1: icon = ImageIO.read(new File("images/E.png")); bwIcon = ImageIO.read(new File("images/EBlack.PNG")); break;
			case 2: icon = ImageIO.read(new File("images/A.png")); bwIcon = ImageIO.read(new File("images/ABlack.PNG")); break;
			case 3: icon = ImageIO.read(new File("images/R.png")); bwIcon = ImageIO.read(new File("images/RBlack.PNG")); break;
			case 4: icon = ImageIO.read(new File("images/N.png")); bwIcon = ImageIO.read(new File("images/NBlack.PNG")); break;
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
	
	public void possibleCollect(int x, int y) {
		if (x == this.x && y == this.y) {
			collected = true;
		}
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

	@Override
	public int compare(Collectable arg0, Collectable arg1) {
		if (arg0.index > arg1.index) {
			return 1;
		} else if (arg0.index == arg1.index) {
			return 0;
		}
		return -1;
	}
}
