import java.util.ArrayList;

abstract class Strategy
{
  protected ArrayList<ArrayList<ArrayList<int[]>>>	_matrice;
  protected int						_currentStrategy;
  protected Map						_map;

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
  abstract ArrayList<int []>	getPath();

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
      if (OldestNode == null
	  || node.getNextUrgency().getTriggerDate() > OldestNode.getNextUrgency().getTriggerDate())
	OldestNode = node;
    }
    return (OldestNode);
  }
}
