import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MazePanel extends JPanel implements Observer {
	private static final long serialVersionUID = -3329021623371321857L;
	private GUI gui;
		
	public MazePanel(GUI g) {
		gui = g;
		this.setPreferredSize(new Dimension(672, 672));
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gui.getModel().updatePlayer(e.getKeyCode());
			}
		});
		setBackground(Color.white);
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
		g.setColor(Color.red);
		for (State s: gui.getModel().getMaze()) {
			if (s.getDown() == null) 
				g.drawLine(s.getX()*intervalx, (s.getY()+1)*intervaly, (s.getX()+1)*intervalx, (s.getY()+1)*intervaly);
			if (s.getRight() == null)
				g.drawLine((s.getX()+1)*intervalx, s.getY()*intervaly, (s.getX()+1)*intervalx, (s.getY()+1)*intervaly);
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
