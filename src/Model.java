import java.awt.Graphics;
import java.util.List;
import java.util.Observer;

public class Model {
	private Maze maze;
	private Player player;
	
	public void createMaze(int mazeSize) {
		maze = new GraphMaze(mazeSize, mazeSize); 
		((SimplePlayer) player).setMaze(maze);
	}
	
	public void createPlayer(List<Observer> observers) {
		player = new SimplePlayer();
		for (Observer j: observers)
			((SimplePlayer) player).addObserver(j);
	}

	public void updatePlayer(int dir) {
		player.move(dir);
	}

	public void drawMaze(Graphics g, int width, int height) {
		maze.draw(g, width, height);
	}

	public void drawPlayer(Graphics g, int width, int height) {
		player.draw(g, width/maze.getSize(), height/maze.getSize());
	}
}