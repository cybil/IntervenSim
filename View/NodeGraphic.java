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
    private int		_node_x;
    private int		_node_y;
    private int		oldx;
    private int		oldy;

    private boolean	isSelected = false;

    public int		getx()
    {
	return (this._node_x);
    }
    public int		gety()
    {
	return (this._node_y);
    }
	
    public NodeGraphic(Image imgNormal, Image imgSelected, Image imgPassedOver, int p_x, int p_y) {
	this.imgNormal = imgNormal;
	this.imgSelected = imgSelected;
	this.imgPassedOver = imgPassedOver;
	this.currentImg = this.imgNormal;
	this._node_x = p_x;
	this._node_y = p_y;
	//System.out.println("NODE --- create ! X: " + this.x + " // Y: " + this.y);
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
    }

    public void		paintComponent(Graphics g) {
	super.paintComponent(g);
	this.setBounds(this.getx(), this.gety(), 20, 20);
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

    public void mouseReleased(MouseEvent e) {
	System.out.println("Node --- Released !");
	if (e.getButton() == MouseEvent.BUTTON3)
	    {
		oldx = e.getXOnScreen();
		oldy = e.getYOnScreen();
	    }
    }

    public void mousePressed(MouseEvent e) {
	System.out.println("Node --- Pressed !");
	if (e.getButton() == MouseEvent.BUTTON3)
	    {
		oldx = e.getXOnScreen();
		oldy = e.getYOnScreen();
	    }
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Node --- Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	System.out.println("NODE --- Drag !");
	int new_x = oldx_rel + (e.getXOnScreen() - oldx);
	int new_y = oldy_rel + (e.getYOnScreen() - oldy);
	this._node_x = new_x;
	this._node_y = new_y;
    }
} 
