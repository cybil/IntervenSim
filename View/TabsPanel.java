import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class TabsPanel extends JPanel {
	JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);
	JPanel tab1 = new JPanel();
    JLabel titletab1 = new JLabel("Edit map");
    JPanel tab2 = new JPanel();
    JLabel titletab2 = new JLabel("Statistics");
    
	TabsPanel() {
		this.setBackground(new Color(255, 0, 0));
		this.setPreferredSize(new Dimension(400, 600));
		this.setLayout(new BorderLayout());
		this.setTabs();

	}
	void setTabs() {
	    tab1.setPreferredSize(new Dimension(400, 500));
	    tab1.add(titletab1);
	    tabs.addTab("Map", tab1);
	    tab2.add(titletab2);
	    tabs.addTab("Stat", tab2);
	    tabs.setOpaque(true);
	    add(tabs, BorderLayout.CENTER);
	}
}
