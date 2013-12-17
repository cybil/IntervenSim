import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

class Property extends JFrame implements ActionListener {

    private JPanel		panelX = new JPanel();
    private JTextField jtfX = new JTextField();
    private JLabel labelX = new JLabel("X: ");

    private JPanel		panelY = new JPanel();
    private JTextField jtfY = new JTextField();
    private JLabel labelY = new JLabel("Y: ");

    private JPanel		panelAttachPoint = new JPanel();
    private JCheckBox		attachPoint = new JCheckBox("Attach point ");

    private JPanel		panelAddUrgency = new JPanel();
    private JButton		addUrgency = new JButton("Add urgency");

    private JPanel		panelButton = new JPanel();
    private JButton		ok = new JButton("Apply");
    private JButton		cancel = new JButton("Cancel");

    private JPanel		panelList = new JPanel();
    private Vector<String>	listObj = new Vector<String>();
    private JList		list = new JList(listObj);
    private JButton		deleteUrgency = new JButton("Delete selected urgency");

    private NodeGraphic			node = null;

    public Property(NodeGraphic n) {
      super.setSize(300, 400);
	this.node = n;
	configureJDial();
	fillJDial();

	this.addUrgency.addActionListener(this);
	this.deleteUrgency.addActionListener(this);
	this.ok.addActionListener(this);
	this.cancel.addActionListener(this);
    }

    void	configureJDial() {
	this.setTitle("Edit property");
	this.setVisible(true);
	this.setSize(300, 400);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setAlwaysOnTop(true);
    }

    void	fillJDial() {
	this.jtfX.setText(Integer.toString(this.node.getRealX()));
	this.jtfX.setPreferredSize(new Dimension(100, 30));
	this.panelX.setLayout(new FlowLayout());
	this.panelX.add(labelX);
	this.panelX.add(jtfX);

	this.jtfY.setText(Integer.toString(this.node.getRealY()));
	this.jtfY.setPreferredSize(new Dimension(100, 30));
	this.panelY.setLayout(new FlowLayout());
	this.panelY.add(labelY);
	this.panelY.add(jtfY);

	this.panelAttachPoint.setLayout(new FlowLayout());
	if (MapPanel.getAttachPoint(this.node) == true)
	    attachPoint.setSelected(true);
	this.panelAttachPoint.add(attachPoint);

	this.panelAddUrgency.setLayout(new FlowLayout());
	this.panelAddUrgency.add(addUrgency);

	this.panelButton.setLayout(new FlowLayout());
	this.panelButton.add(cancel);
	this.panelButton.add(ok);
	
	this.listObj.addAll(MapPanel.getUrgencyList(this.node));
	list.updateUI();

	this.panelList.setLayout(new FlowLayout());
	list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	list.setSelectedIndex(0);
	JScrollPane listScroller = new JScrollPane(list);
	listScroller.setPreferredSize(new Dimension(100, 80));
	this.deleteUrgency.setPreferredSize(new Dimension(250, 30));
	this.panelList.add(deleteUrgency);

	this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
	this.getContentPane().setBackground(Color.GRAY);
	this.getContentPane().add(panelX);
	this.getContentPane().add(panelY);
	this.getContentPane().add(panelAttachPoint);	
	this.getContentPane().add(panelAddUrgency);	
	this.getContentPane().add(listScroller);	
	this.getContentPane().add(panelList);	
	this.getContentPane().add(panelButton);	
    }

    public void actionPerformed(ActionEvent arg0) {
	if (arg0.getSource() == addUrgency) {
	    AddUrgency	frame = new AddUrgency(this);
	}
	if (arg0.getSource() == deleteUrgency) {
	    if (this.list.getSelectedIndex() != -1)
		this.listObj.remove(this.list.getSelectedIndex());
	    this.list.updateUI();
	}
	if (arg0.getSource() == ok) {
	    MapPanel.moveNode(this.node, Integer.parseInt(this.jtfX.getText()),
			      Integer.parseInt(this.jtfY.getText()));

	    MapPanel.clearUrgency(this.node);

	    ListModel	model = list.getModel();

	    for (int i = 0; i < model.getSize(); i++) {
	    	String		str = (String)model.getElementAt(i);
	    	float		trigg = Float.parseFloat(str.substring(str.indexOf(":") + 2,
	    							       str.indexOf(";") - 1));
	    	float		treat = Float.parseFloat(str.substring(str.lastIndexOf(":") + 2));
	    	MapPanel.addUrgencyToNode(this.node, trigg, treat, i);
	    }

	    if (this.attachPoint.isSelected() == true)
		MapPanel.setAttachPoint(this.node);
	    this.dispose();
	}
	if (arg0.getSource() == cancel) {
	    this.dispose();
	}
    }      

    public void	addUrgencyToList(String trigg, String treat) {
	this.listObj.add("Date: " + trigg + " ; Treatment: " + treat);
	this.list.updateUI();
    }

}
