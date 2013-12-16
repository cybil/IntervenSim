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

public class VehiculeGraphic extends JPanel implements MouseListener, MouseMotionListener {

    private JPopupMenu	jpm = new JPopupMenu();
    // private JMenuItem	property = new JMenuItem("Property");
    private JMenuItem	delete = new JMenuItem("Delete selection");

    private DeleteNode		toDel = new DeleteNode(this);
    // private EditProperty	editProperty = new EditProperty(this);


    // A utiliser pour resize l'image en zoom
    // public Image getScaledInstance(int width,
    // 				 int height,
    // 				 int hints)
    // Original
    protected Image	_imgNormal;

    protected Image	imgNormal;
    public Image	currentImg;
    private int		_node_x;
    private int		_node_y;
    // absolute Coord copy
    private int		oldx_win;
    private int		oldy_win;
    private int		oldx_screen;
    private int		oldy_screen;

    // Real coord from the real graph
    private int		real_x;
    private int		real_y;

    // pos du panel
    private int		image_x;
    private int		image_y;

    private boolean	isSelected = false;
    private int		buttonPressed = 0;


    // private class EditProperty implements ActionListener {
    // 	NodeGraphic	node;
    // 	public EditProperty(NodeGraphic n) {
    // 	    this.node = n;
    // 	}
    // 	public void actionPerformed(ActionEvent e) {
    // 	    Property	prop = new Property(node);
    // 	}
    // }

    private class DeleteNode implements ActionListener {
	VehiculeGraphic	node;
	public DeleteNode(VehiculeGraphic n) {
	    this.node = n;
	}

	public void actionPerformed(ActionEvent e) {
	    System.out.println("============================ DELETE ============");
	    MapPanel.deleteVehicule();
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

    public int		getx() {
	return this._node_x;
    }

    public int		gety() {
	return this._node_y;
    }

    public void		scaleImage()
    {
	int		tmp;
	int		new_img_width;
	int		new_img_heigth;

	// tmp = this.imgNormal.getWidth(null);
	new_img_width = MapPanel.unScaleX(25);

	// new_img_heigth = this.imgNormal.getHeight(null);
	new_img_heigth = MapPanel.unScaleY(25);
	this.imgNormal = this._imgNormal.getScaledInstance(new_img_width,
							   new_img_heigth,
							   Image.SCALE_SMOOTH);
    }

    public void		setx(int new_x) {

	this._node_x = new_x;
	// this._node_x += -25;
    }

    public void		sety(int new_y) {
	this._node_y = new_y;
	// this._node_y += -25;
    }

    public int		getRealX() {
	return (this.real_x);
    }
    public int		getRealY() {
	return (this.real_y);
    }

    public void		setRealX(int new_real_y) {
	this.real_x = new_real_y;
    }
    public void		setRealY(int new_real_y) {
	this.real_y = new_real_y;
    }

    public boolean	isVehicule() {
	return true;
    }

    public VehiculeGraphic() {
	System.out.println("NODE --- CONSTRUCTION DEFAULT");
    }

    public VehiculeGraphic( Image imgNormal, int p_x, int p_y,
			int real_x, int real_y) {
	System.out.println("NODE --- CONSTRUCTION");
	this.imgNormal = imgNormal;
	this._imgNormal = imgNormal;
	this.currentImg = this.imgNormal;
	this.setx(p_x);
	this.sety(p_y);
	this.real_x = real_x;
	this.real_y = real_y;
	this.image_x = p_x - 10;
	this.image_y = p_y - 10;
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.setOpaque(false);
	// if (this.type != MapPanel.EObjectTools.VEHICULE)
	//     this.jpm.add(this.property);
	this.jpm.add(this.delete);
	this.delete.addActionListener(this.toDel);
	// this.property.addActionListener(this.editProperty);
    }

    public void		paintComponent(Graphics g) {
	// super.paintComponent(g);
	this.setBounds(this.getx(), this.gety(),
		       this.currentImg.getWidth(null), this.currentImg.getHeight(null));
	g.drawImage(this.currentImg, 0, 0, this);
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("NODE --- Clicked ! X: " + e.getX() + " // Y: " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("NODE --- Entered !");
    }

    public void mouseReleased(MouseEvent e) {
	System.out.println("Node --- Released !");
    }

    public void	updateMouseCoordInfo(MouseEvent e) {
	updateMouseCoordInfo(e.getX(), e.getY());
    }

    public void	updateMouseCoordInfo(int x, int y) {
	MapPanel.mouseX = x;
	MapPanel.mouseY = y;
    }

    public void mousePressed(MouseEvent e) {
	this.buttonPressed = e.getButton();
	if (e.getButton() == MouseEvent.BUTTON1)
	    if (MapPanel.selectedObject == MapPanel.EObjectTools.VEHICULE) {
		System.out.println("COUCOU LES AMIS");
		MapPanel.setVehiculeAt(this.getx(), this.gety());
	    }
	if (e.getButton() == MouseEvent.BUTTON3) {
	    jpm.show(this, e.getX(), e.getY());
	}
	oldx_win = this.getx();
	oldy_win = this.gety();
	oldx_screen = e.getXOnScreen();
	oldy_screen = e.getYOnScreen();
	this.updateMouseCoordInfo(e);
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Node --- Move !");
	this.updateMouseCoordInfo(e);
    }

    public void	mouseDragged(MouseEvent e) {
	if (this.buttonPressed == MouseEvent.BUTTON1
	    && MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR) {
	    System.out.println("NODE --- Drag !");
	}
	this.updateMouseCoordInfo(this.getx(), this.gety());
    }
}
