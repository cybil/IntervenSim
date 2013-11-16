import java.awt.Image;
import java.util.ArrayList;

public class Map {
	
	//Container 
	Graphe _graphe;
	
	//
	float _scale = 1;
	
	//Class of vehicule
	Vehicule _vehicule;
	
	//Background Image
	Image _image;
	
	//
	int _zoom;
	
	//******************
	//	Constructor
	//******************
	
	public Map() {
		
	}
	
	//***************
	//	Destructor
	//***************
	
	public void finalize() {
		
	}
	
	boolean addNode(ArrayList<Integer> coord, ArrayList<Urgency> urgencyList) {
		return false;
	}
		
	boolean editNodeCoord(ArrayList<Integer> oldCoord, ArrayList<Integer> newCoord) {
		return false;
	}
	
	//Ca le colle enormement a une classe qui est a 10000 kilometres ATTENTION!!!!
	boolean editNodeUrgency(ArrayList<Integer> coord, Urgency.EUrgencyState Estate , long triggerDate) {
		return false;
	}
	
	boolean editTreatmentTime(ArrayList<Integer> coord, long time) {
		return false;
	}
	
	boolean editAttachPoint(ArrayList<Integer> coord, boolean state) {
		return false;
	}
	
	boolean addRoad(ArrayList<Integer> coordNode1, ArrayList<Integer> coordNode2) {
		return false;
	}
	
	boolean creatVehicule(ArrayList<Integer> coord) {
		return false;
	}
	
	boolean deleteNode(ArrayList<Integer> coord) {
		return false;
	}
	
	boolean deleteRoad(ArrayList<Integer> coordNode1, ArrayList<Integer> coordNode2) {
		return false;
	}
	
	boolean deleteVehicule() {
		return false;
	}

	int getNbNode() {
		return 0;	
	}
	
	int getNbRoad() {
		return 0;	
	}
	
	String getFormatMap() {
		return "";
	}
	
	 ArrayList<Node> getNodeUrgency() {
		return null;
	}
	
	boolean setZoom()
	{
		return false;
	}
	
}
