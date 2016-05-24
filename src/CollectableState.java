import java.awt.image.BufferedImage;

public class CollectableState extends StateDecorator implements Comparable<CollectableState> {
	private Collectable item;
	
	public CollectableState(BaseState state) {
		super(state);
		item = null;
	}
	 
	public void addCollectable (Collectable item) {
		this.item = item;
	}

	public Collectable getCollectable () {
		return item;
	}
	
	public void collect() {
		item.collect();
		setChanged();
		notifyObservers("Collect");
	}
	
	public boolean checkCollected() {
		if (item == null) return true;
		return item.returnStatus();
	}
	
	public BufferedImage getCollectedSprite() {
		return item.getCollectedImage();
	}

	public void signal(String sig) {
		setChanged();
		notifyObservers(sig);
	}

	public BufferedImage getUncollectedSprite() {
		return item.getBWImage();
	}

	@Override
	public int compareTo(CollectableState arg0) {
		int compare = arg0.getCollectable().getIndex();
		int thisIndex = getCollectable().getIndex();
		if (thisIndex < compare) return -1;
		else if (thisIndex == compare) return 0;
		else return 1;
	}
	
	public String toString() {
		return ""+getCollectable().getIndex();
	}
}
