import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * The MazeScreen is one of the game's screens. It contains a MazePanel that the user interacts with to play the
 * game, an exit button that takes the user back to the menu, a mute/unmute button to toggle the music and a
 * CollectedPanel that displays any items that the player has collected if collectible mode is turned on.
 */
public class MazeScreen extends JPanel {
	private static final long serialVersionUID = -8972885841964219641L;
	JPanel mazePanel;
	private Clip clip;  
	//private boolean muted;
	private Image bg;

	/**
	 * Constructor for a MazeScreen.
	 * A MazeScreen contains a MazePanel, an exit button, a mute/unmute button and a CollectedPanel.
	 * @param gui - A reference to the parent GUI class to allow for the switching of screens
     */
	public MazeScreen(final GUI gui) {
		this.setLayout(new BorderLayout());
		try {
			bg = ImageIO.read(new File("images/greenBG.jpg"));
		} catch (IOException e1) {}
		this.setLayout(new BorderLayout());
		mazePanel = new MazePanel(gui);
		gui.setPlayerObserver((Observer)mazePanel);
		this.add(mazePanel, BorderLayout.CENTER);
		
		JPanel otherControls = new JPanel(new GridLayout(3, 1));
		this.add(otherControls, BorderLayout.EAST);
		otherControls.setOpaque(false);
		
		final JButton exitButton = new JButton(new ImageIcon("images/exit.png"));
		exitButton.setBorder(null);
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(gui, "Are you sure you want to exit?", 
				   "Exit", JOptionPane.YES_NO_OPTION, 
				   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					gui.switchScreen("Menu");
				}
				
				mazePanel.requestFocusInWindow();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(new ImageIcon("images/exitfilled.png"));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(new ImageIcon("images/exit.png"));

			}
		});
		
		JPanel collected = new CollectedPanel();
		collected.setPreferredSize(new Dimension(100, 190));
		gui.setCollectableObserver((Observer) collected);
		
		final JToggleButton muteButton = new JToggleButton(new ImageIcon("images/sound.png"));
		muteButton.setBorder(BorderFactory.createEmptyBorder());
		muteButton.setContentAreaFilled(false);
		muteButton.setFocusPainted(false);

	    muteButton.addMouseListener(new MouseAdapter() {
	    	int clicked = 0;
	        @Override
	        public void mouseClicked(MouseEvent e) {
	           // if (muted == true) {
	            //	clip.loop(Clip.LOOP_CONTINUOUSLY);
	           // 	muted = false;
	          //  } else {
	           // 	clip.stop();
	           // 	muted = true;
	           // }
	        	clicked ++;
	        	if (clicked%2==0){
		             muteButton.setIcon(new ImageIcon("images/sound.png"));
	              } else {
		              muteButton.setIcon(new ImageIcon("images/mute.png"));         	 
	             }
	        	
	            mazePanel.requestFocusInWindow();
            }

	    });
	    
	    otherControls.add(muteButton);
	    otherControls.add(collected);
		otherControls.add(exitButton); 	
		//music();
	}

	/**
	 * Draws the graphical elements of a MazeScreen.
	 * The background of a MazeScreen is the only graphical element drawn here.
	 * @param g - This component's graphics context
     */
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}

	/**
	 * Plays music when the user is on the MazeScreen.
	 * Takes in a .wav file and plays it on loop.
	 */
	private void music() {
		try {
            File file = new File("audio/Music.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
