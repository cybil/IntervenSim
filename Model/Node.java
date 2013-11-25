import java.util.*;


public class Node implements java.io.Serializable {

    private int[]		coord;
    private ArrayList<Urgency>	urgencyList = new ArrayList<Urgency>();
    private AttachPoint		attachPoint;
	
    //******************
    //	Constructor
    //******************
	
    public Node() {
	this.attachPoint = null;
    }
	
    public Node(int[] newCoord, AttachPoint newAttachPoint) {
	this.coord = newCoord;
	this.attachPoint = newAttachPoint;
    }
	
    public Node(Node node) {
	this.coord = node.getCoord();
	this.urgencyList = node.getUrgency();
	this.attachPoint = node.getAttachPoint();
    }

    //*****************
    //	Get Functions
    //*****************
	
    public AttachPoint		getAttachPoint() {
	return this.attachPoint;
    }
	
    public int[]		getCoord() {
	return this.coord;
    }
	
    public boolean		hasUrgency() {
	return !(this.urgencyList.isEmpty());
    }

    public ArrayList<Urgency>	getUrgency() {
	return this.urgencyList;
    }
	
    public float		getTriggerDate() {
	if (!this.urgencyList.isEmpty())
	    return (this.urgencyList.get(0).getTriggerDate());
	return -1;
    }

    public int			getNbUrgency() {
	return this.urgencyList.size();
    }

    public Urgency		getNextUrgency() {
	Urgency			next = this.urgencyList.get(0);
	for (Urgency u : this.urgencyList) {
	    if (u.getTriggerDate() < next.getTriggerDate())
		next = u;
	}
	return next;
    }

    //****************
    //	Set Functions
    //****************
	
    public void			setAttachPoint(AttachPoint newAttachPoint) {
	this.attachPoint = newAttachPoint;
    }
	
    public void			setCoord(int[] newCoord) {
	this.coord = newCoord;
    }
	
    public void			setUrgencyTime(int id, float time) {
	for (Urgency u : this.urgencyList) {
	    if (u.getId() == id)
		u.setTreatmentTime(time);
	}
    }

    //**
    // * Other
    // *

    public void			addUrgency(Urgency newUrgency) {
	this.urgencyList.add(newUrgency);
    }

    public void			removeUrgency(Urgency urgency) {
	this.urgencyList.remove(urgency);
    }
}
