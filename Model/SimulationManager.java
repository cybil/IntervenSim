import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.Serializable;

public class SimulationManager implements Serializable, ActionListener {

    public enum ESimulationState {
	BEGIN, RUNNING, PAUSED, END;
    }

    private Map			map;
    private	ArrayList<Strategy>	strategyList = new ArrayList<Strategy>();
    private Strategy		strategy;
    // private Statistic		statistic = new Statistic();
    private int			speed = 1;
    private ESimulationState	state = ESimulationState.BEGIN;
  private Timer		timer = new Timer(1000 / speed, this);

    //***************
    // * Constructor
    //***************

    public SimulationManager(Map map) {
	this.map = map;
	//this.strategy = new StratOldestUrgency(map);
	// this.strategyList.add(0, new StratOldestUrgency(map));
    }

    //***************
    // * Getters
    //***************

    public int		getSpeed() {
	return this.speed;
    }

    public ESimulationState	getState() {
	return this.state;
    }

    public int	getStrategy() {
    	return strategyList.indexOf(strategy);
    }

    public ArrayList<String> getStrategyList() {
    	ArrayList<String>	list = new ArrayList<String>();

    	for (int i = 0; i < strategyList.size(); i++)
    		list.add(i, strategyList.get(i).getSrategyName());
    	return list;
    }

    //***************
    // * Setters
    //***************

    public void			setSpeed(int newSpeed) {
      if (newSpeed <= 0)
	timer.stop();
      else
      {
	this.speed = newSpeed;
	timer.setDelay(1000 / this.speed);
	if (timer.isRunning() == false)
	  timer.start();
      }
    }

    public void			setStrategy(int strat) {
    	if (strat <= strategyList.size())
    		strategy = strategyList.get(strat);
    }

    //***************
    // * Others
    //***************

    // Methode surchargee de ActionListener (action a chaque tick du timer)
    public void actionPerformed(ActionEvent event) {
    	System.out.println("*TICK*");
    	for (Node n : this.map.getNodeUrgency()) {
    	    for (Urgency u : n.getUrgency()) {
    		u.setTriggerDate(u.getTriggerDate() - 1); // 1 selon gestion du temps
    		if (u.getTriggerDate() <= 0 && u.getState() == Urgency.EUrgencyState.SLEEPING)
    		    u.setState(Urgency.EUrgencyState.WAITING);
    	    }
    	}

	Vehicule v = this.map.getVehicule();
	// System.out.println("SimulationManager: " + v);
	// System.out.println("SimulationManager: " + this.strategyList.size());
	// System.out.println("SimulationManager: " + v.getState() + ":" + Vehicule.EVehiculeState.WAITING);
	if (v != null && v.getState() == Vehicule.EVehiculeState.WAITING
	    && this.strategyList.size() > 0)
	{
	  ArrayList<Node>	path = null;

	  System.out.println("SimulationManager: Setting path to vehicule");
	  path = this.strategyList.get(0).getPath();
	  // this.strategyList.get(0)._map =  this.map;
	  // this.strategyList.add(0, new StratOldestUrgency(this.map));
	  this.strategyList.get(0).setAttachPoint(this.map.getAttachPointCoord()[0],
						  this.map.getAttachPointCoord()[1]);
	  if (path == null || path.size() == 0)
	    path = this.strategyList.get(0).getWaitingPath();
	  v.setPath(path);
	}
    	this.map.actualizeVehicule();
    }

    public void			play(boolean display) {
	this.state = ESimulationState.RUNNING;
	this.timer.start();
	this.strategyList.add(0, new StratOldestUrgency(map));
    }

    public void			pause() {
	if (this.state == ESimulationState.RUNNING) {
	    this.state = ESimulationState.PAUSED;
	    this.timer.stop();
		}
	else if (this.state == ESimulationState.PAUSED) {
	    this.state = ESimulationState.RUNNING;
	    this.timer.start();
		}
    }

    public void			stop() {
	System.out.println("STOP !");
	this.state = ESimulationState.BEGIN;
	this.timer.stop();
    }

    public void			goToStat() {
	this.play(false);
    }
}
