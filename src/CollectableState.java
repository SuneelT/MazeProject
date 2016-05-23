import java.awt.image.BufferedImage;

public class CollectableState extends StateDecorator {
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
}
