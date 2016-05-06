import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import javax.swing.*;

public abstract class Screen extends JPanel implements Observer {
	private static final long serialVersionUID = 2480454775809714426L;
	private Container contentPane;
	private List<JComponent> mazeComponents = new LinkedList<JComponent>();
	private final GUI gui;
	
	public Screen(GUI gui) {
		this.gui = gui;
	}
	
	public List<JComponent> getMazeComponents() {
		return this.mazeComponents;
	}
	
	public void addMazeComponent(JComponent c) {
		this.mazeComponents.add(c);
	}
	
	public void setCP(Container cp) {
		this.contentPane = cp;
	}
	
	public Container getCP() {
		return this.contentPane;
	}
	
	public GUI getGUI() {
		return this.gui;
	}
}
