import java.util.*;

public class SimulationManager {

    public enum ESimulationState {
	BEGIN, RUNNING, PAUSED, END;
    }

    private Map			map;
    // private Strategy		strategy = new Strategy();
    // private Statistic		statistic = new Statistic();
    private int			speed = 1000;
    private ESimulationState	state = ESimulationState.BEGIN;
    private Timer		timer = new Timer();

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

    //***************
    // * Setters
    //***************

    public void			setSpeed(int newSpeed) {
	this.speed = newSpeed;
    }

    //***************
    // * Others
    //***************

    public void			play(boolean display) {
	this.state = ESimulationState.RUNNING;
	TimerTask		task = new TimerTask()
	    {
		public void run() {
		    System.out.println("Coucou les gens !");
		}
	    };

	this.timer.scheduleAtFixedRate(task, 0, this.speed);

    }

    public void			pause() {
	System.out.println("PAUUUSED");
	if (this.state == ESimulationState.RUNNING)
	    {
		this.state = ESimulationState.PAUSED;
		// this.timer.wait();
	    }
	else
	    {
		this.state = ESimulationState.RUNNING;
		// this.timer.notify();
	    }
    }

    public void			stop() {
	this.state = ESimulationState.BEGIN;
    }

    public void			goToStat() {
	this.play(false);
    }
}