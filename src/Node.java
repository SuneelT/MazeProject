public class Node {
	private State item;
	private Edge up;
	private Edge down;
	private Edge left;
	private Edge right;
	
	private int x;
	private int y;
	
	public Node(State item, int x, int y) {
		this.item = item;
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		
		this.x = x;
		this.y = y;
	}
	
	public State getItem() {
		return this.item;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean edgeExists(String edge) {
		edge.toLowerCase();
		if (edge.equals("up") && this.up == null) return false;
		else if (edge.equals("down") && this.down == null) return false;
		else if (edge.equals("left") && this.left == null) return false;
		else if (edge.equals("right") && this.right == null) return false;
		
		if (edge.equals("up") && !this.up.getWall()) return true;
		else if (edge.equals("down") && !this.down.getWall()) return true;
		else if (edge.equals("left") && !this.left.getWall()) return true;
		else if (edge.equals("right") && !this.right.getWall()) return true;
		return false;
	}
	
	public Node getUp() {
		if (up == null) return null;
		if (up.getWall() == false) return up.getTo();
		return null;
	}
	public Node getDown() {
		if (down == null) return null;
		if (down.getWall() == false) return down.getTo();
		return null;
	}
	public Node getLeft() {
		if (left == null) return null;
		if (left.getWall() == false) return left.getTo();
		return null;
	}
	public Node getRight() {
		if (right == null) return null;
		if (right.getWall() == false) return right.getTo();
		return null;
	}
	
	public Edge getEdge(String edge) {
		edge.toLowerCase();
		if (edge.equals("up")) {return up;}
		if (edge.equals("right")) {return right;}
		if (edge.equals("down")) {return down;}
		if (edge.equals("left")) {return left;}
		return null;
	}
	
	public void addConnectionUp(Node node, boolean wall) {
		this.up = new Edge(this, node, wall);
	}
	public void addConnectionDown(Node node, boolean wall) {
		this.down = new Edge(this, node, wall);
	}
	public void addConnectionLeft(Node node, boolean wall) {
		this.left = new Edge(this, node, wall);
	}
	public void addConnectionRight(Node node, boolean wall) {
		this.right = new Edge(this, node, wall);
	}
}
