import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import javax.swing.*;

public abstract class Screen extends JPanel {
	private static final long serialVersionUID = 2480454775809714426L;
	private List<Observer> mazeComponents = new LinkedList<Observer>();
	private GUI gui;
	
	public void setGUI(GUI g) {
		this.gui = g;
	}
	
	public List<Observer> getMazeComponents() {
		return this.mazeComponents;
	}
	
	public void addMazeComponent(Observer c) {
		this.mazeComponents.add(c);
	}
	
	public GUI getGUI() {
		return this.gui;
	}
}
