import java.util.ArrayList;


public class Node {

    private ArrayList<Integer>	coord;
    private ArrayList<Urgency>	urgencyList;
    private AttachPoint		attachPoint;
	
    //******************
    //	Constructor
    //******************
	
    public Node() {
	this.coord = null;
	this.urgencyList = null;
	this.attachPoint = null;
    }
	
    public Node(ArrayList<Integer> newCoord,
		ArrayList<Urgency> newUrgencyList,
		AttachPoint newAttachPoint) {
	this.coord = newCoord;
	this.urgencyList = newUrgencyList;
	this.attachPoint = newAttachPoint;
    }
	
    public Node(Node node) {
	this.coord = node.getCoord();
	this.urgencyList = node.getUrgency();
	this.attachPoint = node.getAttachPoint();
    }
	
    //***************
    //	Destructor
    //***************
	
    public void finalize() {
		
    }
	 
    //*****************
    //	Get Functions
    //*****************
	
    public AttachPoint getAttachPoint() {
	return this.attachPoint;
    }
	
    public ArrayList<Integer> getCoord() {
	return this.coord;
    }
	
    public ArrayList<Urgency> getUrgency() {
	return this.urgencyList;
    }
	
    //****************
    //	Set Functions
    //****************
	
    public void setAttachPoint(AttachPoint newAttachPoint) {
	this.attachPoint = newAttachPoint;
    }
	
    public void setCoord(ArrayList<Integer> newCoord) {
	this.coord = newCoord;
    }
	
    public void setUrgency(ArrayList<Urgency> newUrgencyList) {
	this.urgencyList = newUrgencyList;
    }
	
    //************
    //	Others
    //************
	
    public float getTriggerDate() {
	return this.urgencyList.get(0).getTriggerDate();
    }
}
