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

public class menu extends JMenu implements ActionListener{
	JMenuItem	item1 = new JMenuItem("Item1");
	JMenuItem	item2 = new JMenuItem("Item2");
	JMenuItem	item3 = new JMenuItem("Item3");
	JMenuItem	item4 = new JMenuItem("Item4");
	
	menu(String str) {
		setText(str);
		
		add(item1);
		add(item2);
		add(item3);
		add(item4);
		

		setMnemonic(KeyEvent.VK_C);
		
		item1.setMnemonic(KeyEvent.VK_R);
		item1.addActionListener(this);
		add(item1);
		
		item2.setAccelerator(KeyStroke.getKeyStroke
					(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
		item2.addActionListener(this);
		add(item2);
		
		item3.addActionListener(this);
		add(item3);

		addSeparator();

//		JMenu sousMenu = new JMenu("fond");
//		
//		ButtonGroup groupe = new ButtonGroup();

//		groupe.add(fondBleu);
//		fondBleu.addItemListener(this);
//		sousMenu.add(fondBleu);
//		
//		groupe.add(fondNoir);
//		fondNoir.addItemListener(this);
//		sousMenu.add(fondNoir);

//		add(sousMenu);
	}
	 public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == item1) System.out.print("item1");
		else if (source == item2) System.out.print("item2");
		else if (source == item3) System.out.print("item3");
		}
		
	    public void itemStateChanged(ItemEvent evt) {
		Object source = evt.getSource();
	//			if (source == fondBleu) ardoise.setBackground(Color.BLUE);
	//			else if (source == fondNoir) ardoise.setBackground(Color.BLACK);
	    }
}
