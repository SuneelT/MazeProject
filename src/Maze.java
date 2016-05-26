import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * The Maze class represents the maze itself.
 * It is responsible for the generation of the maze.  
 *
 */
public class Maze implements Iterable<BaseState> {
	private BaseState[][] states = null;
	private CollectibleState[][] cStates = null;
	private int size;
	private boolean isClassic = true;
	
	/**
	 * Constructor for a Maze
	 * Sets the size of the maze (difficulty)
	 * Generates a new maze depending on the game mode. 
	 * @param size - the size of the maze to be generated. 
	 * @param mode - the mode of the maze to be generated. 
	 */
	public Maze(int size, boolean mode) {
		this.size = size; setClassic(mode);
		if (mode) createClassicMaze();
		else createCollectorMaze();
	}
	
	/**
	 * Creates a new representation of the maze which implements collectibles.
	 */
	private void createCollectorMaze() {
		int x, y;
		this.cStates = new CollectibleState[size][];		//initialise the states
		for (x = 0; x < size; x++) cStates[x] = new CollectibleState[size];
		for (x = 0; x < size; x++) {	//first fill up the states array with stats
			for (y = 0; y < size; y++) cStates[x][y] = new CollectibleState(new State(x, y));
		}
	
		for (x = 0; x < size; x++) {				//add connections
			for (y = 0; y < size; y++) {			//if x == 0 || x == size-1 no side connections
				CollectibleState curr = cStates[x][y];		//likewise for y
				if (y > 0) curr.addConnectionUp(cStates[x][y-1], true);
				if (y < size-1) curr.addConnectionDown(cStates[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(cStates[x-1][y], true);
				if (x < size-1) curr.addConnectionRight(cStates[x+1][y], true);
			}
		}
		
		int difficulty = -1; int index = 0;
		switch (size) {
		case 16: difficulty = 0; index = 1; break;
		case 24: difficulty = 1; index = 3; break;
		case 32: difficulty = 2; index = 5; break;
		}
		
		int[] xCoords = new int[index];
		for (int i = 0; i < index; i++) {
			Collectible collect = new Collectible (xCoords, size, difficulty, i);
			int[] coordinates = collect.getCoords();
			cStates[coordinates[1]][coordinates[0]].addCollectible(collect);
		}
		
		this.generateMaze(cStates);
	}
	/**
	 * Creates a new representation of the maze without collectibles.
	 */
	private void createClassicMaze() {
		int x, y;
		this.states = new BaseState[size][];		//initialise the states
		for (x = 0; x < size; x++) states[x] = new BaseState[size];	
		for (x = 0; x < size; x++) {	//first fill up the states array with stats
			for (y = 0; y < size; y++) states[x][y] = new State(x, y);
		}
	
		for (x = 0; x < size; x++) {				//add connections
			for (y = 0; y < size; y++) {			//if x == 0 || x == size-1 no side connections
				BaseState curr = states[x][y];		//likewise for y
				if (y > 0) curr.addConnectionUp(states[x][y-1], true);
				if (y < size-1) curr.addConnectionDown(states[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(states[x-1][y], true);
				if (x < size-1) curr.addConnectionRight(states[x+1][y], true);
			}
		}
		this.generateMaze(states);
	}
	/**
	 * A helper function that is used by createClassicMaze() and createCollectorMaze()
	 * Uses an algorithm to generate the actual maze representation.
	 * @param states
	 */
	private void generateMaze(BaseState[][] states) {
		//Add all edges to a bag
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (BaseState[] Collection: states) {
			for (BaseState individualNode: Collection) {
				int x = individualNode.getX(); int y = individualNode.getY();
				if (y > 0) edges.add(individualNode.getEdge("up")); 
				if (x > 0) edges.add(individualNode.getEdge("left"));
			}
		}

		Random generator = new Random(); Edge examine = null;
		
		while (edges.size() != 0) {
			int index = generator.nextInt(edges.size());
			int factor = generator.nextInt(3);
			switch (factor) {
			case 0: index = index/3; break;	
			case 2: index = edges.size() - 1 - index/3; break;	
			}
			examine = edges.remove(index);
			
			ArrayList<BaseState> visited = new ArrayList<BaseState>();
			if (!getConnected(visited, examine.getTo(), examine.getFrom())) {
				examine.destroyWall();
			}
		}
	}
	/**
	 * checks if two nodes are connected
	 * @param visited - a list of nodes that have been visited
	 * @param to - the first node
	 * @param from - node to be checked if it is connected to the first node
	 * @return true if it is connected and false if it isn't connected
	 */
	public boolean getConnected(ArrayList<BaseState> visited, BaseState to, BaseState from) {
		if (to == null) {return false;}
		if (to.getX() == from.getX() && to.getY() == from.getY()) {return true;}
	
		if (visited.indexOf(to) == -1) { 
			visited.add(to);
			boolean connected = getConnected(visited, to.getUp(),from);
			if (connected == true) {return true;}
			connected = getConnected(visited, to.getLeft(),from);
			if (connected == true) {return true;}
			connected = getConnected(visited, to.getRight(),from);
			if (connected == true) {return true;}
			connected = getConnected(visited, to.getDown(),from);
			if (connected == true) {return true;}
		}
		return false;
	}
	/**
	 * Gets the size of the maze. 
	 * @return the size of the maze.
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Used by classic maze mode. 
	 * Checks if there is an edge from a coordinate in a particular direction
	 * @param x - the x coordinate of the node
	 * @param y - the y coordinate of the node
	 * @param dir - the direction of the edge to be checked if it is connected
	 * @return true if there is an edge connected and false if there isn't
	 */
	public boolean isConnected(int x, int y, int dir) {
		if (states == null) return cisConnected(x,y,dir);
		switch (dir) {
		case 0:
			return states[x][y].getUp() != null;
		case 1:
			return states[x][y].getDown() != null;
		case 2:
			return states[x][y].getLeft() != null;
		case 3:
			return states[x][y].getRight() != null;
		default:
			return false;
		}
	}
	/**
	 * Used by collector maze mode. 
	 * c is for collector
	 * Checks if there is an edge from a coordinate in a particular direction
	 * @param x - the x coordinate of the node
	 * @param y - the y coordinate of the node
	 * @param dir - the direction of the edge to be checked if it is connected
	 * @return true if there is an edge connected and false if there isn't
	 */
	private boolean cisConnected(int x, int y, int dir) {
		switch (dir) {
		case 0:
			return cStates[x][y].getUp() != null;
		case 1:
			return cStates[x][y].getDown() != null;
		case 2:
			return cStates[x][y].getLeft() != null;
		case 3:
			return cStates[x][y].getRight() != null;
		default:
			return false;
		}
	}
	/**
	 * Iterator through different base states
	 */
	@Override
	public Iterator<BaseState> iterator() {
		Iterator<BaseState> it = new Iterator<BaseState>() {
			private int x = 0;
			private int y = 0;

			@Override
			public boolean hasNext() {
				if (y == getSize()) return false;
				else return true;
			}

			@Override
			public BaseState next() {
				BaseState s;
				if (states != null)
					s = states[x++][y];
				else
					s = cStates[x++][y];
				if (x == getSize()) {x = 0; y++;}
				return s;
			}

			@Override
			public void remove() {}
		};
		return it;
	}
	/**
	 * Checks if the game mode is classic
	 * @return true if it is classic and false if it isn't classic
	 */
	public boolean isClassic() {
		return isClassic;
	}
	/**
	 * Sets the game mode to be classic
	 * @param isClassic - boolean representation of the game mode
	 */
	public void setClassic(boolean isClassic) {
		this.isClassic = isClassic;
	}
	/**
	 * Gets one of the nodes
	 * @param x - x coordinate of the node
	 * @param y - y coordinate of the node
	 * @return one of the nodes
	 */
	public BaseState getState(int x, int y) {
		if (states != null) return states[x][y];
		else return cStates[x][y];
	}
}
