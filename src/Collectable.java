import java.util.Random;

import javax.swing.ImageIcon;

public class Collectable {
	private int x;
	private int y;
	private ImageIcon icon; 
	private boolean collected;
	
	public Collectable (int size, int mode, int index) {
		Random generator = new Random();
		this.x = generator.nextInt(size);
		this.y = generator.nextInt(size);
		this.collected = false;
		setImage(mode, index);
	}
	
	public void setImage(int mode, int index) {
		if (mode == 1) {
			switch (index) {
			case 0: icon = new ImageIcon("F.JPG"); break;
			case 1: icon = new ImageIcon("U.JPG"); break;
			case 2: icon = new ImageIcon("N.JPG"); break;
			}
		} else if (mode == 2) {
			switch (index) {
			case 0: icon = new ImageIcon("L.JPG"); break;
			case 1: icon = new ImageIcon("E.JPG"); break;
			case 2: icon = new ImageIcon("A.JPG"); break;
			case 3: icon = new ImageIcon("R.JPG"); break;
			case 4: icon = new ImageIcon("N.JPG"); break;
			}
		}
	}
	
	public ImageIcon getImage() {
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
