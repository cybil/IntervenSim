
public class Urgency {

	public enum EUrgencyState {
		SLEEPING, WAITING, IN_PROGRESS, DONE
	}
	
	private EUrgencyState _state = EUrgencyState.SLEEPING;
	
	private float _treatmentTime = 5;
	
	private float _triggerDate;
	
	private float _endUrgencyDate;
	
	//******************
	//	Constructor
	//******************
	
	public Urgency() {
		_treatmentTime = 5;
		_triggerDate = 0;
		_endUrgencyDate = 0;
	}
	
	public Urgency(EUrgencyState state, float treatmentTime, float triggerDate, float endUrgencyDate) {
		_state = state;
		_treatmentTime = treatmentTime;
		_triggerDate = triggerDate;
		_endUrgencyDate = endUrgencyDate;
	}
	
	public Urgency(Urgency urgency) {
		_state = urgency._state;
		_treatmentTime = urgency._treatmentTime;
		_triggerDate = urgency._triggerDate;
		_endUrgencyDate = urgency._endUrgencyDate;
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
		return _state;
	}
	
	public float getTreatmentTime() {
		return  _treatmentTime;
	}
	
	public float getEndUrgencyDate() {
		return _endUrgencyDate;
	}
	
	public float getTriggerDate() {
		return _triggerDate;
	}
	
	//****************
	//	Set Functions
	//****************
	
	public void setState(EUrgencyState newState) {
		_state = newState;
	}
	
	public void setTreatmentTime(float newTreatmentTime) {
		_treatmentTime = newTreatmentTime;
	}
	
	public void setEndUrgencyDate(float newEndUrgencyDate) {
		_endUrgencyDate = newEndUrgencyDate;
	}
	
	public void setTriggerDate(float newTriggerDate) {
		_triggerDate = newTriggerDate;
	}
}
