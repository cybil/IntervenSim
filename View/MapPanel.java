import javax.swing.JPanel; 
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Graphics;

public class MapPanel extends JPanel {
	MapPanel() {
		this.setPreferredSize(new Dimension(300, 600));
		// this.setBackground(new Color(0, 0, 255));
	}

    public void		paintComponent(Graphics g) {
	g.fillOval(20, 20, 75, 75);
	// g.drawLine();
    }

    public void		displayMap(ArrayList<String> formatMap)
    {
	for (String s : formatMap) {
	    System.out.println(s);
	}
    }
}
