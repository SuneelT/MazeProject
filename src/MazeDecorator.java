import java.awt.Graphics;

abstract class MazeDecorator implements Maze{
	protected Maze maze;
	
	public MazeDecorator(Maze newmaze) {
		this.maze = newmaze;
	}

	@Override
	public void draw(Graphics g, int width, int height) {
		maze.draw(g, width, height);
	}

	@Override
	public int getSize() {
		return maze.getSize();
	}

	@Override
	public boolean isConnected(int x, int y, int dir) {
		return maze.isConnected(x, y, dir);
	}
	
	@Override
	public void resetNode () {
		maze.resetNode();
	}
	
	@Override
	public boolean hasNext () {
		return maze.hasNext();
	}
	
	@Override
	public void setNext() {
		maze.setNext();
	}
	
}
