import java.util.ArrayList;

public class StratOldestUrgency extends Strategy
{
  public StratOldestUrgency(Map map)
  {
    super(map);
  }

  /*!
  ** Return the path to the oldest strategy
  ** I know this way is very ugly and slow
  */
  ArrayList<Node>	getPath()
  {
    Node		node;
    int[]		coord;
    int[]		tmp_coord;

    node = this.getOldestNodeUrgency();
    if (node != null)
    {
      coord = node.getCoord();
      for (ArrayList<ArrayList<Node>> list: this._matrice)
	for (ArrayList<Node> path: list)
	{
	  if (path.size() > 0)
	  {
	    tmp_coord = path.get(path.size() - 1).getCoord();
	    if (coord[0] == tmp_coord[0] && coord[1] == tmp_coord[1])
	      return (path);
	  }
	}
    }
    return (null);
  }
}
