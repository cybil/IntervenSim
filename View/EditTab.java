import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;

public class EditTab extends JPanel {
	VehiculeTab vehiculeTab = new VehiculeTab();
	NodeTab nodeTab = new NodeTab();
	RoadTab roadTab = new RoadTab();
	CardLayout cl = new CardLayout();
	String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
	int indice = 0;
	JPanel content = new JPanel();
	
	EditTab() {
		this.setPreferredSize(new Dimension(400, 500));
	    this.add(new JLabel("Edit map"));
	    
	content.setLayout(cl);
    //On definit le layout
    content.setLayout(cl);
    //On ajoute les cartes a la pile avec un nom pour les retrouver
    content.add(vehiculeTab, listContent[0]);
    content.add(nodeTab, listContent[1]);
    content.add(roadTab, listContent[2]);

    add(content, BorderLayout.CENTER);
    this.setVisible(true);
	}
	
	protected void showPanel(int indice) {
        cl.show(content, listContent[indice]);
	}
}
