import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public interface Screen {
	JPanel getContentPane();
	List<JComponent> getMazeListeners();
}
