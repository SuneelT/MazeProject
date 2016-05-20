import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MazePanel extends JPanel implements Observer {
	private static final long serialVersionUID = -3329021623371321857L;
	private GUI gui;
	private List<BufferedImage> images = new ArrayList<BufferedImage>();
	private final int TL_CORNER = 0; private final int BL_CORNER = 1;
	private final int TR_CORNER = 2; private final int BR_CORNER = 3;
	private final int WB = 4; private final int WL = 5;
	private final int WT = 6; private final int WR = 7;
	private final int OPEN = 8; private final int DB = 9;
	private final int DR = 10; private final int DT = 11;
	private final int DL = 12; private final int HV = 13;
	private final int HH = 14; 
		
	public MazePanel(GUI g) {
		gui = g;
		try {
			images.add(ImageIO.read(new File("images/corner_top_left.bmp")));
			images.add(ImageIO.read(new File("images/corner_bottom_left.bmp")));
			images.add(ImageIO.read(new File("images/corner_top_right.bmp")));
			images.add(ImageIO.read(new File("images/corner_bottom_right.bmp")));
			images.add(ImageIO.read(new File("images/wall_bottom.bmp")));
			images.add(ImageIO.read(new File("images/wall_left.bmp")));
			images.add(ImageIO.read(new File("images/wall_top.bmp")));
			images.add(ImageIO.read(new File("images/wall_right.bmp")));
			images.add(ImageIO.read(new File("images/open.bmp")));
			images.add(ImageIO.read(new File("images/deadend_bottom.bmp")));
			images.add(ImageIO.read(new File("images/deadend_right.bmp")));
			images.add(ImageIO.read(new File("images/deadend_top.bmp")));
			images.add(ImageIO.read(new File("images/deadend_left.bmp")));
			images.add(ImageIO.read(new File("images/hall_vertical.bmp")));
			images.add(ImageIO.read(new File("images/hall_horizontal.bmp")));
		} catch (IOException e1) {System.err.println("Image file not found"); System.exit(1);}
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
		int w = getWidth()/gui.getModel().getDifficulty();
		int h = getHeight()/gui.getModel().getDifficulty();
		for (State s: gui.getModel().getMaze()) {
			int x = s.getX(); int y = s.getY();
			if (s.isLeftWall() && s.isTopWall()) {
				if (s.getRight() == null) g.drawImage(images.get(DT), x*w, y*h, w, h, null);
				else if (s.getDown() == null) g.drawImage(images.get(DL), x*w, y*h, w, h, null);
				else g.drawImage(images.get(TL_CORNER), x*w, y*h, w, h, null);
			}
			else if (s.isLeftWall()) {
				if (s.getRight() == null && s.getDown() == null) g.drawImage(images.get(DB), x*w, y*h, w, h, null);
				else if (s.getRight() == null) g.drawImage(images.get(HV), x*w, y*h, w, h, null);
				else if (s.getDown() == null) g.drawImage(images.get(BL_CORNER), x*w, y*h, w, h, null);
				else g.drawImage(images.get(WL), x*w, y*h, w, h, null);
			} else if (s.isTopWall()) {
				if (s.getRight() == null && s.getDown() == null) g.drawImage(images.get(DR), x*w, y*h, w, h, null);
				else if (s.getRight() == null) g.drawImage(images.get(TR_CORNER), x*w, y*h, w, h, null);
				else if (s.getDown() == null) g.drawImage(images.get(HH), x*w, y*h, w, h, null);
				else g.drawImage(images.get(WT), x*w, y*h, w, h, null);
			} else {
				if (s.getRight() == null && s.getDown() == null) g.drawImage(images.get(BR_CORNER), x*w, y*h, w, h, null);
				else if (s.getRight() == null) g.drawImage(images.get(WR), x*w, y*h, w, h, null);
				else if (s.getDown() == null) g.drawImage(images.get(WB), x*w, y*h, w, h, null);
				else g.drawImage(images.get(OPEN), x*w, y*h, w, h, null);
			}
		}
		int[] pos = gui.getModel().getPlayerPos();
		g.setColor(Color.black);
		g.drawOval(pos[0]*w, pos[1]*h, w, h);
		}
		
	@Override
	public void update(Observable observable, Object object) {
		if (object.equals(new Boolean(true))) gui.switchScreen("Menu");
		else repaint();
	}
}
