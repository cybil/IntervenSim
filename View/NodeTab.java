import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;


import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

public class NodeTab extends JPanel {
	private JLabel nodeLabel = new JLabel("Node");
	private JPanel xPanel = new JPanel();
	private JPanel yPanel = new JPanel();
	private JFormattedTextField xJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField yJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JLabel propertiesLabel = new JLabel("Properties");
	private JRadioButton nodejr1 = new JRadioButton("None");
	private JRadioButton nodejr2 = new JRadioButton("Attachment point");
	private JRadioButton nodejr3 = new JRadioButton("Urgency");
	private JPanel propertiesPanel = new JPanel();
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	NodeTab() {
		this.setLayout(new GridLayout(5, 1));
		this.setPreferredSize(new Dimension(200, 500));
		this.setSize(new Dimension(200, 500));
		this.setMaximumSize(new Dimension(200, 500));
		this.setMinimumSize(new Dimension(200, 500));
		nodeLabel.setSize(50, 30);
		this.add(nodeLabel);
			
		xJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    xJtf.setPreferredSize(new Dimension(50, 30));
		xPanel.add(new JLabel("x :"));
	    xPanel.add(xJtf);
		this.add(xPanel);
		
		yJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    yJtf.setPreferredSize(new Dimension(50, 30));
		yPanel.add(new JLabel("y :"));
	    yPanel.add(yJtf);
		this.add(yPanel);
		
		this.add(propertiesLabel);
		
		propertiesPanel.setLayout(new GridLayout(3, 1));
		buttonGroup.add(nodejr1);
		buttonGroup.add(nodejr2);
		buttonGroup.add(nodejr3);
		propertiesPanel.add(nodejr1);
		propertiesPanel.add(nodejr2);
		propertiesPanel.add(nodejr3);
		this.add(propertiesPanel);
	}
}
