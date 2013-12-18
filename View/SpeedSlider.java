import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

class SpeedSlider extends JSlider {

    private JLabel		label;
  private final String		value = "Animation speed: x";

    public SpeedSlider(JLabel p_label) {
	this.label = p_label;
	this.setPreferredSize(new Dimension(200, 50));
	this.setMaximum(32);
	this.setMinimum(0);
	this.setValue(1);
	this.setPaintTicks(true);
	this.setPaintLabels(true);
	this.setMinorTickSpacing(2);
	this.setMajorTickSpacing(4);
	label.setText(value + "01");
	this.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent event){
		  String	value1;

		  value1 = Integer.toString(((JSlider)event.getSource()).getValue());
		  while (value1.length() < 2)
		    value1 = "0" + value1;
		  label.setText(value + value1);
		    MainWindow.setAnimationSpeed(((JSlider)event.getSource()).getValue());
		}
	    });
    }

}
