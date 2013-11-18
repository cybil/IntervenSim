
public class Road {
    
    private Node	node1;
    private Node	node2;
    private boolean	hasVehicule;

    //******************
    //	Constructor
    //******************
	
    public Road() {
	this.hasVehicule = false;
    }
	
    public Road(Node n1, Node n2, boolean hasVehicule) {
	this.node1 = n1;
	this.node2 = n2;
	this.hasVehicule = hasVehicule;
    }
	
    public Road(Road road) {
	this.hasVehicule = road.getHasVehicule();
    }
	
    //***************
    //	Destructor
    //***************
	
    public void finalize() {
		
    }
	
    //****************
    //	Get Functions
    //****************
	
    public int		getLength() {
	return 1;
    }
	
    public boolean	getHasVehicule() {
	return this.hasVehicule;
    }

    //****************
    //	Set Functions
    //****************
	
    public void		setHasVehicule(boolean newHasVehicule) {
	this.hasVehicule = newHasVehicule;
    }
	
    //***************
    // Other
    //***************
	
    public void		changeStatus() {
	this.hasVehicule = !this.hasVehicule;
    }
	
    public boolean	hasVehicule() {
	return this.hasVehicule;
    }
}
