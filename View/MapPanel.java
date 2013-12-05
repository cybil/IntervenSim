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

  private boolean		mapChanged;
  private int			wasOut = 0; // 0:OK - 1:Out - 2:Enter
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

    //Zoom
    private static int W;
    private static int H;
    public static int maxX = 300;
    public static int maxY = 300;

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

  // Delete road in the real graph
    static void		deleteRoad(RoadGraphic r) {
	int[]		coord1 = {r.getRealx1(), r.getRealy1()};
	int[]		coord2 = {r.getRealx2(), r.getRealy2()};
	if (controller.eventDeleteRoad(coord1, coord2) == false)
	  System.out.println("MapPanel.deleteRoad(): Error: Road does not exist");

	int[]		coord3 = {r.getRealx2(), r.getRealy2()};
	int[]		coord4 = {r.getRealx1(), r.getRealy1()};
	// roads.remove(getRoad(coord3[0], coord3[1], coord4[0], coord4[1]));
	if (controller.eventDeleteRoad(coord3, coord4) == false)
	  System.out.println("MapPanel.deleteRoad(): Error: Road does not exist");
	// roads.remove(roads.size() - 1);
	System.out.println("MapPanel.deleteRoad: Total road left: " + roads.size());
    }

    static void		deleteNode(NodeGraphic n) {
	int[]		coord = {n.getRealX(), n.getRealY()};

	if (nodes.contains(n) == true) {
	    nodes.remove(0);
	    System.out.println("deleteNode: delete Node  ============");
	    controller.eventDeleteNode(coord);
	}
	else {
	    graphVehicule = null;
	    System.out.println("deleteNode: delete vehicule  ============");
	    controller.eventDeleteVehicule();
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
	g2.drawString("X_real: " + scaleX(this.mouseX) + " Y_real: " + scaleY(this.mouseY),
		      view_x + 10,
		      view_y + scrolBarRef.getHeight() - 30);
	g2.drawString("X: " + this.mouseX + " Y: " + this.mouseY,
		      view_x + 10,
		      view_y + scrolBarRef.getHeight() - 45);
	// g2.scale(
	// 	 (double) this.getW() / (double)maxX,
	// 	 (double) this.getH() / (double)maxY);

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

    // public static boolean	containsNode(int p_x, int p_y) {
    // 	int	i = 0;

    // 	while (i < nodes.size()) {
    // 	    if (nodes.get(i).getx() == p_x
    // 		&& nodes.get(i).gety() == p_y)
    // 		return true;
    // 	    ++i;
    // 	}
    // 	return false;
    // }

    // public static boolean	containsRoad(int x1, int y1, int x2, int y2) {
    // 	int	i = 0;

    // 	while (i < roads.size()) {
    // 	    if (roads.get(i).getx1() == x1
    // 		&& roads.get(i).gety1() == y1
    // 		&& roads.get(i).getx2() == x2
    // 		&& roads.get(i).gety2() == y2)
    // 		return true;
    // 	    ++i;
    // 	}
    // 	return false;
    // }

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

  private NodeGraphic		_displayMapNode(String s, int nodes_it)
    {
	NodeGraphic			newNode = null;
	int rel_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int rel_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
	int	_x = unScaleX(rel_x);
	int	_y = unScaleY(rel_y);

	while (nodes.size() <= nodes_it)
	{
	  nodes.add(new NodeGraphic(EObjectTools.NODE,
				    this.nodeNormal,
				    this.nodeAttachPoint,
				    this.nodeUrgency,
				    0, 0, 0, 0));
	    System.out.println("NODE --- Creating new graphic");
	  this.mapChanged = true;
	}
	newNode = nodes.get(nodes_it);
	if (newNode.getRealX() != rel_x || newNode.getRealY() != rel_y)
	{
	  newNode.setx(_x);
	  newNode.sety(_y);
	  newNode.setRealX(rel_x);
	  newNode.setRealY(rel_y);
	  nodes.set(nodes_it, newNode);
	  System.out.println("NODE --- Changing graphic coord to real " + rel_x+":"+rel_y);
	  // this.mapChanged = true;
	}
	// System.out.println("CREATE NODE GRAPHIC AT ?? : " + scaleX(_x) + " - " + scaleY(_y));
	// if (this.containsNode(_x, _y) == false) {
	    // System.out.println("CREATE NODE GRAPHIC : " + _x + " - " + _y);
	    // newNode = new NodeGraphic(EObjectTools.NODE, this.nodeNormal,
	    // 			      this.nodeAttachPoint,
	    // 			      this.nodeUrgency,
	    // 			      _x, _y,
	    // 			      rel_x, rel_y);
	    // nodes.add(newNode);
	// }
	// return (null);
	return (newNode);
    }

    private NodeGraphic		_displayMapAttachPoint(String s)
    {
	NodeGraphic			newNode = null;
	int rel_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int rel_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
	int	_x = unScaleX(rel_x);
	int	_y = unScaleY(rel_y);

	// if (this.containsNode(_x, _y) == false) {
	//     System.out.println("CREATE ATTACH GRAPHIC : " + _x + " - " + _y);
	//     newNode = new NodeGraphic(EObjectTools.ATTACH_POINT,
	// 			      this.nodeAttachPoint,
	// 			      this.nodeAttachPoint,
	// 			      this.nodeAttachPoint,
	// 			      _x, _y,
	// 			      rel_x, rel_y);
	//     nodes.add(newNode);
	// }
	return (newNode);
    }

    private NodeGraphic		_displayMapVehicule(String s)
    {
	NodeGraphic		newNode = null;
	int rel_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int rel_y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
	int	_x = unScaleX(rel_x);
	int	_y = unScaleY(rel_y);

	if (this.graphVehicule == null) {
	    newNode = new NodeGraphic(EObjectTools.VEHICULE,
				      this.vehicule,
				      this.vehicule,
				      this.vehicule, _x, _y,
				      rel_x, rel_y);
	    this.graphVehicule = newNode;
	}
	return (newNode);
    }

    private NodeGraphic		_displayMapUrgency(String s)
    {
	NodeGraphic		newNode	= null;
	int	rel_x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int	rel_y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
	int	_x = unScaleX(rel_x);
	int	_y = unScaleY(rel_y);

	// if (this.containsNode(_x, _y) == false) {
	//     newNode = new NodeGraphic(EObjectTools.URGENCY,
	// 			      this.nodeUrgency,
	// 			      this.nodeUrgency,
	// 			      this.nodeUrgency,
	// 			      _x, _y,
	// 			      rel_x, rel_y);
	//     nodes.add(newNode);
	// }
	return (newNode);
    }

  private void		_displayMapRoad(String s, int road_it)
    {
      RoadGraphic	road = null;
	int	x1_real = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
	int	y1_real = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
	String	s2 = s.substring(s.lastIndexOf(":"));
	int	x2_real = Integer.parseInt(s2.substring(s2.indexOf(":") + 1, s2.indexOf(",")));
	int	y2_real = Integer.parseInt(s2.substring(s2.indexOf(",") + 1));
	int	x1, x2, y1, y2;
	// if (this.containsRoad(x1, y1, x2, y2) == false) {
	//     RoadGraphic		newRoad = new RoadGraphic(x1, y1, x2, y2);
	//     this.roads.add(newRoad);
	// }
	x1 = unScaleX(x1_real);
	x2 = unScaleX(x2_real);
	y1 = unScaleX(y1_real);
	y2 = unScaleX(y2_real);
	// System.out.println("====> Adding road ");
	while (this.roads.size() <= road_it)
	{
	  this.roads.add(new RoadGraphic(0, 0, 0, 0));
	  System.out.println("Adding graphical road ");
	}
	road = this.roads.get(road_it);
	if (road.getx1() != x1 || road.getx2() != x2
	    || road.gety1() != y1 || road.gety2() != y2)
	{
	  System.out.println("Changing road coord");
	  System.out.print("From(old) "+road.getx1()+":"+road.gety1());
	  System.out.println(" to(old)"+road.getx2()+":"+road.gety2());

	  System.out.print("From(new) "+x1+":"+y1);
	  System.out.println(" to(new)"+x2+":"+y2);
	  road.setCoord(x1, y1, x2, y2);
	  road.setRealCoord(x1_real, y1_real, x2_real, y2_real);
	  this.roads.set(road_it, road);
	  // this.mapChanged = true;
	}
    }

    public void		displayMap(ArrayList<String> formatMap)
    {
      int		nodes_it;
      int		roads_it;
	NodeGraphic		newNode = null;

	this.mapChanged = false;
	nodes_it = 0;
	roads_it = 0;
	// System.out.println("");
	// roads.clear();
	// nodes.clear();
	for (String s : formatMap) {
	    if (s.charAt(0) == 'R') {
	      this._displayMapRoad(s, roads_it++);
	    }
	    else if (isDragging == false)
		{
		    if (s.charAt(0) == 'V') {
			newNode = this._displayMapVehicule(s);
		    }
		    else if (s.charAt(0) == 'N') {
		      newNode = this._displayMapNode(s, nodes_it++);
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
		    }
		}
	}
	if (isDragging == false)
	{
	  while (roads.size() > roads_it) // Cleaning useless road (to remove the 2Dline on the screen also)
	    roads.remove(roads.size() - 1);
	  while (nodes.size() > nodes_it) // Cleaning useless node
	    nodes.remove(nodes.size() - 1);
	}
	if (this.mapChanged == true || this.wasOut == 2) // Only revalidate if something move
	{
	  this.validate();
	  this.wasOut = 0;
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
      this.wasOut = 1;
      // revalidate();
	// this.mouseX = e.getX();
	// this.mouseY = e.getY();
    }

    public void mouseEntered(MouseEvent e) {
      if (this.wasOut == 1)
	wasOut = 2;
      this.mouseX = e.getX();
      this.mouseY = e.getY();
    }

  public void mouseReleased(MouseEvent e) {
    NodeGraphic	newNode;

    if (e.getButton() == MouseEvent.BUTTON1
	&& this.selectedObject == EObjectTools.NODE) {
      if (this.controller.eventPutNode(scaleX(e.getX()), scaleY(e.getY())) == true)
      {
	// System.out.println("CREATE NODE GRAPHIC : " + e.getX() + " - " + e.getY());
	// newNode = new NodeGraphic(EObjectTools.NODE, this.nodeNormal,
	// 			  this.nodeAttachPoint,
	// 			  this.nodeUrgency,
	// 			  e.getX(), e.getY(),
	// 			  scaleX(e.getX()), scaleY(e.getY()));
	// nodes.add(newNode);
      }
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
	    int		x1 = this.selectedBoxCoord1[0];
	    int		y1 = this.selectedBoxCoord1[1];
	    int		x2 = this.selectedBoxCoord2[0];
	    int		y2 = this.selectedBoxCoord2[1];

	    for (NodeGraphic n : this.nodes) {
		if ((x1 < x2 && y1 < y2
		    && n.getx() < x2 && n.getx() > x1
		     && n.gety() < y2 && n.gety() > y1)
		    || (x2 < x1 && y2 < y1
			&& n.getx() < x1 && n.getx() > x2
			&& n.gety() < y1 && n.gety() > y2)
		    || (x1 > x2 && y1 < y2
			&& n.getx() < x1 && n.getx() > x2
			&& n.gety() < y2 && n.gety() > y1)
		    || (x1 < x2 && y1 > y2
			&& n.getx() < x2 && n.getx() > x1
			&& n.gety() < y1 && n.gety() > y2)) {
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

  // x_rel and y_rel are the real coord in the Graph
    static void	setMovedNode1(int x_rel, int y_rel) {
      coordMovedNode[0] = x_rel;
      coordMovedNode[1] = y_rel;
    }

  // x and y are the graphical coord in the Graph
    static void	setMovedNode2(int x, int y) {
      // int[]		coordMovedNode2 = {scaleX(x), scaleY(y)};
      int[]		coordMovedNode2 = {scaleX(x), scaleY(y)};
      // int[]		coordMovedNode2 = {x, y};

      if (controller.eventEditNodeCoord(coordMovedNode, coordMovedNode2) == false)
	System.out.println("setMovedNode2: Error: Node does not exist !!!");
      coordMovedNode[0] = coordMovedNode2[0];
      coordMovedNode[1] = coordMovedNode2[1];
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


    static void	setRoadNode(int x_real, int y_real) {
	if (drawing == false) {
	    roadCoord1[0] = x_real;
	    roadCoord1[1] = y_real;
	    drawing = true;
	}
	else {
	    roadCoord2[0] = x_real;
	    roadCoord2[1] = y_real;
	    controller.eventAddRoad(roadCoord1, roadCoord2);
	    drawing = false;
	}
    }

    static void		setIsDragging(boolean b) {
	isDragging = b;
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

    public static int getW()  {
	return (W);
    }
    public static int getH()  {
	return (H);
    }

    public static int scaleX(int x) {
	return (int)(((double) x / (double)getW()) * (double)maxX);
    }
    public static int scaleY(int y) {
	return (int)(((double) y / (double)getH()) * (double)maxY);
    }
    public static int unScaleX(int x) {
	return (int)(((double) x / (double)maxX) * (double)getW());
    }
    public static int unScaleY(int y) {
	return (int)(((double) y / (double)maxY) * (double)getH());
    }

  // Passage des coord graphique aux coord réel
    static public int scale(int coord, int curr_max, int real_max) {
	return (int)(((double) coord / (double)curr_max) * (double)real_max);
    }
  // Passage des coord réel aux coord graphique
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
