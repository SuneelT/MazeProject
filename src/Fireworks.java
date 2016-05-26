import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Displays a firework animation on the WinScreen. Fireworks originate from five points on the panel one at a time and
 * are randomised in colour and location.
 */
public class Fireworks extends JPanel {
    private final long serialVersionUID = 5053650234847311814L;
    private final int numFireworks;
    private int xCentre[], yCentre[];
    private Color colours[];
    private int currRadius;
    private int stepSize;
    private int location;
    private int colourIndex;
    List<Integer> x = new ArrayList<Integer>();
    List<Integer> y = new ArrayList<Integer>();

    /**
     * Constructor for fireworks.
     * The x- and y-coordinates of the five firework origin points are initialised in the xCentre and yCentre arrays
     * and the colours array is populated. A timer is used to control the speed of the fireworks. The radius is
     * initialised to zero and the number of pixels by which it steps is initialised.
     */
    public Fireworks() {
        numFireworks = 5;
        xCentre = new int[numFireworks];
        yCentre = new int[numFireworks];
        colours = new Color[numFireworks];
        currRadius = location = colourIndex = 0;
        stepSize = 4;
        Timer timer = new Timer(10, new MyChangeListener());
        timer.start();
        xCentre[0] = 100;
        yCentre[0] = 100;

        xCentre[1] = 200;
        yCentre[1] = 200;

        xCentre[2] = 300;
        yCentre[2] = 300;

        xCentre[3] = 300;
        yCentre[3] = 100;

        xCentre[4] = 100;
        yCentre[4] = 300;

        colours[0] = Color.RED;
        colours[1] = Color.YELLOW;
        colours[2] = Color.GREEN;
        colours[3] = Color.BLUE;
        colours[4] = Color.MAGENTA;

        setOpaque(false);
    }

    /**
     * Draws the firework image as a circle of coloured dots.
     * @param g - This component's graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D) g;
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 5, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(colours[colourIndex]);
        for (int i = 0; i < x.size(); i++) {
            graphics2d.drawLine(x.get(i), y.get(i), x.get(i)+2, y.get(i)+2);
        }
    }

    /**
     * Event handling class identifies when a firework burst has finished its animation and begins a new one.
     */
    private class MyChangeListener implements ActionListener {
        /**
         * Keeps track of the current radius of the fireworks and decides if it should continue growing or begin a new
         * firework.
         * @param arg0 - The action being reacted to
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
            currRadius += stepSize;
            if (currRadius >= 150) {
                currRadius = 0;
                Random random = new Random();
                location = random.nextInt(numFireworks);
                colourIndex = random.nextInt(numFireworks);
            }
            x.clear();
            y.clear();
            for (int i = 0; i < 10; i++) {
                x.add((int) (xCentre[location] + currRadius * Math.cos((36 * i * 3.14159) / 180)));
                y.add((int) (yCentre[location] + currRadius * Math.sin((36 * i * 3.14159) / 180)));
            }
            repaint();
        }
    }
}