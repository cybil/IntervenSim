import java.util.ArrayList;

public class StratOldestUrgency extends Strategy implements java.io.Serializable
{
  static String _name = "Plus vieille urgence";


  public StratOldestUrgency(Map map)
  {
    super(map);
  }

  /*!
  ** Return the path from the vehicule current location and the oldest urgency
  */
  ArrayList<Node>	getPath()
  {
    Node		node;
    int[]		coord_src;
    int[]		coord_dst;
    int[]		tmp_coord_src;
    int[]		tmp_coord_dst;

    node = this.getOldestNodeUrgency();
    if (node != null)
    {
      coord_src = this._map.getVehiculeCoord();
      coord_dst = node.getCoord();
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

  @Override
  String getStrategyName() {
    return _name;
  }
}
