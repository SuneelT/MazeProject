import java.awt.image.BufferedImage;
 
public class CollectableState extends StateDecorator implements Comparable<CollectableState> {
	private Collectable item;
	
	/**
	 * Constructor for the CollectableState Class
	 * Creates a new instance of CollectableState, which is used to represent a single point in 
	 * the maze which may contain a collectable. This class is implemented via the decorator pattern
	 * on the BaseState interface.
	 * @param state - The baseState already constructed to represent a point on the maze
	 * @postconditions The constructed object will decorate the BaseState to now include Collectable fields.
	 */
	public CollectableState(BaseState state) {
		super(state);
		item = null;
	}
	 
	/**
	 * Adds a constructed collectable into a given CollectableState 
	 * The collectable-state will now contain a reference to the collectable which it contains.
	 * @param item - The collectable to be added to the state
	 */
	public void addCollectable (Collectable item) {
		this.item = item;
	}

	/**
	 * Returns the collectable stored in a given CollectableState
	 * @return The collectable stored in a CollectableState
	 */
	public Collectable getCollectable () {
		return item;
	}
	
	/**
	 * Collects an item from it's location on the map
	 * The collectable stored in a given state will be marked as collected. This change will be notified
	 * to all relevant observers for purposes of redrawing the maze without the collectable sprite. The
	 * collectableState will now contain an inactive collectable.
	 */
	public void collect() {
		item.collect();
		setChanged();
		notifyObservers("Collect");
	}
	
	/**
	 * Checks if a collectable in a given location has already been collected
	 * @return A boolean signifying the state of a collectable. If the collectable is collected, true will be returned.
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
	 * Notifies observers of any changes in the CollectableState
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
	 * Compares two CollectableStates to one another.
	 * Compares two CollectableStates to one another and returns a positive number if this CollectableState
	 * is larger than the given CollectableState. If the two states are equal, 0 is returned. If this CollectableState
	 * is smaller than the given state, then a negative number will be returned. This is used to implement Comparable in
	 * CollectedState and allow for sorting. The sorting key is based on the index number of the Collectable object.
	 */
	@Override
	public int compareTo(CollectableState arg0) {
		int compare = arg0.getCollectable().getIndex();
		int thisIndex = getCollectable().getIndex();
		if (thisIndex < compare) return -1;
		else if (thisIndex == compare) return 0;
		else return 1;
	}
}
