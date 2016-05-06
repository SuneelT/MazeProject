public class Player {
	int x;
	int y;
	Node current;
	
	public Player(int xStart, int yStart, Node startingNode) {
		this.x = xStart;
		this.y = yStart;
		this.current = startingNode;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void move(int direction) {
		if (direction == 0) this.x ++;
		if (direction == 1) this.x --;
		if (direction == 2) this.y ++;
		if (direction == 3) this.y --;
	}
	
	public void move(String direction) {
		direction.toLowerCase();
		
		if (!current.edgeExists(direction)) {
			System.out.println("you ran into a wall");
			return;
		}
		
		if (direction.equals("right")) {
			this.current = current.getRight();
			this.x ++;
		} else if (direction.equals("left")) {
			this.current = current.getLeft();
			this.x --;
		} else if (direction.equals("up")) {
			this.current = current.getUp();
			this.y ++;
		} else if (direction.equals("down")) {
			this.current = current.getDown();
			this.y --;
		}
	}
}
