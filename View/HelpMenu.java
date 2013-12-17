import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

public class HelpMenu extends JMenu {
	JMenuItem	tutorialItem = new JMenuItem("Tutorial");
	JMenuItem	demoItem = new JMenuItem("Demo");
	JMenuItem	helpItem = new JMenuItem("Help");
	JMenuItem	aboutItem = new JMenuItem("About...");
	
	HelpMenu(String str) {
		setText(str);
		this.setMnemonic(KeyEvent.VK_H);

		tutorialItem.addActionListener(new TutorialItemAction());
		this.add(tutorialItem);

		demoItem.addActionListener(new DemoItemAction());
		this.add(demoItem);

		helpItem.addActionListener(new HelpItemAction());
		helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		this.add(helpItem);

		aboutItem.addActionListener(new AboutItemAction());
		this.add(aboutItem);
	}

	public class TutorialItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("tutorialItem\n");
			}
	}
	
		public class DemoItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("DemoItem\n");
			}
	}
	
		public class HelpItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("HelpItem\n");
			}
	}
	
		public class AboutItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("AboutItem\n");
			JFrame		about = new JFrame();
			JPanel		pane = new JPanel();
			JPanel		pane1 = new JPanel();
			JPanel		pane2 = new JPanel();
			JPanel		pane3 = new JPanel();
			JPanel		pane4 = new JPanel();
			JPanel		pane5 = new JPanel();
			JLabel		label1 = new JLabel("IntervenSim");
			JLabel		label2 = new JLabel("version 0.2");
			JLabel		label3 = new JLabel("Authors: ");
			JLabel		label4 = new JLabel("CMBOU5 - MEDUC34");
			JLabel		label5 = new JLabel("JBLAU4 - BEDEA1");
			about.setTitle("About IntervenSim");
			about.setSize(400, 350);
			about.setVisible(true);
			
			label1.setFont(new Font("Helvetica", 3, 50));
			label2.setFont(new Font("Verdana", 1, 10));
			label3.setFont(new Font("Verdana", 2, 20));
			label4.setFont(new Font("Verdana", 2, 30));
			label5.setFont(new Font("Verdana", 2, 30));
			pane1.add(label1, BorderLayout.CENTER);
			pane2.add(label2, BorderLayout.CENTER);
			pane3.add(label3, BorderLayout.CENTER);
			pane4.add(label4, BorderLayout.CENTER);
			pane5.add(label5, BorderLayout.CENTER);
			pane.setLayout(new GridLayout(5, 1));
			pane.add(pane1);
			pane.add(pane2);
			pane.add(pane3);
			pane.add(pane4);
			pane.add(pane5);
			about.setContentPane(pane);
			}
	}
}

