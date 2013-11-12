import java.lang.Enum;


public class Urgency
{
    public enum EState
    {
	SLEEPING, WAITING, IN_PROGRESS, DONE; 
    }

    private EState		_state = EState.SLEEPING;
    private int			_triggerDate;
    private int			_treatmentTime;
    private int			_endTreatmentDate;

    public Urgency(int triggerDate, int treatmentTime)
    {
	_triggerDate = triggerDate;
	_treatmentTime = treatmentTime;
    }

    //
    // Getters
    //

    public EState	getState()
    {
	return _state;
    }

    public int		getTreatmentTime()
    {
	return _treatmentTime;
    }

    public int		getTriggerDate()
    {
	return _triggerDate;
    }

    public int		getEndTreatmentDate()
    {
	return _endTreatmentDate;
    }

    //
    // Setters
    //
    public void		setState(EState newState)
    {
	_state = newState;
    }

    public void		setEndTreatmentDate(int date)
    {
	_treatmentTime = date;
    }
}