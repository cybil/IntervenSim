import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
