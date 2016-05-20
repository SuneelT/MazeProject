import java.awt.Color;
import java.awt.Dimension;
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
		this.setPreferredSize(new Dimension(656, 656));
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocusInWindow();
		g.drawImage(bImage, 0, 0, null);
		int w = getWidth()/gui.getModel().getDifficulty();
		int h = getHeight()/gui.getModel().getDifficulty();
		int[] pos = gui.getModel().getPlayerPos();
		g.setColor(Color.black);
		g.drawOval(pos[0]*w, pos[1]*h, w, h);
	}
		
	@Override
	public void update(Observable observable, Object object) {
		if (object.equals(new Boolean(true))) gui.switchScreen("Menu");
		else if (object.equals(new String("Create"))) makeMaze();
		else repaint();
	}

	private void makeMaze() {
		try {
			int w = getWidth()/gui.getModel().getDifficulty();
			int h = getHeight()/gui.getModel().getDifficulty();
			boolean mode = gui.getModel().isClassicMode();
			bImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
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
					if (s.getRight() == null && s.getDown() == null) g.drawImage(ImageIO.read(new File("images/corner_bottom_right.bmp")), x*w, y*h, w, h, null);
					else if (s.getRight() == null) g.drawImage(ImageIO.read(new File("images/wall_right.bmp")), x*w, y*h, w, h, null);
					else if (s.getDown() == null) g.drawImage(ImageIO.read(new File("images/wall_bottom.bmp")), x*w, y*h, w, h, null);
					else g.drawImage(ImageIO.read(new File("images/open.bmp")), x*w, y*h, w, h, null);
				}
				if (mode == false) {
					CollectableState cstate = (CollectableState) s;
					if (cstate.checkCollected() == false) {
						g.drawImage(cstate.getCollectable().getImage(), x*w, y*h, w, h, null);
					}
				}
			}
		} catch(IOException e) {System.err.println("Images not found.");}
	}
}
