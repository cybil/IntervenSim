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
			}
	}
}

