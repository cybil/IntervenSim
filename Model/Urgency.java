
public class Urgency implements java.io.Serializable {

    public enum EUrgencyState {
	SLEEPING, WAITING, IN_PROGRESS, DONE;
    }
	
    private EUrgencyState	state = EUrgencyState.SLEEPING;
    private float		treatmentTime = 5;
    private float		triggerDate;
    private float		endUrgencyDate;
    static int			id = 0;

    //******************
    //	Constructor
    //******************
	
    public Urgency() {
	this.treatmentTime = 5;
	this.triggerDate = 0;
	this.endUrgencyDate = 0;
	this.id++;
    }
	
    public Urgency(EUrgencyState state, float triggDate, float endUrgenDate, float treatTime) {
	this.state = state;
	this.treatmentTime = treatTime;
	this.triggerDate = triggDate;
	this.endUrgencyDate = endUrgenDate;
	this.id++;
    }
	
    public Urgency(Urgency urgency) {
	this.state = urgency.state;
	this.treatmentTime = urgency.treatmentTime;
	this.triggerDate = urgency.triggerDate;
	this.endUrgencyDate = urgency.endUrgencyDate;
    	this.id++;
    }

    //*****************
    //	Get Functions
    //*****************
	
    public EUrgencyState	getState() {
	return this.state;
    }
	
    public float		getTreatmentTime() {
	return  this.treatmentTime;
    }
	
    public float		getEndUrgencyDate() {
	return this.endUrgencyDate;
    }
	
    public float		getTriggerDate() {
	return this.triggerDate;
    }
	
    public int			getId() {
	return this.id;
    }
    //****************
    //	Set Functions
    //****************
	
    public void			setState(EUrgencyState newState) {
	this.state = newState;
    }
	
    public void			setTreatmentTime(float newTreatmentTime) {
	this.treatmentTime = newTreatmentTime;
    }
	
    public void			setEndUrgencyDate(float newEndUrgencyDate) {
	this.endUrgencyDate = newEndUrgencyDate;
    }
	
    public void			setTriggerDate(float newTriggerDate) {
	this.triggerDate = newTriggerDate;
    }

}
