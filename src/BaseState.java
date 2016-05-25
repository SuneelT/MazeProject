
public interface BaseState { 

	public int getX();
	
	public int getY();
	
	public boolean edgeExists(String edge);
	
	public BaseState getUp();

	public BaseState getDown();
	
	public BaseState getLeft();
	
	public BaseState getRight();
	
	public Edge getEdge(String edge);
	
	public void addConnectionUp(BaseState node, boolean wall);
	
	public void addConnectionDown(BaseState node, boolean wall);
	
	public void addConnectionLeft(BaseState node, boolean wall);
	
	public void addConnectionRight(BaseState node, boolean wall);
}
