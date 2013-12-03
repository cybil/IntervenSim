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
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MapPanel extends JPanel implements
					 MouseListener,
					 MouseMotionListener,
					 MouseWheelListener {

    public enum EObjectTools {
	VEHICULE, NODE, CURSOR, ROAD, ATTACH_POINT, URGENCY;
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

    private int[]	selectedBoxCoord1 = new int[2];
    private int[]	selectedBoxCoord2 = new int[2];

    private ArrayList<NodeGraphic>	selectedItemsList = new ArrayList<NodeGraphic>();

    private JPopupMenu	jpm = new JPopupMenu();
    private JMenuItem	delete = new JMenuItem("Delete");
    private DeleteRoad	toDel = new DeleteRoad();

    private JScrollPane	scrolBarRef = null;

    // zoom
    private int W;
    private int H;
    //Zoom
    private static int maxX = 300;
    private static int maxY = 300;

    private class DeleteRoad implements ActionListener {
	RoadGraphic		road;
	ArrayList<NodeGraphic>	nodes;
	public DeleteRoad() {
	}

	public void	setRoad(RoadGraphic r) {
	    this.road = r;
	}

	public void	setNodes(ArrayList<NodeGraphic> n) {
	    this.nodes = n;
	}

	public void actionPerformed(ActionEvent e) {
	    System.out.println("============================ DELETE ROAD  ============");
	    if (this.nodes != null) {
		for (NodeGraphic n : this.nodes)
		    MapPanel.deleteNode(n);
	    }
	    else
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

	// Zoom
	// maxY = this.getHeight();
	// maxX = this.getWidth();
	W = maxX;
	H = maxY;

	// JScrollPane scrollPane = new JScrollPane();
	// scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	// this.add(scrollPane);
	this.setPreferredSize(new Dimension(maxX, maxY));

	// this.setSize(new Dimension(maxX, maxY));
	// this.setMaximumSize(new Dimension(maxX, maxY));
	// this.setMinimumSize(new Dimension(maxX, maxY));
	this.addMouseListener(this);
	this.addMouseMotionListener(this);
	this.addMouseWheelListener(this);

	// Zoom
	// this.addKeyListener(new KeyHandler());
	// this.addKeyListener(this);

	this.jpm.add(this.delete);
	this.delete.addActionListener(this.toDel);
	// this.setFocusable(true);
	// System.out.println("Construction reussit");
    }

    public void		setSelectedObject(EObjectTools obj) {
	this.selectedObject = obj;
    }

    @Override
	public void		paintComponent(Graphics g) {
	int		view_x;
	int		view_y;
	Graphics2D	g2 = (Graphics2D)g;
	// Image		bck;

	super.paintComponent(g);
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
	AffineTransform at = g2.getTransform();

	view_x = scrolBarRef.getViewport().getViewPosition().x;
	view_y = scrolBarRef.getViewport().getViewRect().y;
	// + scrolBarRef.getViewport().getViewRect().width;
	// view_y = scrolBarRef.getViewport().getViewPosition().y;
	// System.out.println("x:" + view_x + " / y:" + view_y);
	g2.drawString("Echelle:" + 100,
		      view_x + scrolBarRef.getWidth() - 120,
		      view_y + scrolBarRef.getHeight() - 30);
	g2.drawString("X: " + scaleX(this.mouseX) + " Y: " + scaleY(this.mouseY),
		      view_x + 10,
		      view_y + scrolBarRef.getHeight() - 30);
	g2.scale(
		 (double) this.getW() / (double)maxX,
		 (double) this.getH() / (double)maxY);

	this.removeAll();
	// if ((bck = this.controller._model.getMap().getBackground()) != null)
	//     g.drawImage(bck, 0, 0, this);

	for (RoadGraphic r : this.roads) {
	    g2.setStroke(r.getStroke());
	    g.drawLine(r.getx1(), r.gety1(), r.getx2(), r.gety2());
	}

	int i = -1;
	while (++i < this.nodes.size()) {
	    // Node		node;
	    // GraphNode	gn = null;
	    // int[]		oldCoord;
	    // int[]		newCoord;

	    // gn = this.nodes.get(i);
	    // node = this.nodes.get(i).getData();
	    // oldCoord = node.getCoord();
	    // newCoord[0] = scaleX(oldcoord[0]);
	    // newCoord[1] = scaleX(oldcoord[1]);
	    // node.setCoord(newCoord);
	    // gn.setData(node);
	    // this.add(new NodeGraphic(this.nodes.get(i).getImgNormal(),
	    // 			   this.nodes.get(i).getImgSelected(),
	    // 			   this.nodes.get(i).getImgPassedOver(),
	    // 			   this.nodes.get(i).getx(),
	    // 			   this.nodes.get(i).gety()));
	    // System.out.println("loop");
	    this.add(this.nodes.get(i));
	}
	g2.setTransform(at);
	if (this.graphVehicule != null)
	    this.add(this.graphVehicule);
	
	if (this.selectedObject == EObjectTools.CURSOR
	    && this.isDragging == true) {
	    g2.setStroke(new BasicStroke(1.0f));
	    g2.setColor(Color.BLUE);
	    g2.drawLine(this.selectedBoxCoord1[0], this.selectedBoxCoord1[1], this.selectedBoxCoord2[0], this.selectedBoxCoord1[1]);
	    g2.drawLine(this.selectedBoxCoord2[0], this.selectedBoxCoord1[1], this.selectedBoxCoord2[0], this.selectedBoxCoord2[1]);

	    g2.drawLine(this.selectedBoxCoord1[0], this.selectedBoxCoord1[1], this.selectedBoxCoord1[0], this.selectedBoxCoord2[1]);
	    g2.drawLine(this.selectedBoxCoord1[0], this.selectedBoxCoord2[1], this.selectedBoxCoord2[0], this.selectedBoxCoord2[1]);
	}
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

    private NodeGraphic		_displayMapNode(String s)
    {
	NodeGraphic			newNode = null;
	int	_x = unScaleX(Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(","))));
	int	_y = unScaleY(Integer.parseInt(s.substring(s.indexOf(",") + 1)));

	// System.out.println("CREATE NODE GRAPHIC AT ?? : " + scaleX(_x) + " - " + scaleY(_y));
	if (this.containsNode(_x, _y) == false) {
	    System.out.println("CREATE NODE GRAPHIC : " + _x + " - " + _y);
	    newNode = new NodeGraphic(EObjectTools.NODE, this.nodeNormal,
				      this.nodeAttachPoint,
				      this.nodeUrgency,
				      _x, _y,
				      scaleX(_x), scaleY(_y));
	    nodes.add(newNode);
	}
	return (newNode);
    }

    private NodeGraphic		_displayMapAttachPoint(String s)
    {
	NodeGraphic			newNode = null;
	int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));

	if (this.containsNode(_x, _y) == false) {
	    System.out.println("CREATE ATTACH GRAPHIC : " + _x + " - " + _y);
	    newNode = new NodeGraphic(EObjectTools.ATTACH_POINT,
				      this.nodeAttachPoint,
				      this.nodeAttachPoint,
				      this.nodeAttachPoint,
				      _x, _y,
				      unScaleX(_x), unScaleY(_y));
	    nodes.add(newNode);
	}
	return (newNode);
    }

    private NodeGraphic		_displayMapVehicule(String s)
    {
	NodeGraphic		newNode = null;
	int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1));

	if (this.graphVehicule == null) {
	    newNode = new NodeGraphic(EObjectTools.VEHICULE,
				      this.vehicule,
				      this.vehicule,
				      this.vehicule, x, y,
				      unScaleX(x), unScaleY(y));
	    this.graphVehicule = newNode;
	}
	return (newNode);
    }

    private NodeGraphic		_displayMapUrgency(String s)
    {
	NodeGraphic		newNode	= null;
	int	_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int	_y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));

	if (this.containsNode(_x, _y) == false) {
	    newNode = new NodeGraphic(EObjectTools.URGENCY,
				      this.nodeUrgency,
				      this.nodeUrgency,
				      this.nodeUrgency,
				      _x, _y,
				      unScaleX(_x), unScaleY(_y));
	    nodes.add(newNode);
	}
	return (newNode);
    }

    private void		_displayMapRoad(String s)
    {
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

    public void		displayMap(ArrayList<String> formatMap)
    {
	NodeGraphic		newNode = null;

	roads.clear();
	for (String s : formatMap) {
	    if (s.charAt(0) == 'R') {
		this._displayMapRoad(s);
	    }
	    else if (isDragging == false)
	    	{
		    if (s.charAt(0) == 'V') {
			newNode = this._displayMapVehicule(s);
		    }
		    else if (s.charAt(0) == 'N') {
			newNode = this._displayMapNode(s);
		    }
		    else if (s.charAt(0) == 'A') {
			newNode = this._displayMapAttachPoint(s);
		    }
		    else if (s.charAt(0) == 'U') {
			newNode = this._displayMapUrgency(s);
		    }
		    if (newNode != null) {
			newNode.setLayout(null);
			this.add(newNode);
			this.validate();
		    }
		}
	}
	this.repaint();
    }

    public void mouseClicked(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1
	    && this.selectedObject == EObjectTools.CURSOR) {
	    for (NodeGraphic n : this.nodes)
		n.setIsSelected(false);
	    this.selectedItemsList.clear();
	}
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
	    this.controller.eventPutNode(scaleX(e.getX()), scaleY(e.getY()));
	}
	else if (e.getButton() == MouseEvent.BUTTON1
		 && this.selectedObject == EObjectTools.VEHICULE) {
	    this.controller.eventCreatVehicule(scaleX(e.getX()), scaleY(e.getY()));
	}
	this.isDragging = false;
    }

    public void	rescaleAllNode()
    {
	for (NodeGraphic node:nodes)
	    node.rescaleCoord(getW(), getH(), maxX, maxY);
    }

    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON3) {
	    for (RoadGraphic r : this.roads) {
		if (r.containsPoint(this.mouseX, this.mouseY) <= 6) {
		    this.toDel.setRoad(r);
		    this.toDel.setNodes(null);
		    this.jpm.show(this, e.getX(), e.getY());
		}
	    }
	    if (this.selectedItemsList.size() != 0) {
		this.toDel.setRoad(null);
		this.toDel.setNodes(this.selectedItemsList);
		this.jpm.show(this, e.getX(), e.getY());
	    }
	}
	if (this.selectedObject == EObjectTools.CURSOR
	    && e.getButton() == MouseEvent.BUTTON1) {
	    if (this.selectedItemsList.size() == 0) {
		this.selectedBoxCoord1[0] = e.getX();
		this.selectedBoxCoord1[1] = e.getY();
	    }
	}
    }

    public void	mouseMoved(MouseEvent e) {
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    public void	mouseDragged(MouseEvent e) {
	System.out.println("Drag !");
	this.isDragging = true;
	if (this.selectedObject == EObjectTools.CURSOR) {
	    this.selectedBoxCoord2[0] = e.getX();
	    this.selectedBoxCoord2[1] = e.getY();

	    // Selection des elements a l'interieur du rectangle
	    for (NodeGraphic n : this.nodes) {
		if (n.getx() > this.selectedBoxCoord1[0]
		    && n.getx() < this.selectedBoxCoord2[0]
		    && n.gety() > this.selectedBoxCoord1[1]
		    && n.gety() < this.selectedBoxCoord2[1]) {
		    this.selectedItemsList.add(n);
		    n.setIsSelected(true);
		}
		else {
		    this.selectedItemsList.remove(n);
		    n.setIsSelected(false);
		}
	    }
	}
	this.mouseX = e.getX();
	this.mouseY = e.getY();
    }

    static void	setMovedNode1(int x, int y) {
	coordMovedNode[0] = x;
	coordMovedNode[1] = y;
    }

    static void	setMovedNode2(int x, int y) {
	int[]		coordMovedNode2 = {x, y};

	controller.eventEditNodeCoord(coordMovedNode, coordMovedNode2);
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

    @Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	int	max_node_x = maxX;
	int	max_node_y = maxY;
	int notches = e.getWheelRotation();

	// if (notches < 0) {
	// 	System.out.println("Zoom");
	// } else {
	// 	System.out.println("UnZoom");
	// }
	if ((notches > 0
	     && (this.H - (10 * notches) > 10)
	     && (this.W - (10 * notches) > 10))
	    || (notches < 0))
	    {
		this.H -= 10 * notches + (this.H / 10) * (notches > 0 ? 1 : -1);
		this.W -= 10 * notches + (this.W / 10) * (notches > 0 ? 1 : -1);
		// if (H > maxX || W > maxY)
		// this.setPreferredSize(new Dimension(this.W,
		// 					  this.H));
		// this.setPreferredSize(new Dimension((int)((double) this.getW() / (double)maxX) * maxX,
		// 					  (int)((double) this.getH() / (double)maxY) * maxY));
		// this.setPreferredSize(new Dimension(scale(this.W, maxX, this.W),
		// 					  scale(this.H, maxY, this.H)));
		// this.setPreferredSize(new Dimension(scale(maxX, this.W, maxX),
		// 					  scale(maxY, this.H, maxY)));
		// this.setPreferredSize(new Dimension(scale(maxX, this.W, unScaleX(this.W)),
		// 				    scale(maxY, this.H, unScaleY(this.H))));
		this.rescaleAllNode();
		for (NodeGraphic node: nodes)
		    {
			if (node.getx() > max_node_x)
			    max_node_x = node.getx();
			if (node.gety() > max_node_y)
			    max_node_y = node.gety();
		    }
		this.setPreferredSize(new Dimension(max_node_x + 50, max_node_y + 50));
		revalidate();
	    }
	// saySomething(message, e);
    }

    public void	setScrollBarRef(JScrollPane scrolBarRef)  {
	this.scrolBarRef = scrolBarRef;
    }

    private int getW()  {
	return (this.W);
    }
    private int getH()  {
	return (this.H);
    }

    private int scaleX(int x) {
	return (int)(((double) x / (double)getW()) * (double)maxX);
    }
    private int scaleY(int y) {
	return (int)(((double) y / (double)getH()) * (double)maxY);
    }
    private int unScaleX(int x) {
	return (int)(((double) x / (double)maxX) * (double)getW());
    }
    private int unScaleY(int y) {
	return (int)(((double) y / (double)maxY) * (double)getH());
    }

    static public int scale(int coord, int curr_max, int real_max) {
	return (int)(((double) coord / (double)curr_max) * (double)real_max);
    }
    static public int unScale(int coord, int curr_max, int real_max) {
	return (int)(((double) coord / (double)real_max) * (double)curr_max);
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
