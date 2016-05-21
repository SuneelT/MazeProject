import java.util.Observer;

public interface Player {
	public void move(int direction, Maze maze);
	public void reset();
	public int[] getCoords();
	public void addObserver(Observer o);
}
