import java.util.*;


public class GraphNode implements java.io.Serializable {

    private boolean				flag;
    private Node				data;
    private Hashtable<GraphNode, Integer>	neighbors = new Hashtable<GraphNode, Integer>();

    //******************
    //	Constructor
    //******************

    public GraphNode() {
	flag = false;
	this.data = null;
    }

    public GraphNode(Node newData) {
	flag = false;
	this.data = newData;
    }

    public GraphNode(GraphNode graphNode) {
	flag = false;
	this.data = graphNode.data;
	this.neighbors = graphNode.neighbors;
    }

    //*****************
    //	Get Functions
    //*****************

    public boolean		getFlag() {
	return (this.flag);
    }

    public Node			getData() {
	return this.data;
    }

    public ArrayList<GraphNode>		getNeighbors() {
	ArrayList<GraphNode>		ret = new ArrayList<GraphNode>();

	for (GraphNode n: this.neighbors.keySet())
	    ret.add(n);
	return (ret);
    }

    //****************
    //	Set Functions
    //****************

    public void			setFlag() {
	if (this.flag == false)
	    this.flag = true;
	else
	    System.out.println("setFlag: Error: Flag already set");
    }

    public void			clearFlag() {
	if (this.flag == true)
	    this.flag = false;
	else
	    System.out.println("clearFlag: Error: Flag already clear");
    }

    public void			setData(Node newData) {
	this.data = newData;
    }

    //****************
    //	Others
    //****************

    public void			addNeighbor(GraphNode node, int lenght) {
	this.neighbors.put(node, lenght);
    }

    public void			deleteNeighbor(Node node) {
      if (node != null)
      {
	for (GraphNode n : this.getNeighbors()) {
	    if (n.getData().getCoord()[0] == node.getCoord()[0]
		&& n.getData().getCoord()[1] == node.getCoord()[1])
		this.neighbors.remove(n);
	}
      }
      else
	System.out.println("GraphNode.deleteNeighbor: node is null");
    }

    public boolean	equals(GraphNode other) {
	if (this.getData().getCoord()[0] == other.getData().getCoord()[0]
	    && this.getData().getCoord()[1] == other.getData().getCoord()[1])
	    return (true);
	return (false);
    }
}
