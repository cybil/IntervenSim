import java.util.ArrayList;


public class Graphe {
	
    private ArrayList<GraphNode>	graphNode;

    //******************
    //	Constructor
    //******************
	
    public Graphe() {
	this.graphNode = null;
    }
	
    public Graphe(ArrayList<GraphNode> newGraphNode) {
	this.graphNode = newGraphNode;
    }
	
    public Graphe(Graphe graphe) {
	this.graphNode = graphe.graphNode;
    }
	
    //***************
    //	Destructor
    //***************

	
    public void finalize() {
		
    }
	
    //*****************
    //	Get Functions
    //*****************
	
    public ArrayList<GraphNode> getGraphNode() {
	return this.graphNode;
    }
	
    //****************
    //	Set Functions
    //****************
	
    public void setGraphNode(ArrayList<GraphNode> graphNode) {
	this.graphNode = graphNode;
    }
	
    //************
    //	Others
    //************
	
    public boolean init() {
	return false;
    }
	
    public boolean creatNode(int x, int y) {
	return false;
    }
	
    public boolean creatRoad(ArrayList<Integer> node1, ArrayList<Integer> node2) {
	return false;
    }
	
    public ArrayList<Node> getNodeUrgency()
    {
	return null;
    }
}
