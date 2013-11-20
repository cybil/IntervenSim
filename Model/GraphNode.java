import java.util.*;


public class GraphNode {

    private Node				data;
    private ArrayList<Road>			roads;
    private Hashtable<GraphNode, Integer>	neighbors = new Hashtable<GraphNode, Integer>();
	
    //******************
    //	Constructor
    //******************
	
    public GraphNode() {
	this.data = null;
    }
	
    public GraphNode(Node newData) {
	this.data = newData;
    }
	
    public GraphNode(GraphNode graphNode) {
	this.data = graphNode.data;
	this.roads = graphNode.roads;
    }
	
    //***************
    //	Destructor
    //***************

	
    public void finalize() {
		
    }
	
    //*****************
    //	Get Functions
    //*****************
	
    public Node			getData() {
	return this.data;
    }
	
    public ArrayList<Road>	getRoads() {
	return this.roads;
    }


    public ArrayList<GraphNode>		getNeighbors() {
	Enumeration<GraphNode>		e = this.neighbors.keys();

	return (Collections.list(e));
    }

    //****************
    //	Set Functions
    //****************
	
    public void			setData(Node newData) {
	this.data = newData;
    }
	
    public void			addRoad(Road newRoad) {
	this.roads.add(newRoad);
    }

    public void			addNeighbor(GraphNode node, int lenght) {
	this.neighbors.put(node, lenght);
    }

    public void			deleteNeighbor(Node node) {
	for (GraphNode n : this.getNeighbors()) {
	    if (n.getData().getCoord()[0] == node.getCoord()[0]
		&& n.getData().getCoord()[1] == node.getCoord()[1])
		this.neighbors.remove(n);
	}
    }
}
