import java.util.ArrayList;


public class Model {

	
    private Map			_map = new Map();
    private SimulationManager	_sim = new SimulationManager(_map);
    //private FileManager	_file;
	
	
    //******************
    //	Constructor
    //******************
	
    public Model() {

    }
	
    public Model(Map map) {
	_map = map;
    }
		
    public Model(Model model) {
	_map = model._map;
	_sim = model._sim;
	// _file = model._file;
    }

    //******************
    //	Getters
    //******************

    public Map			getMap() {
	return _map;
    }

    /*public SimulationManager getSimulationManager() {
      return _sim;
      }
	
      public FileManager	getFileManager() {
      return _file;
      }*/
	
	
    public void setMap(Map map) {
	_map = map;
    }
	
    /*public void setSimulationManager(SimulationManager sim) {
      _sim = sim;
      }
	
      public void setFileManager(FileManager file) {
      _file = file;
      }*/
	
    //Fonction for Map
	
    public boolean putNode(int x, int y) {		
	return _map.addNode(x, y);
    }
	
    public void getDisplay() {
	_map.display();
    }
	
    //******************
    //	Setters
    //******************

    public void setReturn(boolean ret) {
	
    }
	
    public boolean	editTreatmentTime(int[] coord, float time, int id) {
	return _map.editTreatmentTime(coord, time, id);
    }
	
    public boolean		editAttachPoint(int[] coord, boolean state) {
	return _map.editAttachPoint(coord, state);
    }
	
    public boolean		addRoad(int[] coordNode1, int[] coordNode2) {
	return _map.addRoad(coordNode1, coordNode2);
    }
	
    public boolean		creatVehicule(int[] coord) {
	return _map.creatVehicule(coord);
    }
	
    public boolean		deleteNode(int[] coord) {
	return _map.deleteNode(coord);
    }
	
    public boolean		deleteRoad(int[] coord1, int[] coord2) {
	return _map.deleteRoad(coord1, coord2);
    }
	
    public boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
	return _map.editNodeCoord(oldCoord, newCoord);
    }
	
    boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state, float triggDate) {
	return _map.addNodeUrgency(coord, state, triggDate);
    }
	
    //quequesse?
    public void operation() {
	;
    }
	
    //Fonction for SimulationManager
    public void play() {
      _sim.play(true);
      }
	
      public void pause() {
      _sim.pause();
      }
	
      public void goToStat() {
      _sim.goToStat();
      }
	
      public void setSpeed(int speed) {
      _sim.setSpeed(speed);
      }
	
      public int getSpeed() {
      return _sim.getSpeed();
      }
	
      //Fonction for FileManager
	
      // public boolean saveMap() {
      // return _file.saveMap();
      // }
	
      // public boolean loadMap() {
      // return _file.loadMap();
      // }
	
      // public boolean importImage() {
      // return _file.importImage();
      // }
	
      // public boolean saveSim() {
      // return _file.saveSim();
      // }
	
      // public boolean loadSim() {
      // return _file.loadSim();
      // }
	
      // public boolean saveStat() {
      // return _file.saveStat;
      // }
	
      // public boolean loadStat() {
      // return _file.loadStat;
      // }
}
