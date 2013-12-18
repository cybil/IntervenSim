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
import java.awt.Graphics2D;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class NodeGraphic extends JPanel implements MouseListener, MouseMotionListener {

  private JPopupMenu	jpm = new JPopupMenu();
  private JMenuItem	property = new JMenuItem("Property");
  private JMenuItem	delete = new JMenuItem("Delete selection");

  private DeleteNode		toDel = new DeleteNode(this);
  private EditProperty	editProperty = new EditProperty(this);


  // Scaled image
  private Image	imgSelected;
  private Image	imgPassedOver;
  private Image	imgNormal;
  private Image	imgAttachPoint;
  private Image	imgUrgency;

  // Original image
  static private Image	_imgSelected = null;
  static private Image	_imgPassedOver = null;
  static private Image	_imgNormal = null;
  static private Image	_imgAttachPoint = null;
  static private Image	_imgUrgency = null;
  private boolean	debug = false;

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

  public MapPanel.EObjectTools	type;

  private class EditProperty implements ActionListener {
    NodeGraphic	node;
    public EditProperty(NodeGraphic n) {
      this.node = n;
    }
    public void actionPerformed(ActionEvent e) {
      Property	prop = new Property(node);
    }
  }

  private class DeleteNode implements ActionListener {
    NodeGraphic	node;
    public DeleteNode(NodeGraphic n) {
      this.node = n;
    }

    public void actionPerformed(ActionEvent e) {
      MapPanel.setSelection(this.node);
      MapPanel.deleteSelection();
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

  static public void		setImgNormal(Image img)  {
    _imgNormal = img;
  }
  static public void		setImgSelected(Image img)  {
    _imgSelected = img;
  }
  static public void		setImgPassedOver(Image img)  {
    _imgPassedOver = img;
  }
  static public void		setImgUrgency(Image img)  {
    _imgUrgency = img;
  }
  static public void		setImgAttachPoint(Image img)  {
    _imgAttachPoint = img;
  }

  public int		getx() {
    return this._node_x;
  }
  public int		gety() {
    return this._node_y;
  }


  public void		scaleImage()
  {
    int		new_img_width;
    int		new_img_heigth;

    new_img_width = MapPanel.unScaleX(this._imgNormal.getWidth(null));
    new_img_heigth = MapPanel.unScaleY(this._imgNormal.getHeight(null));
    new_img_width = (new_img_width <= 0 ? 1 : new_img_width);
    new_img_heigth = (new_img_heigth <= 0 ? 1 : new_img_heigth);
    this.imgNormal = this._imgNormal.getScaledInstance(new_img_width,
						       new_img_heigth,
						       Image.SCALE_SMOOTH);
    this.imgSelected = this._imgSelected.getScaledInstance(new_img_width,
							   new_img_heigth,
							   Image.SCALE_SMOOTH);
    this.imgPassedOver = this._imgPassedOver.getScaledInstance(new_img_width,
							       new_img_heigth,
							       Image.SCALE_SMOOTH);
    this.imgUrgency = this._imgUrgency.getScaledInstance(new_img_width,
							 new_img_heigth,
							 Image.SCALE_SMOOTH);
    this.imgAttachPoint = this._imgAttachPoint.getScaledInstance(new_img_width,
								 new_img_heigth,
								 Image.SCALE_SMOOTH);
    this.currentImg = this.imgNormal;
  }

  public void		setx(int new_x) {

    this._node_x = new_x;
  }

  public void		sety(int new_y) {
    this._node_y = new_y;
  }

  public void		setIsSelected(boolean b) {
    this.isSelected = b;
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
    return false;
  }

  public NodeGraphic() {
  }

  public NodeGraphic(MapPanel.EObjectTools type,
		     int p_x, int p_y,
		     int real_x, int real_y) {
    this.type = type;
    if (_imgUrgency == null)
    {
      try {
	_imgUrgency = ImageIO.read(new File("img/nodeUrgency.png"));
	_imgAttachPoint = ImageIO.read(new File("img/nodeAttachPoint.png"));
	_imgNormal = ImageIO.read(new File("img/nodeNormal.png"));

	_imgSelected = ImageIO.read(new File("img/nodeAttachPoint.png"));
	_imgPassedOver = ImageIO.read(new File("img/nodeUrgency.png"));
      }
      catch (IOException e)
      {
	e.printStackTrace();
      }
    }

    this.setx(p_x);
    this.sety(p_y);
    this.real_x = real_x;
    this.real_y = real_y;
    this.image_x = p_x - 10;
    this.image_y = p_y - 10;
    this.scaleImage();

    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    this.setOpaque(false);
    if (this.type != MapPanel.EObjectTools.VEHICULE)
      this.jpm.add(this.property);
    this.jpm.add(this.delete);
    this.delete.addActionListener(this.toDel);
    this.property.addActionListener(this.editProperty);
  }

  public void		paintComponentCustom(Graphics g) {
    int		w;
    int		h;

    w = this.currentImg.getWidth(null);
    h = this.currentImg.getHeight(null);
    this.setBounds(this.getx() - w/2,
		   this.gety() - h/2, w, h);
  }

  public void		paintComponent(Graphics g) {
    super.paintComponent(g);
    if (this.isSelected == true)
      this.currentImg = this.imgSelected;
    else if (this.type == MapPanel.EObjectTools.ATTACH_POINT)
      this.currentImg = this.imgAttachPoint;
    else if (this.type == MapPanel.EObjectTools.URGENCY)
      this.currentImg = this.imgUrgency;
    else
      this.currentImg = this.imgNormal;
    paintComponentCustom(g);
    g.drawImage(this.currentImg, 0, 0, null); // (0, 0) from the relative Bounds
  }

  public void mouseClicked(MouseEvent e) {
    if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR
	&& this.isSelected == false) {
      this.currentImg = this.imgSelected;
      this.isSelected = true;
      MapPanel.setSelection(this);
    }
    else if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR) {
      this.currentImg = this.imgPassedOver;
      this.isSelected = false;
      MapPanel.setSelection(null);
    }

  }

  public void mouseExited(MouseEvent e) {
    if (debug == true) System.out.println("NodeGraphic.mouseExited");
    if (this.isSelected == true)
      this.currentImg = this.imgSelected;
    else
      this.currentImg = this.imgNormal;
  }

  public void mouseEntered(MouseEvent e) {
    if (debug == true) System.out.println("NodeGraphic.mouseEntered");
    this.currentImg = this.imgPassedOver;
  }

  public void mouseReleased(MouseEvent e) {
    if (debug == true)System.out.println("NodeGraphic.mouseReleased");
    if (e.getButton() == MouseEvent.BUTTON1
	&& MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR
	&& MapPanel.isDragging() == true)
    {
      if (debug == true) System.out.println("Node --- definitive change of node coord !");
      this.setx(oldx_win + (e.getXOnScreen() - oldx_screen));
      this.sety(oldy_win + (e.getYOnScreen() - oldy_screen));
      // Pour etre synchro et eviter la perte de prevision avec MapPanel
      this.setRealX(MapPanel.scaleX(this.getx()));
      this.setRealY(MapPanel.scaleY(this.gety()));
      MapPanel.setMovedNode2(this.getx(), this.gety());
    }
    else if (e.getButton() == MouseEvent.BUTTON1
	     && MapPanel.selectedObject == MapPanel.EObjectTools.ROAD)
      MapPanel.setRoadNode(this.getRealX(), this.getRealY());
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
      if (MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR)
      {
	MapPanel.setMovedNode1(this.getRealX(), this.getRealY());
      }
      else if (MapPanel.selectedObject == MapPanel.EObjectTools.VEHICULE) {
	MapPanel.setVehiculeAt(this.getRealX(), this.getRealY());
	if (MapPanel.graphVehicule != null)
	  MapPanel.graphVehicule.setNodeOn(this);
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
    this.updateMouseCoordInfo(e);
  }

  public void	mouseDragged(MouseEvent e) {
    if (this.buttonPressed == MouseEvent.BUTTON1
	&& MapPanel.selectedObject == MapPanel.EObjectTools.CURSOR) {
      this.setx(oldx_win + (e.getXOnScreen() - oldx_screen));
      this.sety(oldy_win + (e.getYOnScreen() - oldy_screen));
      MapPanel.setMovedNode2(this.getx(), this.gety());
      MapPanel.setIsDragging(true);
    }
    this.updateMouseCoordInfo(this.getx(), this.gety());
  }
}
