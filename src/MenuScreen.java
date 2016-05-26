import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The MenuScreen is one of the game's screens. It contains easy, medium and hard difficulty selection buttons for the
 * maze game as well as an optional collectible mode button that puts items in the maze that the user must collect
 * before exiting the maze. There is a play button that takes the user to a maze game of the specified difficulty and
 * collectible mode as well as a help button that takes the user to the instructions.
 */
public class MenuScreen extends JPanel {
	private static final long serialVersionUID = -1725872337351590898L;
	private JButton playButton;
	private JButton helpButton;
	private final int EASY = 16; 
	private final int MEDIUM = 24;
	private final int HARD = 32;
	private Image bg;
	private boolean classic = true;

	/**
	 * Constructor for a MenuScreen.
	 * A MenuScreen consists of a play button, a help button, easy/medium/hard difficulty selections and a collectible
	 * mode toggle button.
	 * @param gui - A reference to the parent GUI class to allow for the switching of screens
     */
	public MenuScreen(final GUI gui) {
		this.classic = true;
		this.setLayout(new BorderLayout());
		try {
			bg = ImageIO.read(new File("images/blueBG.jpg"));
		} catch (IOException e1) {}
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		playButton = new JButton(new ImageIcon("images/buttonplay.png"));
		playButton.setBorder(BorderFactory.createEmptyBorder());
		playButton.setContentAreaFilled(false);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.insets = new Insets(0, 10, 20,0);
		playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.getModel().createMaze();
				gui.switchScreen("Maze");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(new ImageIcon("images/buttonplayfilled.png"));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				playButton.setIcon(new ImageIcon("images/buttonplay.png"));

			}
		});
		this.add(playButton, c);

		helpButton = new JButton(new ImageIcon("images/buttonhelp.png"));
		helpButton.setBorder(BorderFactory.createEmptyBorder());
		helpButton.setContentAreaFilled(false);
		helpButton.setFocusPainted(false);
        c.gridx = 1;
        c.insets = new Insets(0, 20, 20, 0);
        helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.switchScreen("Help");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				helpButton.setIcon(new ImageIcon("images/buttonhelpfilled.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				helpButton.setIcon(new ImageIcon("images/buttonhelp.png"));
			}
		});
        this.add(helpButton, c);
		
		JPanel difficulty = new JPanel();
        difficulty.setOpaque(false);
		difficulty.setLayout(new GridBagLayout());
		c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);

        final JButton easy = new JButton(new ImageIcon("images/easyselect.png"));
        easy.setBorder(BorderFactory.createEmptyBorder());
		easy.setContentAreaFilled(false);
		easy.setRolloverEnabled(true);
		easy.setRolloverIcon(new ImageIcon("images/easyselect.png"));

	    
	    final JButton medium = new JButton(new ImageIcon("images/medium.png"));
	    medium.setBorder(BorderFactory.createEmptyBorder());
		medium.setContentAreaFilled(false);
		medium.setRolloverEnabled(true);
		medium.setRolloverIcon(new ImageIcon("images/mediumselect.png"));
	    
	    final JButton hard = new JButton(new ImageIcon("images/hard.png"));
	    hard.setBorder(BorderFactory.createEmptyBorder());
		hard.setContentAreaFilled(false);
		hard.setRolloverEnabled(true);
		hard.setRolloverIcon(new ImageIcon("images/hardselect.png"));

        difficulty.add(easy, c);
	    easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gui.getModel().setDifficulty(EASY);
				easy.setIcon(new ImageIcon("images/easyselect.png"));
				medium.setIcon(new ImageIcon("images/medium.png"));
				hard.setIcon(new ImageIcon("images/hard.png"));
			}

		});
	    
	    
        c.gridy = 1;
        difficulty.add(medium, c);
	    medium.addMouseListener(new MouseAdapter() {
		@Override
            public void mouseClicked(MouseEvent e) {
                gui.getModel().setDifficulty(MEDIUM);
                easy.setIcon(new ImageIcon("images/easy.png"));
				medium.setIcon(new ImageIcon("images/mediumselect.png"));
				hard.setIcon(new ImageIcon("images/hard.png"));
            }
		});
	    
	  
        c.gridy = 2;
        difficulty.add(hard, c);
	    hard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	gui.getModel().setDifficulty(HARD);
            	easy.setIcon(new ImageIcon("images/easy.png"));
				medium.setIcon(new ImageIcon("images/medium.png"));
				hard.setIcon(new ImageIcon("images/hardselect.png"));
                }
		});

	    
	    this.add(difficulty);
	    
	    JPanel mode = new JPanel();
	    mode.setOpaque(false);
	    final JToggleButton collector = new JToggleButton(new ImageIcon("images/collector.png"));
	    collector.setBorder(BorderFactory.createEmptyBorder());
        collector.setContentAreaFilled(false);
        collector.setRolloverEnabled(true);
        collector.setRolloverIcon(new ImageIcon("images/collectorfilled.png"));
	    collector.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (classic == true) {
	    			gui.getModel().setCollectorMode();
	    			collector.setIcon(new ImageIcon("images/collectorfilled.png"));
	    			classic = false;
	    		} else {
	    			gui.getModel().setClassicMode();
	    			collector.setIcon(new ImageIcon("images/collector.png"));
	    			classic = true;
	    		}
	    	}
	    });
	    mode.add(collector);
	    this.add(mode);
	}

	/**
	 * Draws the graphical elements of a MenuScreen.
	 * The background of a MenuScreen is the only graphical element drawn here.
	 * @param g - This component's graphics context
     */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}
}
