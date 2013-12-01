import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

import javax.swing.*;


public class ToolsBar extends JToolBar {
	protected SelectButton selectButton = new SelectButton();
	protected VehiculeButton vehiculeButton = new VehiculeButton();
	protected RoadButton roadButton = new RoadButton();
	protected NodeButton nodeButton = new NodeButton();
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	ToolsBar() {
		this.setPreferredSize(new Dimension(60, 192));

		this.setLayout(new GridLayout(4, 1));
	    buttonGroup.add(selectButton);
	    buttonGroup.add(vehiculeButton);
	    buttonGroup.add(nodeButton);
	    buttonGroup.add(roadButton);
	    this.add(selectButton);
	    this.add(vehiculeButton);
	    this.add(nodeButton);
	    this.add(roadButton);
	    this.setFloatable(false);
	    this.setVisible(true);
	}
	
	void deselectButton(MapPanel.EObjectTools e) {
		switch (e) {
		case VEHICULE :
			nodeButton.deselectButton();
			selectButton.deselectButton();
			roadButton.deselectButton();
			break;
		case NODE :
			vehiculeButton.deselectButton();
			selectButton.deselectButton();
			roadButton.deselectButton();
			break;
		case CURSOR :
			vehiculeButton.deselectButton();
			nodeButton.deselectButton();
			roadButton.deselectButton();
			break;
		case ROAD :
			vehiculeButton.deselectButton();
			nodeButton.deselectButton();
			selectButton.deselectButton();
			break;
		default :
			break;
		}
	}
}
