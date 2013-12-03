import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Statistic implements java.io.Serializable {
	
	VehiculeInfo	_vehicule;
	ArrayList<UrgencyInfo>	_urgencyInfo;
	Dates	_date;
	
	private class Dates implements java.io.Serializable
	{
		Date actuelle = new Date();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");

		public String date()
		{
			String dat = dateFormat.format(actuelle);
			return dat;
		}
	}
	
	public Statistic() {
		_vehicule = new VehiculeInfo();
		_urgencyInfo = new ArrayList<UrgencyInfo>();
		_date = new Dates();
	}
	
	public Statistic(VehiculeInfo vehicule, ArrayList<UrgencyInfo> urgencyInfo) {
		_vehicule = vehicule;
		_urgencyInfo = urgencyInfo;
		_date = new Dates();
	}
	
	public Statistic(Statistic stat) {
		_vehicule = stat._vehicule;
		_urgencyInfo = stat._urgencyInfo;
		_date = new Dates();
	}
	
	public boolean load(String pathFileName) {
		try {
			FileInputStream fichier = new FileInputStream(pathFileName);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Statistic stat = (Statistic) ois.readObject();
			ois.close();
			}
			catch (java.io.IOException e) {
				e.printStackTrace();
				return false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}
	
	public boolean save() {
		try {
		      FileOutputStream file = new FileOutputStream("Stat"+ _date.date() +".ser");
		      System.out.println("test : " + "Stat"+ _date.date() +".ser");
		      ObjectOutputStream oos = new ObjectOutputStream(file);
		      oos.writeObject(this);
		      oos.flush();
		      oos.close();
		    }
		    catch (java.io.IOException e) {
		    	e.printStackTrace();
		    	return false;
		    }		
		return true;
	}
	
	public int getKm() {
		return _vehicule._km;
	}
	
	public int getSpeed() {
		return _vehicule._speed;
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
	
	public void setKm(int km) {
		_vehicule._km = km;
	}
	
	public void setSpeed(int speed) {
		_vehicule._speed = speed;
	}
	
	public void setMidWaiting(int midTimeOnTheRoad) {
		_vehicule._midTimeOnTheRoad = midTimeOnTheRoad;
	}
	
	public void setStrategy(int strat) {
		_vehicule._useStrat = strat;
	}
	
	public boolean addUrgencyInfo(UrgencyInfo urgencyInfo) {
		return _urgencyInfo.add(urgencyInfo);
	}
	
	public boolean addUrgencyInfo(int triggerDate, int endDate, int treatmentTime) {
		return _urgencyInfo.add(new UrgencyInfo(triggerDate, endDate, treatmentTime));
	}
	
	public int getTimeFinal() {
		int time;
		
		time = _vehicule._km * _vehicule._speed;
		for (int i = 0; i < _urgencyInfo.size(); i++)
			time += _urgencyInfo.get(i)._treatmentTime;
		return time;
		
	}
}