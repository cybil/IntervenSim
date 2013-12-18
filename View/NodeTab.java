import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/**
 *
 * @author Administrateur
 */
public class NodeTab extends javax.swing.JPanel {

    /**
     * Creates new form NodeTab
     */
    public NodeTab() {
        initComponents();
      try
      {
	Image img = ImageIO.read(new File("img/nodeNormal.png"));
	img = img.getScaledInstance((int)img.getWidth(null),
				    (int)img.getHeight(null),
				    Image.SCALE_SMOOTH);

	jLabel3.setIcon(new ImageIcon(img));

	img = ImageIO.read(new File("img/nodeUrgency.png"));
	img = img.getScaledInstance((int)img.getWidth(null),
				    (int)img.getHeight(null),
				    Image.SCALE_SMOOTH);

	jLabel9.setIcon(new ImageIcon(img));
      }
      catch (Exception e)
      {
      }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        toNodeTextFieldCoord_x = new javax.swing.JTextField();
        toNodeTextFieldCoord_y = new javax.swing.JTextField();
        nodeCoordChangeButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        nodeSetAttachPointButton = new javax.swing.JButton();
        nodeAddUrgencyButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fromNodeTextFieldCoord_y = new javax.swing.JTextField();
        fromNodeTextFieldCoord_x = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nodeAddButton = new javax.swing.JButton();
        nodeDeleteButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Node");

        jLabel5.setText("y");

        nodeCoordChangeButton.setText("Change coord");
        nodeCoordChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeCoordChangeButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("x");

        nodeSetAttachPointButton.setText("Set as attach point");
        nodeSetAttachPointButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeSetAttachPointButtonActionPerformed(evt);
            }
        });

        nodeAddUrgencyButton.setText("Add urgency");
        nodeAddUrgencyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeAddUrgencyButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("y");

        jLabel8.setText("x");

        jLabel1.setText("Source position:");

        jLabel2.setText("Destination:");

        nodeAddButton.setText("Add node");
        nodeAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeAddButtonActionPerformed(evt);
            }
        });

        nodeDeleteButton.setText("Delete node");
        nodeDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodeDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nodeAddUrgencyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(nodeCoordChangeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toNodeTextFieldCoord_y))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromNodeTextFieldCoord_y))
                    .addComponent(nodeAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nodeDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nodeSetAttachPointButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromNodeTextFieldCoord_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toNodeTextFieldCoord_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toNodeTextFieldCoord_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeCoordChangeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeSetAttachPointButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeAddUrgencyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeAddButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeDeleteButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }

    private void nodeCoordChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int[]		from = {0, 0};
      int[]		to = {0, 0};

      try
      {
	from[0] = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	from[1] = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	to[0] = Integer.parseInt(toNodeTextFieldCoord_x.getText());
	to[1] = Integer.parseInt(toNodeTextFieldCoord_y.getText());
	if (MapPanel.controller.eventEditNodeCoord(from, to) == false)
	  JOptionPane.showMessageDialog(null, "Unable to move node from "
					+ from[0] + ":" + from[1]
					+ " to " + to[0] + ":" + to[1]);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide coord value.");
      }
    }

    private void nodeSetAttachPointButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int		x;
      int		y;
      NodeGraphic	node;

      try
      {
	x = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	y = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	MapPanel.moveAttachPointAt(x, y);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide Coord value.");
      }
    }

    private void nodeAddUrgencyButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int		x;
      int		y;
      NodeGraphic	node;

      try
      {
	x = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	y = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	node = MapPanel._getNode(x, y);
	if (node != null)
	{
	  Property	prop = new Property(node);
	}
	else
	  JOptionPane.showMessageDialog(null, "Unable to find node at " + x + ":" + y);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide Coord value.");
      }
    }

    private void nodeAddButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int		x;
      int		y;

      try
      {
	x = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	y = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	if (MapPanel.controller.eventPutNode(x, y) == false)
	  JOptionPane.showMessageDialog(null, "There is already a node at " + x + ":" + y);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide Coord value.");
      }
    }

    private void nodeDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
      int[]		coord = {0, 0};

      try
      {
	coord[0] = Integer.parseInt(fromNodeTextFieldCoord_x.getText());
	coord[1] = Integer.parseInt(fromNodeTextFieldCoord_y.getText());
	if (MapPanel.controller.eventDeleteNode(coord) == false)
	  JOptionPane.showMessageDialog(null, "Unable to delete node at " + coord[0] + ":" + coord[1]);
      }
      catch (Exception e)
      {
	JOptionPane.showMessageDialog(null, "Invalide Coord value.");
      }
    }

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
    private javax.swing.JButton nodeAddButton;
    private javax.swing.JButton nodeAddUrgencyButton;
    private javax.swing.JButton nodeCoordChangeButton;
    private javax.swing.JButton nodeDeleteButton;
    private javax.swing.JButton nodeSetAttachPointButton;
    private javax.swing.JTextField toNodeTextFieldCoord_x;
    private javax.swing.JTextField toNodeTextFieldCoord_y;
}
