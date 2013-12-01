import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;

public class TabsPanel extends JPanel {
	JTabbedPane tabs = new JTabbedPane(SwingConstants.TOP);
	EditTab editTab = new EditTab();
    StatTab statTab = new StatTab();
    protected HideButton hideButton = new HideButton();
    private GridBagConstraints	gbc = new GridBagConstraints();
    
	TabsPanel() {
//		this.setBackground(new Color(255, 0, 0));
		this.setSize(new Dimension(225, 600));
		this.setPreferredSize(new Dimension(225, 600));
		this.setMaximumSize(new Dimension(225, 600));
		this.setMinimumSize(new Dimension(225, 600));
		this.setLayout(new GridBagLayout());
//		pan.setBackground(new Color(255, 0, 0));
//		pan.setPreferredSize(new Dimension(380, 600));
//		pan.setLayout(new BorderLayout());
		
		this.setTabs();
		
		gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = GridBagConstraints.RELATIVE;	//Avant dernier de la ligne
    	gbc.gridheight = GridBagConstraints.REMAINDER; //Dernier de la colone
    	gbc.weightx = 1;
    	gbc.weighty = 1;
    	gbc.fill = GridBagConstraints.VERTICAL;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	this.add(tabs, gbc);
		
    	gbc.gridx = GridBagConstraints.RELATIVE;
    	gbc.gridy = 0;
    	gbc.gridwidth = GridBagConstraints.REMAINDER;	//Avant dernier de la ligne
    	gbc.gridheight = GridBagConstraints.REMAINDER; //Dernier de la colone
    	gbc.weightx = 1;
    	gbc.weighty = 1;
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_END;
//		this.add(pan);
		this.add(hideButton, gbc);
	}
	
	void setTabs() {
	    tabs.addTab("Map", editTab);
	    tabs.addTab("Stat", statTab);
	    tabs.setOpaque(true);
//	    pan.add(tabs, BorderLayout.CENTER);
//	    this.add(hideButton, BorderLayout.EAST);
	}
}
