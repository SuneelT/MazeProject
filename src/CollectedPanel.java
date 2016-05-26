import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
 
import javax.swing.*;

public class CollectedPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -4222092480497835994L;
	private int nCollectibles = 0;
	private Hashtable<Observable, CollectedTile> collectibles = new Hashtable<Observable, CollectedTile>();
	private PriorityQueue<CollectibleState> tmpList = new PriorityQueue<CollectibleState>();
	
	/**
	 * Constructor for the CollectedPanel class
	 * Makes the CollectedPanel visible and sets it as opaque - allowing for the background to 
	 * show through. The drawing of the panel is completed in the update class.
	 */
	public CollectedPanel() {
		this.setVisible(false); this.setOpaque(false);
	}
	
	/**
	 * Updates the CollectedPanel with the new state of the game
	 * The function has three modes depending on the arg1 parameters.
	 * Create: The CollectedPanel is constructed with the correct number of rows to accommodate the number of collectibles
	 * Done: All relevant collectible sprites are drawn in the panel
	 * Collect: A collectibles' CollectedPanel sprite is updated to reflect that it has now been collected
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (!this.isVisible()) this.setVisible(true);
		CollectedTile tile;
		switch((String) arg1) {
			case "Create":
				this.setLayout(new GridLayout(++nCollectibles, 1));
				tmpList.add(((CollectibleState)arg0));
				break;
			case "Done":
				CollectibleState s;
				while(!tmpList.isEmpty()) {
					s = tmpList.poll();
					tile = new CollectedTile();
					tile.setSprite(s.getUncollectedSprite());
					collectibles.put((Observable) s, tile);
					this.add(tile);
				}
				break;
			case "Collect":
				tile = collectibles.get(arg0);
				tile.setSprite(((CollectibleState) arg0).getCollectedSprite());
				break;
		}
	}
	
	/**
	 * Resets the CollectedPanel so that it can be used.
	 * Resetting is defined to be the removal of all JComponents embedded in this panel, as well resetting of internal fields used to ensure
	 * proper formatting of the placement of the collectible sprites.
	 */
	public void reset() {
		nCollectibles = 0;
		this.removeAll();
		tmpList.clear();
	}
}
