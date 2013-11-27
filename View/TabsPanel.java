import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class TabsPanel extends JPanel {
	JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);
	EditTab editTab = new EditTab();
    StatTab statTab = new StatTab();
    
	TabsPanel() {
		this.setBackground(new Color(255, 0, 0));
		this.setPreferredSize(new Dimension(400, 600));
		this.setLayout(new BorderLayout());
		this.setTabs();
	}
	void setTabs() {
	    tabs.addTab("Map", editTab);
	    tabs.addTab("Stat", statTab);
	    tabs.setOpaque(true);
	    add(tabs, BorderLayout.CENTER);
	}
}
