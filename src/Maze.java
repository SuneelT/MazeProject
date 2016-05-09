import java.awt.Graphics;

public interface Maze {
	public void updatePosition(int direction);
	public void draw(Graphics g, int width, int height);
	public int getSize();
	// use strategy in generateMaze to have easy, hard, medium algorithm to generate different mazes
	//public void generateMaze();
	
}
