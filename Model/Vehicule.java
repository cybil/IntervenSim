import java.util.ArrayList;


public class Vehicule {
	
	float _km;
	
	ArrayList<Integer> _coord;
	
	Node _attachPoint;
	
	ArrayList<Node> _path;
	
	int	_speed;
	
	//******************
	//	Constructor
	//******************
	
	public Vehicule() {
		_km = 0;
		_coord = null;
		_attachPoint = null;
		_path = null;
		_speed = 1;
	}

	public Vehicule(float km, ArrayList<Integer> coord, 
			Node attachPoint, ArrayList<Node> path, int speed) {
		_km = km;
		_coord = coord;
		_attachPoint = attachPoint;
		_path = path;
		_speed = speed;
	}
	
	public Vehicule(Vehicule vehicule) {
		_km = vehicule._km;
		_coord = vehicule._coord;
		_attachPoint = vehicule._attachPoint;
		_path = vehicule._path;
		_speed = vehicule._speed;
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
		return _km;
	}
	
	int getSpeed() {
		return _speed;
	}
	
	ArrayList<Integer> getCoord() {
		return _coord;
	}
	
	//****************
	//	Set Functions
	//****************
	
	//If an Path exist, add new path after or not?
	void setPath(ArrayList<Node> newPath) {
		if (_path.isEmpty())
			_path = newPath;
		else
			_path.addAll(newPath);
	}
	
	void setSpeed(int newSpeed) {
		_speed = newSpeed;
	}

	//***************
	// Other
	//***************	
	
		int treatUrgency() {
			return 0;
		}
		
		boolean isFree() {
			if (_path.isEmpty())
				return false;
			return true;
		}
		
}
