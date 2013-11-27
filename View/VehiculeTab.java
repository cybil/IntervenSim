import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class VehiculeTab extends JPanel {
	private JFormattedTextField xJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel xPanel = new JPanel();
	private JFormattedTextField yJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel yPanel = new JPanel();
	private JFormattedTextField xPtAttacheJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel xPtAttachePanel = new JPanel();
	private JFormattedTextField yPtAttacheJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel yPtAttachePanel = new JPanel();
	private JButton ptAttacheButton = new JButton("Select node");
	private JComboBox strategieCombo = new JComboBox();
	private JFormattedTextField speedJtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JPanel speedPanel = new JPanel();
	
	VehiculeTab() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(400, 600));
		this.setLayout(new GridLayout(11, 1));
		this.add(new JLabel("vehicle"));
	    
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
	    
		this.add(new JLabel("Attachment point"));
		xPtAttacheJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    xPtAttacheJtf.setPreferredSize(new Dimension(50, 30));
		xPtAttachePanel.add(new JLabel("x :"));
	    xPtAttachePanel.add(xPtAttacheJtf);
	    this.add(xPtAttachePanel);
	    
	    yPtAttacheJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    yPtAttacheJtf.setPreferredSize(new Dimension(50, 30));
		yPtAttachePanel.add(new JLabel("y :"));
	    yPtAttachePanel.add(yPtAttacheJtf);
	    this.add(yPtAttachePanel);
	    
	    ptAttacheButton.setSize(50, 20);
	    this.add(ptAttacheButton);
	    
		this.add(new JLabel("Strategy"));
		
		strategieCombo.setSize(new Dimension(50, 20));
		strategieCombo.addItem("Oldest Strategy");
		this.add(strategieCombo);
		
		this.add(new JLabel("Speed"));
		
		speedJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    speedJtf.setPreferredSize(new Dimension(50, 30));
	    speedPanel.add(speedJtf);
	    speedPanel.add(new JLabel("km/h"));
	    this.add(speedPanel);
		
	}
}
