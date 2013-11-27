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
import java.awt.Font;

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
	VEHICULE, NODE, CURSOR, ROAD;
    }

    static EObjectTools selectedObject = EObjectTools.CURSOR;
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
    static ArrayList<NodeGraphic>	nodes = new ArrayList<NodeGraphic>();
    private ArrayList<RoadGraphic>	roads = new ArrayList<RoadGraphic>();
    static NodeGraphic			movedNode;

    static Controller		controller;
    static int[]		coordMovedNode = new int[2];
    static boolean		isDragging = false;
    static int[]		roadCoord1 = new int[2];
    static int[]		roadCoord2 = new int[2];
    static NodeGraphic		destRoad = null;
    static boolean	        drawTempRoad = false;

    static int			mouseX;
    static int			mouseY;

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
	//this.setBackground(Color.);
	this.setPreferredSize(new Dimension(300, 600));
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
    }

    public void		setSelectedObject(EObjectTools obj) {
	this.selectedObject = obj;
    }

    public void		paintComponent(Graphics g) {
	int		i;
	Image		bck;

	super.paintComponent(g);

	Font f = new Font("Helvetica", Font.BOLD, 14);
	g.setFont(f);
	g.drawString("Echelle:" + 100, this.getWidth() - 120, this.getHeight() - 10);
	g.drawString("X: " + this.mouseX + " Y: " + this.mouseY, 10, this.getHeight() - 10);
	g.setColor(Color.GRAY);
	int		j = 60;
	while (j < 10000) {
	    g.drawLine(0, j, 10000, j);
	    j += 60;
	}
	int		k = 60;
	while (k < 10000) {
	    g.drawLine(k, 0, k, 10000);
	    k += 60;
	}
	this.removeAll();
	i = -1;
	if ((bck = this.controller._model.getMap().getBackground()) != null)
	    g.drawImage(bck, 0, 0, this);
	if (this.drawTempRoad == true) {
	    g.setColor(Color.GREEN);
	    g.drawLine(this.roadCoord1[0], this.roadCoord1[1], this.roadCoord2[0], this.roadCoord2[1]);
	    this.drawTempRoad = false;
	}
	for (RoadGraphic r : this.roads) {
	    g.setColor(Color.BLACK);
	    g.drawLine(r.x1, r.y1, r.x2, r.y2);
	}
	while (++i < this.nodes.size()) {
	    this.add(this.nodes.get(i));
	}
	if (this.isPressed == true) {
	    g.drawLine(this.x1, this.y1, this.x2, this.y2);
	}

    }

    public static boolean	containsNode(int p_x, int p_y)
    {
	int	i = 0;

	while (i < nodes.size()) {
	    if (nodes.get(i).getx() == p_x
		&& nodes.get(i).gety() == p_y)
		return true;
	    ++i;
	}
	return false;
    }

    public void		displayMap(ArrayList<String> formatMap)
    {
	roads.clear();
	for (String s : formatMap) {
	    if (s.charAt(0) == 'V') {
		if (isDragging == false) {
		    int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		    if (this.containsNode(x, y) == false) {
			NodeGraphic		newNode = new NodeGraphic(this.vehicule,
									  this.vehicule,
									  this.vehicule, x, y);
			newNode.setLayout(null);
			this.add(newNode);
			this.validate();
			nodes.add(newNode);
		    }
		}
	    }
	    else if (s.charAt(0) == 'N') {
		if (isDragging == false) {
		    int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		    
		    if (this.containsNode(_x, _y) == false) {
			NodeGraphic		newNode = new NodeGraphic(this.nodeNormal,
									  this.nodeAttachPoint,
									  this.nodeUrgency,
									  _x,
									  _y);
			newNode.setLayout(null);
			this.add(newNode);
			this.validate();
			nodes.add(newNode);
		    }
		}
	    }
	    else if (s.charAt(0) == 'A') {
		// int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		// int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		// NodeGraphic		newNode = new NodeGraphic(this.nodeAttachPoint,
		// 						  this.nodeAttachPoint,
		// 						  this.nodeAttachPoint, x, y);
		// this.add(newNode);
		// this.nodes.add(newNode);
	    }
	    else if (s.charAt(0) == 'U') {
		// int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		// int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
		// NodeGraphic		newNode = new NodeGraphic(this.nodeUrgency,
		// 						  this.nodeUrgency,
		// 						  this.nodeUrgency, x, y);
		// this.nodes.add(newNode);
		// this.add(newNode);
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
    }

    public void mouseExited(MouseEvent e) {
	System.out.println("Exited !");
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("Entered !");
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
	System.out.println("Released !");
	if (e.getButton() == MouseEvent.BUTTON1
	    && this.selectedObject == EObjectTools.NODE) {
	    this.controller.eventPutNode(e.getX(), e.getY());
	}
    }

    public void mousePressed(MouseEvent e) {
	System.out.println("Pressed !");
    }

    public void	mouseMoved(MouseEvent e) {
	// System.out.println("Move !"); 
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    public void	mouseDragged(MouseEvent e) {
	System.out.println("Drag !");
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    static void	setMovedNode1(int x, int y) {
	coordMovedNode[0] = x;
	coordMovedNode[1] = y;
    }

    static void	setMovedNode2(int x, int y) {
	int[]		coordMovedNode2 = {x, y};
	boolean		ret;

	ret = controller.eventEditNodeCoord(coordMovedNode, coordMovedNode2);
	System.out.println("Ret = " + ret);
    }

    static void	setDestRoad(NodeGraphic n) {
	destRoad = n;
    }

    static void	setRoadNode1(int x, int y) {
	roadCoord1[0] = x;
	roadCoord1[1] = y;
    }

    static void	setRoadNode2() {
	int[]	coord2 = {destRoad.getx(), destRoad.gety()};
	controller.eventAddRoad(roadCoord1, coord2);
    }

    static void		drawRoadToCursor(int p_x, int p_y) {
	drawTempRoad = true;
	roadCoord2[0] = p_x;
	roadCoord2[1] = p_y;
    }
    
    static void		setIsDragging(boolean b) {
	isDragging = b;
    }

    static void		deleteNode(NodeGraphic n) {
	int[]		coord = {n.getx(), n.gety()};
	System.out.println(" ----------------------------- DELETE NODE ");
	nodes.remove(n);
	controller.eventDeleteNode(coord);
    }
}
