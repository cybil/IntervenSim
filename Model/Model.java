import java.util.Stack;


public class Model {
	
    private Map			_map = new Map();
    private SimulationManager	_sim = new SimulationManager(_map);
    private FileManager	_file = new FileManager(_map, _sim);
    private Stack<Map> _undo = new Stack<Map>();
    private Stack<Map> _redo = new Stack<Map>();
	private int _xPos = 0;
	private int _yPos = 0;
    
    
    //******************
    //	Constructor
    //******************
	
    public Model() {

    }
	
    public Model(Map map) {
    	_map = map;
    	_sim = new SimulationManager(_map);
    	_file = new FileManager(_map, _sim);
    }
    
    public Model(Map map, SimulationManager sim)
    {
    	_map = map;
    	_sim = sim;
    	_file = new FileManager(_map, _sim);
    }
    
    public Model(Map map, SimulationManager sim, FileManager file) {
    	_map = map;
    	_sim = sim;
    	_file = file;
    }

    public Model(Model model) {
	_map = model._map;
	_sim = model._sim;
	_file = model._file;
    }

    //******************
    //	Getters
    //******************

    public Map			getMap() {
	return _map;
    }

    public SimulationManager getSimulationManager() {
      return _sim;
    }
	
      public FileManager	getFileManager() {
      return _file;
    }
	
	
    public void setMap(Map map) {
	_map = map;
    }
	
    public void setSimulationManager(SimulationManager sim) {
      _sim = sim;
    }
	
      public void setFileManager(FileManager file) {
      _file = file;
    }
	
    //Fonction for Map
	
    public boolean putNode(int x, int y) {
    	// addToUndo();
    	return _map.addNode(x, y);
    }
	
    public void getDisplay() {
	// _map.display();
	for (String s : _map.getFormatMap())
	    System.out.println(s);
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
    	// addToUndo();
    	return _map.addRoad(coordNode1, coordNode2);
    }
	
    public boolean		creatVehicule(int[] coord) {
    	// addToUndo();
    	return _map.creatVehicule(coord);
    }
	
    public boolean		deleteNode(int[] coord) {
    	// addToUndo();
    	return _map.deleteNode(coord);
    }
	
    public boolean		deleteRoad(int[] coord1, int[] coord2) {
    	// addToUndo();
    	return _map.deleteRoad(coord1, coord2);
    }
	
    public boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
    	// addToUndo(oldCoord, newCoord);
    	return _map.editNodeCoord(oldCoord, newCoord);
    }
	
    boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state,
				       float triggDate, float treatmentTime, int id) {
    	// addToUndo();
    	return _map.addNodeUrgency(coord, state, triggDate, triggDate, id);
    }
    
    public boolean		deleteVehicule() {
    	// addToUndo();
    	return _map.deleteVehicule();
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
    public void stop() {
	_sim.stop();
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
    
    public void redo() {
    	if (_redo.size() != 0) {
    		// addToUndo();
    		_map = _redo.pop();
    	}
    }
    
    public void undo() {
    	System.out.println("undo - start function");
    	if (_undo.size() != 0) {
    		System.out.println("undo - dans if");
    		addToRedo();
    		_map = _undo.pop();
    		_map.display();
    	}
    }
    
    public void addToUndo(int[] coordOld, int[] coordNew) {
    	if (_xPos == coordOld[0] && _yPos == coordOld[1]) {
    		_xPos = coordNew[0];
    		_yPos = coordNew[1];
    		_undo.pop();
    		_redo.push(_map);
    	}
    	else {
    		if (_undo.size() > 7)
    			_undo.remove(_redo.size() - 1);
    		_undo.push(_map);
    	}
    }
	
    public void addToRedo(int[] coordOld, int[] coordNew) {
    	if (_xPos == coordOld[0] && _yPos == coordOld[1]) {
    		_xPos = coordNew[0];
    		_yPos = coordNew[1];
    		_redo.pop();
    		_redo.push(_map);
    	}
    	else {
    		if (_redo.size() > 7)
    			_redo.remove(_redo.size() - 1);
    		_redo.push(_map);
    	}
    }
    
    public void addToRedo() {
    	if (_redo.size() > 7)
    		_redo.remove(_redo.size() - 1);
    	_map.display();
    	Map _newMap = new Map(_map);
    	_redo.push(_newMap);
    }
    
    public void addToUndo() {
    	if (_undo.size() > 7)
		_undo.remove(_undo.size() - 1);
    	_map.display();
    	Map _newMap = new Map(_map);
    	_undo.push(_newMap);
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
