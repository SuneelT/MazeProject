
public class StateDecorator implements BaseState{
	private State state;
	
	public StateDecorator(State state) {
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
	public State getUp() {
		return state.getUp();
	}

	@Override
	public State getDown() {
		return state.getDown();
	}

	@Override
	public State getLeft() {
		return state.getLeft();
	}

	@Override
	public State getRight() {
		return state.getRight();
	}

	@Override
	public Edge getEdge(String edge) {
		return state.getEdge(edge);
	}

	@Override
	public void addConnectionUp(State node, boolean wall) {
		state.addConnectionUp(node, wall);
	}

	@Override
	public void addConnectionDown(State node, boolean wall) {
		state.addConnectionDown(node, wall);
	}

	@Override
	public void addConnectionLeft(State node, boolean wall) {
		state.addConnectionLeft(node, wall);
	}

	@Override
	public void addConnectionRight(State node, boolean wall) {
		state.addConnectionRight(node, wall);
	}

}
