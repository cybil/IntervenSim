import java.util.ArrayList;


public class Vehicule implements java.io.Serializable {

  public enum EVehiculeState {
    WAITING, ON_THE_ROAD, WORKING;
  }

  private float		km;
  private int[]		coord;
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
    this.path = path;
    this.speed = speed;
  }

  public Vehicule(Vehicule vehicule) {
    this.km = vehicule.km;
    this.coord = vehicule.coord;
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
    this.state = EVehiculeState.ON_THE_ROAD;
    if (this.path.isEmpty())
      this.path = newPath;
    else
      this.path.addAll(newPath);
  }

  void		setSpeed(int newSpeed) {
    this.speed = newSpeed;
  }

  void		setCoord(int[] newCoord) {
    this.coord = newCoord;
  }

  //***************
  // Other
  //***************

  public void		moveOn() {
    if (this.isFree() == false)
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

      x_dst = path.get(0).getCoord()[0];
      y_dst = path.get(0).getCoord()[1];
      dx = x_dst - coord[0];dx = (dx > 0 ? dx : -dx);
      dy = y_dst - coord[1];dy = (dy > 0 ? dy : -dy);
      dir_x = (x_dst > coord[0] ? 1 : -1);
      dir_y = (y_dst > coord[1] ? 1 : -1);
      err = dx - dy;

      while (!(coord[0] == x_dst && coord[1] == y_dst))
      {
	e2 = 2 * err;
	if (e2 > -dy)
	{
	  err -= dy;
	  coord[0] += dir_x;
	  System.out.println("Vehicule.moveOn(): Moving on X to " + coord[0] + ":" + coord[1]
			     + " (dst node = " + x_dst + ":" + y_dst + ")");
	}
	if (!(coord[0] == x_dst && coord[1] == y_dst))
	  break;
	if (e2 < dx)
	{
	  err = err + dx;
	  coord[1] += dir_y;
	  System.out.println("Vehicule.moveOn(): Moving on Y to " + coord[0] + ":" + coord[1]
			     + " (dst node = " + x_dst + ":" + y_dst + ")");
	}
      }
    }
    else
      System.out.println("Vehicule.moveOn(): Error: Trying to move whitout any path set.");
  }

  int			treatUrgency() {
    this.state = EVehiculeState.WORKING;
    return 0;
  }

  boolean		isFree() {
    if (this.path != null && this.path.isEmpty())
      return false;
    return true;
  }

}
