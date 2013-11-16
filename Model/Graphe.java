import java.util.ArrayList;


public class Graphe {
	
	private ArrayList<GraphNode> _graphNode;

	//******************
	//	Constructor
	//******************
	
	public Graphe() {
		_graphNode = null;
	}
	
	public Graphe(ArrayList<GraphNode> newGraphNode) {
		_graphNode = newGraphNode;
	}
	
	public Graphe(Graphe graphe) {
		_graphNode = graphe._graphNode;
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
		return _graphNode;
	}
	
	//****************
	//	Set Functions
	//****************
	
	public void setGraphNode(ArrayList<GraphNode> graphNode) {
		_graphNode = graphNode;
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
