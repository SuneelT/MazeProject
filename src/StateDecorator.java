import java.util.Observable;

public abstract class StateDecorator extends Observable implements BaseState{
	private BaseState state; 
	
	public StateDecorator(BaseState state) {
		this.state = state;
	}
	
	@Override
	public int getX() {
		return state.getX();
	}

	@Override
	public int getY() {
		return state.getY();
	}

	@Override
	public boolean getPlayer() {
		return state.getPlayer();
	}

	@Override
	public void movePlayer() {
		state.movePlayer();
	}

	@Override
	public boolean edgeExists(String edge) {
		return state.edgeExists(edge);
	}

	@Override
	public BaseState getUp() {
		return state.getUp();
	}

	@Override
	public BaseState getDown() {
		return state.getDown();
	}

	@Override
	public BaseState getLeft() {
		return state.getLeft();
	}

	@Override
	public BaseState getRight() {
		return state.getRight();
	}

	@Override
	public Edge getEdge(String edge) {
		return state.getEdge(edge);
	}

	@Override
	public void addConnectionUp(BaseState node, boolean wall) {
		state.addConnectionUp(node, wall);
	}

	@Override
	public void addConnectionDown(BaseState node, boolean wall) {
		state.addConnectionDown(node, wall);
	}

	@Override
	public void addConnectionLeft(BaseState node, boolean wall) {
		state.addConnectionLeft(node, wall);
	}

	@Override
	public void addConnectionRight(BaseState node, boolean wall) {
		state.addConnectionRight(node, wall);
	}
	
	public boolean isTopWall() {
		return state.isTopWall();
	}

	@Override
	public boolean isBottomWall() {
		return state.isBottomWall();
	}

	@Override
	public boolean isLeftWall() {
		return state.isLeftWall();
	}

	@Override
	public boolean isRightWall() {
		return state.isRightWall();
	}

}
