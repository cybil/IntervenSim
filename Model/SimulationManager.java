import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;

public class SimulationManager implements ActionListener, java.io.Serializable {

    public enum ESimulationState {
	BEGIN, RUNNING, PAUSED, END;
    }

    private Map			map;
    private	ArrayList<Strategy>	strategyList = new ArrayList<Strategy>();
    private Strategy		strategy;
    // private Statistic		statistic = new Statistic();
    private int			speed = 1;
    private ESimulationState	state = ESimulationState.BEGIN;
    private Timer		timer = new Timer(1000 * speed, this);

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
	this.speed = newSpeed;
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
    		if (u.getTriggerDate() <= 0)
    		    u.setState(Urgency.EUrgencyState.WAITING);
    	    }
    	}

	Vehicule v = this.map.getVehicule();
	if (v != null && v.getState() == Vehicule.EVehiculeState.WAITING
	    && this.strategyList.size() > 0)
	{
	  System.out.println("SimulationManager: Setting path to vehicule");
	  v.setPath(this.strategyList.get(0).getPath());
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
