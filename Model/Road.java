
public class Road {
	//ATTENTION NE PLUS METTRE DE LENTGH A MODIFER
	int _length;
	
	boolean _hasVehicule;

	//******************
	//	Constructor
	//******************
	
	public Road() {
		_length = 0;
		_hasVehicule = false;
	}
	
	public Road(int length, boolean hasVehicule) {
		_length = length;
		_hasVehicule = hasVehicule;
	}
	
	public Road(Road road) {
		_length = road.getLength();
		_hasVehicule = road.getHasVehicule();
	}
	
	//***************
	//	Destructor
	//***************
	
	public void finalize() {
		
	}
	
	//****************
	//	Get Functions
	//****************
	
	public int getLength() {
		return _length;
	}
	
	public boolean getHasVehicule() {
		return _hasVehicule;
	}
	
	//****************
	//	Set Functions
	//****************
	
	public void setLength(int newLength) {
		_length = newLength;
	}
	
	public void setHasVehicule(boolean newHasVehicule) {
		_hasVehicule = newHasVehicule;
	}
	
	//***************
	// Other
	//***************
	
	public void changeStatus() {
		_hasVehicule = !_hasVehicule;
	}
	
	public boolean hasVehicule() {
		return _hasVehicule;
	}
}
