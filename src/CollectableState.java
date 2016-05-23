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
		this.item = null;
	}
	
	public boolean checkCollected() {
		return (item == null);
	}
	
	public BufferedImage getCollectableSprite() {
		if (item == null) return null;
		else return item.getCollectedImage();
	}

	public void signal() {
		setChanged();
		notifyObservers("Create");
	}

	public BufferedImage getUncollectedSprite() {
		return item.getBWImage();
	}
}
