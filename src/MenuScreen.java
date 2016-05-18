import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuScreen extends Screen {
	private static final long serialVersionUID = -1725872337351590898L;
	private JButton playButton;
	private JButton helpButton;
	JButton settingsBtn;
	private final int EASY = 16;
	private final int MEDIUM = 24;
	private final int HARD = 32;

	public MenuScreen(GUI gui) {
		setGUI(gui);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.setBackground(new Color(255,204,255));

		playButton = new JButton("Play");
		playButton.setBackground(Color.WHITE);
		playButton.setForeground(Color.BLACK);
		playButton.setFocusPainted(false);
		playButton.setFont(new Font("Ariel", Font.BOLD, 40));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(20, 120, 20, 20);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getModel().createMaze();
				getGUI().switchScreen("Maze");
			}
		});
		this.add(playButton, c);

		helpButton = new JButton("Help");
		helpButton.setBackground(Color.WHITE);
		helpButton.setForeground(Color.BLACK);
		helpButton.setFocusPainted(false);
		helpButton.setFont(new Font("Ariel", Font.BOLD, 40));
        c.gridx = 1;
        c.insets = new Insets(20, 20, 20, 120);
        helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getGUI().switchScreen("Help");
			}
		});
		this.add(helpButton, c);
		
		JPanel difficulty = new JPanel();
        difficulty.setBackground(new Color(255,204,255));
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
	    
	    this.add(difficulty);
	}
}
