import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

public class SelectButton extends JButton implements MouseListener {
	private String name;
	private Image img;
	boolean getCliked = false;
	
	
	SelectButton() {
//		super(title);
//	    this.name = title;
		this.setSize(new Dimension(48, 48));
	    try {
	      img = ImageIO.read(new File("cursor48x48WoB.png"));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    //Grace a cette instruction, notre objet va s'ecouter
	    //Des qu'un evenement de la souris sera intercepte, il en sera averti
	    this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
	    Graphics2D g2d = (Graphics2D)g;
	    g2d.drawImage(this.img, 0, 0, this.getWidth(), this.getHeight(), this);
	  }
	
	//Methode appelee lors du clic de souris
	  public void mouseClicked(MouseEvent event) {
			if (getCliked == false)
				try {
					this.img = ImageIO.read(new File("img/cursor48x48WoLG.png"));
				    } catch (IOException e) {
				    	e.printStackTrace();
				    	}
			else
				try {
					this.img = ImageIO.read(new File("img/cursor48x48WoB.png"));
				    } catch (IOException e) {
				    	e.printStackTrace();
				    	}
			getCliked = !(getCliked);
			}

	  //Methode appelee lors du survol de la souris
	  public void mouseEntered(MouseEvent event) { 
		  if (getCliked == false)
			  try {
				  this.img = ImageIO.read(new File("img/cursor48x48WoG.png"));
				  } catch (IOException e) {
					  e.printStackTrace();
					  }
	  }

	  //Methode appelee lorsque la souris sort de la zone du bouton
	  public void mouseExited(MouseEvent event) {
		  if (getCliked == false)
		  try {
		      this.img = ImageIO.read(new File("img/cursor48x48WoB.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  else
			  try {
			      this.img = ImageIO.read(new File("img/cursor48x48WoLG.png"));
			    } catch (IOException e) {
			      e.printStackTrace();
			    } 
	  }

	  //Methode appelee lorsque l'on presse le bouton gauche de la souris
	  public void mousePressed(MouseEvent event) { }

	  //Methode appelee lorsque l'on relache le clic de souris
	  public void mouseReleased(MouseEvent event) {

	  }
	  
	  protected void setGetCliked(boolean clic) {
		  getCliked = clic;
		  if (clic == false) {
			  try {
				  System.out.print("select efface et clic vaut : " + clic + " \n");
				  this.img = ImageIO.read(new File("img/cursor48x48WoB.png"));
				  } catch (IOException e) {
					  e.printStackTrace();
					  }
			  }
		  else
			  System.out.print("select NON efface et clic vaut : " + clic + " \n");
	  }
}