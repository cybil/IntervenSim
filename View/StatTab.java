
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class StatTab extends javax.swing.JPanel {

  public StatTab() {
    initComponents();
    try
    {
      Image img = ImageIO.read(new File("img/panebutton.png"));
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

    jPanel1 = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jSeparator3 = new javax.swing.JSeparator();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jTextField4 = new javax.swing.JTextField();
    jTextField5 = new javax.swing.JTextField();
    jTextField6 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    kmTextField = new javax.swing.JTextField();
    speedTextField = new javax.swing.JTextField();
    efficiencyTextField = new javax.swing.JTextField();
    jLabel11 = new javax.swing.JLabel();
    strategyInUseTextField = new javax.swing.JTextField();
    jLabel12 = new javax.swing.JLabel();
    totalKmTextField = new javax.swing.JTextField();
    jSeparator2 = new javax.swing.JSeparator();
    vehiculeLogoLabel = new javax.swing.JLabel();
    refreshButton = new javax.swing.JButton();

    jPanel1.setPreferredSize(new java.awt.Dimension(146, 338));

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel6.setText("Vehicule");

    jLabel7.setText("Km :");

    jLabel8.setText("Speed :");

    jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel9.setText("Statistique");

    jLabel10.setText("Efficiency :");

    jTextField5.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  jTextField5ActionPerformed(evt);
	}
      });

    jTextField6.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  jTextField6ActionPerformed(evt);
	}
      });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			  .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addGroup(jPanel1Layout.createSequentialGroup()
				    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					      .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
					      .addComponent(jLabel6)
					      .addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(10, 10, 10)
							.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								  .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								  .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								  .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								  .addComponent(jTextField4)
								  .addComponent(jTextField5)
								  .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
				    .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
      );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel7)
			  .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel8)
			  .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
			  .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(199, Short.MAX_VALUE))
      );

    setPreferredSize(new java.awt.Dimension(146, 338));

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
    jLabel1.setText("Vehicule");

    jLabel2.setText("Km :");

    jLabel3.setText("Speed :");

    jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText("Statistique");

    jLabel5.setText("Efficiency :");

    kmTextField.setEditable(false);
    kmTextField.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  kmTextFieldActionPerformed(evt);
	}
      });

    speedTextField.setEditable(false);
    speedTextField.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  speedTextFieldActionPerformed(evt);
	}
      });

    efficiencyTextField.setEditable(false);
    efficiencyTextField.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  efficiencyTextFieldActionPerformed(evt);
	}
      });

    jLabel11.setText("Strategy in use :");

    strategyInUseTextField.setEditable(false);
    strategyInUseTextField.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  strategyInUseTextFieldActionPerformed(evt);
	}
      });

    jLabel12.setText("Total:");

    totalKmTextField.setEditable(false);
    totalKmTextField.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  totalKmTextFieldActionPerformed(evt);
	}
      });

    refreshButton.setText("Refresh");
    refreshButton.addActionListener(new java.awt.event.ActionListener() {
	public void actionPerformed(java.awt.event.ActionEvent evt) {
	  refreshButtonActionPerformed(evt);
	}
      });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			  .addComponent(vehiculeLogoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
			  .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			  .addComponent(jSeparator2)
			  .addComponent(strategyInUseTextField, javax.swing.GroupLayout.Alignment.TRAILING)
			  .addGroup(layout.createSequentialGroup()
				    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					      .addGroup(layout.createSequentialGroup()
							.addComponent(jLabel1)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
					      .addGroup(layout.createSequentialGroup()
							.addGap(10, 10, 10)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
									    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										      .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										      .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									    .addGap(5, 5, 5)
									    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										      .addComponent(speedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
										      .addComponent(efficiencyTextField)))
								  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
									    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										      .addGroup(layout.createSequentialGroup()
												.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										      .addGroup(layout.createSequentialGroup()
												.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGap(10, 10, 10)))
									    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										      .addComponent(kmTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
										      .addComponent(totalKmTextField)))))
					      .addComponent(jLabel11))
				    .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
      );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel1)
			  .addComponent(refreshButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel2)
			  .addComponent(kmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel12)
			  .addComponent(totalKmTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel3)
			  .addComponent(speedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			  .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
			  .addComponent(efficiencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strategyInUseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vehiculeLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
      );
  }

  private void efficiencyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    efficiencyTextField.setText(Integer.toString(MapPanel.controller.eventGetEfficiency()));
  }

  private void speedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    speedTextField.setText(Integer.toString(MapPanel.controller.eventGetSpeed()));
  }

  private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void strategyInUseTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    strategyInUseTextField.setText(MapPanel.controller.eventGetStrategy());
  }

  private void totalKmTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    totalKmTextField.setText(Integer.toString(MapPanel.controller.eventKmDone()));
  }

  private void kmTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
    kmTextField.setText(Integer.toString(MapPanel.controller.eventGetKm()));
  }

  private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
    kmTextFieldActionPerformed(evt);
    totalKmTextFieldActionPerformed(evt);
    strategyInUseTextFieldActionPerformed(evt);
    jTextField6ActionPerformed(evt);
    jTextField5ActionPerformed(evt);
    speedTextFieldActionPerformed(evt);
    efficiencyTextFieldActionPerformed(evt);
  }



  private javax.swing.JTextField efficiencyTextField;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JSeparator jSeparator3;
  private javax.swing.JTextField jTextField4;
  private javax.swing.JTextField jTextField5;
  private javax.swing.JTextField jTextField6;
  private javax.swing.JTextField kmTextField;
  private javax.swing.JButton refreshButton;
  private javax.swing.JTextField speedTextField;
  private javax.swing.JTextField strategyInUseTextField;
  private javax.swing.JTextField totalKmTextField;
  private javax.swing.JLabel vehiculeLogoLabel;

}
