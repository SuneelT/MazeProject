import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JButton;

public class PauseScreen extends Screen {
	private static final long serialVersionUID = 3233877355262024159L;

	public PauseScreen(GUI gui) {
		setGUI(gui);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton returnButton = new JButton("Return To Game");
		returnButton.setBackground(Color.WHITE);
	    	returnButton.setForeground(Color.BLACK);
	    	returnButton.setFocusPainted(false);
	    	returnButton.setFont(new Font("Ariel", Font.BOLD, 50));
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Maze");
			}
		});
		add(returnButton);
	}
}
