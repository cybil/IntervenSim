import java.util.ArrayList;


public class Graph implements java.io.Serializable{

    private ArrayList<GraphNode>	graphNode = new ArrayList<GraphNode>();

    //******************
    //	Constructor
    //******************

    public Graph() {
    }

    public Graph(ArrayList<GraphNode> newGraphNode) {
	this.graphNode = newGraphNode;
    }

    public Graph(Graph graph) {
	this.graphNode = (ArrayList<GraphNode>) graph.graphNode.clone();
    }

    //*****************
    //	Getters
    //*****************

    public ArrayList<Node>	getNodeUrgency() {
	ArrayList<Node>		urgencyList = new ArrayList<Node>();

	for (GraphNode n : this.graphNode) {
	    if (n.getData().hasUrgency() == true)
		urgencyList.add(n.getData());
	}
	return urgencyList;
    }

    public Node			getNode(int[] coord) {
    	for (GraphNode n : this.graphNode) {
    	    if (n.getData().getCoord()[0] == coord[0]
		&& n.getData().getCoord()[1] == coord[1])
		return n.getData();
	}
	return null;
    }

    public ArrayList<GraphNode>	getGraphNode() {
	return this.graphNode;
    }

    public int			getNbNode() {
	return this.graphNode.size();
    }

    public int			getNbRoad() {
	int			i = 0;
	for (GraphNode n : this.graphNode)
	    i = i + n.getNeighbors().size();
	return i;
    }

    public ArrayList<GraphNode>	getAllNodes() {
	return this.graphNode;
    }

    public int[]		getAttachPointCoord() {
	for (GraphNode n : this.graphNode) {
	    if (n.getData().getAttachPoint() == true)
		return n.getData().getCoord();
	}
	return null;
    }

    //****************
    //	Set Functions
    //****************

    public void			setGraphNode(ArrayList<GraphNode> newGraphNode) {
	this.graphNode = newGraphNode;
    }

    //************
    //	Add
    //************

    public boolean		creatNode(int x, int y) {
	int[]			coord = {x, y};
	Node			newNode = new Node(coord, false);
	GraphNode		newGraphNode = new GraphNode(newNode);

	for (GraphNode node:graphNode)
	{
	  if (node.equals(newGraphNode) == true)
	    return (false);
	}
	this.graphNode.add(newGraphNode);
	return (true);
    }

    public boolean		creatRoad(Node node1, Node node2) {
	int			status;
	int			roadLength;

	if (node1 != null && node2 != null)
	{
	System.out.println("=========== NEW ROAD ===========");
	status = 0;
	roadLength = (int)Vehicule.pythagore(node1.getCoord(), node2.getCoord());
	for (GraphNode n : this.graphNode) {
	    if (n.getData() == node1) {
		n.addNeighbor(new GraphNode(node2), roadLength);
		status++;
	    }
	    if (n.getData() == node2) {
		n.addNeighbor(new GraphNode(node1), roadLength);
		status++;
	    }
	    if (status == 2)
		return true;
	}
	}
	else
	  System.out.println("Graph.creatRoad(): Error: node1 or node2 is null. ");
	return false;
    }

    //************
    //	Delete
    //************

    public boolean		deleteNode(int[] coord) {
	for (GraphNode n : this.graphNode) {
	    n.deleteNeighbor(this.getNode(coord));
	}
	for (GraphNode n : this.graphNode) {
	    if (n.getData().getCoord()[0] == coord[0]
		&& n.getData().getCoord()[1] == coord[1]) {
		this.graphNode.remove(n);
		return true;
	    }
	}
	return false;
    }

    public void			deleteAttachPoint() {
	for (GraphNode n : this.graphNode) {
	    if (n.getData().getAttachPoint() == true)
		n.getData().setAttachPoint(false);
	}
    }

    public boolean		deleteRoad(Node node1, Node node2) {
      if (node1 == null || node2 == null)
      {
	System.out.println("Graph.deleteRoad: Node1 or Node2 is null");
	return (false);
      }
	int			status = 0;
	for (GraphNode n : this.graphNode) {
	  if (n.getData().getCoord()[0] == node1.getCoord()[0]
		&& n.getData().getCoord()[1] == node1.getCoord()[1]) {
		n.deleteNeighbor(node2);
		++status;
	    }
	    if (n.getData().getCoord()[0] == node2.getCoord()[0]
		&& n.getData().getCoord()[1] == node2.getCoord()[1]) {
		n.deleteNeighbor(node1);
		++status;
	    }
	    if (status == 2)
		return true;
	}
	return false;
    }
}
