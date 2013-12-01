import javax.swing.JPanel; 
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class MapPanel extends JPanel implements MouseListener, MouseMotionListener {

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
    static ArrayList<RoadGraphic>	roads = new ArrayList<RoadGraphic>();
    static NodeGraphic			movedNode;

    static Controller		controller;
    static int[]		coordMovedNode = new int[2];
    static boolean		isDragging = false;
    static int[]		roadCoord1 = new int[2];
    static int[]		roadCoord2 = new int[2];

    static int[]		startCoord = new int[2];
    static int[]		endCoord = new int[2];
    static boolean	        isMovingNode = false;

    static int			mouseX;
    static int			mouseY;

    private JPopupMenu	jpm = new JPopupMenu();
    private JMenuItem	delete = new JMenuItem("Supprimer");
    private DeleteRoad	toDel = new DeleteRoad();

    private class DeleteRoad implements ActionListener {
	RoadGraphic	road;
	public DeleteRoad() {
	}

	public void	setRoad(RoadGraphic r) {
	    this.road = r;
	}

	public void actionPerformed(ActionEvent e) {
	    System.out.println("============================ DELETE ROAD  ============");
	    MapPanel.deleteRoad(road);
	}
    }

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
	this.setBackground(Color.GRAY);
	this.setPreferredSize(new Dimension(300, 600));
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.jpm.add(this.delete);
	this.delete.addActionListener(this.toDel);
    }

    public void		setSelectedObject(EObjectTools obj) {
	this.selectedObject = obj;
    }

    public void		paintComponent(Graphics g) {
	Graphics2D	g2 = (Graphics2D)g;
	// Image		bck;

	super.paintComponent(g);

	Font f = new Font("Helvetica", Font.BOLD, 14);
	g2.setFont(f);
	g2.drawString("Echelle:" + 100, this.getWidth() - 120, this.getHeight() - 10);
	g2.drawString("X: " + this.mouseX + " Y: " + this.mouseY, 10, this.getHeight() - 10);
	this.removeAll();
	// if ((bck = this.controller._model.getMap().getBackground()) != null)
	//     g.drawImage(bck, 0, 0, this);

	for (RoadGraphic r : this.roads) {
	    g2.setStroke(r.getStroke());
	    g.drawLine(r.getx1(), r.gety1(), r.getx2(), r.gety2());
	}

	int i = -1;
	while (++i < this.nodes.size()) {
	    this.add(this.nodes.get(i));
	}
	if (this.graphVehicule != null)
	    this.add(this.graphVehicule);
    }

    public static boolean	containsNode(int p_x, int p_y) {
	int	i = 0;

	while (i < nodes.size()) {
	    if (nodes.get(i).getx() == p_x
		&& nodes.get(i).gety() == p_y)
		return true;
	    ++i;
	}
	return false;
    }

    public static boolean	containsRoad(int x1, int y1, int x2, int y2) {
	int	i = 0;

	while (i < roads.size()) {
	    if (roads.get(i).getx1() == x1
		&& roads.get(i).gety1() == y1
		&& roads.get(i).getx2() == x2
		&& roads.get(i).gety2() == y2)
		return true;
	    ++i;
	}
	return false;
    }

    public static RoadGraphic	getRoad(int x1, int y1, int x2, int y2) {
	int	i = 0;

	while (i < roads.size()) {
	    if (roads.get(i).getx1() == x1
		&& roads.get(i).gety1() == y1
		&& roads.get(i).getx2() == x2
		&& roads.get(i).gety2() == y2)
		return roads.get(i);
	    ++i;
	}
	return null;
    }

    static NodeGraphic		graphVehicule = null;

    public void		displayMap(ArrayList<String> formatMap)
    {
	roads.clear();
	for (String s : formatMap) {
	    if (s.charAt(0) == 'V') {
		if (isDragging == false) {
		    int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		    
		    if (this.graphVehicule == null) {
			NodeGraphic		newNode = new NodeGraphic(this.vehicule,
									  this.vehicule,
									  this.vehicule, x, y);

			newNode.setLayout(null);
			this.add(newNode);
			this.validate();
			this.graphVehicule = newNode;
		    }
		}
	    }
	    else if (s.charAt(0) == 'N') {
		if (isDragging == false) {
		    int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		    
		    if (this.containsNode(_x, _y) == false) {
			System.out.println("CREATE NODE GRAPHIC : " + _x + " - " + _y);
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
		if (isDragging == false) {
		    int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));

		    if (this.containsNode(_x, _y) == false) {
			System.out.println("CREATE ATTACH GRAPHIC : " + _x + " - " + _y);
			NodeGraphic		newNode = new NodeGraphic(this.nodeAttachPoint,
									  this.nodeAttachPoint,
									  this.nodeAttachPoint,
									  _x,
									  _y);
			newNode.setLayout(null);
			this.add(newNode);
			this.validate();
			nodes.add(newNode);
		    }
		}
	    }
	    else if (s.charAt(0) == 'U') {
		if (isDragging == false) {
		    int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		    int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));

		    if (this.containsNode(_x, _y) == false) {
			NodeGraphic		newNode = new NodeGraphic(this.nodeUrgency,
									  this.nodeUrgency,
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
	    else if (s.charAt(0) == 'R') {
		int	x1 = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y1 = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
		String	s2 = s.substring(s.lastIndexOf(":"));
		int	x2 = Integer.parseInt(s2.substring(s2.indexOf(":") + 1, s2.indexOf(",")));
		int	y2 = Integer.parseInt(s2.substring(s2.indexOf(",") + 1));
		if (this.containsRoad(x1, y1, x2, y2) == false) {
		    RoadGraphic		newRoad = new RoadGraphic(x1, y1, x2, y2);
		    this.roads.add(newRoad);
		}
	    }
	    this.repaint();
	}
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1
	    && this.selectedObject == EObjectTools.NODE) {
	    this.controller.eventPutNode(e.getX(), e.getY());
	}
	// else if (e.getButton() == MouseEvent.BUTTON1
	// 	 && this.selectedObject == EObjectTools.VEHICULE) {
	//     this.controller.eventCreatVehicule(e.getX(), e.getY());
	// }
    }

    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON3) {
	    for (RoadGraphic r : this.roads) {
		if (r.containsPoint(this.mouseX, this.mouseY) <= 6) {
		    this.toDel.setRoad(r);
		    this.jpm.show(this, e.getX(), e.getY());
		}
	    }
	}
    }

    public void	mouseMoved(MouseEvent e) {
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
	coordMovedNode[0] = x;
	coordMovedNode[1] = y;
    }

    static void		setStartCoord(int x, int y) {
	startCoord[0] = x;
	startCoord[1] = y;
	isMovingNode = true;
    }

    static void		setEndCoord(int x, int y, boolean b) {
	endCoord[0] = x;
	endCoord[1] = y;
	controller.eventEditNodeCoord(startCoord, endCoord);
	isMovingNode = b;
    }

    static boolean	drawing = false;

    static void	setRoadNode(int x, int y) {
	if (drawing == false) {
	    roadCoord1[0] = x;
	    roadCoord1[1] = y;
	    drawing = true;
	}
	else {
	    roadCoord2[0] = x;
	    roadCoord2[1] = y;
	    controller.eventAddRoad(roadCoord1, roadCoord2);
	    drawing = false;
	}
    }

    static void		setIsDragging(boolean b) {
	isDragging = b;
    }

    static void		deleteNode(NodeGraphic n) {
	int[]		coord = {n.getx(), n.gety()};
	
	if (nodes.contains(n) == true) {
	    nodes.remove(n);
	    controller.eventDeleteNode(coord);
	}
	else {
	    graphVehicule = null;
	    controller.eventDeleteVehicule();
	}
    }

    static void		deleteRoad(RoadGraphic r) {
	int[]		coord1 = {r.getx1(), r.gety1()};
	int[]		coord2 = {r.getx2(), r.gety2()};
	roads.remove(r);
	controller.eventDeleteRoad(coord1, coord2);

	int[]		coord3 = {r.getx2(), r.gety2()};
	int[]		coord4 = {r.getx1(), r.gety1()};
	roads.remove(getRoad(coord3[0], coord3[1], coord4[0], coord4[1]));
	controller.eventDeleteRoad(coord3, coord4);
    }

    static void		setVehiculeAt(int x, int y) {
	boolean		ret;

	ret = controller.eventCreatVehicule(x, y);
	if (ret == false) {
	    graphVehicule.setx(x);
	    graphVehicule.sety(y);
	}
    }

}
