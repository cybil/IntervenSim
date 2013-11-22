import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTabbedPane;

public class TabsPanel extends JPanel {
	JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	JPanel onglet1 = new JPanel();
    JLabel titreOnglet1 = new JLabel("Onglet 1");
    JPanel onglet2 = new JPanel();
    JLabel titreOnglet2 = new JLabel("Onglet 2");
    
	TabsPanel() {
		this.setBackground(new Color(255, 0, 0));
		setPreferredSize(new Dimension(400, 600));
		setLayout(new BorderLayout());
		
	    onglet1.setPreferredSize(new Dimension(400, 500));
	    onglet1.add(titreOnglet1);
	    onglets.addTab("onglet1", onglet1);
	
	    onglet2.add(titreOnglet2);
	    onglets.addTab("onglet2", onglet2);

	    onglets.setOpaque(true);
	    add(onglets, BorderLayout.CENTER);
	}
}
