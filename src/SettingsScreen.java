import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class SettingsScreen extends Screen {
	private static final long serialVersionUID = -4212692790730740664L;

	public SettingsScreen(final GUI gui) {
		setGUI(gui);
		setLayout(new BorderLayout());
		// change difficulty
		
		// change theme - colours etc
		
		// FOR TESTING PURPOSES - will be deleted later
		setGUI(gui);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(new Color(255,204,255));
		JButton returnButton = new JButton("Return To Game");
		add(returnButton);
		returnButton.setBackground(Color.WHITE);
	    returnButton.setForeground(Color.BLACK);
		returnButton.setFocusPainted(false);
		returnButton.setFont(new Font("Ariel", Font.BOLD, 50));
	    returnButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getGUI().switchScreen("Maze");
			}
		});
	}
}
