
public class AttachPoint {

	private boolean _isActiv;
	private Vehicule _vehicule;
	
	//******************
	//	Constructor
	//******************	
	
	public AttachPoint() {
		_isActiv = false;
		_vehicule = null;
	}
	
	public AttachPoint(boolean isActiv, Vehicule vehicule) {
		_isActiv = isActiv;
		_vehicule = vehicule;
	}
	
	public AttachPoint(AttachPoint attachPoint) {
		_isActiv = attachPoint._isActiv;
		_vehicule = attachPoint._vehicule;
	}

	//***************
	//	Destructor
	//***************

	
	public void finalize() {
		_vehicule = null;
	}
	
	//*****************
	//	Get Functions
	//*****************

	
	//return null if no vehicule
	public Vehicule getVehicule() {
		return _vehicule;
	}
	
	//return null if no vehicule
	public boolean getIsActiv() {
		return _isActiv;
	}

	//****************
	//	Set Functions
	//****************
	
	public void setIsActiv(boolean isActiv) {
		_isActiv = isActiv;
	}
	
	public void setVehicule(Vehicule newVehicule) {
		_vehicule = newVehicule;
	}
	
	//************
	//	Others
	//************
	
	public void changeStatus() {
		if (_isActiv)
			_isActiv = false;
		else
			_isActiv = true;
	}
	
	public boolean isActiv() {
		return _isActiv;
	}

}
