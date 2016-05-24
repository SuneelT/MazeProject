import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazePanel extends JPanel implements Observer {
	private static final long serialVersionUID = -3329021623371321857L;
	private GUI gui;
	private BufferedImage mazeImage;
	private BufferedImage end;
		
	public MazePanel(GUI guii) {
		this.gui = guii;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gui.getModel().updatePlayer(e.getKeyCode());
			}
		});
		this.setOpaque(false);
		this.setFocusable(true);
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocusInWindow();
		g.drawImage(mazeImage, 0, 0, null);
		int difficulty = gui.getModel().getDifficulty();
		int w = getWidth()/difficulty; int h = getHeight()/difficulty;
		int[] pos = gui.getModel().getPlayerPos();
		if (!gui.getModel().isClassicMode()) {
			for (BaseState s: gui.getModel().getMaze()) {
				CollectableState cState = (CollectableState) s;
				if (!cState.checkCollected())
					g.drawImage(cState.getCollectedSprite(), cState.getX()*w, cState.getY()*h, w, h, null);
			}
			if (!gui.getModel().allCollected()) g.drawImage(this.end, (difficulty-1)*w, (difficulty-1)*h, w, h, null);
		}
		g.drawImage(gui.getModel().getPlayerSprite(), pos[0]*w, pos[1]*h, w, h, null);
	}
		
	@Override
	public void update(Observable observable, Object object) {
		if (object.equals(true)) gui.switchScreen("Win");
		else if (object.equals("Create")) makeMaze();
		else repaint();
	}

	private void makeMaze() {
		try {
			this.end = ImageIO.read(new File("images/tiles/end_locked.png"));
			int w = getWidth()/gui.getModel().getDifficulty();
			int h = getHeight()/gui.getModel().getDifficulty();
			int difficulty = gui.getModel().getDifficulty();
			mazeImage = new BufferedImage(difficulty*(getWidth()/difficulty), difficulty*(getHeight()/difficulty), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = mazeImage.createGraphics();
			String tile = "";
			for (BaseState s: gui.getModel().getMaze()) {
				int x = s.getX(); int y = s.getY();
				
				// check walls
				int numSides = 0;
				boolean rightWall = false;
				boolean leftWall = false;
				boolean topWall = false;
				boolean bottomWall = false;
				if (s.getRight() == null) rightWall = true;
				if (s.getLeft() == null) leftWall = true;
				if (s.getUp() == null) topWall = true;
				if (s.getDown() == null) bottomWall = true;
				if (topWall) numSides++;
				if (leftWall) numSides++;
				if (bottomWall) numSides++;
				if (rightWall) numSides++;
				
				// find tile type
				if (numSides == 3) { // dead end
					if (!rightWall) tile = "images/tiles/deadend_left.bmp";
					else if (!leftWall) tile = "images/tiles/deadend_right.bmp";
					else if (!topWall) tile = "images/tiles/deadend_bottom.bmp";
					else if (!bottomWall) tile = "images/tiles/deadend_top.bmp";
				} else if (numSides == 2) { // corner or hall
					//hall
					if (rightWall && leftWall) tile = "images/tiles/hall_vertical.bmp";
					else if (topWall && bottomWall) tile = "images/tiles/hall_horizontal.bmp";
					//corner
					else if (topWall && leftWall) tile = "images/tiles/corner_top_left.bmp";
					else if (topWall && rightWall) tile = "images/tiles/corner_top_right.bmp";
					else if (bottomWall && leftWall) tile = "images/tiles/corner_bottom_left.bmp";
					else if (bottomWall && rightWall) tile = "images/tiles/corner_bottom_right.bmp";
				} else if (numSides == 1) { // single side
					if (leftWall) tile = "images/tiles/wall_left.bmp";
					else if (rightWall) tile = "images/tiles/wall_right.bmp";
					else if (topWall) tile = "images/tiles/wall_top.bmp";
					else if (bottomWall) tile = "images/tiles/wall_bottom.bmp";
				} else { // open
					tile = "images/tiles/open.bmp";
				}
				// draw tile
				g.drawImage(ImageIO.read(new File(tile)), x*w, y*h, w, h, null);
				
				// check for ending and draw if need be
				if (s.getX() == gui.getModel().getDifficulty() - 1 && s.getY() == gui.getModel().getDifficulty() - 1) {
					g.drawImage(ImageIO.read(new File("images/tiles/end_unlocked.png")), x*w, y*h, w, h, null);
				}
			}
		} catch(IOException e) {System.err.println("Images not found.");}
	}
}
