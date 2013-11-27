import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

public class ToolsBar extends JPanel {
	protected SelectButton selectButton = new SelectButton();
	protected VehiculeButton vehiculeButton = new VehiculeButton();
	protected RoadButton roadButton = new RoadButton();
	protected NodeButton nodeButton = new NodeButton();

	ToolsBar() {
		this.setPreferredSize(new Dimension(60, 192));
		this.setBackground(new Color(0, 255, 0));

		this.setLayout(new GridLayout(4, 1));
	    this.add(selectButton);
	    this.add(vehiculeButton);
	    this.add(nodeButton);
	    this.add(roadButton);
	    this.setVisible(true);
	}
	
	void setCliked(int button) {
		switch (button) {
		case 0 :
			System.out.print("select\n");
			vehiculeButton.setGetCliked(false);
			roadButton.setGetCliked(false);
			nodeButton.setGetCliked(false);
			break;
		case 1 :
			System.out.print("vehicule\n");
			selectButton.setGetCliked(false);
			roadButton.setGetCliked(false);
			nodeButton.setGetCliked(false);
			break;
		case 2 :
			System.out.print("road\n");
			selectButton.setGetCliked(false);
			vehiculeButton.setGetCliked(false);
			nodeButton.setGetCliked(false);
			break;
		case 3 :
			System.out.print("NODE\n");
			selectButton.setGetCliked(false);
			roadButton.setGetCliked(false);
			vehiculeButton.setGetCliked(false);
			break;
		}
		this.repaint();
	}
}
