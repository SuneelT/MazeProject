import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuScreen extends Screen {
	private static final long serialVersionUID = -1725872337351590898L;

	public MenuScreen(GUI gui) {
		setGUI(gui);
		this.setLayout(new FlowLayout(FlowLayout.CENTER)); //one row, one column for now. in the future, change to 2 or 3 rows.
		this.setBackground(Color.PINK);
		JButton playButton = new JButton("Play");
		playButton.setBackground(Color.WHITE);
	    	playButton.setForeground(Color.BLACK);
	    	playButton.setFocusPainted(false);
	    	playButton.setFont(new Font("Ariel", Font.BOLD, 50));
		this.add(playButton);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().createMaze(25);
				getModel().resetPlayer();
				getGUI().switchScreen("Maze");
			}
		});
	}

}
