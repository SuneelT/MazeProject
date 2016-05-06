import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import javax.swing.*;

public abstract class Screen extends JPanel implements Observer {
	private static final long serialVersionUID = 2480454775809714426L;
	protected Container contentPane;
	protected List<JComponent> mazeComponents = new LinkedList<JComponent>();
	
	public Container getContentPane() {
		return this.contentPane;
	}
	
	public List<JComponent> getMazeListeners() {
		return this.mazeComponents;
	}
}
