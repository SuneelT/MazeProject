import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
		        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);
        
        JRadioButton easy = new JRadioButton("Easy", true);
		easy.setBackground(Color.GREEN);
	    easy.setForeground(Color.WHITE);
	    easy.setFocusPainted(false);
	    easy.setFont(new Font("Ariel", Font.BOLD, 50));

        difficulty.add(easy, c);
	    easy.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getModel().setDifficulty(EASY);
		}
		});
	    
	    JRadioButton medium = new JRadioButton("Medium");
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
            }
		});
	    
	    JRadioButton hard = new JRadioButton("Hard");
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
                }
		});
	    ButtonGroup difficultyButtons = new ButtonGroup();
	    difficultyButtons.add(easy);
	    difficultyButtons.add(medium);
	    difficultyButtons.add(hard);

	    JButton returnButton = new JButton("Return");
		returnButton.setBackground(Color.WHITE);
	    returnButton.setForeground(Color.BLACK);
	    returnButton.setFocusPainted(false);
	    returnButton.setFont(new Font("Ariel", Font.BOLD, 25));
	    returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {   
	            if(JOptionPane.showConfirmDialog(gui, "Are you sure you want to discard Settings?", 
	 				   "Exit", JOptionPane.YES_NO_OPTION, 
	 				   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	 					getGUI().switchToLastScreen();
	 				}
            }
		});
		returnToMaze.add(returnButton);

		JButton newGame = new JButton("New Game");
		newGame.setBackground(Color.WHITE);
		newGame.setForeground(Color.BLACK);
	    newGame.setFocusPainted(false);
	    newGame.setFont(new Font("Ariel", Font.BOLD, 25));
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getModel().createMaze();
				getModel().resetPlayer();
				getGUI().switchScreen("Maze");
			}
		});
		
        c.gridx = 0;
        c.gridy = 0;
		this.add(difficulty, c);
        c.gridx = 1;
		this.add(returnToMaze, c);
		this.add(newGame);
	}
}
