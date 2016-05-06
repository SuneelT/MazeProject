import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GraphMaze extends Observable implements Maze {
	
	private Node[][] nodes;
	private Player player1;	
	
	// creates width*height nodes with no walls between them
	// each node has value initVal
	public GraphMaze(int width, int height, int playerX, int playerY, State initVal) {
		int x, y;
		// need to initialize nodes
		// need to call generator method to generate maze
		this.nodes = new Node[width][];
		for (x = 0; x < width; x++) {
			nodes[x] = new Node[height];
			
		}
		
		// fill nodes with nodes with no connections and initial state 'initVal'
		
		for (x = 0; x < width; x++) {
			for (y = 0; y < height; y++) {
				System.out.println(x + " " + y);
				nodes[x][y] = new Node(initVal, x, y);
			}
		}
		
		// add connections
		// if x = 0, no left connection
		// if x = width-1, no right connection
		// if y = 0, no up connection
		// if y = height-1, no down connection
		for (x = 0; x < width; x++) {
			for (y = 0; y < height; y++) {
				Node curr = nodes[x][y];
				if (y > 0) curr.addConnectionUp(nodes[x][y-1], true);
				if (y < height-1) curr.addConnectionDown(nodes[x][y+1], true);
				if (x > 0) curr.addConnectionLeft(nodes[x-1][y], true);
				if (x < width-1) curr.addConnectionRight(nodes[x+1][y], true);
			}
		}
		
		
		this.player1 = new Player(playerX-1, playerY-1, nodes[playerX-1][playerY-1]);
		System.out.println("blah");
		this.generateMaze();
	}

/*
	@Override
	public int getHeight() {
		return nodes[0].length;
	}

	@Override
	public int getWidth() {
		return nodes.length;
	}

	@Override
	public int getPlayerX() {
		return player1.getX();
	}

	@Override
	public int getPlayerY() {
		return player1.getY();
	}
*/
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

			if (!getConnected(examine.getTo(), examine.getFrom())) {
				examine.destroyWall();
			}
		}
	}
	
	public boolean getConnected(Node to, Node from) {
		
		return false;
	}

	private void movePlayer(int direction) {
		player1.move(direction);
		
	}

	
	/*private void movePlayer(String direction) {
		player1.move(direction);
	}*/

	@Override
	public void updatePosition(int direction) {
		movePlayer(direction);
		
	}

}
