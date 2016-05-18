import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Observer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class MazeScreen extends Screen {
	private static final long serialVersionUID = -8972885841964219641L;
	JPanel mazePanel;
	private static Clip clip; 
	private boolean muted;
	
	public MazeScreen(final GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		mazePanel = new MazePanel(getGUI());
		getGUI().setPlayerObserver((Observer)mazePanel);
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel otherControls = new JPanel(new GridLayout(2, 1, 100, 100));
		add(otherControls, BorderLayout.EAST);
		otherControls.setBackground(Color.WHITE);
		
		JButton exitButton = new JButton("Exit To Menu");
		exitButton.setBackground(Color.WHITE);
	    exitButton.setForeground(Color.BLACK);
	    exitButton.setFocusPainted(false);
	    exitButton.setFont(new Font("Ariel", Font.BOLD, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(gui, "Are you sure you want to exit?", 
				   "Exit", JOptionPane.YES_NO_OPTION, 
				   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					getGUI().switchScreen("Menu");
				}
			}
		});
		JButton muteButton = new JButton("Mute");
		muteButton.setBackground(Color.WHITE);
	    muteButton.setForeground(Color.BLACK);
	    muteButton.setFocusPainted(false);
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
            }
	    });
	    
	    otherControls.add(muteButton);
		otherControls.add(exitButton); 	
		//music();
	}
	
	public static void music() {
		try {
            File file = new File("Music.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
