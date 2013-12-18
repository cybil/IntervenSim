import java.util.ArrayList;

public class StratNearestUrgency extends Strategy implements java.io.Serializable
{
	static String _name = "Plus proche urgence";


  public StratNearestUrgency(Map map)
  {
    super(map);
  }

  /*!
  ** Return the path from the vehicule current location and the nearest urgency
  */
  ArrayList<Node>	getPath()
  {
    int[]		coord_src;
    int[]		tmp_coord_src;
    ArrayList<Node>	path = null;
    float		current_cost = 0;
    float		new_cost = 0;

    coord_src = this._map.getVehiculeCoord();
    for (ArrayList<ArrayList<Node>> list: this._matrice)
      for (ArrayList<Node> _path: list)
      {
	if (_path.size() > 0)
	{
	  tmp_coord_src = _path.get(0).getCoord();
	  if (_path.get(_path.size() - 1).getNextUrgency() != null
	      && coord_src[0] == tmp_coord_src[0] && coord_src[1] == tmp_coord_src[1])
	  {
	    new_cost = AdjMatriceGen.pathCost(_path);
	    if (path == null || new_cost < current_cost)
	    {
	      path = _path;
	      current_cost = new_cost;
	    }
	  }
	}
      }
    return (path);
  }

  @Override
  String getStrategyName() {
	  return _name;
  }
}
