import javax.swing.JPanel; 
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class NodeGraphic extends JPanel implements MouseListener, MouseMotionListener {
    
    private Image	imgNormal;
    private Image	imgSelected;
    private Image	imgPassedOver;
    public Image	currentImg;
    public int		x;
    public int		y;
    private boolean	isSelected = false;
	
    public NodeGraphic(Image imgNormal, Image imgSelected, Image imgPassedOver, int x, int y) {
	this.imgNormal = imgNormal;
	this.imgSelected = imgSelected;
	this.imgPassedOver = imgPassedOver;
	this.currentImg = this.imgNormal;
	this.x = x;
	this.y = y;
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("Clicked ! X: " + e.getX() + " // Y: " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
	System.out.println("Exited !");
	if (this.isSelected == true)
	    this.currentImg = this.imgSelected;
	else
	    this.currentImg = this.imgNormal;
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("Entered !");
	this.currentImg = this.imgPassedOver;
    }

    // Pour les Noeuds/Roads, voir pour faire une class NodeGraphic/RoadGraphic qui herite de JPanel et MouseListener, pour pouvoir detecter les events souris dessus et les dessiner plus facilement
    // Plus simple pour deplacer les composants sur la map, les selectionner, faire des effets dessus etc.

    public void mouseReleased(MouseEvent e) {
	System.out.println("Released !");
    }

    public void mousePressed(MouseEvent e) {
	System.out.println("Pressed !");
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	System.out.println("Drag !");
    }

} 
