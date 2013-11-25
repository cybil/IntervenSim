import java.util.ArrayList;


public class Vehicule implements java.io.Serializable {

    public enum EVehiculeState {
	WAITING, ON_THE_ROAD, WORKING;
    }

    private float		km;
    private int[]		coord;
    private Node		attachPoint;
    private ArrayList<Node>	path = new ArrayList<Node>();
    private int			speed;
    private EVehiculeState	state = EVehiculeState.WAITING;
	
    //******************
    //	Constructor
    //******************
	
    public Vehicule() {
	this.km = 0;
	this.attachPoint = null;
	this.speed = 1;
    }

    public Vehicule(float km, int[] coord, 
		    Node attachPoint, ArrayList<Node> path, int speed) {
	this.km = km;
	this.coord = coord;
	this.attachPoint = attachPoint;
	this.path = path;
	this.speed = speed;
    }
	
    public Vehicule(Vehicule vehicule) {
	this.km = vehicule.km;
	this.coord = vehicule.coord;
	this.attachPoint = vehicule.attachPoint;
	this.path = vehicule.path;
	this.speed = vehicule.speed;
	this.state = vehicule.state;
    }
	

    //*****************
    //	Get Functions
    //*****************
	
    float		getKm() {
	return this.km;
    }
	
    int			getSpeed() {
	return this.speed;
    }
	
    int[]		 getCoord() {
	return this.coord;
    }
	
    EVehiculeState	getState() {
	return this.state;
    }

    //****************
    //	Set Functions
    //****************

    void		setPath(ArrayList<Node> newPath) {
	this.state = EVehiculeState.ON_THE_ROAD;
	if (this.path.isEmpty())
	    this.path = newPath;
	else
	    this.path.addAll(newPath);
    }
	
    void		setSpeed(int newSpeed) {
	this.speed = newSpeed;
    }

    void		setCoord(int[] newCoord) {
	this.coord = newCoord;
    }

    //***************
    // Other
    //***************	
    
    public void		moveOn() {
	++this.coord[0];
	++this.coord[1];
    }

    int			treatUrgency() {
	this.state = EVehiculeState.WORKING;
	return 0;
    }
		
    boolean		isFree() {
	if (this.path.isEmpty())
	    return false;
	return true;
    }
		
}
