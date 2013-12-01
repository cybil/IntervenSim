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
	private JLabel vehicleLabel = new JLabel("Vehicle");
	private JLabel apLabel = new JLabel("Attachment point");
	private JLabel strategyLabel = new JLabel("Select your strategy");
	private JLabel speedLabel = new JLabel("Enter speed");
	private JButton vehiculeCoordinatesConfirmButton = new JButton("OK");
	
	
	VehiculeTab() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(200, 500));
		this.setSize(new Dimension(200, 500));
		this.setMaximumSize(new Dimension(200, 500));
		this.setMinimumSize(new Dimension(200, 500));
		this.setLayout(new GridLayout(13, 1));

		//vehicul coordonates
		vehicleLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		this.add(vehicleLabel);

		xJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    xJtf.setPreferredSize(new Dimension(50, 30));
		xPanel.add(new JLabel("x :"));
	    xPanel.add(xJtf);
	    //xPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    this.add(xPanel);
	    
	    yJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    yJtf.setPreferredSize(new Dimension(50, 30));
		yPanel.add(new JLabel("y :"));
	    yPanel.add(yJtf);
	    yPanel.add(vehiculeCoordinatesConfirmButton);
	    this.add(yPanel);
	
	    // Attchment point coordinates
	    apLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		this.add(apLabel);
		
		xPtAttacheJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    xPtAttacheJtf.setPreferredSize(new Dimension(50, 30));
		xPtAttachePanel.add(new JLabel("x :"));
	    xPtAttachePanel.add(xPtAttacheJtf);
	    xPtAttacheJtf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    this.add(xPtAttachePanel);
	    
	    yPtAttacheJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    yPtAttacheJtf.setPreferredSize(new Dimension(50, 30));
		yPtAttachePanel.add(new JLabel("y :"));
	    yPtAttachePanel.add(yPtAttacheJtf);
	    yPtAttacheJtf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	    this.add(yPtAttachePanel);
	    
	    ptAttacheButton.setSize(50, 20);
	    this.add(ptAttacheButton);
	    
	    // Strategy choice
		strategieCombo.setSize(new Dimension(50, 20));
		strategieCombo.addItem("Select your strategy");
		strategieCombo.addItem("Oldest Strategy");
		this.add(strategieCombo);
		
		//Speed choice
		speedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		this.add(speedLabel);
		
		speedJtf.setFont(new Font("Arial", Font.BOLD, 14));
	    speedJtf.setPreferredSize(new Dimension(50, 30));
	    speedPanel.add(speedJtf);
	    speedPanel.add(new JLabel("km/h"));
	    this.add(speedPanel);		
	}
}
