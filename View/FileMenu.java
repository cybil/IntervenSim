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
  private boolean		debug = false;

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
      if (debug == true) System.out.print("newItem\n");
      MapPanel.controller.eventNewFile();
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
      if (debug == true) System.out.print("openItem\n");
      JFileChooser chooser = new JFileChooser();

      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
	MapPanel.controller.eventLoadMap(new File(chooser.getSelectedFile().getAbsolutePath()));
      }
    }
  }

  public class SaveItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("saveItem\n");
      JFileChooser chooser = new JFileChooser();

      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
	MapPanel.controller.eventSaveMap(new File(chooser.getSelectedFile().getAbsolutePath()));
      }
    }
  }

  public class SaveAsItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("saveAsItem\n");
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
      if (debug == true) System.out.print("exportStatItem\n");
    }
  }

  public class ExitItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("exitItem\n");
      System.exit(0);
    }
  }
}
