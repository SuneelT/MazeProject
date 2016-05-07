
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
	
	public void changeWall(boolean state) {
		this.wall = state;
	}
	
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
