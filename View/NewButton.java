import java.awt.Color;
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
import java.awt.Dimension;

public class NewButton extends JButton implements MouseListener{
  private String name;
  private Image img;

  NewButton() {
    this.setSize(48, 48);
    this.setPreferredSize(new Dimension(48, 48));
    this.setMaximumSize(new Dimension(48, 48));
    this.setMinimumSize(new Dimension(48, 48));
    try {
      img = ImageIO.read(new File("img/newbutton.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.setOpaque(false);
    this.addMouseListener(this);
    this.setToolTipText("New file");
  }

  public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D)g;

    g2d.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
  }

  //Methode appelee lors du clic de souris
  public void mouseClicked(MouseEvent event) {
  }

  //Methode appelee lors du survol de la souris
  public void mouseEntered(MouseEvent event) {
  }

  //Methode appelee lorsque la souris sort de la zone du bouton
  public void mouseExited(MouseEvent event) {
  }

  //Methode appelee lorsque l'on presse le bouton gauche de la souris
  public void mousePressed(MouseEvent event) { }

  //Methode appelee lorsque l'on relache le clic de souris
  public void mouseReleased(MouseEvent event) {
  }
  protected void setGetCliked(boolean clic) {
  }
}
