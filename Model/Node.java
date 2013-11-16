import java.util.ArrayList;


public class Node {

	private ArrayList<Integer> _coord;
	
	private ArrayList<Urgency> _urgencyList;
	
	private AttachPoint	_attachPoint;
	
	
	//******************
	//	Constructor
	//******************
	
	public Node() {
		_coord = null;
		_urgencyList = null;
		_attachPoint = null;
	}
	
	public Node(ArrayList<Integer> newCoord,
				ArrayList<Urgency> newUrgencyList,
				AttachPoint newAttachPoint) {
		_coord = newCoord;
		_urgencyList = newUrgencyList;
		_attachPoint = newAttachPoint;
	}
	
	public Node(Node node) {
		_coord = node.getCoord();
		_urgencyList = node.getUrgency();
		_attachPoint = node.getAttachPoint();
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
		return _attachPoint;
	}
	
	public ArrayList<Integer> getCoord() {
		return _coord;
	}
	
	public ArrayList<Urgency> getUrgency() {
		return _urgencyList;
	}
	
	//****************
	//	Set Functions
	//****************
	
	public void setAttachPoint(AttachPoint newAttachPoint) {
		_attachPoint = newAttachPoint;
	}
	
	public void setCoord(ArrayList<Integer> newCoord) {
		_coord = newCoord;
	}
	
	public void setUrgency(ArrayList<Urgency> newUrgencyList) {
		_urgencyList = newUrgencyList;
	}
	
	//************
	//	Others
	//************
	
	public float getTriggerDate() {
		return _urgencyList.get(0).getTriggerDate();
	}
}
