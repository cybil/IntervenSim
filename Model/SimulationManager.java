import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;

public class SimulationManager implements ActionListener, java.io.Serializable {

    public enum ESimulationState {
	BEGIN, RUNNING, PAUSED, END;
    }

    private Map			map;
    private Strategy		strategy = new StratOldestUrgency(map);
    // private Statistic		statistic = new Statistic();
    private int			speed = 1000;
    private ESimulationState	state = ESimulationState.BEGIN;
    private Timer		timer = new Timer(speed, this);

    //***************
    // * Constructor
    //***************

    public SimulationManager(Map map) {
	this.map = map;
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
    	return strategy.getCurrStrategy();
    }
    //***************
    // * Setters
    //***************

    public void			setSpeed(int newSpeed) {
	this.speed = newSpeed;
    }
    
    public void			setStrategy(int strat) {
    this.strategy.setCurrStrategy(strat);
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

    	this.map.actualizeVehicule();
    }
   
    public void			play(boolean display) {
	this.state = ESimulationState.RUNNING;
	this.timer.start();;
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