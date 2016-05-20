import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MazePanel extends JPanel implements Observer {
	private static final long serialVersionUID = -3329021623371321857L;
	private GUI gui;
		
	public MazePanel(GUI g) {
		gui = g;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gui.getModel().updatePlayer(e.getKeyCode());
			}
		});
		setBorder(BorderFactory.createLineBorder(Color.red));
		setFocusable(true);
	}
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		requestFocusInWindow();
		int[] pos = gui.getModel().getPlayerPos();
		int intervalx = getWidth()/gui.getModel().getDifficulty();
		int intervaly = getHeight()/gui.getModel().getDifficulty();
		for (State s: gui.getModel().getMaze()) {
			if (s.getLeft() == null && s.getUp() == null)
				try {
					g.drawImage(ImageIO.read(new File("images/corner_top_left.bmp")), s.getX()*intervalx, s.getY()*intervaly, intervalx, intervaly, null);
				} catch (IOException e) {}
			/*if (s.getDown() == null) 
				g.drawLine(s.getX()*intervalx, (s.getY()+1)*intervaly, (s.getX()+1)*intervalx, (s.getY()+1)*intervaly);
			if (s.getRight() == null)
				g.drawLine((s.getX()+1)*intervalx, s.getY()*intervaly, (s.getX()+1)*intervalx, (s.getY()+1)*intervaly);*/
		}
		g.setColor(Color.black);
		g.drawOval(pos[0]*intervalx, pos[1]*intervaly, intervalx, intervaly);
		}
		
	@Override
	public void update(Observable observable, Object object) {
		if (object.equals(new Boolean(true))) gui.switchScreen("Menu");
		else repaint();
	}
}
