
public class Edge {
	private BaseState from;
	private BaseState to;
	private boolean wall;
	
	public Edge(BaseState from, BaseState to, boolean wall) {
		this.from = from;
		this.to = to;
		this.wall = wall;
	} 
	
	public BaseState getTo() {
		return this.to;
	}
	
	public BaseState getFrom() {
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
