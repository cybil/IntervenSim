import java.util.ArrayList;


public class Vehicule {
	
    private float		km;
    private ArrayList<int>	coord;
    private Node		attachPoint;
    private ArrayList<Node>	path;
    private int			speed;
	
    //******************
    //	Constructor
    //******************
	
    public Vehicule() {
	this.km = 0;
	this.coord = null;
	this.attachPoint = null;
	this.path = null;
	this.speed = 1;
    }

    public Vehicule(float km, ArrayList<int> coord, 
		    Node attachPoint, ArrayList<Node> path, int speed) {
	this.km = km;
	this.coord = coord;
	this.attachPoint = attachPoint;
	this.path = path;
	this.speed = speed;
    }
	
    public Vehicule(Vehicule vehicule) {
	this.km = vehicule.this.km;
	this.coord = vehicule.this.coord;
	this.attachPoint = vehicule.this.attachPoint;
	this.path = vehicule.this.path;
	this.speed = vehicule.this.speed;
    }
	
    //***************
    //	Destructor
    //***************
	
    public void finalize() {
		
    }

    //*****************
    //	Get Functions
    //*****************
	
    float getKm() {
	return this.km;
    }
	
    int getSpeed() {
	return this.speed;
    }
	
    ArrayList<int> getCoord() {
	return this.coord;
    }
	
    //****************
    //	Set Functions
    //****************
	
    //If an Path exist, add new path after or not?
    void setPath(ArrayList<Node> newPath) {
	if (this.path.isEmpty())
	    this.path = newPath;
	else
	    this.path.addAll(newPath);
    }
	
    void setSpeed(int newSpeed) {
	this.speed = newSpeed;
    }

    //***************
    // Other
    //***************	
	
    int treatUrgency() {
	return 0;
    }
		
    boolean isFree() {
	if (this.path.isEmpty())
	    return false;
	return true;
    }
		
}
