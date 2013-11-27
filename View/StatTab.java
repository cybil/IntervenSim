import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatTab extends JPanel {
	StatTab() {
	    this.add(new JLabel("Statistics"));
	    this.setPreferredSize(new Dimension(400, 600));
	}
}
