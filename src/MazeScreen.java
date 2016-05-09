import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	private Maze maze;
	private Player player;

	public MazeScreen(GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		JPanel mazePanel = new MazePanel();
		addMazeComponent((Observer)mazePanel);
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel otherControls = new JPanel(new GridLayout(2, 1, 100, 100));
		add(otherControls, BorderLayout.EAST);
		otherControls.setBackground(Color.WHITE);
		
		JButton pauseButton = new JButton("Pause Game");
		pauseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Pause");
			}
		});
		
		JButton exitButton = new JButton("Exit To Menu");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Menu");
			}
		});
		otherControls.add(pauseButton); otherControls.add(exitButton);
	}
	
	public void setMaze(Maze m) {
		this.maze = m;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	private class MazePanel extends JPanel implements Observer {
		private static final long serialVersionUID = -3329021623371321857L;
		private boolean mazeDrawn = false;
		
		public MazePanel() {
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("rferg");
					getGUI().updateMaze(e.getKeyCode());
				}
			});
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			if (!mazeDrawn) {maze.draw(g, getWidth(), getHeight()); mazeDrawn = true;}
			player.draw(g, getWidth()/maze.getSize(), getHeight()/maze.getSize());
		}
		
		@Override
	    public void update(Observable observable, Object object) {
			repaint();
	    }
	}
}
