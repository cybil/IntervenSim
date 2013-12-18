import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author Administrateur
 */
public class VehiculeTab extends javax.swing.JPanel {

  public VehiculeTab() {
    initComponents();
    try
    {
      Image img = ImageIO.read(new File("img/vehicule.png"));
      img = img.getScaledInstance((int)img.getWidth(null) * 3,
				  (int)img.getHeight(null) * 3,
				  Image.SCALE_SMOOTH);

      vehiculeLogoLabel.setIcon(new ImageIcon(img));
    }
    catch (Exception e)
    {
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    strategyComboBox = new javax.swing.JComboBox();
    waitingStrategyComboBox = new javax.swing.JComboBox();
    jLabel3 = new javax.swing.JLabel();
    TextFieldCoord_x = new javax.swing.JTextField();
    TextFieldCoord_y = new javax.swing.JTextField();
    vehiculeCoordChangeButton = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    attachPointChangeButton = new javax.swing.JButton();
    vehiculeLogoLabel = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();

    jLabel1.setText("Strategy");

    jLabel2.setText("Waiting strategy");

    strategyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Oldest", "Nearest" }));
    strategyComboBox.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  strategyComboBoxActionPerformed(evt);
	}
      });

    waitingStrategyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Waiting", "Move to attach point" }));
    waitingStrategyComboBox.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  waitingStrategyComboBoxActionPerformed(evt);
	}
      });

    jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("Vehicule");

    vehiculeCoordChangeButton.setText("Change coord");
    vehiculeCoordChangeButton.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  vehiculeCoordChangeButtonActionPerformed(evt);
	}
      });

    jLabel4.setText("x");

    jLabel5.setText("y");

    attachPointChangeButton.setText("Change attach point");
    attachPointChangeButton.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  attachPointChangeButtonActionPerformed(evt);
	}
      });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
			  .addComponent(jSeparator1)
			  .addComponent(vehiculeLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
				    .addComponent(jLabel4)
				    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				    .addComponent(TextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
				    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				    .addComponent(jLabel5)
				    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				    .addComponent(TextFieldCoord_y))
			  .addComponent(waitingStrategyComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addComponent(attachPointChangeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
			  .addComponent(vehiculeCoordChangeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addComponent(strategyComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
				    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					      .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
					      .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
				    .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
      );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waitingStrategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(TextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			  .addComponent(TextFieldCoord_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			  .addComponent(jLabel4)
			  .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehiculeCoordChangeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attachPointChangeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehiculeLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
      );
  }

  private void vehiculeCoordChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try
    {
      MapPanel.moveVehiculeAt(Integer.parseInt(TextFieldCoord_x.getText()),
			      Integer.parseInt(TextFieldCoord_y.getText()));
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Invalide Coord value.");
    }
  }

  private void attachPointChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
    try
    {
      MapPanel.moveAttachPointAt(Integer.parseInt(TextFieldCoord_x.getText()),
				 Integer.parseInt(TextFieldCoord_y.getText()));
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Invalide Coord value.");
    }

  }

  private void waitingStrategyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
    JComboBox		cb = ((JComboBox)evt.getSource());

    MapPanel.controller.eventSetWaitingStrategy(cb.getSelectedIndex());
  }

  private void strategyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
    JComboBox		cb = ((JComboBox)evt.getSource());

    MapPanel.controller.eventSetStrategy(cb.getSelectedIndex());
  }



  private javax.swing.JTextField TextFieldCoord_x;
  private javax.swing.JTextField TextFieldCoord_y;
  private javax.swing.JButton attachPointChangeButton;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JComboBox strategyComboBox;
  private javax.swing.JButton vehiculeCoordChangeButton;
  private javax.swing.JLabel vehiculeLogoLabel;
  private javax.swing.JComboBox waitingStrategyComboBox;

}
