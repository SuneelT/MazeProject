import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class HelpScreen extends Screen {
	private static final long serialVersionUID = -264471428282433023L;
	JButton returnButton;
    JLabel text;
    private Image bg;
 
    public HelpScreen(GUI gui) {
        setGUI(gui);
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		try {
			bg = ImageIO.read(new File("images/yellowBG.jpg"));
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
                getGUI().switchToLastScreen();
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
        		+ "bottom right-hand corner of the maze.</html>", JLabel.CENTER);
        text.setFont(new Font("Ariel",Font.PLAIN, 30));
        c.gridy = 1;
        c.ipadx = 500;
        c.ipady = 10;
        c.insets = new Insets(20, 20, 250, 20);
        c.anchor = GridBagConstraints.CENTER;
        this.add(text, c);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
