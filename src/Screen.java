import javax.swing.*;

public abstract class Screen extends JPanel {
	private static final long serialVersionUID = 2480454775809714426L;
	private GUI gui; 
	
	public void setGUI(GUI g) {
		this.gui = g;
	}
	
	public GUI getGUI() {
		return this.gui;
	}
	
	public Model getModel() {
		return gui.getModel();
	}
}
