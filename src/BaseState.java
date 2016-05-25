 
public interface BaseState { 

	/**
	 * Return the X-Coord of a state representation
	 * @return The X-Coord of a state representation
	 */
	public int getX();
	
	/**
	 * Return the Y-Coord of a state representation
	 * @return The Y-Coord of a state representation
	 */
	public int getY();
	
	/**
	 * Returns whether an edge exists in a given direction for a State representation
	 * @param edge - The direction in which we are looking to examine for a valid edge connection
	 * @return A boolean signifying the existence of an edge. True means that an edge exists.
	 */
	public boolean edgeExists(String edge);
	
	/**
	 * Returns the baseState located above the given state representation
	 * @return The baseState located above the given state representation
	 */
	public BaseState getUp();

	/**
	 * Returns the baseState located below the given state representation
	 * @return The baseState located below the given state representation
	 */
	public BaseState getDown();
	
	/**
	 * Returns the baseState located to the left of the given state representation
	 * @return The baseState located to the left the given state representation
	 */
	public BaseState getLeft();
	
	/**
	 * Returns the baseState located to the right of the given state representation
	 * @return The baseState located to the right the given state representation
	 */
	public BaseState getRight();
	
	/**
	 * Returns the edge object connecting two state representations
	 * @param edge - A string containing the name of the direction whose edge we wish to receive
	 * @return The edge connecting the given baseState in the specified direction.
	 */
	public Edge getEdge(String edge);
	
	/**
	 * Connects a baseState to another specified baseState in the upward direction.
	 * @param node - The state which we would like to create a connection with
	 * @param wall - Whether a wall exists between the two respective baseStates
	 */
	public void addConnectionUp(BaseState node, boolean wall);
	
	/**
	 * Connects a baseState to another specified baseState in the downward direction.
	 * @param node - The state which we would like to create a connection with
	 * @param wall - Whether a wall exists between the two respective baseStates
	 */
	public void addConnectionDown(BaseState node, boolean wall);
	
	/**
	 * Connects a baseState to another specified baseState in the leftward direction.
	 * @param node - The state which we would like to create a connection with
	 * @param wall - Whether a wall exists between the two respective baseStates
	 */
	public void addConnectionLeft(BaseState node, boolean wall);
	
	/**
	 * Connects a baseState to another specified baseState in the rightward direction.
	 * @param node - The state which we would like to create a connection with
	 * @param wall - Whether a wall exists between the two respective baseStates
	 */
	public void addConnectionRight(BaseState node, boolean wall);
}
