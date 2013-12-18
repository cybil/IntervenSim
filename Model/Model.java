import java.util.ArrayList;
import java.util.Stack;


public class Model {
	
    private Map			_map = new Map();
    private SimulationManager	_sim = new SimulationManager(_map);
    private FileManager	_file = new FileManager(_map, _sim);
    private Stack<ArrayList<String>> _undo = new Stack<ArrayList<String>>();
    private Stack<ArrayList<String>> _redo = new Stack<ArrayList<String>>();
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
    	_redo.clear();
    	addToUndo();
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
    	_redo.clear();
    	addToUndo();
    	return _map.addRoad(coordNode1, coordNode2);
    }
	
    public boolean		creatVehicule(int[] coord) {
    	_redo.clear();
    	addToUndo();
    	return _map.creatVehicule(coord);
    }
	
    public boolean		deleteNode(int[] coord) {
    	_redo.clear();
    	addToUndo();
    	return _map.deleteNode(coord);
    }
	
    public boolean		deleteRoad(int[] coord1, int[] coord2) {
    	_redo.clear();
    	addToUndo();
    	return _map.deleteRoad(coord1, coord2);
    }
	
    public boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
    	//addToUndo(oldCoord, newCoord);
    	return _map.editNodeCoord(oldCoord, newCoord);
    }
	
    boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state,
				       float triggDate, float treatmentTime, int id) {
    	_redo.clear();
    	addToUndo();
    	return _map.addNodeUrgency(coord, state, triggDate, triggDate, id);
    }
    
    public boolean		deleteVehicule() {
    	_redo.clear();
    	addToUndo();
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
    		addToUndo();
    		_map.clearMap();
    		_map.setFormatMap(_redo.pop());
    	}
    	_map.display();
    }
    
    public void undo() {
    	if (_undo.size() != 0) {
    		addToRedo();
    		_map.clearMap();
    		_map.setFormatMap(_undo.pop());
    	}
    	getUndoState();
    }
	
    public void addToRedo(int[] coordOld, int[] coordNew) {
    	if (_xPos == coordOld[0] && _yPos == coordOld[1]) {
    		_xPos = coordNew[0];
    		_yPos = coordNew[1];
    		_redo.pop();
    		_redo.push((ArrayList<String>) _map.getFormatMap().clone());
    	}
    	else {
    		if (_redo.size() > 7)
    			_redo.remove(0);
    		_redo.push((ArrayList<String>) _map.getFormatMap().clone());
    	}
    }
    
    public void addToUndo(int[] coordOld, int[] coordNew) {
    	if (_xPos == coordOld[0] && _yPos == coordOld[1]) {
    		_xPos = coordNew[0];
    		_yPos = coordNew[1];
    		_undo.pop();
    		_undo.push((ArrayList<String>) _map.getFormatMap().clone());
    	}
    	else {
    		if (_undo.size() > 7)
    			_undo.remove(0);
    		_undo.push((ArrayList<String>) _map.getFormatMap().clone());
    	}
    }
    
    public void addToRedo() {
    	if (_redo.size() > 7) {
    		_redo.remove(0);
    	}
    	_redo.push((ArrayList<String>) _map.getFormatMap().clone());
    }
   
    private void getUndoState() {
    	System.out.println("--+++-- Undo now --+++--");
//    	for (int i = 0; i < _undo.size(); i++)
//    		System.out.println("+++" + _undo.get(i).getNbNode() + "+++");
    	System.out.println("----" + _undo.size() + "----");
    }
    
    public void addToUndo() {
    	if (_undo.size() > 7)
    		_undo.remove(0);
	_undo.push((ArrayList<String>) _map.getFormatMap().clone());
	getUndoState();
    }
}
