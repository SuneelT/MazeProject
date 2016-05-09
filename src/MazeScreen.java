import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;

	public MazeScreen(GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		JPanel mazePanel = new MazePanel();
		mazePanel.setFocusable(true); mazePanel.requestFocusInWindow();
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
	
	private class MazePanel extends JPanel implements Observer {
		private static final long serialVersionUID = -3329021623371321857L;
		
		public MazePanel() {
		this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					getModel().updatePlayer(e.getKeyCode());
				}
			});
			setBackground(Color.white);
			setBorder(BorderFactory.createLineBorder(Color.black));
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			getModel().drawMaze(g, getWidth(), getHeight());
			getModel().drawPlayer(g, getWidth(), getHeight());
		}
		
		@Override
	    public void update(Observable observable, Object object) {
			repaint();
	    }
	}
}
