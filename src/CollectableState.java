import java.awt.Image;

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
	
	public Image getCollectableSprite() {
		if (item == null) return null;
		else return item.getImage();
	}
}
