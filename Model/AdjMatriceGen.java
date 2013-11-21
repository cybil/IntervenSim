import java.util.ArrayList;

public class AdjMatriceGen {
  // We only need the list of node to work on (not the complete graph)
  private ArrayList<GraphNode>	_graphNode;

  public AdjMatriceGen(ArrayList<GraphNode> graphNode) {
    _graphNode = graphNode;
  }
  // Use the list generated and spread all sub path on the empty cell of the matrice
  private void	_SpreadDeducedPath()
  {
  }
  // Recursive
  // Each node add him self to the path, the final one add the full path to the list of path
  private void			_recursive(ArrayList<ArrayList<int[]>> pathList,
						   ArrayList<int[]> path,
						   GraphNode node, int depth)
  {
    System.out.println("Loop(" + depth + "): " + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1]);
    path.add(node.getData().getCoord());
    if (depth > 0)
    {
      node.setFlag();
      System.out.println("Total Neighbors(" +  node.getNeighbors().size() + ")");
      for (GraphNode neigh_node : node.getNeighbors())
      {
	System.out.println("Total Neigh/Neighbors(" +  neigh_node.getNeighbors().size() + ")");
	if (neigh_node.getFlag() == false)
	  this._recursive(pathList, path, neigh_node, depth - 1);
      }
      node.clearFlag();
    }
    else
    {
      System.out.println("end: " + node.getData().getCoord()[0] + ":" + node.getData().getCoord()[1]);
      pathList.add(new ArrayList<int[]>(path));
    }
    path.remove(node.getData().getCoord());
    System.out.println("Return");
  }
  // Generate a list from a point to all the other
  // Return List2 (list of path of tuple)
  private ArrayList<ArrayList<int[]>>	_GetAllPathForOneNode(GraphNode node)
  {
    int					max_recur;
    ArrayList<ArrayList<int[]>>		pathList = new ArrayList<ArrayList<int[]>>();
    ArrayList<ArrayList<int[]>>		tmp = new ArrayList<ArrayList<int[]>>();

    max_recur = _graphNode.size();
    // while (tmp.size() < _graphNode.size()) // Preallocate
    //   tmp.add(new ArrayList<int[]>());
    // System.out.println("List size before: " + pathList.size());
    this._recursive(pathList, new ArrayList<int[]>(), node, 2);
    // System.out.println("List size after: " + pathList.size());
    return (pathList);
  }
  private void					prettyPrintList(ArrayList<int[]> list)
  {
    int						i;

    i = -1;
    System.out.println("List size: " + list.size());
    while (++i < list.size())
    {
      System.out.println("==> " + i + "= " + list.get(i)[0] + ":" + list.get(i)[1]);
    }
  }
  // Return a matrice with the shortest path from all to all node
  // Return List3 (list of list of path of tuple)
  public ArrayList<ArrayList<ArrayList<int[]>>>	GetAdjMatrice()
  {
    ArrayList<ArrayList<ArrayList<int[]>>> AdjMatrice = new ArrayList<ArrayList<ArrayList<int[]>>>();

    if (this._graphNode.size() > 0)
    {
      ArrayList<ArrayList<int[]>>	list = new ArrayList<ArrayList<int[]>>();

      // System.out.println("Root List size before: " + list.size());
      list = this._GetAllPathForOneNode(_graphNode.get(0));
      System.out.println("Root List size after: " + list.size());
      // this.prettyPrintList(list.get(0));
      AdjMatrice.add(list);
    }
    return (AdjMatrice);
  }
}
