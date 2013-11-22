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

public class ViewMenu extends JMenu {
	JMenuItem	ViewMenu1 = new JMenuItem("ViewMenu1");
	JMenuItem	ViewMenu2 = new JMenuItem("ViewMenu2");
	JMenuItem	ViewMenu3 = new JMenuItem("ViewMenu3");
	JMenuItem	ViewMenu4 = new JMenuItem("ViewMenu4");	
	ViewMenu(String str) {
		setText(str);

		ViewMenu1.addActionListener(new ViewMenu1Action());
		add(ViewMenu1);

		ViewMenu2.addActionListener(new ViewMenu2Action());
		add(ViewMenu2);

		ViewMenu3.addActionListener(new ViewMenu3Action());
		add(ViewMenu3);

		ViewMenu4.addActionListener(new ViewMenu4Action());
		add(ViewMenu4);


// .setMnemonic(KeyEvent.VK_R);
// .addActionListener(this);
// .setAccelerator(KeyStroke.getKeyStroke
// 					(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
// addSeparator();
	}

public class ViewMenu1Action implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("ViewMenu1\n");
		}
	}

	public class ViewMenu2Action implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("ViewMenu2\n");
		}
	}

	public class ViewMenu3Action implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("ViewMenu3\n");
		}
	}

	public class ViewMenu4Action implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("ViewMenu4\n");
		}
	}
}

