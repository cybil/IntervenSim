import java.awt.Image;
import java.util.ArrayList;

public class Map {

    //Container 
    private Graph	graph = new Graph();
    private float	scale = 1;
    //Class of vehicule
    private Vehicule	vehicule = new Vehicule();
    //Background Image
    private Image	image;
    private int		zoom;
	
    //******************
    //	Constructor
    //******************
	
    public Map() {
		
    }
	
    //***************
    //	Other
    //***************
	
    boolean		addNode(int x, int y, ArrayList<Urgency> urgencyList) {
	this.graph.creatNode(x, y, urgencyList);
	return true;
    }
		
    boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
	if (this.graph.getNode(oldCoord) != null) {
	    this.graph.getNode(oldCoord).setCoord(newCoord);
	    return true;
	}
	return false;
    }
	
    boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state, float triggDate) {
	if (this.graph.getNode(coord) != null) {
	    Urgency	urg = new Urgency(state, triggDate, 0, 5);
	    this.graph.getNode(coord).addUrgency(urg);
	}
	return false;
    }
	
    boolean		editTreatmentTime(int[] coord, float time, int id) {
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).setUrgencyTime(id, time);
	    return true;
	}
	return false;
    }
	
    boolean		editAttachPoint(int[] coord, boolean state) {
	if (this.graph.getNode(coord) != null) {
	    this.graph.getNode(coord).setAttachPoint(new AttachPoint(state, this.vehicule));
	    return true;
	}
	return false;
    }
	
    boolean		addRoad(int[] coordNode1, int[] coordNode2) {
	return false;
    }
	
    boolean		creatVehicule(int[] coord) {
	return false;
    }
	
    boolean		deleteNode(int[] coord) {
	return false;
    }
	
    boolean		deleteRoad(int[] coordNode1, int[] coordNode2) {
	return false;
    }
	
    boolean		deleteVehicule() {
	return false;
    }

    //**
    // * Getters
    //**

    int			getNbNode() {
	return graph.getNbNode();
    }
	
    int			getNbRoad() {
	return graph.getNbRoad();
    }

    String		getFormatMap() {
	return "";
    }

    ArrayList<Node>	getNodeUrgency() {
	return graph.getNodeUrgency();
    }

    //**
    // * Setters
    //**
	
    boolean		setZoom(int newZoom) {
	this.zoom = newZoom;
	return true;
    }
	
}
