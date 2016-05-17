import java.awt.Graphics;
import java.util.Observer;

public class Model {
	private Maze maze;
	private Player player;
	private int difficulty = 15;
	
	public Model() {
		player = new SimplePlayer();
	}
	
	public void createMaze() {
		player.reset();
		maze = new Maze(difficulty); 
	}

	public void updatePlayer(int dir) {
		player.move(dir, maze);
	}

	public void drawMaze(Graphics g, int width, int height) {
		maze.draw(g, width, height);
	}

	public void drawPlayer(Graphics g, int width, int height) {
		player.draw(g, width/maze.getSize(), height/maze.getSize());
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void setPlayerObserver(Observer o) {
		player.addObserver(o);
	}
}