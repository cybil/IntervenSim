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
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
    }

    public void		paintComponent(Graphics g) {
	super.paintComponent(g);
	this.setBounds(this.x, this.y, 20, 20);
	g.drawImage(this.currentImg, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("NODE --- Clicked ! X: " + e.getX() + " // Y: " + e.getY());
	if (this.isSelected == false) {
	    this.currentImg = this.imgSelected;
	    this.isSelected = true;
	}
	else {
	    this.currentImg = this.imgPassedOver;
	    this.isSelected = false;
	}
	    
    }

    public void mouseExited(MouseEvent e) {
	System.out.println("Node --- Exited !");
	if (this.isSelected == true)
	    this.currentImg = this.imgSelected;
	else
	    this.currentImg = this.imgNormal;
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("NODE --- Entered !");
	this.currentImg = this.imgPassedOver;
    }

    // Pour les Noeuds/Roads, voir pour faire une class NodeGraphic/RoadGraphic qui herite de JPanel et MouseListener, pour pouvoir detecter les events souris dessus et les dessiner plus facilement
    // Plus simple pour deplacer les composants sur la map, les selectionner, faire des effets dessus etc.

    static int	oldX;
    static int	oldY;

    public void mouseReleased(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    System.out.println("Node --- Released !");
	    oldX = e.getX();
	    oldY = e.getY();
	    this.setBounds(oldX, oldY, 20, 20);
	    MapPanel.setMovedNode(null);
	}
    }
    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    System.out.println("Node --- Pressed !");
	    oldX = e.getX();
	    oldY = e.getY();
	    MapPanel.setMovedNode(this);
	}
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Node --- Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    System.out.println("NODE --- Drag !");
	    this.x = e.getX();
	    this.y = e.getY();
	}
    }
} 
