import javax.swing.JPanel; 
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class NodeGraphic extends JPanel implements MouseListener, MouseMotionListener {
    
    public Image	imgNormal;
    public Image	imgSelected;
    public Image	imgPassedOver;
    public int		x;
    public int		y;
	
    public NodeGraphic(Image imgNormal, Image imgSelected, Image imgPassedOver, int x, int y) {
	this.imgNormal = imgNormal;
	this.imgSelected = imgSelected;
	this.imgPassedOver = imgPassedOver;
	this.x = x;
	this.y = y;
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("Clicked ! X: " + e.getX() + " // Y: " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
	System.out.println("Exited !");
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("Entered !");
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
