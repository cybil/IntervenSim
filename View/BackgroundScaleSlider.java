import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

class BackgroundScaleSlider extends JSlider {

  private JLabel		label;
  private final String		 value = "Background Image scale: ";


  public BackgroundScaleSlider(JLabel p_label) {
    this.label = p_label;
    this.setPreferredSize(new Dimension(200, 50));
    this.setMaximum(3000);
    this.setMinimum(10);
    this.setValue(100);
    this.setPaintTicks(true);
    this.setPaintLabels(true);
    this.setMinorTickSpacing(2);
    this.setMajorTickSpacing(4);
    label.setText(value + "0100%");
    this.addChangeListener(new ChangeListener(){
	public void stateChanged(ChangeEvent event){
	  String	value1;

	  value1 = ((JSlider)event.getSource()).getValue() + "%";
	  while (value1.length() < 5)
	    value1 = "0" + value1;

	  label.setText(value + value1);
	  MainWindow.setBackgroundScale(((JSlider)event.getSource()).getValue());
	}
      });
  }

}
