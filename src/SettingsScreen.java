import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsScreen extends Screen{
	private static final long serialVersionUID = -4212692790730740664L;
	private final int EASY = 15;
	private final int MEDIUM = 25;
	private final int HARD = 35;

	public SettingsScreen(final GUI gui) {
		setGUI(gui);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setBackground(new Color(255,204,255));
		JPanel difficulty = new JPanel();
        difficulty.setBackground(new Color(255,204,255));
		JPanel returnToMaze = new JPanel();
        returnToMaze.setBackground(new Color(255,204,255));

        difficulty.setLayout(new GridBagLayout());
		JButton easy = new JButton("Easy");
		easy.setBackground(Color.GREEN);
	    easy.setForeground(Color.WHITE);
	    easy.setFocusPainted(false);
	    easy.setFont(new Font("Ariel", Font.BOLD, 50));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);
        difficulty.add(easy, c);
	    easy.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getModel().setDifficulty(EASY);
			getGUI().switchToLastScreen();
			}
		});
	    
	    JButton medium = new JButton("Medium");
		medium.setBackground(Color.ORANGE);
	    medium.setForeground(Color.WHITE);
	    medium.setFocusPainted(false);
	    medium.setFont(new Font("Ariel", Font.BOLD, 50));
        c.gridy = 1;
        difficulty.add(medium, c);
	    medium.addMouseListener(new MouseAdapter() {
		@Override
            public void mouseClicked(MouseEvent e) {
                getModel().setDifficulty(MEDIUM);
                getGUI().switchToLastScreen();
                }
		});
	    
	    JButton hard = new JButton("Hard");
		hard.setBackground(Color.RED);
	    hard.setForeground(Color.WHITE);
	    hard.setFocusPainted(false);
	    hard.setFont(new Font("Ariel", Font.BOLD, 50));
        c.gridy = 2;
        difficulty.add(hard, c);
	    hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	getModel().setDifficulty(HARD);
                getGUI().switchToLastScreen();
                }
		});

	    JButton saveButton = new JButton("Return");
		saveButton.setBackground(Color.WHITE);
	    saveButton.setForeground(Color.BLACK);
	    saveButton.setFocusPainted(false);
	    saveButton.setFont(new Font("Ariel", Font.BOLD, 50));
	    saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getGUI().switchToLastScreen();
                }
		});

		returnToMaze.add(saveButton);

        c.gridx = 0;
        c.gridy = 0;
		this.add(difficulty, c);
        c.gridx = 1;
		this.add(returnToMaze, c);
	}
}
