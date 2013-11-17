
public class AttachPoint {

    private boolean	isActiv;
    private Vehicule	vehicule;
	
    //******************
    //	Constructor
    //******************	
	
    public AttachPoint() {
	this.isActiv = false;
	this.vehicule = null;
    }
	
    public AttachPoint(boolean isActiv, Vehicule vehicule) {
	this.isActiv = this.isActiv;
	this.vehicule = this.vehicule;
    }
	
    public AttachPoint(AttachPoint attachPoint) {
	this.isActiv = attachPoint.isActiv;
	this.vehicule = attachPoint.vehicule;
    }

    //***************
    //	Destructor
    //***************

	
    public void finalize() {
	this.vehicule = null;
    }
	
    //*****************
    //	Get Functions
    //*****************

	
    //return null if no this.vehicule
    public Vehicule getVehicule() {
	return this.vehicule;
    }
	
    //return null if no this.vehicule
    public boolean getIsActiv() {
	return this.isActiv;
    }

    //****************
    //	Set Functions
    //****************
	
    public void setIsActiv(boolean isActiv) {
	this.isActiv = this.isActiv;
    }
	
    public void setVehicule(Vehicule newVehicule) {
	this.vehicule = newVehicule;
    }
	
    //************
    //	Others
    //************
	
    public void changeStatus() {
	if (this.isActiv)
	    this.isActiv = false;
	else
	    this.isActiv = true;
    }
	
    public boolean isActiv() {
	return this.isActiv;
    }

}
