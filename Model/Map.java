import java.awt.Image;
import java.util.*;

public class Map implements java.io.Serializable {

    private Graph		graph = new Graph();
    private float		scale = 1;
    private Vehicule		vehicule;
    private Image		image = null;
    private int			zoom = 100;

    //******************
    //	Constructor
    //******************

    public Map() {
    }
    public Map(Map map) {
    	this.scale = map.getScale();
    	
    	this.graph = new Graph(map.getGraph());
    	/*if (this.vehicule != null)*/
	  //this.vehicule = new Vehicule(map.getVehicule());
    	vehicule = map.vehicule;
    	this.image = map.getImage();
    	this.zoom = map.getZoom();
    	
	//    	graph = new Graph(map.graph);
	//    	scale = map.scale;
	//    	//vehicule = new Vehicule(map.vehicule);
	//    	vehicule = map.vehicule;
	//    	image = map.image;
	//    	zoom = map.zoom;
    }

    //***************
    //	Add
    //***************

    public Vector<String>	getUrgencyList(int[] coord) {
	Node			node = this.graph.getNode(coord);

	if (node != null)
	    {
		ArrayList<Urgency>	list = node.getUrgency();
		Vector<String>		ret = new Vector<String>();

		for (Urgency u : list) {
		    String		str;
		    str = "Date: " + Float.toString(u.getTriggerDate()) + " ; Treatment: " + Float.toString(u.getTreatmentTime());
		    ret.add(str);
		}
		return ret;
	    }
	else
	    System.out.println("Map.getUrgencyList(): Error Unable to find Node");
	return (new Vector<String>());
    }

    public boolean		addNode(int x, int y) {
	return (this.graph.creatNode(x, y));
    }

    public boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state,
					       float triggDate, float treatmentTime, int id) {
	if (this.graph.getNode(coord) != null) {
	    Urgency			urg = new Urgency(state, triggDate, 0, treatmentTime, id);
	    this.graph.getNode(coord).addUrgency(urg);
	    return (true);
	}
	return false;
    }

    public void			clearUrgency(int[] coord) {
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).clearUrgency();
	}	
    }


    public boolean		addRoad(int[] coordNode1, int[] coordNode2) {
	return (this.graph.creatRoad(this.graph.getNode(coordNode1), this.graph.getNode(coordNode2)));
	// Node			src;
	// Node			dst;

	// src = this.graph.getNode(coordNode1);
	// dst = this.graph.getNode(coordNode2);
	// if (src == null || dst == null)
	// {
	// 	System.out.print("Map.addRoad(): Error: Can't find node at ");
	// 	if (src == null) System.out.println(coordNode1[0] + ":" + coordNode1[1]);
	// 	else System.out.println(coordNode2[0] + ":" + coordNode2[1]);
	// 	return (false);
	// }
	// else
	// 	this.graph.creatRoad(this.graph.getNode(coordNode1), this.graph.getNode(coordNode2));
	// return (true);
    }

    public boolean		creatVehicule(int[] coord) {
	if (this.vehicule != null)
	    return false;
	this.vehicule = new Vehicule();
	this.vehicule.setCoord(coord.clone());
	return true;
    }

    //***************
    //	Delete
    //***************

    public void			clearMap() {
	this.graph.clearGraph();
    }

    public boolean		deleteVehicule() {
	if (this.vehicule != null)
	    {
		this.vehicule = null;
		return (true);
	    }
	return (false);
    }

    public boolean		deleteNode(int[] coord) {
	return (this.graph.deleteNode(coord));
    }

    public boolean		deleteRoad(int[] coord1, int[] coord2) {
	return (this.graph.deleteRoad(this.graph.getNode(coord1), this.graph.getNode(coord2)));
    }

    //***************
    //	Other
    //***************


    public boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
	System.out.println("============ EDIT NODE ==============");

	if (this.graph.getNode(oldCoord) != null) {
	    this.graph.getNode(oldCoord).setCoord(newCoord);
	    return true;
	}
	return false;
    }

    public boolean		editTreatmentTime(int[] coord, float time, int id) {
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).setUrgencyTime(id, time);
	    return true;
	}
	return false;
    }

    public boolean		editAttachPoint(int[] coord, boolean state) {
	this.graph.deleteAttachPoint();
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).setAttachPoint(state);
	    if (this.vehicule == null)
		this.vehicule = new Vehicule(coord);
	    return true;
	}
	return false;
    }

    //**
    // * Getters
    //**
    
    public int[]		getAttachPointCoord() {
	return this.graph.getAttachPointCoord();
    }

    public Image		getBackground() {
	return this.image;
    }

    public ArrayList<GraphNode>	getAllGraphNodes() {
	return graph.getAllNodes();
    }

    public int[]		getVehiculeCoord() {
	if (this.vehicule != null)
	    return this.vehicule.getCoord();
	return null;
    }

    public int			getNbNode() {
	return graph.getNbNode();
    }

    public int			getNbRoad() {
	return graph.getNbRoad();
    }

    public void			setFormatMap(ArrayList<String> format) {
	for (String s : format) {
	    if (s.charAt(0) == 'V') {
		int x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		int[]	coord = {x, y};
		creatVehicule(coord);
	    }
	    else if (s.charAt(0) == 'N') {
		int x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		int[]	coord = {x, y};
		addNode(x, y);
	    }
	    else if (s.charAt(0) == 'A') {
		int x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int y = Integer.parseInt(s.substring(s.indexOf(",") + 1));
		int[]	coord = {x, y};
		addNode(x, y);
		editAttachPoint(coord, true);
	    }
	    else if (s.charAt(0) == 'U') {
		int	x = Integer.parseInt(s.substring(s.indexOf(":") + 1, s.indexOf(",")));
		int	y = Integer.parseInt(s.substring(s.indexOf(",") + 1, s.lastIndexOf(":")));
		int[]	coord = {x, y};
		addNode(x, y);

		String s2 = s.substring(s.lastIndexOf(":"));
		int		i = 0;
		while (s2.length() != 0) {
		    char	state = s2.charAt(0);
		    Float	trigg = Float.parseFloat(s2.substring(s2.indexOf("!") + 1,
								      s2.indexOf("|")));
		    Float	treat = Float.parseFloat(s2.substring(s2.indexOf("|") + 1,
								      s2.indexOf("/")));

		    addNodeUrgency(coord, Urgency.EUrgencyState.SLEEPING, trigg, treat, i++);
		    s2 = s2.substring(s2.indexOf("/") + 1);
		}
	    }
	}
	for (String s : format) {
	    if (s.charAt(0) == 'R') {
		int	x1 = Integer.parseInt(s.substring(s.indexOf(":") + 1,
							  s.indexOf(",")));
		int	y1 = Integer.parseInt(s.substring(s.indexOf(",") + 1,
							  s.lastIndexOf(":")));
		String	s2 = s.substring(s.lastIndexOf(":"));
		int	x2 = Integer.parseInt(s2.substring(s2.indexOf(":") + 1,
							   s2.indexOf(",")));
		int	y2 = Integer.parseInt(s2.substring(s2.indexOf(",") + 1));
		int[]	coord1 = {x1, y1};
		int[]	coord2 = {x2, y2};
		addRoad(coord1, coord2);
	    }
	}

    }

    public ArrayList<String>	getFormatMap() {
   
	ArrayList<String>	format = new ArrayList<String>();
	// Urgence:	U:x,y:S(sleeping) or W(waiting) or P(in progress) or D(done)
	// AttachPoint: A:x,y
	// Both:	B:x,y:S(sleeping) or W(waiting) or P(in progress) or D(done)
	// Normal:	N:x,y
	// Vehicule:	V:x,y
	// Road:	R:x1,y1:x2,y2

	// Ajout du vehicule
	if (this.vehicule != null) {
	    String			formatVehicule = "V:";
	    formatVehicule += this.vehicule.getCoord()[0] + "," + this.vehicule.getCoord()[1];
	    format.add(formatVehicule);
	}
	// Ajout des noeuds
	ArrayList<GraphNode>	nodes = this.graph.getGraphNode();
	for (GraphNode n : nodes) {
	    String		str;
	    if (n.getData().hasUrgency() == true
		&& n.getData().getAttachPoint() == true)
		str = "B:";
	    else if (n.getData().getAttachPoint() == true)
		str = "A:";
	    else if (n.getData().hasUrgency() == true)
		str = "U:";
	    else
		str = "N:";
	    str += n.getData().getCoord()[0] + "," + n.getData().getCoord()[1];
	    if (str.charAt(0) == 'U' || str.charAt(0) == 'B')
		str += ":";
	    for (Urgency u : n.getData().getUrgency())
		{
		    if (str.charAt(0) == 'U' || str.charAt(0) == 'B') {
			if (n.getData().getNextUrgency().getState()
			    == Urgency.EUrgencyState.SLEEPING)
			    str += "S!";
			if (n.getData().getNextUrgency().getState()
			    == Urgency.EUrgencyState.WAITING)
			    str += "W!";
			if (n.getData().getNextUrgency().getState()
			    == Urgency.EUrgencyState.IN_PROGRESS)
			    str += "P!";
			if (n.getData().getNextUrgency().getState()
			    == Urgency.EUrgencyState.DONE)
			    str += "D!";
		    }
		    str += Float.toString(u.getTriggerDate()) + "|" + Float.toString(u.getTreatmentTime()) + "/";
		}
	    format.add(str);

	    // Ajout des routes
	    for (GraphNode ne : n.getNeighbors()) {
		String		road;
		road = "R:" + n.getData().getCoord()[0] + "," + n.getData().getCoord()[1]
		    + ":" + ne.getData().getCoord()[0] + "," + ne.getData().getCoord()[1];
		format.add(road);
	    }
	}

	return format;
    }

    public ArrayList<Node>	getNodeUrgency() {
	return graph.getNodeUrgency();
    }

    public boolean		hasVehicule() {
	if (this.vehicule == null)
	    return false;
	return true;
    }

    //**
    // * Setters
    //**

    public void		actualizeVehicule() {
      System.out.println("Map.actualizeVehicule()");
	this.vehicule.moveOn();
    }

    public boolean	setZoom(int newZoom) {
	this.zoom = newZoom;
	return true;
    }

    public void		setVehiculePath(ArrayList<Node> path) {
	this.vehicule.setPath(path);
    }

    public void		setVehiculeCoord(int x, int y) {
	int[]		newCoord = {x, y};
	if (this.vehicule != null)
	    this.vehicule.setCoord(newCoord);
    }

    public void		setBackground(Image bck) {
	this.image = bck;
    }

    //**
    // * DISPLAY
    //**
    public void		display()
    {
    	System.out.println("Nombre noeud : " + this.graph.getNbNode());
    	System.out.println("Nombre de route : " + this.graph.getNbRoad());
    	System.out.println("Noeuds :");
    	ArrayList<GraphNode>	nodes = this.graph.getAllNodes();

    	for (GraphNode g : nodes)
    	    {
    		System.out.print("Coord: " + g.getData().getCoord()[0]
    				 + " - " + g.getData().getCoord()[1]);
    		System.out.println("  " + (g.getData().hasUrgency() == true
    					   ? "URGENCY (" + g.getData().getNbUrgency() + ") => " + g.getData().getNextUrgency().getTriggerDate()
    					   : "Normal"));
    		System.out.println("  Voisins:");
    		for (GraphNode n : g.getNeighbors())
    		    {
    			System.out.println("Total Neigh/Neighbors(" +  n.getNeighbors().size() + ")");
    			System.out.println("    Coord: " + n.getData().getCoord()[0]
    					   + " - " + n.getData().getCoord()[1]);
    		    }
    		System.out.println("");
    	    }
    }

    public Graph getGraph() {
	return graph;
    }

    public void setGraph(Graph graph) {
	this.graph = graph;
    }

    public float getScale() {
	return scale;
    }

    public void setScale(float scale) {
	this.scale = scale;
    }

    public Vehicule getVehicule() {
	return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
	this.vehicule = vehicule;
    }

    public Image getImage() {
	return image;
    }

    public void setImage(Image image) {
	this.image = image;
    }

    public int getZoom() {
	return zoom;
    }
}
