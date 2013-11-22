import java.util.ArrayList;

public class AdjMatriceGen {
  // We only need the list of node to work on (not the complete graph)
  private ArrayList<ArrayList<ArrayList<int[]>>>	matrice;
  private ArrayList<GraphNode>				_graphNode;
  private int						debug;

  public AdjMatriceGen(ArrayList<GraphNode> graphNode) {
    _graphNode = graphNode;
    debug = 0; // Level of verbosity (0-5)
    matrice = null;
  }

  // Check if a node is already added to the path (flag like way, in order to user a constant graph)
  private boolean		_isInPath(ArrayList<int[]> path, GraphNode neigh_node)
  {
    boolean			ret;

    ret = false;
    for (int[] coord : path)
      if (coord[0] == neigh_node.getData().getCoord()[0]
	  && coord[1] == neigh_node.getData().getCoord()[1])
	return (true);
    return (false);
  }

  // Find if we have already a path to this node, add it or replace it with a shorter one
  private void			_addOrReplacePath(ArrayList<ArrayList<int[]>> pathList,
						  ArrayList<int[]> path)
  {
    int				i;
    ArrayList<int[]>		known_path;

    i = pathList.size();
    if (i > 0)
      while (--i >= 0)
      {
	known_path = pathList.get(i);
	if (known_path.size() > 0 && known_path.get(known_path.size() - 1)
	    == path.get(path.size() - 1))
	{
	  if (debug >= 5) System.out.println("_addOrReplacePath(): Replacing existing path");
	  pathList.set(i, new ArrayList<int[]>(path));
	  return;
	}
      }
    if (debug >= 5) System.out.println("_addOrReplacePath(): Adding new path");
    pathList.add(new ArrayList<int[]>(path));
  }

  // Recursive
  // Each node add him self to the path, the final one add the full path to the list of path
  // Const, does not change any node
  private void			_recursive(ArrayList<ArrayList<int[]>> pathList,
						   ArrayList<int[]> path,
						   GraphNode node, int depth)
  {
    if (debug >= 3) System.out.println("_recursive("
				       + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1]
				       + ", depth=" + depth
				       + ")");
    path.add(node.getData().getCoord());
    if (depth > 0)
    {
      for (GraphNode neigh_node : node.getNeighbors())
      {
	if (this._isInPath(path, neigh_node) == false)
	{
	  for (GraphNode clean_node : _graphNode) // To get a neighbors list which is working
	    if (clean_node.equals(neigh_node))
	    {
	      neigh_node = clean_node;
	      break;
	    }
	  this._recursive(pathList, path, neigh_node, depth - 1);
	  if (debug >= 3) System.out.println("");
	}
      }
    }
    else
      this._addOrReplacePath(pathList, path);
    path.remove(node.getData().getCoord());
  }

  // Generate a list from a point to all the other
  // Return List2 (list of path of tuple)
  private ArrayList<ArrayList<int[]>>	_GetAllPathForOneNode(GraphNode node)
  {
    int					i;
    int					max_recur;
    ArrayList<ArrayList<int[]>>		pathList = new ArrayList<ArrayList<int[]>>();
    ArrayList<ArrayList<int[]>>		tmp = new ArrayList<ArrayList<int[]>>();

    if (debug >= 2) System.out.println("_GetAllPathForOneNode(" + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1] + ")");
    max_recur = _graphNode.size();
    i = _graphNode.size();
    while (--i > 0)
    {
      if (debug >= 3) System.out.println("_GetAllPathForOneNode(): Starting depth " + i);
      this._recursive(pathList, new ArrayList<int[]>(), node, i);
    }
    return (pathList);
  }

  // Pretty print the content of a path
  private void					prettyPrintPath(ArrayList<int[]> path)
  {
    int						i;

    i = -1;
    System.out.print("list path(size: "+ path.size() + "): ");
    while (++i < path.size())
    {
      if (i > 0) System.out.print(", ");
      System.out.print(path.get(i)[0] + ":" + path.get(i)[1]);
    }
    System.out.println("");
  }

  // Force to remake the matrice
  public ArrayList<ArrayList<ArrayList<int[]>>>	RegenGetAdjMatrice()
  {
    int						i;
    GraphNode					node;
    ArrayList<ArrayList<ArrayList<int[]>>>	AdjMatrice = new ArrayList<ArrayList<ArrayList<int[]>>>();

    if (debug >= 1) System.out.println("GetAdjMatrice()");
    if (this._graphNode.size() > 0)
    {
      ArrayList<ArrayList<int[]>>	list = new ArrayList<ArrayList<int[]>>();

      i = _graphNode.size();
      while (--i >= 0)
      {
	node = _graphNode.get(i);
	if (debug >= 1) System.out.println("Generating all path from the node at "
				      + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1]);
	list = this._GetAllPathForOneNode(node);
	AdjMatrice.add(list);
      }
      if (debug >= 1)
	for (ArrayList<ArrayList<int[]>> listOpath:AdjMatrice)
	{
	  System.out.println("");
	  for (ArrayList<int[]> path:listOpath)
	  {
	    prettyPrintPath(path);
	  }
	}
    }
    this.matrice = AdjMatrice;
    return (AdjMatrice);
  }

  // Return a matrice with the shortest path from all to all node
  // Return List3 (list of list of path of tuple)
  public ArrayList<ArrayList<ArrayList<int[]>>>	GetAdjMatrice()
  {
    if (matrice == null)
      return (this.RegenGetAdjMatrice());
    return (matrice);
  }
}
