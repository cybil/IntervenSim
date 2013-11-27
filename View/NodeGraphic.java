import javax.swing.JPanel; 
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;


public class NodeGraphic extends JPanel implements MouseListener, MouseMotionListener {

    protected Image	imgNormal;
    private Image	imgSelected;
    private Image	imgPassedOver;
    public Image	currentImg;
    private int		_node_x;
    private int		_node_y;
    // absolute Coord copy
    private int		oldx;
    private int		oldy;
    // relative coord copy
    private int		oldx_rel;
    private int		oldy_rel;

    private boolean	isSelected = false;
    private int		buttonPressed = 0;

    public int		getx()
    {
	return (this._node_x);
    }
    public int		gety()
    {
	return (this._node_y);
    }
	
    public NodeGraphic()
    {
	System.out.println("NODE --- CONSTRUCTION DEFAULT");
    }

    public NodeGraphic(Image imgNormal, Image imgSelected, Image imgPassedOver, int p_x, int p_y) {
	System.out.println("NODE --- CONSTRUCTION");
	this.imgNormal = imgNormal;
	this.imgSelected = imgSelected;
	this.imgPassedOver = imgPassedOver;
	this.currentImg = this.imgNormal;
	this._node_x = p_x;
	this._node_y = p_y;
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
	MapPanel.setDestRoad(this);
    }

    public void mouseReleased(MouseEvent e) {
	System.out.println("Node --- Released !");
	if (e.getButton() == MouseEvent.BUTTON1)
	    {
		oldx = e.getXOnScreen();
		oldy = e.getYOnScreen();
		MapPanel.setMovedNode2(this.getx(), this.gety());
		MapPanel.setIsDragging(false);
	    }
	else if (e.getButton() == MouseEvent.BUTTON3)
	    MapPanel.setRoadNode2();
    }

    public void mousePressed(MouseEvent e) {
	this.buttonPressed = e.getButton();
	if (e.getButton() == MouseEvent.BUTTON1)
	    {
		System.out.println("Node --- Pressed !");
		oldx = e.getXOnScreen();
		oldy = e.getYOnScreen();
		oldx_rel = this.getx();
		oldy_rel = this.gety();
		MapPanel.setMovedNode1(this.getx(), this.gety());
	    }
	else if (e.getButton() == MouseEvent.BUTTON3) {
	    MapPanel.setRoadNode1(this.getx(), this.gety());
	}
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Node --- Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	if (this.buttonPressed == MouseEvent.BUTTON1) {
	    System.out.println("NODE --- Drag !");
	    int new_x = oldx_rel + (e.getXOnScreen() - oldx);
	    int new_y = oldy_rel + (e.getYOnScreen() - oldy);
	    this._node_x = new_x;
	    this._node_y = new_y;
	    MapPanel.setIsDragging(true);
	}
    }
} 
