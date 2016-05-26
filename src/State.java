/**
 * The State class is basic implementation of the BaseState interface. This class holds all of the actual methods needed to operate
 * on a State, and is the reference object held by any decorated states. The class itself contains connections to other states, as well as knowledge of where
 * it resides spatially within the maze.
 *
 */
public class State implements BaseState {
	private Edge up; 
	private Edge down;
	private Edge left;
	private Edge right;
	private int x;
	private int y;
	
	/**
	 * Constructs a State object.
	 * Initially there are no connections to or from the State, and its x and y coordinates are given.
	 * @param x - The x-coordinate of this State.
	 * @param y - The y-coordinate of this State.
	 */
	public State(int x, int y) {
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x-coordinate of this State.
	 * @returns int - the x-coordinate of this State.
	 */
	@Override
	public int getX() {
		return this.x;
	}

	/**
	 * Get the y-coordinate of this State.
	 * @returns int - the y-coordinate of this State.
	 */
	@Override
	public int getY() {
		return this.y;
	}
	
	/**
	 * Check to see if an edge exists from this State to another State.
	 * The direction to check an edge for is specified by the "edge" paramter and must be one of up, down, left, or right.
	 * @param edge - the direction to check an edge for.
	 * @returns boolean - True if the specified edge exists, false otherwise.
	 */
	@Override
	public boolean edgeExists(String edge) {
		edge.toLowerCase();
		if (edge.equals("up") && this.up == null) return false;
		else if (edge.equals("down") && this.down == null) return false;
		else if (edge.equals("left") && this.left == null) return false;
		else if (edge.equals("right") && this.right == null) return false;
		
		if (edge.equals("up") && !this.up.getWall()) return true;
		else if (edge.equals("down") && !this.down.getWall()) return true;
		else if (edge.equals("left") && !this.left.getWall()) return true;
		else if (edge.equals("right") && !this.right.getWall()) return true;
		return false;
	}
	
	/**
	 * Get the state's "Up" wall.
	 * @returns BaseState - the state's up wall.
	 */
	@Override
	public BaseState getUp() {
		if (up == null) return null;
		if (up.getWall() == false) return up.getTo();
		return null;
	}
	
	/**
	 * Get the state's "Down" wall.
	 * @returns BaseState - the state's down wall.
	 */
	@Override
	public BaseState getDown() {
		if (down == null) return null;
		if (down.getWall() == false) return down.getTo();
		return null;
	}
	
	/**
	 * Get the state's "Left" wall.
	 * @returns BaseState - the state's left wall.
	 */
	@Override
	public BaseState getLeft() {
		if (left == null) return null;
		if (left.getWall() == false) return left.getTo();
		return null;
	}
	
	/**
	 * Get the state's "Right" wall.
	 * @returns BaseState - the state's right wall.
	 */
	@Override
	public BaseState getRight() {
		if (right == null) return null;
		if (right.getWall() == false) return right.getTo();
		return null;
	}
	
	/**
	 * Get the edge that is connected to the "edge" direction of this state.
	 * @param edge - the string of the direction to get the edge for.
	 * @returns Edge - the edge that is connected to the "edge" direction of this state.
	 */
	@Override
	public Edge getEdge(String edge) {
		edge.toLowerCase();
		if (edge.equals("up")) {return up;}
		if (edge.equals("right")) {return right;}
		if (edge.equals("down")) {return down;}
		if (edge.equals("left")) {return left;}
		return null;
	}
	
	/**
	 * Adds an up connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionUp(BaseState node, boolean wall) {
		this.up = new Edge(this, node, wall);
	}
	
	/**
	 * Adds a down connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionDown(BaseState node, boolean wall) {
		this.down = new Edge(this, node, wall);
	}
	
	/**
	 * Adds a left connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionLeft(BaseState node, boolean wall) {
		this.left = new Edge(this, node, wall);
	}
	
	/**
	 * Adds a right connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionRight(BaseState node, boolean wall) {
		this.right = new Edge(this, node, wall);
	}
}
