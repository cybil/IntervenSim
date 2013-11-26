import java.util.ArrayList;

public class AdjMatriceGen {
  // We only need the list of node to work on (not the complete graph)
  private ArrayList<ArrayList<ArrayList<Node>>>	matrice;
  private ArrayList<GraphNode>				_graphNode;
  private int						debug;

  public AdjMatriceGen(ArrayList<GraphNode> graphNode) {
    _graphNode = graphNode;
    debug = 0; // Level of verbosity (0-5)
    matrice = null;
  }

  // Check if a node is already added to the path (flag like way, in order to user a constant graph)
  private boolean		_isInPath(ArrayList<Node> path, GraphNode neigh_node)
  {
    int[]			coord;
    boolean			ret;

    ret = false;
    for (Node node: path)
    {
      coord = node.getCoord();
      if (coord[0] == neigh_node.getData().getCoord()[0]
	  && coord[1] == neigh_node.getData().getCoord()[1])
      {
	return (true);
      }
    }
    return (false);
  }

  // Find if we have already a path to this node, add it or replace it with a shorter one
  private void			_addOrReplacePath(ArrayList<ArrayList<Node>> pathList,
						  ArrayList<Node> path)
  {
    int				i;
    ArrayList<Node>		known_path;

    i = pathList.size();
    if (i > 0)
      while (--i >= 0)
      {
	known_path = pathList.get(i);
	if (known_path.size() > 0 && known_path.get(known_path.size() - 1)
	    == path.get(path.size() - 1))
	{
	  if (debug >= 5) System.out.println("_addOrReplacePath(): Replacing existing path");
	  if (debug >= 5) System.out.print("==> List before: ");
	  if (debug >= 5) prettyPrintPath(pathList.get(i)); // DEBUG
	  pathList.set(i, new ArrayList<Node>(path));
	  // pathList.remove(i);
	  // pathList.add(i, new ArrayList<Node>(path));
	  if (debug >= 5) System.out.print("==>  List After: ");
	  if (debug >= 5) prettyPrintPath(pathList.get(i)); // DEBUG;
	  return;
	}
      }
    if (debug >= 5) System.out.println("_addOrReplacePath(): Adding new path");
    pathList.add(new ArrayList<Node>(path));
  }

  // Recursive
  // Each node add him self to the path, the final one add the full path to the list of path
  // Const, does not change any node
  private void			_recursive(ArrayList<ArrayList<Node>> pathList,
						   ArrayList<Node> path,
						   GraphNode node, int depth)
  {
    if (debug >= 3) System.out.println("_recursive("
				       + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1]
				       + ", depth=" + depth
				       + ")");
    path.add(node.getData());
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
    path.remove(node.getData());
  }

  // Generate a list from a point to all the other
  // Return List2 (list of path of tuple)
  private ArrayList<ArrayList<Node>>	_GetAllPathForOneNode(GraphNode node)
  {
    int					i;
    int					max_recur;
    ArrayList<ArrayList<Node>>		pathList = new ArrayList<ArrayList<Node>>();
    ArrayList<ArrayList<Node>>		tmp = new ArrayList<ArrayList<Node>>();

    if (debug >= 2) System.out.println("_GetAllPathForOneNode(" + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1] + ")");
    max_recur = _graphNode.size();
    i = _graphNode.size();
    while (--i > 0)
    {
      if (debug >= 3) System.out.println("_GetAllPathForOneNode(): Starting depth " + i);
      this._recursive(pathList, new ArrayList<Node>(), node, i);
    }
    return (pathList);
  }

  // Pretty print the content of a path
  private void					prettyPrintPath(ArrayList<Node> path)
  {
    int						i;

    i = -1;
    System.out.print("list path(size: "+ path.size() + "): ");
    while (++i < path.size())
    {
      if (i > 0) System.out.print(", ");
      System.out.print(path.get(i).getCoord()[0] + ":" + path.get(i).getCoord()[1]);
    }
    System.out.println("");
  }

  // Force to remake the matrice
  public ArrayList<ArrayList<ArrayList<Node>>>	RegenGetAdjMatrice()
  {
    int						i;
    GraphNode					node;
    ArrayList<ArrayList<ArrayList<Node>>>	AdjMatrice = new ArrayList<ArrayList<ArrayList<Node>>>();

    if (debug >= 1) System.out.println("GetAdjMatrice()");
    if (this._graphNode.size() > 0)
    {
      ArrayList<ArrayList<Node>>	list = new ArrayList<ArrayList<Node>>();

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
	for (ArrayList<ArrayList<Node>> listOpath:AdjMatrice)
	{
	  System.out.println("");
	  for (ArrayList<Node> path:listOpath)
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
  public ArrayList<ArrayList<ArrayList<Node>>>	GetAdjMatrice()
  {
    if (matrice == null)
      return (this.RegenGetAdjMatrice());
    return (matrice);
  }
}
