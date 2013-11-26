import java.util.ArrayList;

public class Vehicule implements java.io.Serializable {

  public enum EVehiculeState {
    WAITING, ON_THE_ROAD, WORKING;
  }

  private boolean	debug = true;
  private float		km;
  private int[]		coord;
  private int[]		incomingCoord = {0, 0}; // The point from where the vehicule is comming
  private Node		attachPoint;
  private ArrayList<Node>	path = new ArrayList<Node>();
  private int			speed;
  private EVehiculeState	state = EVehiculeState.WAITING;

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
    this.coord = coord;
    this.attachPoint = attachPoint;
    this.incomingCoord = this.coord;
    this.path = path;
    this.speed = speed;
  }

  public Vehicule(Vehicule vehicule) {
    this.km = vehicule.km;
    this.coord = vehicule.coord;
    this.incomingCoord = this.coord;
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

  void		setPath(ArrayList<Node> newPath) {
    if (newPath != null && newPath.size() > 0)
    {
      this.state = EVehiculeState.ON_THE_ROAD;
      if (this.path.isEmpty())
	this.path = newPath;
      else
	this.path.addAll(newPath);
    }
    else
      System.out.println("Vehicule.setPath(): Error: Trying to set a null or empty path");
  }

  void		setSpeed(int newSpeed) {
    this.speed = newSpeed;
  }

  void		setCoord(int[] newCoord) {
    this.coord = newCoord;
    this.incomingCoord = this.coord;
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
  private void		_updatingKm()
  {
    int			i;
    int[]		tmp_coord = {0, 0};
    int[]		pythagore = {0, 0};

    i = 0;
    while (i < 2)
    {
      tmp_coord[i] = (path.get(0).getCoord()[i] - this.incomingCoord[i]);
      tmp_coord[i] = ((tmp_coord[i] > 0) ? (tmp_coord[i]) : -(tmp_coord[i]));
      pythagore[i] = tmp_coord[i] * tmp_coord[i];
      i = i + 1;
    }
    this.km += Math.sqrt((pythagore[0]) + (pythagore[1]));
    this.incomingCoord = path.get(0).getCoord();
    if (debug == true) System.out.println("Vehicule.moveOn(): Updating km to " + this.km);
  }

  public void		moveOn() {
    if (this.path != null && this.path.size() > 0 && this.isFree() == false)
    {
      int	e2;
      int	dx;
      int	dy;
      int	err;
      int	dir_x;
      int	dir_y;
      int	x_dst;
      int	y_dst;
      Node	next_node;

      if (debug == true) System.out.print("Vehicule.moveOn() path: ");
      if (debug == true) AdjMatriceGen.prettyPrintPath(this.path);
      x_dst = path.get(0).getCoord()[0];
      y_dst = path.get(0).getCoord()[1];
      dx = x_dst - coord[0];dx = (dx > 0 ? dx : -dx);
      dy = y_dst - coord[1];dy = (dy > 0 ? dy : -dy);
      dir_x = (x_dst > coord[0] ? 1 : -1);
      dir_y = (y_dst > coord[1] ? 1 : -1);
      err = dx - dy;

      if (coord[0] == x_dst && coord[1] == y_dst) // If we are on the point, remove it from the path and go on
      {
	this._updatingKm();
	this.path.remove(0);
	this.moveOn();
	return;
      }
      while (!(coord[0] == x_dst && coord[1] == y_dst))
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
	if (coord[0] == x_dst && coord[1] == y_dst)
	{
	  this._updatingKm();
	  this.path.remove(0);
	  return;
	}
	if (e2 < dx)
	{
	  err = err + dx;
	  coord[1] += dir_y;
	  if (this.debug == true)
	    System.out.println("Vehicule.moveOn(): Moving on Y to " + coord[0] + ":" + coord[1]
			       + " (dst node = " + x_dst + ":" + y_dst + ")");
	}
      }
    }
    else if (path != null && path.size() == 0)
      System.out.println("Vehicule.moveOn(): Error: Trying to move whitout any path set."
			 + " Path = " + path.size());
    else if (this.isFree() == true)
      System.out.println("Vehicule.moveOn(): Error: this.isFree() == true");
    else
      System.out.println("Vehicule.moveOn(): Error: Trying to move with a 'null' path set.");
  }

  int			treatUrgency() {
    this.state = EVehiculeState.WORKING;
    return 0;
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
