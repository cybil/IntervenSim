import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Statistic {
	
	VehiculeInfo	_vehicule;
	ArrayList<UrgencyInfo>	_urgencyInfo;
	
	public Statistic() {
		_vehicule = new VehiculeInfo();
		_urgencyInfo = new ArrayList<UrgencyInfo>();
	}
	
	public Statistic(VehiculeInfo vehicule, ArrayList<UrgencyInfo> urgencyInfo) {
		_vehicule = vehicule;
		_urgencyInfo = urgencyInfo;
	}
	
	public Statistic(Statistic stat) {
		_vehicule = stat._vehicule;
		_urgencyInfo = stat._urgencyInfo;
	}
	
	public boolean load() {
		
		return false;
	}
	
	public boolean save() throws IOException {
		FileWriter writer = new FileWriter("C:/Users/benjamin/workspace/Livrable 3/src/test.txt");

		writer.write("Coucou les gens");
		return false;
	}
	
	public int getKm() {
		return _vehicule._km;
	}
	
	public int getMidWaiting() {
		return _vehicule._midTimeOnTheRoad;
	}
	
	public int getStrategy() {
		return _vehicule._useStrat;
	}
	
	public int getEfficiency() {
		int res = 0;
		
		if (_urgencyInfo.size() != 0)
			return (res);
		
		for (int i = 0; i < _urgencyInfo.size(); i++)
			res += (_urgencyInfo.get(i)._endDate - _urgencyInfo.get(i)._triggerDate);
		
		res = res / _urgencyInfo.size();
		return res;
	}
	
	public int getNbTreatedUrgency() {
		return _urgencyInfo.size();
	}
	
}