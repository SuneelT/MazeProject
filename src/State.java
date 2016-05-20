public class State implements BaseState {
	private Edge up;
	private Edge down;
	private Edge left;
	private Edge right;
	
	private int x;
	private int y;
	
	private boolean player;
	
	public State(int x, int y) {
		this.up = null;
		this.down = null;
		this.left = null;
		this.right = null;
		this.player = false;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getX() {
		return this.x;
	}
	
	@Override
	public int getY() {
		return this.y;
	}
	
	@Override
	public boolean getPlayer() {
		return this.player;
	}
	
	@Override
	public void movePlayer() {
		this.player = true;
	}
	
	@Override
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
	
	@Override
	public BaseState getUp() {
		if (up == null) return null;
		if (up.getWall() == false) return up.getTo();
		return null;
	}
	
	@Override
	public BaseState getDown() {
		if (down == null) return null;
		if (down.getWall() == false) return down.getTo();
		return null;
	}
	
	@Override
	public BaseState getLeft() {
		if (left == null) return null;
		if (left.getWall() == false) return left.getTo();
		return null;
	}
	
	@Override
	public BaseState getRight() {
		if (right == null) return null;
		if (right.getWall() == false) return right.getTo();
		return null;
	}
	
	@Override
	public Edge getEdge(String edge) {
		edge.toLowerCase();
		if (edge.equals("up")) {return up;}
		if (edge.equals("right")) {return right;}
		if (edge.equals("down")) {return down;}
		if (edge.equals("left")) {return left;}
		return null;
	}
	
	@Override
	public void addConnectionUp(BaseState node, boolean wall) {
		this.up = new Edge(this, node, wall);
	}
	
	@Override
	public void addConnectionDown(BaseState node, boolean wall) {
		this.down = new Edge(this, node, wall);
	}
	
	@Override
	public void addConnectionLeft(BaseState node, boolean wall) {
		this.left = new Edge(this, node, wall);
	}
	
	@Override
	public void addConnectionRight(BaseState node, boolean wall) {
		this.right = new Edge(this, node, wall);
	}
	
	@Override
	public boolean isTopWall() {
		if (getY() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBottomWall() {
		if (down == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLeftWall() {
		if (getX() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isRightWall() {
		if (right == null) {
			return true;
		}
		return false;
	}
}
