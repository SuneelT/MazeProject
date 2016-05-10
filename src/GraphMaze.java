import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class GraphMaze implements Maze {
	private Node[][] nodes;	
	
	// creates width*height nodes with no walls between them
	// each node has value initVal
	public GraphMaze(int size) {
		int x, y;
		// need to initialize nodes
		// need to call generator method to generate maze
		this.nodes = new Node[size][];
		for (x = 0; x < size; x++) {
			nodes[x] = new Node[size];
			
		}
		
		// fill nodes with nodes with no connections and initial state 'initVal'
		
		for (x = 0; x < size; x++) {
			for (y = 0; y < size; y++) {
				nodes[x][y] = new Node(x, y);
			}
		}
		
		// add connections
		// if x = 0, no left connection
		// if x = width-1, no right connection
		// if y = 0, no up connection
		// if y = height-1, no down connection
		for (x = 0; x < size; x++) {
			for (y = 0; y < size; y++) {
				Node curr = nodes[x][y];
				if (y > 0) curr.addConnectionUp(nodes[x][y-1], true);
				if (y < size-1) curr.addConnectionDown(nodes[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(nodes[x-1][y], true);
				if (x < size-1) curr.addConnectionRight(nodes[x+1][y], true);
			}
		}
		this.generateMaze();
	}

	private void generateMaze() {
		//Add all edges to a bag
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Node[] Collection: nodes) {
			for (Node individualNode: Collection) {
				int x = individualNode.getX(); int y = individualNode.getY();
				if (y > 0) edges.add(individualNode.getEdge("up")); 
				if (x > 0) edges.add(individualNode.getEdge("left"));
			}
		}

		Random generator = new Random(); Edge examine = null;
		while (edges.size() != 0) {
			int index = generator.nextInt(edges.size());
			examine = edges.remove(index);
			
			ArrayList<Node> visited = new ArrayList<Node>();
			if (!getConnected(visited, examine.getTo(), examine.getFrom())) {
				examine.destroyWall();
			}
		}
	}
	
	public boolean getConnected(ArrayList<Node> visited, Node to, Node from) {
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
		int intervalx = width/nodes.length;
		int intervaly = height/nodes.length;
		for (int posy = 0; posy < nodes.length; posy++) {
			for (int posx = 0; posx < nodes.length; posx++) {
				if (nodes[posx][posy].getDown() == null) {
					g.drawLine(posx*intervalx, (posy+1)*intervaly, (posx+1)*intervalx, (posy+1)*intervaly);
				}
				if (nodes[posx][posy].getRight() == null) {
					g.drawLine((posx+1)*intervalx, posy*intervaly, (posx+1)*intervalx, (posy+1)*intervaly);
				}
			}
		}
	}

	public int getSize() {
		return nodes.length;
	}

	@Override
	public boolean isConnected(int x, int y, int dir) {
		switch (dir) {
		case 0:
			return nodes[x][y].getUp() != null;
		case 1:
			return nodes[x][y].getDown() != null;
		case 2:
			return nodes[x][y].getLeft() != null;
		case 3:
			return nodes[x][y].getRight() != null;
		default:
			return false;
		}
	}
}
