import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Maze implements Iterable<BaseState> {
	private BaseState[][] states = null;
	private CollectableState[][] cStates = null;
	private int size;
	
	public Maze(int size, boolean mode) {
		this.size = size;
		if (mode) createClassicMaze();
		else createCollectorMaze();
	}

	private void createCollectorMaze() {
		int x, y;
		this.cStates = new CollectableState[size][];		//initialise the states
		for (x = 0; x < size; x++) cStates[x] = new CollectableState[size];	
		for (x = 0; x < size; x++) {	//first fill up the states array with stats
			for (y = 0; y < size; y++) cStates[x][y] = new CollectableState(new State(x, y));
		}
	
		for (x = 0; x < size; x++) {				//add connections
			for (y = 0; y < size; y++) {			//if x == 0 || x == size-1 no side connections
				CollectableState curr = cStates[x][y];		//likewise for y
				if (y > 0) curr.addConnectionUp(cStates[x][y-1], true);
				if (y < size-1) curr.addConnectionDown(cStates[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(cStates[x-1][y], true);
				if (x < size-1) curr.addConnectionRight(cStates[x+1][y], true);
			}
		}
		this.generateMaze(cStates);
	}

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
			examine = edges.remove(index);
			
			ArrayList<BaseState> visited = new ArrayList<BaseState>();
			if (!getConnected(visited, examine.getTo(), examine.getFrom())) {
				examine.destroyWall();
			}
		}
	}
	
	public boolean getConnected(ArrayList<BaseState> visited, BaseState to, BaseState from) {
		if (to == null) {return false;}
		if (to.equals(from)) {return true;}
		
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

	public void draw(Graphics g, int width, int height) {
		g.setColor(Color.red);
		int intervalx = width/states.length;
		int intervaly = height/states.length;
		for (int posy = 0; posy < states.length; posy++) {
			for (int posx = 0; posx < states.length; posx++) {
				if (states[posx][posy].getDown() == null) {
					g.drawLine(posx*intervalx, (posy+1)*intervaly, (posx+1)*intervalx, (posy+1)*intervaly);
				}
				if (states[posx][posy].getRight() == null) {
					g.drawLine((posx+1)*intervalx, posy*intervaly, (posx+1)*intervalx, (posy+1)*intervaly);
				}
			}
		}
	}

	public int getSize() {
		return size;
	}

	public boolean isConnected(int x, int y, int dir) {
		switch (dir) {
		case 0:
			if (states != null)
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
}
