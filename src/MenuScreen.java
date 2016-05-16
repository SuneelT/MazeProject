import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuScreen extends Screen {
	private static final long serialVersionUID = -1725872337351590898L;
	private JButton playButton;
	private JButton helpButton;
	JButton settingsBtn;

	public MenuScreen(GUI gui) {
		setGUI(gui);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setBackground(new Color(255,204,255));

		playButton = new JButton("Play");
		playButton.setBackground(Color.WHITE);
		playButton.setForeground(Color.BLACK);
		playButton.setFocusPainted(false);
		playButton.setFont(new Font("Ariel", Font.BOLD, 50));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(20, 120, 20, 20);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getModel().createMaze();
				getModel().resetPlayer();
				getGUI().switchScreen("Maze");
			}
		});
		this.add(playButton, c);

		helpButton = new JButton("Help");
		helpButton.setBackground(Color.WHITE);
		helpButton.setForeground(Color.BLACK);
		helpButton.setFocusPainted(false);
		helpButton.setFont(new Font("Ariel", Font.BOLD, 50));
        c.gridx = 1;
        c.insets = new Insets(20, 20, 20, 120);
        helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Help");
			}
		});
		this.add(helpButton, c);
		
		settingsBtn = new JButton("Settings");
		settingsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				getGUI().switchScreen("Settings");
			}
		});
		this.add(settingsBtn);
	}

}
