import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class WinScreen extends Screen{
    private Image bg;

    public WinScreen(GUI gui) {
        setGUI(gui);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        try {
            bg = ImageIO.read(new File("images/redBG.jpg"));
        } catch (IOException e) { e.printStackTrace(); }

        Fireworks fireworks = new Fireworks();
        fireworks.setPreferredSize(new Dimension(500, 500));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.insets = new Insets(50, 50, 0, 0);
        this.add(fireworks, c);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setContentAreaFilled(false);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 100, 50, 0);
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
        c.gridy = 1;
        c.insets = new Insets(0, 0, 50, 100);
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
    }
}
