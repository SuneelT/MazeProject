/**
 * Represents an edge between two 'nodes' (States) in the graph which is used to represent the maze.
 * Is responsible for containing information about whether or not the particular connection is open or walled off.
 * If it is open, the two cells of the maze are connected and the player can travel freely between them.
 * If it is closed, then a wall exists between the two cells and the player cannot travel between them.
 */
public class Edge {
	private BaseState from;
	private BaseState to;
	private boolean wall;
	
	/**
	 * Constructor for an edge
	 * @param from the state (node) from which the edge is connected
	 * @param to the state (node) to which the edge is connected
	 * @param wall identifies whether or not the edge represents an open connection between two cells or is walled off
	 */
	public Edge(BaseState from, BaseState to, boolean wall) {
		this.from = from;
		this.to = to;
		this.wall = wall;
	} 
	
	/**
	 * Gets the state (graph node) to which the edge is connected
	 * @return the state that the edge links to
	 */
	public BaseState getTo() {
		return this.to;
	}
	
	/**
	 * Gets the state (graph node) from which the edge is connected
	 * @return the state that the edge is linked from
	 */
	public BaseState getFrom() {
		return this.from;
	}
	
	/**
	 * Identifies whether or not the edge represents a wall
	 * @return True if the edge is a wall, and false otherwise
	 */
	public boolean getWall() {
		return wall;
	}
	
	/**
	 * Used to change the state of the edge; whether it has a wall or not
	 * @param state a boolean representing whether or not the edge is to represent a wall
	 */
	public void changeWall(boolean state) {
		this.wall = state;
	}
	/**
	 * Destroys a wall in the maze. 
	 * Changes the wall boolean to signify that the edge does not represent a wall.
	 * Does the same for the edge connecting the same two States in the reverse direction.
	 */
	public void destroyWall() {
		this.wall = false;
		if (from.getX() == to.getX()) {
			if (from.getY() < to.getY()) {
				to.getEdge("up").changeWall(false);
			} else {
				to.getEdge("down").changeWall(false);
			}
		} else if (from.getY() == to.getY()) {
			if (from.getX() < to.getX()) {
				to.getEdge("left").changeWall(false);
			} else {
				to.getEdge("right").changeWall(false);
			}
		}
	}
	
}
