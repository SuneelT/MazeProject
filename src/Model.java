import java.awt.Image;
import java.util.Observer;

/**
 * The Model contains and manages the internal representation of the components of a maze game. This includes the
 * player and the maze itself. The Model is also responsible for creating a new maze of the specified difficulty and
 * mode (classic/collectible).
 */
public class Model {
	private Maze maze;
	private Player player; 
	private boolean isClassic = true;
	private final int EASY = 16;
	private final int MEDIUM = EASY+8;
	private int difficulty = EASY;
	private Observer collectorObserver;

	/**
	 * Constructor for a Model.
	 * Creates a new player.
	 */
	public Model() {
		player = new Player();
	}

	/**
	 * Creates a new maze of the specified difficulty and mode.
	 */
	public void createMaze() {
		maze = new Maze(difficulty, isClassic);
		player.reset(0);
		((CollectedPanel) collectorObserver).reset();
		if (isClassic) return;
		int numCollectibles;
		if (difficulty == EASY) numCollectibles = 1;
		else if (difficulty == MEDIUM) numCollectibles = 3;
		else numCollectibles = 5;
		player.reset(numCollectibles);
		for (BaseState s: maze) {
			if (!((CollectibleState) s).checkCollected()) {
				((CollectibleState) s).addObserver(this.collectorObserver);
				((CollectibleState) s).signal("Create");
				numCollectibles--;
			}
			if (numCollectibles == 0) ((CollectibleState) s).signal("Done");
		}
	}

	/**
	 * Moves the player one cell up, down, left or right.
	 * @param dir - The direction in which the player is to move
     */
	public void updatePlayer(int dir) {
		player.move(dir, maze);
	}

	/**
	 * Gets the difficulty setting of the maze.
	 * @return The difficulty of the maze
     */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets the difficulty for the maze.
	 * @param difficulty - Specified difficulty for the maze
     */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Puts an observer on the player.
	 * @param o - An observer
     */
	public void setPlayerObserver(Observer o) {
		player.addObserver(o);
	}

	/**
	 * Gets the x- and y-coordinates of the player within the maze in an array..
	 * @return The position of the player
     */
	public int[] getPlayerPos() {
		return player.getCoords();
	}

	/**
	 * Gets the maze.
	 * @return The maze
     */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the mode of the game to classic.
	 */
	public void setClassicMode() {
		isClassic = true;
	}

	/**
	 * Sets the mode of the game to collectible.
	 */
	public void setCollectorMode() {
		isClassic = false;
	}

	/**
	 * Gets whether the game is in classic or collectible mode.
	 * @return true if classic, false if collectible
     */
	public boolean isClassicMode () {
		return isClassic;
	}

	/**
	 * Gets the image of the player sprite.
	 * @return The player sprite
     */
	public Image getPlayerSprite() {
		return player.getSprite();
	}

	/**
	 * Gets whether or not the player has collected all the collectibles in the maze.
	 * @return true if all collectibles collected, false otherwise
     */
	public boolean allCollected() {
		if (player.getNumLeft() == 0) return true;
		else return false;
	}

	/**
	 * Puts an observer on the collectibles.
	 * @param collected - An observer
     */
	public void setCollectibleOberver(Observer collected) {
		this.collectorObserver = collected;
	}
}