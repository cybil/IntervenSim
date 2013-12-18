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
  private JMenuItem	delete = new JMenuItem("Delete selection");

  private DeleteNode		toDel = new DeleteNode(this);
  public NodeGraphic		nodeOn = null;
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

  public boolean	isSelected = false;
  private int		buttonPressed = 0;

  private class DeleteNode implements ActionListener {
    VehiculeGraphic	node;
    public DeleteNode(VehiculeGraphic n) {
      this.node = n;
    }

    public void actionPerformed(ActionEvent e) {
      // System.out.println("============================ DELETE ============");
      MapPanel.deleteVehicule();
    }
  }

  public void		setIsSelected(boolean s)
  {
    isSelected = s;
  }

  public void		setNodeOn(NodeGraphic nodeOn)
  {
    this.nodeOn = nodeOn;
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
    int		new_img_width;
    int		new_img_heigth;

    new_img_width = MapPanel.unScaleX(this._imgNormal.getWidth(null));
    new_img_heigth = MapPanel.unScaleX(this._imgNormal.getHeight(null));
    new_img_width = (new_img_width <= 0 ? 1 : new_img_width);
    new_img_heigth = (new_img_heigth <= 0 ? 1 : new_img_heigth);
    this.imgNormal = this._imgNormal.getScaledInstance(new_img_width,
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
  }

  public VehiculeGraphic( Image imgNormal, int p_x, int p_y,
			  int real_x, int real_y) {
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
    this.jpm.add(this.delete);
    this.delete.addActionListener(this.toDel);
  }

  public void		paintComponentCustom(Graphics g) {
    int		w;
    int		h;

    if (MapPanel.running == false && nodeOn != null)
    {
      setRealX(nodeOn.getRealX());
      setRealY(nodeOn.getRealY());
    }
    w = this.currentImg.getWidth(null);
    h = this.currentImg.getHeight(null);

    this.setBounds(this.getx() - w/2,
		   this.gety() - h/2,
		   w, h);
  }

  public void		paintComponent(Graphics g) {
    int		w;
    int		h;

    super.paintComponent(g);
    paintComponentCustom(g);
    g.drawImage(this.currentImg, 0, 0, this);
  }

  public void mouseClicked(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
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
	MapPanel.setVehiculeAt(MapPanel.scaleX(this.getx()), MapPanel.scaleY(this.gety()));
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
    }
    this.updateMouseCoordInfo(this.getx(), this.gety());
  }
}
