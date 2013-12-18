import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;

public class ToolsMenu extends JMenu {
	JMenuItem	nodeItem = new JMenuItem("Node");
	JMenuItem	roadItem = new JMenuItem("Road");
	JMenuItem	vehiculeItem = new JMenuItem("Vehicule");
	JMenuItem	mapItem = new JMenuItem("Map");
	JMenuItem	scaleItem = new JMenuItem("Zoom");
	JMenuItem	simulationItem = new JMenuItem("Simulation");

	ToolsMenu(String str) {
		setText(str);
		this.setMnemonic(KeyEvent.VK_T);

		nodeItem.addActionListener(new NodeItemAction());
		this.add(nodeItem);

		roadItem.addActionListener(new RoadItemAction());
		this.add(roadItem);

		vehiculeItem.addActionListener(new VehiculeItemAction());
		this.add(vehiculeItem);

		mapItem.addActionListener(new MapItemAction());
		this.add(mapItem);

		scaleItem.addActionListener(new ScaleItemAction());
		this.add(scaleItem);

		simulationItem.addActionListener(new SimulationItemAction());
		this.add(simulationItem);
	}

	public class NodeItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("NodeItem\n");
		}
	}

	public class RoadItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("RoadItem\n");
		}
	}

	public class VehiculeItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("vehiculeItem\n");
		}
	}

	public class MapItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("MapItem\n");
		}
	}

	public class ScaleItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("scaleItem\n");
		}
	}

	public class SimulationItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("simulationItem\n");
		}
	}
}
