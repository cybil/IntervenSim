
public class Urgency {

    public enum EUrgencyState {
	SLEEPING, WAITING, IN_PROGRESS, DONE;
    }
	
    private EUrgencyState	state = EUrgencyState.SLEEPING;
    private float		treatmentTime = 5;
    private float		triggerDate;
    private float		endUrgencyDate;
	
    //******************
    //	Constructor
    //******************
	
    public Urgency() {
	this.treatmentTime = 5;
	this.triggerDate = 0;
	this.endUrgencyDate = 0;
    }
	
    public Urgency(EUrgencyState state, float treatmentTime, float triggerDate, float endUrgencyDate) {
	this.state = state;
	this.treatmentTime = treatmentTime;
	this.triggerDate = triggerDate;
	this.endUrgencyDate = endUrgencyDate;
    }
	
    public Urgency(Urgency urgency) {
	this.state = urgency.this.state;
	this.treatmentTime = urgency.this.treatmentTime;
	this.triggerDate = urgency.this.triggerDate;
	this.endUrgencyDate = urgency.this.endUrgencyDate;
    }
	
    //***************
    //	Destructor
    //***************
	
    public void finalize() {
		
    }
	
    //*****************
    //	Get Functions
    //*****************
	
    public EUrgencyState getState() {
	return this.state;
    }
	
    public float getTreatmentTime() {
	return  this.treatmentTime;
    }
	
    public float getEndUrgencyDate() {
	return this.endUrgencyDate;
    }
	
    public float getTriggerDate() {
	return this.triggerDate;
    }
	
    //****************
    //	Set Functions
    //****************
	
    public void setState(EUrgencyState newState) {
	this.state = newState;
    }
	
    public void setTreatmentTime(float newTreatmentTime) {
	this.treatmentTime = newTreatmentTime;
    }
	
    public void setEndUrgencyDate(float newEndUrgencyDate) {
	this.endUrgencyDate = newEndUrgencyDate;
    }
	
    public void setTriggerDate(float newTriggerDate) {
	this.triggerDate = newTriggerDate;
    }
}
