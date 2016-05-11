import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class SettingsScreen extends Screen{
	private static final long serialVersionUID = -4212692790730740664L;

	public SettingsScreen(final GUI gui) {
		setGUI(gui);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(Color.WHITE);
		JPanel difficulty = new JPanel();
		JPanel theme = new JPanel();
		JPanel returnToMaze = new JPanel();
		
		// change difficulty
		JCheckBox easy = new JCheckBox("Easy");
		easy.setBackground(Color.GREEN);
	    easy.setForeground(Color.WHITE);
	    easy.setFocusPainted(false);
	    easy.setFont(new Font("Ariel", Font.BOLD, 25));
	    easy.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getGUI().switchScreen("Maze");
			}
		});
	    
	    JCheckBox medium = new JCheckBox("Medium");
		medium.setBackground(Color.ORANGE);
	    medium.setForeground(Color.WHITE);
	    medium.setFocusPainted(false);
	    medium.setFont(new Font("Ariel", Font.BOLD, 25));
	    medium.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getGUI().switchScreen("Maze");
			}
		});
	    
	    JCheckBox hard = new JCheckBox("HARD");
		hard.setBackground(Color.RED);
	    hard.setForeground(Color.WHITE);
	    hard.setFocusPainted(false);
	    hard.setFont(new Font("Ariel", Font.BOLD, 25));
	    hard.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getGUI().switchScreen("Maze");
			}
		});
		
		// change theme - colours etc
	    
	    
	    // Save and new game
	    JButton saveButton = new JButton("New Game");
		saveButton.setBackground(Color.WHITE);
	    saveButton.setForeground(Color.BLACK);
	    saveButton.setFocusPainted(false);
	    saveButton.setFont(new Font("Ariel", Font.BOLD, 50));
	    saveButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getGUI().createMaze(25);
			getModel().resetPlayer();
			getGUI().switchScreen("Maze");
			}
		});

		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		returnToMaze.add(saveButton);
		returnToMaze.add(continueButton);
		
		this.add(difficulty);
		this.add(returnToMaze);
	}
}
