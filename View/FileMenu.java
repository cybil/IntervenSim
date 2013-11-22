import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.KeyStroke;

public class FileMenu extends JMenu {
	JMenuItem	newItem = new JMenuItem("New");
	JMenuItem	openItem = new JMenuItem("Open");
	JMenuItem	saveItem = new JMenuItem("Save");
	JMenuItem	saveAsItem = new JMenuItem("Save As...");
	JMenuItem	exportStatItem = new JMenuItem("Export Statistics");
	JMenuItem	exitItem = new JMenuItem("Exit");	
	
	FileMenu(String str) {
		setText(str);
		this.setMnemonic(KeyEvent.VK_F);
		
		newItem.addActionListener(new NewItemAction());
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		this.add(newItem);

		openItem.addActionListener(new OpenItemAction());
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		this.add(openItem);

		addSeparator();

		saveItem.addActionListener(new SaveItemAction());
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		this.add(saveItem);

		saveAsItem.addActionListener(new SaveAsItemAction());
		saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		add(saveAsItem);

		addSeparator();

		exportStatItem.addActionListener(new ExportStatItemAction());
		add(exportStatItem);

		addSeparator();

		exitItem.addActionListener(new ExitItemAction());
		add(exitItem);
	}

	public class NewItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("newItem\n");
			}
		}
	
	public class OpenItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("openItem\n");
			}
		}
	
	public class SaveItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("saveItem\n");
			}
		}
	
	public class SaveAsItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("saveAsItem\n");
			}
		}
	
	public class ExportStatItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("exportStatItem\n");
			}
		}
		
	public class ExitItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("exitItem\n");
			}
		}
}