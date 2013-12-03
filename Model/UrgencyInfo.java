public class UrgencyInfo implements java.io.Serializable {
	public int _triggerDate;
	public int _endDate;
	public int _treatmentTime;
	
	public UrgencyInfo() {
		_triggerDate = 0;
		_endDate = 0;
		_treatmentTime = 5;
	}
	
	public UrgencyInfo(int triggerDate, int endDate, int treatmentTime) {
		_triggerDate = triggerDate;
		_endDate = endDate;
		_treatmentTime = treatmentTime;
	}
}