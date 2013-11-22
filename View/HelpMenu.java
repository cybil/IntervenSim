import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
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


// .setMnemonic(KeyEvent.VK_R);
// .addActionListener(this);
// .setAccelerator(KeyStroke.getKeyStroke
// 					(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
// addSeparator();
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
		}
	}
}

