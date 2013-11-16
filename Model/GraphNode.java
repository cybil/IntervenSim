import java.util.ArrayList;


public class GraphNode {

    private ArrayList<Node> this.data;
    private ArrayList<GraphNode> this.neighbors;
	
    //******************
    //	Constructor
    //******************
	
    public GraphNode() {
	this.data = null;
	this.neighbors = null;
    }
	
    public GraphNode(ArrayList<Node> newData, ArrayList<GraphNode> newNeighbors) {
	this.data = newData;
	this.neighbors = newNeighbors;
    }
	
    public GraphNode(GraphNode graphNode) {
	this.data = graphNode.this.data;
	this.neighbors = graphNode.this.neighbors;
    }
	
    //***************
    //	Destructor
    //***************

	
    public void finalize() {
		
    }
	
    //*****************
    //	Get Functions
    //*****************
	
    public ArrayList<Node> getData() {
	return this.data;
    }
	
    public ArrayList<GraphNode> getNeighbors() {
	return this.neighbors;
    }
	
    //****************
    //	Set Functions
    //****************
	
    public void setData(ArrayList<Node> newData) {
	this.data = newData;
    }
	
    public void setNeighbors(ArrayList<GraphNode> newNeighbors) {
	this.neighbors = newNeighbors;
    }
}
