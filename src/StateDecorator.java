import java.util.Observable;

/**
 * The StateDecorator class is a concrete use of the Decorator pattern in an attempt to alter the Maze's state easily.
 * The states of the maze would change due to the inclusion of more modes of play in the maze; specifically collector's mode. When
 * using collectible states, the new class must extend this abstract class.
 * 
 *
 */
public abstract class StateDecorator extends Observable implements BaseState{
	private BaseState state; 
	
	/**
	 * Construct a decorated state..
	 * @param state The state to be used for base methods.
	 */
	public StateDecorator(BaseState state) {
		this.state = state;
	}
	
	/**
	 * Get the x-coordinate of this state.
	 * @returns int - the x-coordinate of this state.
	 */
	@Override
	public int getX() {
		return state.getX();
	}

	/**
	 * Get the y-coordinate of this state.
	 * @returns int - the y-coordinate of this state.
	 */
	@Override
	public int getY() {
		return state.getY();
	}

	/**
	 * Checks to see if an edge exists.
	 * The edge is a string that signals one of the four basic directions.
	 * @param edge - The string of the direction to check for if an edge exists.
	 * @returns boolean - True if the edge exists, false if it doesn not.
	 */
	@Override
	public boolean edgeExists(String edge) {
		return state.edgeExists(edge);
	}

	/**
	 * Get the state's "Up" wall.
	 * @returns BaseState - the state's up wall.
	 */
	@Override
	public BaseState getUp() {
		return state.getUp();
	}

	/**
	 * Get the state's "Down" wall.
	 * @returns BaseState - the state's down wall.
	 */
	@Override
	public BaseState getDown() {
		return state.getDown();
	}

	/**
	 * Get the state's "Left" wall.
	 * @returns BaseState - the state's left wall.
	 */
	@Override
	public BaseState getLeft() {
		return state.getLeft();
	}

	/**
	 * Get the state's "Right" wall.
	 * @returns BaseState - the state's right wall.
	 */
	@Override
	public BaseState getRight() {
		return state.getRight();
	}

	/**
	 * Get the edge that is connected to the "edge" direction of this state.
	 * @param edge - the string of the direction to get the edge for.
	 * @returns Edge - the edge that is connected to the "edge" direction of this state.
	 */
	@Override
	public Edge getEdge(String edge) {
		return state.getEdge(edge);
	}

	/**
	 * Adds an up connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionUp(BaseState node, boolean wall) {
		state.addConnectionUp(node, wall);
	}

	/**
	 * Adds a down connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionDown(BaseState node, boolean wall) {
		state.addConnectionDown(node, wall);
	}

	/**
	 * Adds a left connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionLeft(BaseState node, boolean wall) {
		state.addConnectionLeft(node, wall);
	}

	/**
	 * Adds a right connection from this state to another state.
	 * Note: the other node may be a wall, which is signified from the wall parameter.
	 * @param node - the node to connect this node to.
	 * @param wall - a boolean to see if the node to connect to is a wall or not.
	 */
	@Override
	public void addConnectionRight(BaseState node, boolean wall) {
		state.addConnectionRight(node, wall);
	}
}
