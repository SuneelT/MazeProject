import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class WinScreen extends Screen{
    JButton newGameButton;

    public WinScreen(GUI gui) {
        setGUI(gui);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        newGameButton = new JButton("New Game");
        newGameButton.setContentAreaFilled(false);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(0, 100, 0, 0);
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getModel().createMaze();
                getGUI().switchScreen("Maze");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // newGameButton.setIcon(new ImageIcon("images/buttonplayfilled.png"));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                // newGameButton.setIcon(new ImageIcon("images/buttonplay.png"));

            }
        });
        this.add(newGameButton, c);

        final JButton menuButton = new JButton("Back to Menu");
        menuButton.setContentAreaFilled(false);
        c.gridx = 1;
        c.insets = new Insets(0, 0, 0, 100);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getGUI().switchScreen("Menu");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                // menuButton.setIcon(new ImageIcon("images/buttonhelpfilled.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // menuButton.setIcon(new ImageIcon("images/buttonhelp.png"));
            }
        });
        this.add(menuButton, c);
    }
}
