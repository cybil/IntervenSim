import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FileMenu extends JMenu {
	private JMenuItem		newItem = new JMenuItem("New");
	private JMenuItem		openItem = new JMenuItem("Open");
	private JMenuItem		saveItem = new JMenuItem("Save");
	private JMenuItem		saveAsItem = new JMenuItem("Save As...");
	private JMenuItem		exportStatItem = new JMenuItem("Export Statistics");
	private JMenuItem		exitItem = new JMenuItem("Exit");	
	private JFileChooser 	fc = new JFileChooser();
	private JFrame 			openDialog = new JFrame();
	private File file;
	
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
			// check si chgment  depuis dernier save ou si fichier non save
			// si oui fenetre;
			// si user ok
			// save
			// sinon quitte
			
			//	if ((b = comparetosavedFilemap()) {
			//		openWinDiag()
			//		if (ok) {
			//		openSave();		
			//		}
			//	}
			// exit
			}
		}
	
	// appeler existeFileMap => Map
	// saveMap => FileManager
	// loadMap = FileManager
	public class OpenItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("openItem\n");
			
			//	int returnVal = fc.showOpenDialog(openDialog);
			//	if (returnVal == JFileChooser.APPROVE_OPTION) {
			//	open()
			//	} else {
			//	
			// }
			
//					file = fc.getSelectedFile();//This is where a real application would open the file.
//		            System.out.print("Opening: " + file.getName() + "\n");
//		            System.out.print("In directory: " + file.getAbsolutePath() + "\n");
//		            } else {
//		        	file = fc.getSelectedFile();
//		        	System.out.print("Can't opening: " + file.getName() + "\n");
		        }
			}
	
	public class SaveItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("saveItem\n");
//			//	exist?
			//	if true {save(getFileMap)}
			//	else 
			//		saveas
		

			
//			int returnVal = fc.showSaveDialog(openDialog);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					file = fc.getSelectedFile();//This is where a real application would open the file.
//		            System.out.print("Saving: " + file.getName() + "\n");
//		            System.out.print("In directory: " + fc.getCurrentDirectory().getName() + "\n");
//		            
//		            } else {
//		        	file = fc.getSelectedFile();
//		        	System.out.print("Can't save: " + file.getName() + "\n");
		        }
			}
	
	public class SaveAsItemAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			System.out.print("saveAsItem\n");
			//	int returnVal = fc.showOpenDialog(openDialog);
			//	if (returnVal == JFileChooser.APPROVE_OPTION) {
			//	open()
			//	} else {
			//	
			// }
			}
		}
	
	// appeler existeFileStat => Map
	// saveStat => StatManager
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