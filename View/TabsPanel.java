import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.*;

public class TabsPanel extends JPanel {
	TabsPanel() {
		this.setBackground(new Color(255, 0, 0));
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		setPreferredSize(new Dimension(400, 600));
	    
		//JPanel daddy = new JPanel();
		setLayout(new BorderLayout());
		
		
		
		
		JPanel onglet1 = new JPanel();
	    JLabel titreOnglet1 = new JLabel("Onglet 1");
	    onglet1.add(titreOnglet1);
	    onglet1.setPreferredSize(new Dimension(400, 500));
	    onglets.addTab("onglet1", onglet1);
	
	    JPanel onglet2 = new JPanel();
	    JLabel titreOnglet2 = new JLabel("Onglet 2");
	    onglet2.add(titreOnglet2);
	    onglets.addTab("onglet2", onglet2);
	
	    
	    
	    onglets.setOpaque(true);
	    //add(onglets);
	    add(onglets, BorderLayout.CENTER);
	    //daddy.setPreferredSize(new Dimension(400, 600));
	    //daddy.add(onglets);
	    //add(daddy);
	}
}
