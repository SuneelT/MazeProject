import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpScreen extends Screen {
    JButton menuButton;
    JLabel text;

    public HelpScreen(GUI gui) {
        setGUI(gui);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setBackground(new Color(255,204,255));

        menuButton = new JButton("Menu");
        menuButton.setBackground(Color.WHITE);
        menuButton.setForeground(Color.BLACK);
        menuButton.setFocusPainted(false);
        menuButton.setFont(new Font("Ariel", Font.BOLD, 50));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(20, 20, 20, 20);
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(menuButton, c);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getGUI().switchScreen("Menu");
            }
        });

        text = new JLabel("<html>Use the arrow keys on your keyboard to guide your character to the exit.</html>", JLabel.CENTER);
        text.setFont(new Font("Ariel",Font.PLAIN, 30));
        c.gridy = 1;
        c.ipadx = 500;
        c.ipady = 100;
        c.insets = new Insets(20, 20, 250, 20);
        c.anchor = GridBagConstraints.CENTER;
        this.add(text, c);
    }

}
