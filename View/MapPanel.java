import javax.swing.JPanel; 
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class MapPanel extends JPanel implements MouseListener, MouseMotionListener {

    private class RoadGraphic {
	public int		x1;
	public int		x2;
	public int		y1;
	public int		y2;

	public RoadGraphic(int x1, int y1, int x2, int y2) {
	    this.x1 = x1;
	    this.x2 = x2;
	    this.y1 = y1;
	    this.y2 = y2;
	}
    }

    public enum EObjectTools {
	VEHICULE, NODE, CURSOR;
    }

    private EObjectTools selectedObject = EObjectTools.CURSOR;
    private int		x1;
    private int		y1;
    private int		x2;
    private int		y2;
    private boolean	isPressed = false;

    private Image		background;
    private Image		nodeUrgency;
    private Image		nodeAttachPoint;
    private Image		nodeNormal;
    private Image		vehicule;
    private ArrayList<NodeGraphic>	nodes = new ArrayList<NodeGraphic>();
    private ArrayList<RoadGraphic>	roads = new ArrayList<RoadGraphic>();

    private Controller		controller;

    MapPanel(Controller controller) {
	try {
	    this.nodeUrgency = ImageIO.read(new File("img/nodeUrgency.png"));
	    this.nodeAttachPoint = ImageIO.read(new File("img/nodeAttachPoint.png"));
	    this.nodeNormal = ImageIO.read(new File("img/nodeNormal.png"));
	    this.vehicule = ImageIO.read(new File("img/vehicule.png"));
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	this.controller = controller;
	this.setPreferredSize(new Dimension(300, 600));
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
    }

    public void		setSelectedObject(EObjectTools obj) {
	this.selectedObject = obj;
    }

    public void		paintComponent(Graphics g) {
	Image		bck;

	super.paintComponent(g);
	if ((bck = this.controller._model.getMap().getBackground()) != null)
	    g.drawImage(bck, 0, 0, this);
	for (RoadGraphic r : this.roads) {
	    g.drawLine(r.x1, r.y1, r.x2, r.y2);
	}
	for (NodeGraphic n : this.nodes) {
	    g.drawImage(n.imgNormal, n.x - (n.imgNormal.getWidth(null) / 2), n.y - (n.imgNormal.getHeight(null) / 2), this);
	}
	if (this.isPressed == true) {
	    g.drawLine(this.x1, this.y1, this.x2, this.y2);
	}

    }

    public void		displayMap(ArrayList<String> formatMap)
    {
	for (String s : formatMap) {
	    if (s.charAt(0) == 'V') {
		int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		NodeGraphic		newNode = new NodeGraphic(this.vehicule,
								  this.vehicule,
								  this.vehicule, x, y);
		this.nodes.add(newNode);
	    }
	    else if (s.charAt(0) == 'N') {
		int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		NodeGraphic		newNode = new NodeGraphic(this.nodeNormal,
								  this.nodeNormal,
								  this.nodeNormal, x, y);
		this.nodes.add(newNode);
	    }
	    else if (s.charAt(0) == 'A') {
		int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		NodeGraphic		newNode = new NodeGraphic(this.nodeAttachPoint,
								  this.nodeAttachPoint,
								  this.nodeAttachPoint, x, y);
		this.nodes.add(newNode);
	    }
	    else if (s.charAt(0) == 'U') {
		int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
		NodeGraphic		newNode = new NodeGraphic(this.nodeUrgency,
								  this.nodeUrgency,
								  this.nodeUrgency, x, y);
		this.nodes.add(newNode);
	    }
	    else if (s.charAt(0) == 'R') {
		int	x1 = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y1 = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
		String	s2 = s.substring(s.lastIndexOf(":"));
		int	x2 = Integer.parseInt(s2.substring(s2.indexOf(":") + 1, s2.indexOf(",")));
		int	y2 = Integer.parseInt(s2.substring(s2.indexOf(",") + 1));
		RoadGraphic		newRoad = new RoadGraphic(x1, y1, x2, y2);
		this.roads.add(newRoad);
	    }	    
	    this.repaint();
	}
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("Clicked ! X: " + e.getX() + " // Y: " + e.getY());
	if (e.getButton() == MouseEvent.BUTTON1) {
	    // Verifier quel est l'item selectionne pour placer un element
	    this.controller.eventPutNode(e.getX(), e.getY());
	}
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
	if (e.getButton() == MouseEvent.BUTTON3) {
	    this.isPressed = false;
	    int[]	coord1 = {this.x1, this.y1};
	    int[]	coord2 = {e.getX(), e.getY()};
	    this.controller.eventAddRoad(coord1, coord2);
	}
    }

    public void mousePressed(MouseEvent e) {
	System.out.println("Pressed !");
	if (e.getButton() == MouseEvent.BUTTON3) {
	    this.isPressed = true;
	    this.x1 = e.getX();
	    this.y1 = e.getY();
	    this.x2 = e.getX();
	    this.y2 = e.getY();
	}
    }

    public void	mouseMoved(MouseEvent e) {
	System.out.println("Move !");
    }

    public void	mouseDragged(MouseEvent e) {
	System.out.println("Drag !");
	if (this.isPressed == true) {
	    this.x2 = e.getX();
	    this.y2 = e.getY();
	}
    }
}
