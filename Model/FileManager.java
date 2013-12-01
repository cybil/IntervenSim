import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

	private Map _map;
	private SimulationManager _sim;
	//private Dates _date;
	private Statistic _stat;
	private File _fileMap = null;
	private File _fileSim = null;
	private File _fileStat = null;
	
	/*private class Dates
	{
		Date actuelle = new Date();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");

		public String date()
		{
			String dat = dateFormat.format(actuelle);
			return dat;
		}
	}*/
	
	public FileManager(Map map, SimulationManager sim)
	{
		_map = map;
		_sim = sim;
		_stat = new Statistic();
		//_date = new Dates();
	}
	
	public FileManager(Map map, SimulationManager sim, Statistic stat) {
		_map = map;
		_sim = sim;
		_stat = stat;
		//_date = new Dates();
	}
	
	public boolean existFileMap()
	{
		if (_fileMap != null)
			return true;
		return false;
	}
	
	public boolean existFileSim()
	{
		if (_fileStat != null)
			return true;
		return false;
	}
	
	public boolean existFileStat()
	{
		if (_fileSim != null)
			return true;
		return false;
	}
	
	public boolean compareToSavedFileMap()
	{
		if (_fileMap != null)
			return true;
		
		File fichier = new File("tmpMap.ser");
		if (_fileMap.compareTo(fichier) != 0)
			return true;
		return false;
			
	}
	
	public boolean compareToSavedFileSim()
	{
		if (_fileSim != null)
			return true;
		
		File fichier = new File("tmpSim.ser");
		if (_fileSim.compareTo(fichier) != 0)
			return true;
		return false;
			
	}
	
	public boolean compareToSavedFileStat()
	{
		if (_fileStat != null)
			return true;
		
		File fichier = new File("tmpStat.ser");
		if (_fileStat.compareTo(fichier) != 0)
			return true;
		return false;
			
	}
	
	public boolean saveMap(File p_file) {
		try {
			FileOutputStream file = new FileOutputStream(p_file);
		    ObjectOutputStream oos = new ObjectOutputStream(file);
		    oos.writeObject(_map);
		    oos.flush();
		    oos.close();
		    }
		    catch (java.io.IOException e) {
		    	e.printStackTrace();
		    	return false;
		    }
		this._fileMap = p_file;
		return true;
	}
	
	public boolean loadMap(File p_file) {
		try {
			FileInputStream fichier = new FileInputStream(p_file);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			_map = (Map) ois.readObject();
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
		this._fileMap = p_file;
		return true;
	}
	
	public boolean importImage(File p_file) {
		FileChannel in = null;
		FileChannel out = null;
		 
		try {
		  in = new FileInputStream(p_file).getChannel();
		  out = new FileOutputStream("Background-"+ p_file.getName()).getChannel();
		 
		  in.transferTo(0, in.size(), out);
		} catch (Exception e) {
		  e.printStackTrace();
		} finally {
		  if(in != null) {
		  	try {
			  in.close();
			} catch (IOException e) {
				return false;
			}
		  }
		  if(out != null) {
		  	try {
			  out.close();
			} catch (IOException e) {
				return false;
			}
		  }
		}
		return true;
	}
	
	public boolean saveSim(File p_file) {
		try {
		FileOutputStream file = new FileOutputStream(p_file);
	      ObjectOutputStream oos = new ObjectOutputStream(file);
	      oos.writeObject(_sim);
	      oos.flush();
	      oos.close();
	    }
	    catch (java.io.IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
		this._fileSim = p_file;
		return true;
	}
	
	public boolean loadSim(File p_file) {
		try {
			FileInputStream fichier = new FileInputStream(p_file);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			_sim = (SimulationManager) ois.readObject();
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
		this._fileSim = p_file;
		return true;
	}
	
	public boolean saveStat(File p_file) {
		try {
		      FileOutputStream file = new FileOutputStream(p_file);
		      ObjectOutputStream oos = new ObjectOutputStream(file);
		      oos.writeObject(_stat);
		      oos.flush();
		      oos.close();
		    }
		    catch (java.io.IOException e) {
		    	e.printStackTrace();
		    	return false;
		    }	
		this._fileStat = p_file;
		return true;
	}
	
	public boolean loadStat(File p_file) throws IOException, ClassNotFoundException {
		try {
		FileInputStream fichier = new FileInputStream(p_file);
		ObjectInputStream ois = new ObjectInputStream(fichier);
		_stat = (Statistic) ois.readObject();
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
		this._fileStat = p_file;
		return true;
	}
}
