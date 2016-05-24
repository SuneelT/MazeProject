import java.awt.Image;
import java.util.Observer;

public class Model {
	private Maze maze;
	private Player player; 
	private boolean isClassic = true;
	private final int EASY = 16;
	private final int MEDIUM = EASY+8;
	private int difficulty = EASY;
	private Observer collectorObserver;
	
	public Model() {
		player = new SimplePlayer();
	}
	
	public void createMaze() {
		maze = new Maze(difficulty, isClassic);
		player.reset(0);
		((CollectedPanel) collectorObserver).reset();
		if (isClassic) return;
		int numCollectables;
		if (difficulty == EASY) numCollectables = 1;
		else if (difficulty == MEDIUM) numCollectables = 3;
		else numCollectables = 5;
		player.reset(numCollectables);
		for (BaseState s: maze) {
			if (!((CollectableState) s).checkCollected()) {
				((CollectableState) s).addObserver(this.collectorObserver);
				((CollectableState) s).signal("Create");
				numCollectables--;
			}
			if (numCollectables == 0) ((CollectableState) s).signal("Done");
		}
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
		this.collectorObserver = collected;
	}
}