import java.awt.Image;
import java.util.Observer;

public class Model {
	private Maze maze;
	private Player player; 
	private int difficulty = 16;
	private boolean isClassic = true;
	private final int MEDIUM = 24;
	private final int HARD = MEDIUM+8;
	
	public Model() {
		player = new SimplePlayer();
	}
	
	public void createMaze() {
		maze = new Maze(difficulty, isClassic);
		if (difficulty == MEDIUM) player.reset(3);
		else if (difficulty == HARD)player.reset(5);
		else player.reset(0);
	}

	public void updatePlayer(int dir) {
		player.move(dir, maze);
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

	public int[] getPlayerPos() {
		return player.getCoords();
	}

	public Maze getMaze() {
		return maze;
	}

	public void setClassicMode() {
		isClassic = true;
	}

	public void setCollectorMode() {
		isClassic = false;
	}
	
	public boolean isClassicMode () {
		return isClassic;
	}

	public Image getPlayerSprite() {
		return player.getSprite();
	}

	public boolean allCollected() {
		if (player.getNumLeft() == 0) return true;
		else return false;
	}

	public void setCollectableOberver(Observer collected) {
		if (isClassic) return;
		for (BaseState s: maze) {
			if (!((CollectableState) s).checkCollected()) ((CollectableState) s).addObserver(collected);
		}
	}
}