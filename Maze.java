
public interface Maze {
	
	/*public int getHeight();
	public int getWidth();
	public int getPlayerX();
	public int getPlayerY(); */
	
	// use strategy in generateMaze to have easy, hard, medium algorithm to generate different mazes
	//public void generateMaze();
	
	// 0, 1, 2, 3 -> right, left, up, down
	public void movePlayer(int direction);
	public void movePlayer(String direction);
	
}
