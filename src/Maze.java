import java.awt.Graphics;

public interface Maze {
	public void draw(Graphics g, int width, int height);
	public int getSize();
	public boolean isConnected(int x, int y, int dir);
	
	
	// use strategy in generateMaze to have easy, hard, medium algorithm to generate different mazes
	//public void generateMaze();
	
}
