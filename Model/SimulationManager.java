import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.Serializable;

public class SimulationManager implements Serializable, ActionListener {

    public enum ESimulationState {
	BEGIN, RUNNING, PAUSED, END;
    }

  public enum WaitingStrategy {
    WAITING, BACK_TO_ATTACH_POINT;
  }

    private Map			map;
    private	ArrayList<Strategy>	strategyList = new ArrayList<Strategy>();
    private Strategy		strategy;
  private WaitingStrategy		waitingStrategy = WaitingStrategy.WAITING;
    private Statistic		statistic = new Statistic();
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

    public Statistic	getStat() {
	return this.statistic;
    }

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
    		list.add(i, strategyList.get(i).getStrategyName());
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
    public void			setWaitingStrategy(int strat) {
      this.waitingStrategy = (strat == 0 ? WaitingStrategy.WAITING
			      : WaitingStrategy.BACK_TO_ATTACH_POINT);
    }

    //***************
    // * Others
    //***************

    // Methode surchargee de ActionListener (action a chaque tick du timer)
    public void actionPerformed(ActionEvent event) {
    	System.out.println("*TICK*");
	this.strategyList.add(0, new StratOldestUrgency(this.map));
	this.strategyList.add(1, new StratNearestUrgency(this.map));
	if (this.strategy == null)
	  this.strategy = this.strategyList.get(0);
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
	  // path = this.strategyList.get(0).getPath();
	  path = this.strategy.getPath();
	  // this.strategyList.get(0)._map =  this.map;
	  // Pour pouvoir ajouter un node et des roads en cours de route
	  // this.strategyList.add(0, new StratOldestUrgency(this.map));

	  // this.strategyList.add(0, new StratNearestUrgency(this.map));
	  // this.strategyList.get(0).setAttachPoint(this.map.getAttachPointCoord()[0],
	  // 					  this.map.getAttachPointCoord()[1]);

	  this.strategy.setAttachPoint(this.map.getAttachPointCoord()[0],
				       this.map.getAttachPointCoord()[1]);
	  if ((path == null || path.size() == 0)
	      && waitingStrategy == WaitingStrategy.BACK_TO_ATTACH_POINT)
	    path = this.strategy.getWaitingPath();
	  v.setPath(path);
	}
	if (v.getState() == Vehicule.EVehiculeState.ON_THE_ROAD)
	    this.statistic.setMidWaiting();
	
	this.statistic.setKm((int)v.getKm());
	this.statistic.setSpeed(v.getSpeed());
	this.statistic.setStrategy(this.strategy.getStrategyName());
    	this.map.actualizeVehicule();
    }

    public void			play(boolean display) {
	this.state = ESimulationState.RUNNING;
	this.timer.start();
	this.strategyList.add(0, new StratNearestUrgency(map));
	this.strategyList.add(1, new StratOldestUrgency(map));
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
