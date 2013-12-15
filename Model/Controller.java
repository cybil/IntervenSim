import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    Model			_model = new Model();
    GUI				_gui = new GUI(this);

    //******************
    //	Constructor
    //******************
	
    public Controller() {
    }

    public Controller(Model model) {
	_model = model;
	//_gui = gui;
    }
	
    public Controller(Controller controller) {
	_model = controller._model;
	_gui = controller._gui;
    }

    //********************
    //	Another functions
    //********************

    public void	displayMap()
    {
	this._model.getDisplay();
    }
	
    public void	setReturn() {

    } 

    public void setEvent() {
		
    }
	
    public boolean eventPutNode(int x, int y) {
	return _model.putNode(x, y);
    }
	
    public void eventGetDisplay() {
	_model.getMap().display();
    }

    public boolean	eventEditTreatmentTime(int[] coord, float time, int id) {
	return _model.getMap().editTreatmentTime(coord, time, id);
    }
	
    public boolean		eventEditAttachPoint(int x, int y) {
	int[]			coord = {x, y};
	return _model.editAttachPoint(coord, true);
    }
	
    public boolean		eventAddRoad(int[] coordNode1, int[] coordNode2) {
	return _model.addRoad(coordNode1, coordNode2);
    }
	
    //pas encore redo
    public boolean		eventCreatVehicule(int x, int y) {
	int[]		coord = {x, y};
	if (_model.getMap().hasVehicule() == true) {
	    _model.getMap().setVehiculeCoord(x, y);
	    return false;
	}
	_model.getMap().creatVehicule(coord);
	return true;
    }
	
    public boolean		eventDeleteNode(int[] coord) {
	return _model.deleteNode(coord);
    }

    public boolean		eventDeleteVehicule() {
	return _model.deleteVehicule();
    }
	
    public boolean		eventDeleteRoad(int[] coord1, int[] coord2) {
	return _model.deleteRoad(coord1, coord2);
    }
	
    public boolean		eventEditNodeCoord(int[] oldCoord, int[] newCoord) {
	return _model.editNodeCoord(oldCoord, newCoord);
    }
	
    public boolean		eventAddNodeUrgency(int x, int y, Urgency.EUrgencyState state, float triggDate) {
	int[]			coord = {x, y};
	return _model.getMap().addNodeUrgency(coord, state, triggDate);
    }
	
    public void		eventRedo() {
    	_model.redo();
    }
    
    public void		eventUndo() {
    	_model.undo();
    }
    
    //Fonction for SimulationManager
    public void eventPlay() {
	if (_model.getMap().getVehiculeCoord() != null)
	    _model.play();
    }

    public void eventPause() {
	_model.pause();
    }

    public void eventStop() {
	_model.stop();
    }
		
    public void eventGoToStat() {
	_model.goToStat();
    }
		
    public void eventSetSpeed(int speed) {
	_model.setSpeed(speed);
    }
		
    public int eventGetSpeed() {
	return _model.getSpeed();
    }
      
    public ArrayList<String> eventGetStrategyList() {
	return _model.getSimulationManager().getStrategyList();
    }
		
    //Fonction for FileManager
		
    public boolean eventSaveMap(File p_file) {
	return _model.getFileManager().saveMap(p_file);
    }
		
    public boolean eventLoadMap(File p_file) {
	return _model.getFileManager().loadMap(p_file);
    }
		
    public boolean eventImportImage(File p_file) {
	return _model.getFileManager().importImage(p_file);
    }
		
    public boolean eventSaveSim(File p_file) {
	return _model.getFileManager().saveSim(p_file);
    }
		
    public boolean eventLoadSim(File p_file) {
	return _model.getFileManager().loadSim(p_file);
    }
		
    public boolean eventSaveStat(File p_file) {
	return _model.getFileManager().saveStat(p_file);
    }
		
    public boolean eventLoadStat(File p_file) throws ClassNotFoundException, IOException {
	return _model.getFileManager().loadStat(p_file);
    }
       
    public void eventSetStrategy(int strat) {
	_model.getSimulationManager().setStrategy(strat);
    }
       
    public int eventGetStrategy(int strat) {
	return _model.getSimulationManager().getStrategy();
    }
       
    public int eventKmDone(){
	return _model.getFileManager().getStat().getKm();
    }
       
    public int eventTreatmentTimeAvg() {
	return _model.getFileManager().getStat().getMidWaiting();
    }
}
