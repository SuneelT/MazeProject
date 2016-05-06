
public class Edge {
	private Node from;
	private Node to;
	private boolean wall;
	
	public Edge(Node from, Node to, boolean wall) {
		this.from = from;
		this.to = to;
		this.wall = wall;
	}
	
	public Node getTo() {
		return this.to;
	}
	
	public Node getFrom() {
		return this.from;
	}
	
	public boolean getWall() {
		return wall;
	}
	
	public void destroyWall() {
		this.wall = false;
	}
	
}
