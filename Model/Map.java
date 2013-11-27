import java.awt.Image;
import java.util.ArrayList;

public class Map implements java.io.Serializable {

    private Graph		graph = new Graph();
    private float		scale = 1;
    private Vehicule		vehicule = new Vehicule();
    private Image		image = null;
    private int			zoom = 100;

    //******************
    //	Constructor
    //******************

    public Map() {
	// Jb: Pourquoi 10/10 en coordonnee ?
	// Cybil: Pour le mettre a un endroit pour test de l'afficher :)
	int[] coord = {-1000, -1000};
	this.vehicule.setCoord(coord);

    }

    //***************
    //	Add
    //***************

    public boolean		addNode(int x, int y) {
	this.graph.creatNode(x, y);
	return true;
    }

    public boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state, float triggDate) {
	if (this.graph.getNode(coord) != null) {
	    Urgency			urg = new Urgency(state, triggDate, 0, 5);
	    this.graph.getNode(coord).addUrgency(urg);
	}
	return false;
    }


    public boolean		addRoad(int[] coordNode1, int[] coordNode2) {
	this.graph.creatRoad(this.graph.getNode(coordNode1), this.graph.getNode(coordNode2));
	return true;
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
	this.vehicule.setCoord(coord.clone());
	return (true);
    }

    //***************
    //	Delete
    //***************

    public boolean		deleteNode(int[] coord) {
	this.graph.deleteNode(coord);
	return true;
    }

    public boolean		deleteRoad(int[] coord1, int[] coord2) {
	this.graph.deleteRoad(this.graph.getNode(coord1), this.graph.getNode(coord2));
	return true;
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
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).setAttachPoint(new AttachPoint(state, this.vehicule));
	    return true;
	}
	return false;
    }

    //**
    // * Getters
    //**
    
    public Image		getBackground() {
	return this.image;
    }

    public ArrayList<GraphNode>	getAllGraphNodes() {
	return graph.getAllNodes();
    }

    public int[]		getVehiculeCoord() {
	return this.vehicule.getCoord();
    }

    public int			getNbNode() {
	return graph.getNbNode();
    }

    public int			getNbRoad() {
	return graph.getNbRoad();
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
	String			formatVehicule = "V:";
	formatVehicule += this.vehicule.getCoord()[0] + "," + this.vehicule.getCoord()[1];
	format.add(formatVehicule);

	// Ajout des noeuds
	ArrayList<GraphNode>	nodes = this.graph.getGraphNode();
	for (GraphNode n : nodes) {
	    String		str;
	    if (n.getData().hasUrgency() == true
		&& n.getData().getAttachPoint() != null)
		str = "B:";
	    else if (n.getData().getAttachPoint() != null)
		str = "A:";
	    else if (n.getData().hasUrgency() == true)
		str = "U:";
	    else
		str = "N:";
	    str += n.getData().getCoord()[0] + "," + n.getData().getCoord()[1];
		
	    if (str.charAt(0) == 'U' || str.charAt(0) == 'B') {
		if (n.getData().getNextUrgency().getState()
		    == Urgency.EUrgencyState.SLEEPING)
		    str += ":S";
		if (n.getData().getNextUrgency().getState()
		    == Urgency.EUrgencyState.WAITING)
		    str += ":W";
		if (n.getData().getNextUrgency().getState()
		    == Urgency.EUrgencyState.IN_PROGRESS)
		    str += ":P";
		if (n.getData().getNextUrgency().getState()
		    == Urgency.EUrgencyState.DONE)
		    str += ":D";
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

    //**
    // * Setters
    //**

    public void		actualizeVehicule() {
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

}
