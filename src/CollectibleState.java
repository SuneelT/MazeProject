import java.awt.image.BufferedImage;
 
public class CollectibleState extends StateDecorator implements Comparable<CollectibleState> {
	private Collectible item;
	
	/**
	 * Constructor for the CollectibleState Class
	 * Creates a new instance of CollectibleState, which is used to represent a single point in
	 * the maze which may contain a collectible. This class is implemented via the decorator pattern
	 * on the BaseState interface.
	 * @param state - The baseState already constructed to represent a point on the maze
	 * @postconditions The constructed object will decorate the BaseState to now include Collectible fields.
	 */
	public CollectibleState(BaseState state) {
		super(state);
		item = null;
	}
	 
	/**
	 * Adds a constructed collectible into a given CollectibleState
	 * The collectible-state will now contain a reference to the collectible which it contains.
	 * @param item - The collectible to be added to the state
	 */
	public void addCollectible (Collectible item) {
		this.item = item;
	}

	/**
	 * Returns the collectible stored in a given CollectibleState
	 * @return The collectible stored in a CollectibleState
	 */
	public Collectible getCollectible () {
		return item;
	}
	
	/**
	 * Collects an item from it's location on the map
	 * The collectible stored in a given state will be marked as collected. This change will be notified
	 * to all relevant observers for purposes of redrawing the maze without the collectible sprite. The
	 * collectibleState will now contain an inactive collectible.
	 */
	public void collect() {
		item.collect();
		setChanged();
		notifyObservers("Collect");
	}
	
	/**
	 * Checks if a collectible in a given location has already been collected
	 * @return A boolean signifying the state of a collectible. If the collectible is collected, true will be returned.
	 */
	public boolean checkCollected() {
		if (item == null) return true;
		return item.returnStatus();
	}
	
	/**
	 * Returns the sprite of a collected object in a given location
	 * @return The BufferedImage sprite of the collected object which is to be displayed in the maze.
	 */
	public BufferedImage getCollectedSprite() {
		return item.getCollectedImage();
	}

	/**
	 * Notifies observers of any changes in the CollectibleState
	 * @param sig - A string detailing the mode in which the observer function will run in. This can be "Collect", "Done" or "Create".
	 */
	public void signal(String sig) {
		setChanged();
		notifyObservers(sig);
	}

	/**
	 * Returns the sprite of an uncollected object in a given location
	 * @return The BufferedImage sprite of the uncollected object which is to be displayed in the mazePanel.
	 */
	public BufferedImage getUncollectedSprite() {
		return item.getBWImage();
	}

	/**
	 * Compares two CollectibleStates to one another.
	 * Compares two CollectibleStates to one another and returns a positive number if this CollectibleState
	 * is larger than the given CollectibleState. If the two states are equal, 0 is returned. If this CollectibleState
	 * is smaller than the given state, then a negative number will be returned. This is used to implement Comparable in
	 * CollectedState and allow for sorting. The sorting key is based on the index number of the Collectible object.
	 */
	@Override
	public int compareTo(CollectibleState arg0) {
		int compare = arg0.getCollectible().getIndex();
		int thisIndex = getCollectible().getIndex();
		if (thisIndex < compare) return -1;
		else if (thisIndex == compare) return 0;
		else return 1;
	}
}
