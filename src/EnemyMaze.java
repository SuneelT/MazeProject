import java.util.ArrayList;

public class EnemyMaze extends MazeDecorator {
	private ArrayList<Enemy> enemies;
	
	public EnemyMaze(Maze newmaze) {
		super(newmaze);
		enemies = new ArrayList<Enemy>();
		
		int length = newmaze.getSize(); int difficulty = 0;
		switch (length) {
		case 15: difficulty = 0; break;
		case 25: difficulty = 1; break;
		case 35: difficulty = 2; break;
		}
		
		int i = 0; int j = 0;
		if (difficulty == 1) {i = 1;} 
		else if (difficulty == 2) {i = 3;}
		
		while (j < i) {
			enemies.add(new Enemy(difficulty));
			j++;
		}
	}
	
	public void makeEnemyMove () {
		for (Enemy hostile: enemies) {
			hostile.randomMove();
		}
	}
	
}
