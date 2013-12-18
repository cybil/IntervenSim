import java.awt.Image;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.io.*;
import java.nio.channels.FileChannel;

public class FileManager {
  private Map _map;
  private SimulationManager _sim;
  private Statistic _stat;
  private File _fileMap = null;
  private File _fileSim = null;
  private File _fileStat = null;
  private boolean debug = false;

  public void setMap(Map map) {
    _map = map;
  }

  public void setSim(SimulationManager sim) {
    _sim = sim;
  }

  public void setStat(Statistic stat) {
    _stat = stat;
  }

  public Map getMap() {
    return _map;
  }

  public SimulationManager getSim() {
    return _sim;
  }

  public Statistic getStat() {
    return _stat;
  }

  public FileManager(Map map, SimulationManager sim)
  {
    _map = map;
    _sim = sim;
    _stat = _sim.getStat();
  }

  public FileManager(Map map, SimulationManager sim, Statistic stat) {
    _map = map;
    _sim = sim;
    _stat = _sim.getStat();
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
    if (debug == true) System.out.println("---> Save map File: " + p_file.getAbsolutePath() + " <----");
    try {
      FileOutputStream fos = new FileOutputStream(p_file);

      for (String s : _map.getFormatMap()) {
	s += "\n";
	fos.write(s.getBytes());
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    this._fileMap = p_file;
    return true;
  }

  public boolean loadMap(File p_file) {
    if (debug == true) System.out.println("---> Load map File: " + p_file.getAbsolutePath() + " <----");
    if (p_file.exists() == false)
      return false;
    try {
      InputStream		ips = new FileInputStream(p_file.getAbsolutePath());
      InputStreamReader	ipsr = new InputStreamReader(ips);
      BufferedReader		br = new BufferedReader(ipsr);
      String			ligne;

      ArrayList<String>	formatMap = new ArrayList<String>();

      while ((ligne = br.readLine()) != null) {
	System.out.println(ligne);
	formatMap.add(ligne);
      }
      br.close();
      _map.setFormatMap(formatMap);
    } catch (Exception e) {
      System.out.println(e.toString());
    }


    this._fileMap = p_file;
    return true;
  }

  public boolean importImage(File p_file) {
    try {
      if (p_file.exists() == false)
	return false;
      Image	newImage = ImageIO.read(p_file);
      _map.setBackground(newImage);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  public boolean saveStat(File p_file) {
    if (debug == true) System.out.println("---> Save stat File: " + p_file.getAbsolutePath() + " <----");
    if (debug == true) System.out.println("---> Save map File: " + p_file.getAbsolutePath() + " <----");
    try {
      FileOutputStream fos = new FileOutputStream(p_file);
      String	s = "";

      s += "----- Donnees -----\n"
	+ "  Kilometres parcourus: " + _stat.getKm()
	+ "\n  Vitesse: " + _stat.getSpeed()
	+ "\n  Strategie: " + _stat.getStrategy()
	+ "\n----- Statistiques -----\n  Temps d'attente moyen: " + _stat.getMidWaiting()
	+ "\n  Efficacite: " + _stat.getEfficiency()
	+ "\n  Nombre d'urgence traitees :" + _stat.getNbTreatedUrgency()
	+ "\n  Temps total: " + _stat.getTimeFinal();

      if (debug == true) System.out.println(s);
      fos.write(s.getBytes());
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    this._fileStat = p_file;
    return true;
  }
}
