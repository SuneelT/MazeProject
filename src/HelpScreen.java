import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
/**
 * The Help screen is one of the game's screens. 
 * It contains a return button which takes the user back to the menu screen.
 * It also contains a panel containing text that explains the game controls to the user.
 * There are also images of important features of the game. 
 */ 
public class HelpScreen extends JPanel {
	private static final long serialVersionUID = -264471428282433023L;
	JButton returnButton;
    JLabel text;
    private Image bg;
    private Image player;
    private Image end;
    private Image collectible;
    
    /**
     * Constructor for the  Help Screen.
     * Contains a return button, a Jlabel, and three images.
     * @param gui - A reference to the parent GUI class to allow for the switching of screens
     */
    public HelpScreen(final GUI gui) {
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		try {
			bg = ImageIO.read(new File("images/yellowBG.jpg"));
			player = ImageIO.read(new File("images/player_up.png"));
	        end = ImageIO.read(new File("images/tiles/end_unlocked.png"));
	        collectible = ImageIO.read(new File("images/key.png"));
		} catch (IOException e1) {}
       this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        
        returnButton = new JButton(new ImageIcon("images/buttonreturn.png"));
        returnButton.setBorder(BorderFactory.createEmptyBorder());
		returnButton.setContentAreaFilled(false);
        buttonPanel.add(returnButton);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);
        c.anchor = GridBagConstraints.PAGE_START;
        
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.switchScreen("Menu");
            }
            @Override
			public void mouseEntered(MouseEvent e) {
            	returnButton.setIcon(new ImageIcon("images/buttonreturnfilled.png"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				returnButton.setIcon(new ImageIcon("images/buttonreturn.png"));
			}
        });
    	
    	this.add(buttonPanel, c);

        text = new JLabel("<html>Use the arrow keys on your keyboard to guide your character to the exit. The exit is the"
        		+ " bottom right-hand corner of the maze." + "<br><br>This is your player! <br><br> This is the end!"
        		+ "<br><br>If you're playing collectible mode - collect objects to spell out a word and exit the maze!</html>", JLabel.CENTER);
        text.setFont(new Font("Ariel",Font.PLAIN, 25));
        c.gridy = 1;
        c.ipadx = 390;
        c.ipady = 10;
        c.insets = new Insets(20, 20, 110, 200);
        c.anchor = GridBagConstraints.CENTER;
        this.add(text, c);
        
    }
    /**
     * Draws the Help Screen.
     * It draws the screen's bacgkround, the player image, the maze end image
     * and a sample of the collectible image.
     * @param g - This component's graphics context.
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    	g.drawImage(player, 300, 310, 80, 80, null);
    	g.drawImage(end, 268, 380, 70, 70, null);
    	g.drawImage(collectible, 600, 470, 65, 65, null);
    }
}
