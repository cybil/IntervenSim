import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatTab extends JPanel {
	StatTab() {
	    this.add(new JLabel("Statistics"));
		this.setPreferredSize(new Dimension(200, 500));
		this.setSize(new Dimension(200, 500));
		this.setMaximumSize(new Dimension(200, 500));
		this.setMinimumSize(new Dimension(200, 500));
	}
}
