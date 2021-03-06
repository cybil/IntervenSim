import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.KeyStroke;

public class EditMenu extends JMenu {
  JMenuItem	undoItem = new JMenuItem("Undo");
  JMenuItem	redoItem = new JMenuItem("Redo");
  JMenuItem	cutItem = new JMenuItem("Cut");
  JMenuItem	copyItem = new JMenuItem("Copy");
  JMenuItem	pasteItem = new JMenuItem("Paste");
  JMenuItem	selectAllItem = new JMenuItem("Select all");
  JMenuItem	reverseSelectionItem = new JMenuItem("Reverse selection");
  JMenuItem	preferencesItem = new JMenuItem("Preferences");
  JMenuItem	remove = new JMenuItem("Remove");
  private boolean	debug = false;


  EditMenu(String str) {
    setText(str);
    this.setMnemonic(KeyEvent.VK_E);
    undoItem.addActionListener(new UndoItemAction());
    undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
    this.add(undoItem);

    redoItem.addActionListener(new RedoItemAction());
    redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
    this.add(redoItem);
    this.addSeparator();

    cutItem.addActionListener(new CutItemAction());
    cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
    this.add(cutItem);

    copyItem.addActionListener(new CopyItemAction());
    copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
    this.add(copyItem);

    pasteItem.addActionListener(new PasteItemAction());
    pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
    this.add(pasteItem);

    this.addSeparator();

    selectAllItem.addActionListener(new SelectAllItemAction());
    selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
    this.add(selectAllItem);

    reverseSelectionItem.addActionListener(new ReverseSelectionItemAction());
    reverseSelectionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
    this.add(reverseSelectionItem);

    this.addSeparator();

    preferencesItem.addActionListener(new PreferencesItemAction());
    this.add(preferencesItem);

    this.addSeparator();
    remove.addActionListener(new RemoveAction());
    remove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, true));
    this.add(remove);
  }

  public class RemoveAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("Remove Item\n");
      MapPanel.deleteSelection();
      // MapPanel.controller.eventUndo();
    }
  }

  public class UndoItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("UndoItem\n");
      MapPanel.controller.eventUndo();
    }
  }

  public class RedoItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("redoItem\n");
      MapPanel.controller.eventRedo();
    }
  }

  public class CutItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("CutItem\n");
    }
  }

  public class CopyItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("CopyItem\n");
    }
  }

  public class PasteItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("PasteItem\n");
    }
  }

  public class ReverseSelectionItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("ReverseSelectionItem\n");
      // MapPanel.reverseSelect();
    }
  }

  public class SelectAllItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("SelectAllItem\n");
      MapPanel.selectAll();
    }
  }

  public class PreferencesItemAction implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (debug == true) System.out.print("PreferencesItem\n");
    }
  }
}
