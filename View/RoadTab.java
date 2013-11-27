import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;

public class RoadTab extends JPanel {
	private JLabel road1Label = new JLabel("Road 1");
	private JLabel road2Label = new JLabel("Road 2");
	private JPanel x1Panel = new JPanel();
	private JPanel y1Panel = new JPanel();
	private JPanel x2Panel = new JPanel();
	private JPanel y2Panel = new JPanel();
	private JFormattedTextField x1Jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField y1Jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField x2Jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField y2Jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JButton road1Button = new JButton("Select Road 1");
	private JButton road2Button = new JButton("Select Road 2");
	
	RoadTab() {
			this.setLayout(new GridLayout(8, 1));
			setBackground(Color.white);
			
			setBackground(Color.white);
			this.setPreferredSize(new Dimension(400, 600));
			
			road1Label.setSize(50, 30);
			this.add(road1Label);
			
			x1Jtf.setFont(new Font("Arial", Font.BOLD, 14));
		    x1Jtf.setPreferredSize(new Dimension(50, 30));
			x1Panel.add(new JLabel("x :"));
		    x1Panel.add(x1Jtf);
			this.add(x1Panel);
			
			y1Jtf.setFont(new Font("Arial", Font.BOLD, 14));
		    y1Jtf.setPreferredSize(new Dimension(50, 30));
			y1Panel.add(new JLabel("y :"));
		    y1Panel.add(y1Jtf);
			this.add(y1Panel);
			
			this.add(road1Button);
			
			road2Label.setSize(50, 30);
			this.add(road2Label);
			
			x2Jtf.setFont(new Font("Arial", Font.BOLD, 14));
		    x2Jtf.setPreferredSize(new Dimension(50, 30));
			x2Panel.add(new JLabel("x :"));
		    x2Panel.add(x2Jtf);
			this.add(x2Panel);
			
			y2Jtf.setFont(new Font("Arial", Font.BOLD, 14));
		    y2Jtf.setPreferredSize(new Dimension(50, 30));
			y2Panel.add(new JLabel("y :"));
		    y2Panel.add(y2Jtf);
			this.add(y2Panel);
			
			this.add(road2Button);
		}
}
