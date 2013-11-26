import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class ToolsBar extends JPanel {
	private SelectButton selectButton = new SelectButton();
	private VehiculeButton vehiculeButton = new VehiculeButton();
	private RoadButton roadButton = new RoadButton();
	private NodeButton nodeButton = new NodeButton();

	ToolsBar() {
		this.setPreferredSize(new Dimension(80, 250));
		this.setBackground(new Color(0, 255, 0));

		this.setLayout(new GridLayout(4, 1));
	    this.add(selectButton);
	    this.add(vehiculeButton);
	    this.add(roadButton);
	    this.add(nodeButton);
	    this.setVisible(true);
	}
}
