public interface Maze {
	public void updatePosition(int direction);
	
	
	/*public int getHeight();
	public int getWidth();
	public int getPlayerX();
	public int getPlayerY(); */
	
	// use strategy in generateMaze to have easy, hard, medium algorithm to generate different mazes
	//public void generateMaze();
	
	// 0, 1, 2, 3 -> right, left, up, down
	//public void movePlayer(int direction);		these probably should be internal to the maze representation
	//public void movePlayer(String direction);
	
}
