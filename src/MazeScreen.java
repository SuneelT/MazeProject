import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	JPanel mazePanel;
	
	public MazeScreen(final GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		mazePanel = new MazePanel();
		addMazeComponent((Observer)mazePanel);
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel otherControls = new JPanel(new GridLayout(2, 1, 100, 100));
		add(otherControls, BorderLayout.EAST);
		otherControls.setBackground(Color.WHITE);
		
		JButton pauseButton = new JButton("Pause Game");
		pauseButton.setBackground(Color.WHITE);
	    pauseButton.setForeground(Color.BLACK);
	    pauseButton.setFocusPainted(false);
	    pauseButton.setFont(new Font("Ariel", Font.BOLD, 20));
		pauseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Pause");
			}
		});
		
		JButton exitButton = new JButton("Exit To Menu");
		exitButton.setBackground(Color.WHITE);
	    exitButton.setForeground(Color.BLACK);
	    exitButton.setFocusPainted(false);
	    exitButton.setFont(new Font("Ariel", Font.BOLD, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(gui, "Are you sure you want to exit?", 
				   "Exit", JOptionPane.YES_NO_OPTION, 
				   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
				   getGUI().switchScreen("Menu");
				}
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
			setBorder(BorderFactory.createLineBorder(Color.red));
			setFocusable(true);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			requestFocusInWindow();
			getModel().drawMaze(g, getWidth(), getHeight());
			getModel().drawPlayer(g, getWidth(), getHeight());
		}
		
		@Override
	    public void update(Observable observable, Object object) {
			repaint();
	    }
	}
}
