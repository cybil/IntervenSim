import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class AddUrgency extends JFrame implements ActionListener {

    private Property		parent;

    private JPanel		panelTrigg = new JPanel();
    private JTextField		jtfTrigg = new JTextField("0");
    private JLabel		labelTrigg = new JLabel("Trigger date:");

    private JPanel		panelTreat = new JPanel();
    private JTextField		jtfTreat = new JTextField("5");
    private JLabel		labelTreat = new JLabel("Treatment time: ");

    private JPanel		panelButton = new JPanel();
    private JButton		ok = new JButton("Add");
    private JButton		cancel = new JButton("Cancel");

    public AddUrgency(Property parentFrame) {
      super.setSize(300, 400);
	this.parent = parentFrame;
	configure();
	fill();

	this.ok.addActionListener(this);
	this.cancel.addActionListener(this);
    }

    void		configure() {
	this.setVisible(true);
	this.setTitle("Add urgency");
	this.setSize(300, 200);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setAlwaysOnTop(true);
    }

    void		fill() {
	this.jtfTrigg.setPreferredSize(new Dimension(100, 30));
	this.panelTrigg.setLayout(new FlowLayout());
	this.panelTrigg.add(labelTrigg);
	this.panelTrigg.add(jtfTrigg);

	this.jtfTreat.setPreferredSize(new Dimension(100, 30));
	this.panelTreat.setLayout(new FlowLayout());
	this.panelTreat.add(labelTreat);
	this.panelTreat.add(jtfTreat);

	this.panelButton.setLayout(new FlowLayout());
	this.panelButton.add(cancel);
	this.panelButton.add(ok);

	this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
	this.getContentPane().setBackground(Color.GRAY);
	this.getContentPane().add(panelTrigg);
	this.getContentPane().add(panelTreat);
	this.getContentPane().add(panelButton);
    }

    public void actionPerformed(ActionEvent arg0) {
	if (arg0.getSource() == ok) {
	    this.parent.addUrgencyToList(this.jtfTrigg.getText(), this.jtfTreat.getText());
	    this.dispose();
	}
	if (arg0.getSource() == cancel) {
	    this.dispose();
	}
    }      
   
}
