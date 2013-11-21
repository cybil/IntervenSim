import java.util.ArrayList;


public class Graph {
	
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
	this.graphNode = graph.graphNode;
    }
	
    //***************
    //	Destructor
    //***************

    public void			finalize() {
		
    }

    //*****************
    //	Get Functions
    //*****************

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

    public ArrayList<GraphNode>	getAllNodes()
    {
	return this.graphNode;
    }

    //****************
    //	Set Functions
    //****************

    public void			setGraphNode(ArrayList<GraphNode> newGraphNode) {
	this.graphNode = newGraphNode;
    }

    //************
    //	Others
    //************

    public boolean		creatNode(int x, int y, ArrayList<Urgency> urgencyList) {
	int[]			coord = {x, y};
	Node			newNode = new Node(coord, urgencyList, null);
	GraphNode		newGraphNode = new GraphNode(newNode);
	
	this.graphNode.add(newGraphNode);
	return true;
    }
	
  public boolean		creatRoad(Node node1, Node node2) {
    int			status;
    int			roadLength;

    status = 0;
    roadLength = 10;  // 10 valeur par defaut d'une taille de route (a caluculer)
    // System.out.println("Adding road from " + node1.getCoord()[0] + ":" + node1.getCoord()[1]
    // 		       + " to"  + node2.getCoord()[0] + ":" + node2.getCoord()[1] + "");
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
	return (true);
    }
    return false;
  }

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
	
    public boolean		deleteRoad(Node node1, Node node2) {
	return true;
    }

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
    
}
