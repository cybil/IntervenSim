
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
	
    //*****************
    //	Get Functions
    //*****************

    public Vehicule getVehicule() {
	return this.vehicule;
    }

    public boolean getIsActiv() {
	return this.isActiv;
    }

    //****************
    //	Set Functions
    //****************
	
    public void setIsActiv(boolean newActivity) {
	this.isActiv = newActivity;
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
