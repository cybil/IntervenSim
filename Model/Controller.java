import java.util.ArrayList;

public class Controller {

	//simulationGUI _gui;
	Model	_model;

	//******************
    //	Constructor
    //******************
	
	public Controller() {
		_model = new Model();
		//_gui = new simulationGUI();
	}

	public Controller(Model model) {
		_model = model;
		//_gui = gui;
	}
	
	public Controller(Controller controller) {
		_model = controller._model;
		//_gui = controller._gui;
	}
	
	//***************
    //	Destructor
    //***************

	public void			finalize() {

    }

	//********************
	//	Another functions
	//********************
	
	public void	setReturn() {

	} 

	public void setEvent() {
		
	}
	
	public boolean eventPutNode(int x, int y) {
		return _model.getMap().addNode(x, y);
	}
	
	public void eventGetDisplay() {
		_model.getMap().display();
	}

	public boolean	eventEditTreatmentTime(int[] coord, float time, int id) {
		return _model.getMap().editTreatmentTime(coord, time, id);
	}
	
	public boolean		eventEditAttachPoint(int[] coord, boolean state) {
		return _model.getMap().editAttachPoint(coord, state);
	}
	
	public boolean		eventAddRoad(int[] coordNode1, int[] coordNode2) {
		return _model.getMap().addRoad(coordNode1, coordNode2);
	}
	
	public boolean		eventCreatVehicule(int[] coord) {
		return _model.getMap().creatVehicule(coord);
	}
	
	public boolean		eventDeleteNode(int[] coord) {
		return _model.getMap().deleteNode(coord);
	}
	
	public boolean		eventDeleteRoad(int[] coord1, int[] coord2) {
		return _model.getMap().deleteRoad(coord1, coord2);
	}
	
	public boolean		eventEditNodeCoord(int[] oldCoord, int[] newCoord) {
		return _model.getMap().editNodeCoord(oldCoord, newCoord);
	}
	
	public boolean		eventAddNodeUrgency(int[] coord, Urgency.EUrgencyState state, float triggDate) {
		return _model.getMap().addNodeUrgency(coord, state, triggDate);
	}
	
	//Fonction for SimulationManager
		/*public void eventPlay() {
			_model.play();
		}
		
		public void eventPause() {
			_model.pause();
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
		
		//Fonction for FileManager
		
		public boolean eventSaveMap() {
			return _model.saveMap();
		}
		
		public boolean eventLoadMap() {
			return _model.loadMap();
		}
		
		public boolean eventImportImage() {
			return _model.importImage();
		}
		
		public boolean eventSaveSim() {
			return _model.saveSim();
		}
		
		public boolean eventLoadSim() {
			return _model.loadSim();
		}
		
		public boolean eventSaveStat() {
			return _model.saveStat;
		}
		
		public boolean eventLoadStat() {
			return _model.loadStat;
		}*/
}
