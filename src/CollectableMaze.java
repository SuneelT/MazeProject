import java.util.ArrayList;

public class CollectableMaze extends MazeDecorator {
	private ArrayList<Collectable> objectives;
	
	public CollectableMaze(Maze newmaze, int difficulty) {
		super(newmaze);
		objectives = new ArrayList<Collectable>();

		int i = 0; int j = 0;
		if (difficulty == 1) {
			i = 3;
		} else if (difficulty == 2) {
			i = 5;
		}
		
		while (j < i) {
			objectives.add(new Collectable(newmaze.getSize(), difficulty, j));
			j++;
		}
	}
	
	public void checkCollectable (int x, int y) {
		for (Collectable collect: objectives) {
			collect.possibleCollect(x, y);
		}
	}
	
	public boolean checkCollected() {
		for (Collectable collect: objectives) {
			if (collect.returnStatus() == false) {
				return false;
			}
		}
		return true;
	}
}
