import java.awt.Color;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class VehiculeButton extends JToggleButton implements MouseListener {
  private String name;
  private Image imgNotSelected;
  private Image imgSelected;
  private Image imgMouseEntered;
  private Image img;

  VehiculeButton() {
    this.setSize(48, 48);
    this.setPreferredSize(new Dimension(48, 48));
    this.setMaximumSize(new Dimension(48, 48));
    this.setMinimumSize(new Dimension(48, 48));
    try {
      this.imgNotSelected = ImageIO.read(new File("img/vehicule48x48WoB.png"));
      this.imgSelected = ImageIO.read(new File("img/vehicule48x48WoLG.png"));
      this.imgMouseEntered = ImageIO.read(new File("img/vehicule48x48WoG.png"));
    } catch (IOException e) {
      e.printStackTrace();
      setOpaque(false);
    }
    this.img = this.imgNotSelected;
    this.addMouseListener(this);
    this.setToolTipText("Creat the vehicle");
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;

    g2d.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
  }

  public void mouseClicked(MouseEvent event) {
    this.img = this.imgSelected;
  }

  public void mouseEntered(MouseEvent event) {
    if (this.isSelected() == false)
      this.img = this.imgMouseEntered;
  }

  //Methode appelee lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) {
    if (this.isSelected() == false)
      this.img = this.imgNotSelected;
    else
      this.img = this.imgSelected;
  }

  //Methode appelee lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Methode appelee lorsque l'on relache le clic de souris
  public void mouseReleased(MouseEvent event) {
  }

  protected void deselectButton() {
    this.img = this.imgNotSelected;
    this.repaint();
  }
}
