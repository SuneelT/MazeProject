import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class WinScreen extends JPanel {
	private static final long serialVersionUID = -6918359361500026357L;
	private Image bg;
    private JButton newGameButton;
    private JButton menuButton;
    private Image winningMessage;

    public WinScreen(final GUI gui) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        try {
            bg = ImageIO.read(new File("images/redBG.jpg"));
            winningMessage = ImageIO.read(new File("images/youwin.png"));
        } catch (IOException e) { e.printStackTrace(); }

        Fireworks fireworks = new Fireworks();
        fireworks.setPreferredSize(new Dimension(400, 400));
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.insets = new Insets(0, 50, 0, 0);
        this.add(fireworks, c);

        newGameButton = new JButton(new ImageIcon("images/newgame.png"));
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorder(BorderFactory.createEmptyBorder());
        newGameButton.setFocusPainted(false);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 100, 50, 0);
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.getModel().createMaze();
                gui.switchScreen("Maze");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                newGameButton.setIcon(new ImageIcon("images/newgamefilled.png"));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                newGameButton.setIcon(new ImageIcon("images/newgame.png"));

            }
        });
        this.add(newGameButton, c);

        menuButton = new JButton(new ImageIcon("images/menu.png"));
        menuButton.setContentAreaFilled(false);
        menuButton.setBorder(BorderFactory.createEmptyBorder());
        menuButton.setFocusPainted(false);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 50, 100);
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.switchScreen("Menu");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                menuButton.setIcon(new ImageIcon("images/menufilled.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                menuButton.setIcon(new ImageIcon("images/menu.png"));
            }
        });
        this.add(menuButton, c);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(winningMessage, 150, 135, 600, 150, null);
    }
}
