import java.util.ArrayList;

public class Vehicule implements java.io.Serializable {

  public enum EVehiculeState {
    WAITING, ON_THE_ROAD, WORKING;
  }

  private boolean	debug = false;
  private float		km; // number of km done
  private int[]		coord; // Coord of the vehicule
  private int[]		incomingCoord = {0, 0}; // The point from where the vehicule is comming
  private Node		attachPoint; // Attach point of the vehicule
  private ArrayList<Node>	path = new ArrayList<Node>(); // path to follow
  private ArrayList<ArrayList<Node>>	listOpath = new ArrayList<ArrayList<Node>>();
  private int			speed; // km/h
  private EVehiculeState	state = EVehiculeState.WAITING;

  // For the moveOn() methode
  private int		speed_adjust = 0; // Management of the mouvement with the number of tick
  private int		tick_ref = 60;
  private int		tick_synch = tick_ref;
  private boolean	initMoveOn = false;
  private int	e2;
  private int	dx;
  private int	dy;
  private int	err;
  private int	dir_x;
  private int	dir_y;
  private int	x_dst;
  private int	y_dst;
  private Node		urgencyNode = null;
  private Urgency	lastUrgency = null;

  //******************
  //	Constructor
  //******************

  public Vehicule() {
    this.km = 0;
    this.attachPoint = null;
    this.speed = 1;
  }

  public Vehicule(float km, int[] coord,
		  Node attachPoint, ArrayList<Node> path, int speed) {
    this.km = km;
    this.coord = coord.clone();
    this.attachPoint = attachPoint;
    this.incomingCoord = this.coord;
    this.path = path;
    this.speed = speed;
  }

  public Vehicule(Vehicule vehicule) {
    this.km = vehicule.km;
    this.coord = vehicule.coord.clone();
    this.incomingCoord = this.coord.clone();
    this.attachPoint = vehicule.attachPoint;
    this.path = vehicule.path;
    this.speed = vehicule.speed;
    this.state = vehicule.state;
  }


  //*****************
  //	Get Functions
  //*****************

  float		getKm() {
    return this.km;
  }

  int			getSpeed() {
    return this.speed;
  }

  int[]		 getCoord() {
    return this.coord;
  }

  EVehiculeState	getState() {
    return this.state;
  }

  //****************
  //	Set Functions
  //****************

  void		setAttachPoint(Node attachPoint)
  {
    this.attachPoint = attachPoint;
  }

  /*
  ** TODO: remplacer le addAll par une liste de path (plus facile pour gerer les urgences imbrique)
  */
  void		setPath(ArrayList<Node> newPath) {
    int		last;

    this.listOpath.add(newPath);
    if (newPath != null && newPath.size() > 0 && this.path.size() == 0)
    {
      if (this.urgencyNode != null) // Cancel the urgency if a new path is given
      {
	this.lastUrgency.cancelTreatment();
	if (debug == true) System.out.println("Vehicule.setPath(): cancel urgency.");
      }
      this.state = EVehiculeState.ON_THE_ROAD;
      this.path = this.listOpath.get(0);
      this.listOpath.remove(0);
      last = this.path.size() - 1;
      last = (last > 0 ? last : 0);
      this.urgencyNode = this.path.get(last);
      this.lastUrgency = this.urgencyNode.getNextUrgency();
      // this.path.remove(last);
    }
    else
      System.out.println("Vehicule.setPath(): Error: Trying to set a null or empty path");
  }

  void		setSpeed(int newSpeed) {
    this.speed = newSpeed;
  }

  void		setCoord(int[] newCoord) {
    this.coord = newCoord.clone();
    this.incomingCoord = this.coord.clone();
    this.path.clear(); // The path is no longer valid
    this.initMoveOn = false;
  }

  //***************
  // Other
  //***************

  /*
  ** This function is called every time a node from the patch is reached (on Vehicule.moveOn())
  **
  ** Use Pythagore to update the number of km drived by the vehicule so far
  ** This function assume that 1 coord unit is equal to 1km
  **
  */
  static public float		pythagore(int[] src, int[] dst)
  {
    int			i;
    float		total_cost;
    int[]		tmp_coord = {0, 0};
    int[]		pythagore = {0, 0};

    i = 0;
    total_cost = 0;
    while (i < 2)
    {
      tmp_coord[i] = (src[i] - dst[i]);
      tmp_coord[i] = ((tmp_coord[i] > 0) ? (tmp_coord[i]) : -(tmp_coord[i]));
      pythagore[i] = tmp_coord[i] * tmp_coord[i];
      i = i + 1;
    }
    total_cost = (float)Math.sqrt((pythagore[0]) + (pythagore[1]));
    return (total_cost);
  }

  private void		_updatingKm()
  {
    this.km += this.pythagore(this.incomingCoord, path.get(0).getCoord());
    if (debug == true) System.out.println("Vehicule.moveOn(): incoming src: "
					  + this.incomingCoord[0] + ":"
					  + this.incomingCoord[1]);
    this.incomingCoord = path.get(0).getCoord();
    if (debug == true) System.out.println("Vehicule.moveOn(): Updating km to " + this.km);
  }

  /*
  ** Remove the reached point and update the status
  */
  private void		_update()
  {
    this._updatingKm();
    this.path.remove(0);
    this.initMoveOn = false;
  }

/*
** 1 clock tick correspond to 1 minutes.
** Vehicule move in km/h, we move every 60 tick.
** One diff in coord is equal to 1km.
** TODO: tester le cas ou l'on bouge sur son propre noeud / traiter deux urgences de suite sur
** le meme node
*/
  public void		moveOn()
  {
    int			move_rate;

    // Move to the next path availaible after the treatment of the last urgency
    if (this.state == EVehiculeState.WAITING && this.path.size() == 0 && this.listOpath.size() > 0)
    {
      this.path = this.listOpath.get(0);
      this.listOpath.remove(0);
      this.state = EVehiculeState.ON_THE_ROAD;
    }
    if (this.state != EVehiculeState.WAITING)
    {
      move_rate = this.tick_ref / this.speed;
      this.tick_synch -= 1;
      if (this.tick_synch <= 0)
	this.tick_synch = this.tick_ref;

      speed_adjust += (3600 / this.speed);
      while (speed_adjust > 60)
      {
	if (this.path.size() > 0)
	  this._moveOn();
	speed_adjust -= 60;
      }
      speed_adjust = speed_adjust % 60;

      if (this.path.size() == 0 && this.urgencyNode != null)
	this.treatUrgency();
      else
	this.state = EVehiculeState.WAITING;

      // if ((this.tick_sync % move_rate) == 0)
      //   this._moveOn();
    }
  }

  /*
  ** Internal function under Vehicule.moveOn()
  */
  private void		_moveOn()
  {
    this.state = EVehiculeState.ON_THE_ROAD;
    if (this.path != null && this.path.size() > 0 && this.isFree() == false)
    {
// Init: If we are on the point, remove it from the path and go on
      if (this.initMoveOn == false)
      {
	if (debug == true) System.out.print("Vehicule.moveOn() from "
					    + this.incomingCoord[0] + ":"
					    + this.incomingCoord[1]
					    + " path: ");
	if (debug == true) AdjMatriceGen.prettyPrintPath(this.path);
	x_dst = path.get(0).getCoord()[0];
	y_dst = path.get(0).getCoord()[1];
	dx = x_dst - coord[0];dx = (dx > 0 ? dx : -dx);
	dy = y_dst - coord[1];dy = (dy > 0 ? dy : -dy);
	dir_x = (x_dst > coord[0] ? 1 : -1);
	dir_y = (y_dst > coord[1] ? 1 : -1);
	err = dx - dy;
	// this._update();
	this.initMoveOn = true;
	// if (this.path.size() > 0)
	//   this.moveOn();
	// return;
      }
      if (!(coord[0] == x_dst && coord[1] == y_dst))
      {
	e2 = 2 * err;
	if (e2 > -dy)
	{
	  err -= dy;
	  coord[0] += dir_x;
	  if (this.debug == true)
	    System.out.println("Vehicule.moveOn(): Moving on X to " + coord[0] + ":" + coord[1]
			       + " (dst node = " + x_dst + ":" + y_dst + ")");
	}
	if (!(coord[0] == x_dst && coord[1] == y_dst) && e2 < dx)
	{
	  err += dx;
	  coord[1] += dir_y;
	  if (this.debug == true)
	    System.out.println("Vehicule.moveOn(): Moving on Y to " + coord[0] + ":" + coord[1]
			       + " (dst node = " + x_dst + ":" + y_dst + ")");
	}
      }
      if (coord[0] == x_dst && coord[1] == y_dst)
	this._update();
    }
    else if (path != null && path.size() == 0)
      System.out.println("Vehicule.moveOn(): Error: Trying to move whitout any path set."
			 + " Path = " + path.size());
    else if (this.isFree() == true)
      System.out.println("Vehicule.moveOn(): Error: this.isFree() == true");
    else
      System.out.println("Vehicule.moveOn(): Error: Trying to move with a 'null' path set.");
  }

  /*
  ** Manage an urgency as long as necessary
  */
  int			treatUrgency() {
    if (this.path.size() == 0 && this.urgencyNode != null)
    {
      this.state = EVehiculeState.WORKING;
      if (this.lastUrgency.getState() == Urgency.EUrgencyState.WAITING)
	this.lastUrgency.beginTreatment();
      if (this.lastUrgency.Treatment() == true) // Urgency treatment finished
      {
	this.urgencyNode.removeUrgency(this.lastUrgency);
	this.state = EVehiculeState.WAITING;
      }
    }
    return (0);
  }

  /*
  ** Return true if the vehicule has nothing more to do (no path)
  */
  boolean		isFree() {
    if (this.path != null && this.path.size() > 0)
      return (false);
    return (true);
  }
}

