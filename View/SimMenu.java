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

public class SimMenu extends JMenu {
	JMenuItem	playItem = new JMenuItem("Play");
	JMenuItem	stopItem = new JMenuItem("Stop");
	JMenuItem	pauseItem = new JMenuItem("Pause");
	JMenuItem	skipAnimationItem = new JMenuItem("Skip animation");
	JMenuItem	chooseSpeedItem = new JMenuItem("Choose speed");
	
	SimMenu(String str) {
		setText(str);
		this.setMnemonic(KeyEvent.VK_I);
		
		playItem.addActionListener(new PlayItemAction());
		this.add(playItem);

		stopItem.addActionListener(new StopItemAction());
		this.add(stopItem);

		pauseItem.addActionListener(new PauseItemAction());
		this.add(pauseItem);

		skipAnimationItem.addActionListener(new SkipAnimationItemAction());
		add(skipAnimationItem);

		chooseSpeedItem.addActionListener(new ChooseSpeedItemAction());
		add(chooseSpeedItem);
	}

	public class PlayItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("playItem\n");
			}
		}

	public class StopItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("stopItem\n");
			}
		}

	public class PauseItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("pauseItem\n");
			}
		}

	public class SkipAnimationItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("skipAnimationItem\n");
			}
		}

public class ChooseSpeedItemAction implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("chooseSpeedItem\n");
		}
	}
	
public class ExitItemAction implements ActionListener {
	public void actionPerformed(ActionEvent evt) {
		System.out.print("exitItem\n");
		}
	}
}
