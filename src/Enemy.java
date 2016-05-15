import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Enemy extends SimplePlayer {
	public Enemy (int size) {
		super();
		Random generator = new Random();
		setX(generator.nextInt(size));
		setY(generator.nextInt(size));
	}
	
	@Override
	public void draw(Graphics g, int intervalx, int intervaly) {
		g.setColor(Color.pink);
		g.drawOval(getX()*intervalx, getY()*intervaly, intervalx, intervaly);
	}
	
	public void randomMove() {
		Random generator = new Random(); int movementFound = 0;
		while (movementFound == 0) {
			int i = (generator.nextInt(3));
			switch(i) {
			case 0:
				if (getMaze().isConnected(getX(), getY(), 0) == true) {
					setY(getY()-1);
					movementFound = 1;
				}
				break;
			case 1:
				if (getMaze().isConnected(getX(), getY(), 1) == true) {
					setY(getY()+1);
					movementFound = 1;
				}
				break;
			case 2:
				if (getMaze().isConnected(getX(), getY(), 2) == true) {
					setX(getX()-1);
					movementFound = 1;
				}
				break;
			case 3:
				if (getMaze().isConnected(getX(), getY(), 3) == true) {
					setX(getX()+1);
					movementFound = 1;
				}
				break;
			}
		}
		setChanged();
		if (getX() == getMaze().getSize()-1 && getY() == getMaze().getSize()-1) notifyObservers(true);
		else notifyObservers();
	}
}
