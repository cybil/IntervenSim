import java.awt.Color;
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

public class UndoButton extends JButton implements MouseListener{
    private Image img;
		
    UndoButton() {
	this.setSize(48, 48);
	this.setPreferredSize(new Dimension(48, 48));
	this.setMaximumSize(new Dimension(48, 48));
	this.setMinimumSize(new Dimension(48, 48));
	//				super(title);
	//			    this.name = title;
	try {
	    img = ImageIO.read(new File("img/undobutton.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	//Grace a cette instruction, notre objet va s'ecouter
	//Des qu'un evenement de la souris sera intercepte, il en sera averti
	this.setOpaque(false);
	this.addMouseListener(this);
	this.setToolTipText("Undo");
    }
		
    public void paintComponent(Graphics g){
	Graphics2D g2d = (Graphics2D)g;
	//		    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	//		    g2d.setPaint(gp);
	g2d.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
	//		    g2d.setColor(Color.black);
	//		    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() /  2 /4), (this.getHeight() / 2) + 5);
    }
		
    //Methode appelee lors du clic de souris
    public void mouseClicked(MouseEvent event) {
	// this.controller.eventUndo();
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