import java.awt.Image;
import java.util.Observer;

public interface Player { 
	public void move(int direction, Maze maze);
	public void reset(int collectableCount);
	public int[] getCoords();
	public void addObserver(Observer o);
	public Image getSprite();
	public int getNumLeft();
}
