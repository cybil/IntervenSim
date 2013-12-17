import java.util.ArrayList;

abstract class Strategy implements java.io.Serializable
{
  protected ArrayList<ArrayList<ArrayList<Node>>>	_matrice;
  protected int						_currentStrategy;
  protected Map						_map;
  protected int[]					_attachPoint = null;

  public Strategy(Map map)
  {
    AdjMatriceGen	genMatrice = new AdjMatriceGen(map.getAllGraphNodes());

    this._map = map;
    this._matrice = genMatrice.GetAdjMatrice();
  }
  
  /*!
  ** Get the current strategy used
  */
  public int		getCurrStrategy()
  {
    return (this._currentStrategy);
  }

  /*!
  ** Set the current attach point coord
  */
  public void		setAttachPoint(int x, int y)
  {
    _attachPoint = new int[2];
    _attachPoint[0] = x;
    _attachPoint[1] = y;
  }
  /*!
  ** CLear the current attach point coord
  */
  public void		clearAttachPoint(int x, int y)
  {
    _attachPoint = null;
  }

  /*!
  ** Set the current strategy to use
  */
  public int		setCurrStrategy(int strategy)
  {
    this._currentStrategy = strategy;
    return (this._currentStrategy);
  }

  /*!
  ** Return the path to a node define by the strategy
  ** Ex:Return the path to go to the oldest urgency
  */
  abstract ArrayList<Node>	getPath();
  public ArrayList<Node>	getWaitingPath()
  {
    if (_attachPoint != null)
    {
      Node		node;
      int[]		coord_src;
      int[]		coord_dst;
      int[]		tmp_coord_src;
      int[]		tmp_coord_dst;

      coord_src = this._map.getVehiculeCoord();
      coord_dst = this._attachPoint.clone();
      for (ArrayList<ArrayList<Node>> list: this._matrice)
	for (ArrayList<Node> path: list)
	{
	  if (path.size() > 0)
	  {
	    tmp_coord_src = path.get(0).getCoord();
	    tmp_coord_dst = path.get(path.size() - 1).getCoord();
	    if (coord_src[0] == tmp_coord_src[0] && coord_src[1] == tmp_coord_src[1] &&
		coord_dst[0] == tmp_coord_dst[0] && coord_dst[1] == tmp_coord_dst[1])
	      return (path);
	  }
	}
    }
    return (null);
  }

  /*!
  ** Return the name of strategy
  */
  abstract String		getSrategyName();
  
  /*!
  ** Return the oldest urgency on the map
  */
  protected Urgency	getOldestUrgency()
  {
    Node		node = null;
    Urgency		OldestUrgency = null;

    node = this.getOldestNodeUrgency();
    if (node != null)
      OldestUrgency = node.getNextUrgency();
    return (OldestUrgency);
  }

  /*!
  ** Return the node with the oldest urgency
  */
  protected Node	getOldestNodeUrgency()
  {
    Node		OldestNode = null;

    for (Node node: this._map.getNodeUrgency())
    {
      if ((OldestNode == null
	   || node.getNextUrgency().getTriggerDate() < OldestNode.getNextUrgency().getTriggerDate())
	  && node.getNextUrgency().getTriggerDate() <= 0)
	OldestNode = node;
    }
    return (OldestNode);
  }
}
