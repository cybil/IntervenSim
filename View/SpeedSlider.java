import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

class SpeedSlider extends JSlider {  

    private JLabel		label;

    public SpeedSlider(JLabel p_label) {
	this.label = p_label;
	this.setPreferredSize(new Dimension(500, 50));
	this.setMaximum(32);
	this.setMinimum(0);
	this.setValue(1);
	this.setPaintTicks(true);
	this.setPaintLabels(true);
	this.setMinorTickSpacing(2);
	this.setMajorTickSpacing(4);
	this.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent event){
		    label.setText("Animation speed: x" + ((JSlider)event.getSource()).getValue() + ":    ");
		    MainWindow.setAnimationSpeed(((JSlider)event.getSource()).getValue());
		}
	    });
    }

}
