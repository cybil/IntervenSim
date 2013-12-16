
public class Urgency implements java.io.Serializable {

  public enum EUrgencyState {
    SLEEPING, WAITING, IN_PROGRESS, DONE;
  }

  private EUrgencyState	state = EUrgencyState.SLEEPING;
  private float		treatmentTime = 5;
  private float		triggerDate;
  private float		endUrgencyDate;
  private float		UrgencyTreatmentTickLeft;
  private int		id;

  //******************
  //	Constructor
  //******************

  public Urgency() {
    this.treatmentTime = 5;
    this.triggerDate = 0;
    this.endUrgencyDate = 0;
    this.id = 0;
  }

    public Urgency(EUrgencyState state, float triggDate,
		   float endUrgenDate, float treatTime, int urgId) {
    this.state = state;
    this.treatmentTime = treatTime;
    this.triggerDate = triggDate;
    this.endUrgencyDate = endUrgenDate;
    this.id = urgId;
  }

  public Urgency(Urgency urgency) {
    this.state = urgency.state;
    this.treatmentTime = urgency.treatmentTime;
    this.triggerDate = urgency.triggerDate;
    this.endUrgencyDate = urgency.endUrgencyDate;
    this.id = urgency.id;
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

  /*
  ** Begin the treatment of the urgency.
  ** The stat will automaticaly move to done.
  */
  public void			beginTreatment()
  {
    if (this.state == EUrgencyState.WAITING)
    {
      this.setState(EUrgencyState.IN_PROGRESS);
      this.UrgencyTreatmentTickLeft = treatmentTime;
    }
    else
      System.out.println("Urgency.beginTreatment(): Error: this.state != EUrgencyState.WAITING");
  }

  /*
  ** Begin the treatment of the urgency.
  ** The stat will automaticaly move to done.
  ** Return true if it is finished
  */
  public boolean			Treatment()
  {
    if (this.state == EUrgencyState.IN_PROGRESS)
    {
      if (UrgencyTreatmentTickLeft <= 0)
      {
	this.setState(EUrgencyState.DONE);
	return (true);
      }
      UrgencyTreatmentTickLeft = UrgencyTreatmentTickLeft - 1;
    }
    else
      System.out.println("Urgency.beginTreatment(): Error: this.state != EUrgencyState.WAITING");
    return (false);
  }

  /*
  ** Stop the treatment of the urgency and reset the state as it was before.
  */
  public void			cancelTreatment()
  {
    if (this.state == EUrgencyState.IN_PROGRESS)
    {
      this.setState(EUrgencyState.WAITING);
      UrgencyTreatmentTickLeft = 0;
    }
    // else
    //   System.out.println("Urgency.cancelTreatment(): Error: this.state != EUrgencyState.IN_PROGRESS");
  }
}
