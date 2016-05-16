import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpScreen extends Screen {
	private static final long serialVersionUID = -264471428282433023L;
	JButton menuButton;
    JLabel text;

    public HelpScreen(GUI gui) {
        setGUI(gui);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(new Color(255,204,255));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255,204,255));
        
        menuButton = new JButton("Menu");
        menuButton.setBackground(Color.WHITE);
        menuButton.setForeground(Color.BLACK);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Ariel", Font.BOLD, 50));
        buttonPanel.add(menuButton);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);
        c.anchor = GridBagConstraints.PAGE_START;
        
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getGUI().switchScreen("Menu");
            }
        });
        
        JButton playButton = new JButton("Play Now");
		playButton.setBackground(Color.WHITE);
    	playButton.setForeground(Color.BLACK);
    	playButton.setFocusPainted(false);
    	playButton.setFont(new Font("Ariel", Font.BOLD, 50));
    	buttonPanel.add(playButton);
    	playButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getModel().createMaze();
				getModel().resetPlayer();
				getGUI().switchScreen("Maze");
			}
    	});
    	
    	this.add(buttonPanel, c);

        text = new JLabel("<html>Use the arrow keys on your keyboard to guide your character to the exit. The exit is the"
        		+ "bottom right-hand corner of the maze.</html>", JLabel.CENTER);
        text.setFont(new Font("Ariel",Font.PLAIN, 30));
        c.gridy = 1;
        c.ipadx = 500;
        c.ipady = 10;
        c.insets = new Insets(20, 20, 250, 20);
        c.anchor = GridBagConstraints.CENTER;
        this.add(text, c);
        
        
    }

}
