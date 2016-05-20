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

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	JPanel mazePanel;
	private Clip clip; 
	private boolean muted;
	private Image bg;
	
	public MazeScreen(final GUI gui) {
		setGUI(gui);
		this.setLayout(new BorderLayout());
		try {
			bg = ImageIO.read(new File("images/redBG.jpg"));
		} catch (IOException e1) {}
		this.setLayout(new BorderLayout());
		mazePanel = new MazePanel(getGUI());
		getGUI().setPlayerObserver((Observer)mazePanel);
		this.add(mazePanel, BorderLayout.CENTER);
		
		JPanel otherControls = new JPanel(new GridLayout(2, 1, 100, 100));
		this.add(otherControls, BorderLayout.EAST);
		otherControls.setOpaque(false);
		
		JButton exitButton = new JButton("Exit To Menu");
		exitButton.setOpaque(true);
		exitButton.setContentAreaFilled(false);
	    exitButton.setFont(new Font("Ariel", Font.BOLD, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(gui, "Are you sure you want to exit?", 
				   "Exit", JOptionPane.YES_NO_OPTION, 
				   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					getGUI().switchScreen("Menu");
				}
				mazePanel.requestFocusInWindow();
			}
		});
		JButton muteButton = new JButton("Mute");
		muteButton.setOpaque(false);
		muteButton.setContentAreaFilled(false);
	    muteButton.setFont(new Font("Ariel", Font.BOLD, 20));
	    muteButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (muted == true) {
	            	clip.loop(Clip.LOOP_CONTINUOUSLY);
	            	muted = false;
	            } else {
	            	clip.stop();
	            	muted = true;
	            }
	            mazePanel.requestFocusInWindow();
            }
	    });
	    
	    otherControls.add(muteButton);
		otherControls.add(exitButton); 	
		//music();
	}
	
	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
	}
	
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
