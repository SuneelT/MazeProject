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
	
	public void takeCollectable() {
		this.item = null;
	}
	
	public boolean checkCollected() {
		return (item == null);
	}

}
