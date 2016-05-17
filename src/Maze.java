import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Maze implements Iterable<State> {
	private State[][] states;	
	
	// creates width*height nodes with no walls between them
	// each node has value initVal
	public Maze(int size) {
		int x, y;
		// need to initialize nodes
		// need to call generator method to generate maze
		this.states = new State[size][];
		for (x = 0; x < size; x++) {
			states[x] = new State[size];
			
		}
		
		// fill nodes with nodes with no connections and initial state 'initVal'
		
		for (x = 0; x < size; x++) {
			for (y = 0; y < size; y++) {
				states[x][y] = new State(x, y);
			}
		}
		
		// add connections
		// if x = 0, no left connection
		// if x = width-1, no right connection
		// if y = 0, no up connection
		// if y = height-1, no down connection
		for (x = 0; x < size; x++) {
			for (y = 0; y < size; y++) {
				State curr = states[x][y];
				if (y > 0) curr.addConnectionUp(states[x][y-1], true);
				if (y < size-1) curr.addConnectionDown(states[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(states[x-1][y], true);
				if (x < size-1) curr.addConnectionRight(states[x+1][y], true);
			}
		}
		this.generateMaze();
	}

	private void generateMaze() {
		//Add all edges to a bag
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (State[] Collection: states) {
			for (State individualNode: Collection) {
				int x = individualNode.getX(); int y = individualNode.getY();
				if (y > 0) edges.add(individualNode.getEdge("up")); 
				if (x > 0) edges.add(individualNode.getEdge("left"));
			}
		}

		Random generator = new Random(); Edge examine = null;
		while (edges.size() != 0) {
			int index = generator.nextInt(edges.size());
			examine = edges.remove(index);
			
			ArrayList<State> visited = new ArrayList<State>();
			if (!getConnected(visited, examine.getTo(), examine.getFrom())) {
				examine.destroyWall();
			}
		}
	}
	
	public boolean getConnected(ArrayList<State> visited, State to, State from) {
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
		return states.length;
	}

	public boolean isConnected(int x, int y, int dir) {
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

	@Override
	public Iterator<State> iterator() {
		Iterator<State> it = new Iterator<State>() {
			private int x = 0;
			private int y = 0;

			@Override
			public boolean hasNext() {
				if (y == getSize()-1 && x == getSize()-1) return false;
				else return true;
			}

			@Override
			public State next() {
				return states[x++][y++];
			}

			@Override
			public void remove() {}
		};
		return it;
	}
}
