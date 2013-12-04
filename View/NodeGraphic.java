import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class NodeGraphic extends JPanel implements MouseListener, MouseMotionListener {

    private JPopupMenu	jpm = new JPopupMenu();
    private JMenuItem	editNodeItem = new JMenuItem("Edit Node");
    private JMenuItem	addUrgencyItem = new JMenuItem("Add Urgency");
    private JMenuItem	setAttachmentPointItem = new JMenuItem("Set as Attachment Point");
    private JMenuItem	delete = new JMenuItem("Delete");

    private DeleteNode	toDel = new DeleteNode(this);

    protected Image	imgNormal;
    private Image	imgSelected;
    private Image	imgPassedOver;
    public Image	currentImg;
    private int		_node_x;
    private int		_node_y;
    // absolute Coord copy
    private int		oldx_win;
    private int		oldy_win;

    // Real coord from the real graph
    private int		real_x;
    private int		real_y;

    // pos du panel
    private int		image_x;
    private int		image_y;

    private boolean	isSelected = false;
    private int		buttonPressed = 0;

    public MapPanel.EObjectTools	type;

    private class DeleteNode implements ActionListener {
	NodeGraphic	node;
	public DeleteNode(NodeGraphic n) {
	    this.node = n;
	}

	public void actionPerformed(ActionEvent e) {
	    System.out.println("============================ DELETE ============");
	    MapPanel.deleteNode(node);
	}
    }

    public void	rescaleCoord(int curr_x_max, int curr_y_max, int real_x_max, int real_y_max)
    {
	this._node_x = MapPanel.unScale(this.real_x, curr_x_max, real_x_max);
	this._node_y = MapPanel.unScale(this.real_y, curr_y_max, real_y_max);
    }

    public Image	getImgNormal()  {
	return (this.imgNormal);
    }
    public Image	getImgSelected()  {
	return (this.imgSelected);
    }
    public Image	getImgPassedOver()  {
	return (this.imgPassedOver);
    }

    public int		getx() {
	return this._node_x;
    }
    public int		gety() {
	return this._node_y;
    }

    public void		setx(int new_x) {
	this._node_x = new_x;
    }

    public void		sety(int new_y) {
	this._node_y = new_y;
    }

    public void		setIsSelected(boolean b) {
	if (b == true)
	    this.currentImg = this.imgSelected;
	else
	    this.currentImg = this.imgNormal;
	this.isSelected = b;
    }

    public int		getRealX() {
	return (this.real_x);
    }

    public int		getRealY() {
	return (this.real_y);
    }

    public NodeGraphic() {
	System.out.println("NODE --- CONSTRUCTION DEFAULT");
    }

    public NodeGraphic(MapPanel.EObjectTools type, Image imgNormal,
		       Image imgSelected, Image imgPassedOver,
		       int p_x, int p_y,
		       int real_x, int real_y) {
	System.out.println("NODE --- CONSTRUCTION");
	this.type = type;
	this.imgNormal = imgNormal;
	this.imgSelected = imgSelected;
	this.imgPassedOver = imgPassedOver;
	this.currentImg = this.imgNormal;
	this._node_x = p_x;
	this._node_y = p_y;
	this.real_x = real_x;
	this.real_y = real_y;
	this.image_x = p_x - 10;
	this.image_y = p_y - 10;
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.setOpaque(false);
	this.jpm.add(this.delete);
	this.delete.addActionListener(this.toDel);
    }

    public void		paintComponent(Graphics g) {
	super.paintComponent(g);
	this.setBounds(this.getx(), this.gety(), this.currentImg.getWidth(null), this.currentImg.getHeight(null));
	g.drawImage(this.currentImg, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("NODE --- Clicked ! X: " + e.getX() + " // Y: " + e.getY());
	if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR
	    && this.isSelected == false) {
	    this.currentImg = this.imgSelected;
	    this.isSelected = true;
	}
	else if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR) {
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
	if (e.getButton() == MouseEvent.BUTTON1
	    && MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR)
	    {
	      this.real_x = this.getRealX() - MapPanel.scaleX(e.getX() - oldx_win);
	      this.real_y = this.getRealY() - MapPanel.scaleY(e.getY() - oldy_win);
	      this._node_x = e.getX();
	      this._node_y = e.getY();
	      MapPanel.setMovedNode2(this.getRealX(), this.getRealY());
	      MapPanel.setIsDragging(false);
	    }
	else if (e.getButton() == MouseEvent.BUTTON1
		 && MapPanel.selectedObject == MapPanel.EObjectTools.ROAD)
	    MapPanel.setRoadNode(this.getX(), this.getY());
    }

    public void mousePressed(MouseEvent e) {
	this.buttonPressed = e.getButton();
	if (e.getButton() == MouseEvent.BUTTON1)
	    if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR)
		{
		    System.out.println("Node --- Pressed !");
		    oldx_win = this.getx(); // Get relative windows coord X/Y
		    oldy_win = this.gety();
		    MapPanel.setMovedNode1(this.getRealX(), this.getRealY());
		    //editObject(this);
		}
	    else if (MapPanel.selectedObject == MapPanel.EObjectTools.VEHICULE) {
		System.out.println("COUCOU LES AMIS");
		MapPanel.setVehiculeAt(this.getx(), this.gety());
	    }
	if (e.getButton() == MouseEvent.BUTTON3) {
	    jpm.show(this, e.getX(), e.getY());
	}
	MapPanel.mouseX = this.getx();
	MapPanel.mouseY = this.gety();
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Node --- Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	if (this.buttonPressed == MouseEvent.BUTTON1
	    && MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR) {
	    System.out.println("NODE --- Drag !");
	    // int new_x = oldx_rel + (e.getX() - oldx);
	    // int new_y = oldy_rel + (e.getY() - oldy);
	    this._node_x = e.getX();
	    this._node_y = e.getY();
	    MapPanel.setIsDragging(true);
	    // MapPanel.setMovedNode2(this.getx(), this.gety());
	}
	MapPanel.mouseX = this.getx();
	MapPanel.mouseY = this.gety();
    }
}
