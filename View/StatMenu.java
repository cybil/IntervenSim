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

public class StatMenu extends JMenu {
	JMenuItem	exportItem = new JMenuItem("Export Stat");
	JMenuItem	historyItem = new JMenuItem("History");
	
	StatMenu(String str) {
	setText(str);
		this.setMnemonic(KeyEvent.VK_S);	

		exportItem.addActionListener(new ExportItemAction());
		this.add(exportItem);

		historyItem.addActionListener(new HistoryItemAction());
		this.add(historyItem);
	}

	public class ExportItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("ExportItem\n");
		}
	}

	public class HistoryItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("HistoryItem\n");
		}
	}
}