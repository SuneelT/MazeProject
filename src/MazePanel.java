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
	private BufferedImage bImage;
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
		g.drawImage(bImage, 0, 0, null);
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
		if (object.equals(true)) gui.switchScreen("Menu");
		else if (object.equals("Create")) makeMaze();
		else repaint();
	}

	private void makeMaze() {
		try {
			this.end = ImageIO.read(new File("images/end_locked.bmp"));
			int w = getWidth()/gui.getModel().getDifficulty();
			int h = getHeight()/gui.getModel().getDifficulty();
			int difficulty = gui.getModel().getDifficulty();
			bImage = new BufferedImage(difficulty*(getWidth()/difficulty), difficulty*(getHeight()/difficulty), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bImage.createGraphics();
			for (BaseState s: gui.getModel().getMaze()) {
				int x = s.getX(); int y = s.getY();
				if (s.isLeftWall() && s.isTopWall()) {
					if (s.getRight() == null) g.drawImage(ImageIO.read(new File("images/deadend_top.bmp")), x*w, y*h, w, h, null);
					else if (s.getDown() == null) g.drawImage(ImageIO.read(new File("images/deadend_left.bmp")), x*w, y*h, w, h, null);
					else g.drawImage(ImageIO.read(new File("images/corner_top_left.bmp")), x*w, y*h, w, h, null);
				}
				else if (s.isLeftWall()) {
					if (s.getRight() == null && s.getDown() == null) g.drawImage(ImageIO.read(new File("images/deadend_bottom.bmp")), x*w, y*h, w, h, null);
					else if (s.getRight() == null) g.drawImage(ImageIO.read(new File("images/hall_vertical.bmp")), x*w, y*h, w, h, null);
					else if (s.getDown() == null) g.drawImage(ImageIO.read(new File("images/corner_bottom_left.bmp")), x*w, y*h, w, h, null);
					else g.drawImage(ImageIO.read(new File("images/wall_left.bmp")), x*w, y*h, w, h, null);
				} else if (s.isTopWall()) {
					if (s.getRight() == null && s.getDown() == null) g.drawImage(ImageIO.read(new File("images/deadend_right.bmp")), x*w, y*h, w, h, null);
					else if (s.getRight() == null) g.drawImage(ImageIO.read(new File("images/corner_top_right.bmp")), x*w, y*h, w, h, null);
					else if (s.getDown() == null) g.drawImage(ImageIO.read(new File("images/hall_horizontal.bmp")), x*w, y*h, w, h, null);
					else g.drawImage(ImageIO.read(new File("images/wall_top.bmp")), x*w, y*h, w, h, null);
				} else {
					if (s.getRight() == null && s.getDown() == null) {
						if (s.getX() == gui.getModel().getDifficulty() - 1 && s.getY() == gui.getModel().getDifficulty() - 1)
							g.drawImage(ImageIO.read(new File("images/end_unlocked.bmp")), x*w, y*h, w, h, null);
						else g.drawImage(ImageIO.read(new File("images/corner_bottom_right.bmp")), x*w, y*h, w, h, null);
					}
					else if (s.getRight() == null) g.drawImage(ImageIO.read(new File("images/wall_right.bmp")), x*w, y*h, w, h, null);
					else if (s.getDown() == null) g.drawImage(ImageIO.read(new File("images/wall_bottom.bmp")), x*w, y*h, w, h, null);
					else g.drawImage(ImageIO.read(new File("images/open.bmp")), x*w, y*h, w, h, null);
				}
			}
		} catch(IOException e) {System.err.println("Images not found.");}
	}
}
