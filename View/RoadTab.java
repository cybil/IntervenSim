import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author Administrateur
 */
public class RoadTab extends javax.swing.JPanel {

    /**
     * Creates new form RoadTab
     */
    public RoadTab() {
        initComponents();
      try
      {
	Image img = ImageIO.read(new File("img/road48x48WoB.png"));
	img = img.getScaledInstance((int)img.getWidth(null) * 3,
				    (int)img.getHeight(null) * 3,
				    Image.SCALE_SMOOTH);

	roadLogoLabel.setIcon(new ImageIcon(img));
      }
      catch (Exception e)
      {
      }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        roadDeleteButton = new javax.swing.JButton();
        RoadAddButton = new javax.swing.JButton();
        toNodeTextFieldCoord_y = new javax.swing.JTextField();
        toNodeTextFieldCoord_x = new javax.swing.JTextField();
        fromNodeTextFieldCoord_x = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        fromNodeTextFieldCoord_y = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        roadLogoLabel = new javax.swing.JLabel();

        jLabel2.setText("To Node:");

        roadDeleteButton.setText("Delete Road");
        roadDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roadDeleteButtonActionPerformed(evt);
            }
        });

        RoadAddButton.setText("Add Road");
        RoadAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoadAddButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("From Node:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Road");

        jLabel5.setText("y");

        jLabel7.setText("y");

        jLabel6.setText("x");

        jLabel8.setText("x");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roadLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RoadAddButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roadDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(toNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toNodeTextFieldCoord_y))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fromNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromNodeTextFieldCoord_y))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromNodeTextFieldCoord_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toNodeTextFieldCoord_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RoadAddButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roadDeleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roadLogoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    private void roadDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int[]		from = {0, 0};
      int[]		to = {0, 0};

      try
      {
	from[0] = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	from[1] = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	to[0] = Integer.parseInt(toNodeTextFieldCoord_x.getText());
	to[1] = Integer.parseInt(toNodeTextFieldCoord_y.getText());
	if (MapPanel.controller.eventDeleteRoad(from, to) == false)
	  JOptionPane.showMessageDialog(null, "Unable to delete road from "
					+ from[0] + ":" + from[1]
					+ " to " + to[0] + ":" + to[1]);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide coord value.");
      }
    }

    private void RoadAddButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int[]		from = {0, 0};
      int[]		to = {0, 0};

      try
      {
	from[0] = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	from[1] = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	to[0] = Integer.parseInt(toNodeTextFieldCoord_x.getText());
	to[1] = Integer.parseInt(toNodeTextFieldCoord_y.getText());
	if (MapPanel.controller.eventAddRoad(from, to) == false)
	  JOptionPane.showMessageDialog(null, "Unable to add road from "
					+ from[0] + ":" + from[1]
					+ " to " + to[0] + ":" + to[1]);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide coord value.");
      }
    }


    private javax.swing.JButton RoadAddButton;
    private javax.swing.JTextField fromNodeTextFieldCoord_x;
    private javax.swing.JTextField fromNodeTextFieldCoord_y;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton roadDeleteButton;
    private javax.swing.JLabel roadLogoLabel;
    private javax.swing.JTextField toNodeTextFieldCoord_x;
    private javax.swing.JTextField toNodeTextFieldCoord_y;
}
