import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	private JPanel mazePanel = new MazePanel();

	public MazeScreen(GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		add(mazePanel, BorderLayout.CENTER);
		JPanel otherControls = new JPanel(new GridLayout(2, 1, 100, 100));
		add(otherControls, BorderLayout.EAST);
		
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
	

}
