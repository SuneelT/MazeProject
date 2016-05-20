
public interface BaseState {

	public int getX();
	
	public int getY();
	
	public boolean getPlayer();
	
	public void movePlayer();
	
	public boolean edgeExists(String edge);
	
	public State getUp();

	public State getDown();
	
	public State getLeft();
	
	public State getRight();
	
	public Edge getEdge(String edge);
	
	public void addConnectionUp(State node, boolean wall);
	
	public void addConnectionDown(State node, boolean wall);
	
	public void addConnectionLeft(State node, boolean wall);
	
	public void addConnectionRight(State node, boolean wall);
	
	public boolean isTopWall();
	
	public boolean isBottomWall();
	
	public boolean isLeftWall();
	
	public boolean isRightWall();
}
