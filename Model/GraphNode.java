import java.util.ArrayList;


public class GraphNode {

	private ArrayList<Node> _data;
	
	private ArrayList<GraphNode> _neighbors;
	
	//******************
	//	Constructor
	//******************
	
	public GraphNode() {
		_data = null;
		_neighbors = null;
	}
	
	public GraphNode(ArrayList<Node> newData, ArrayList<GraphNode> newNeighbors) {
		_data = newData;
		_neighbors = newNeighbors;
	}
	
	public GraphNode(GraphNode graphNode) {
		_data = graphNode._data;
		_neighbors = graphNode._neighbors;
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
		return _data;
	}
	
	public ArrayList<GraphNode> getNeighbors() {
		return _neighbors;
	}
	
	//****************
	//	Set Functions
	//****************
	
	public void setData(ArrayList<Node> newData) {
		_data = newData;
	}
	
	public void setNeighbors(ArrayList<GraphNode> newNeighbors) {
		_neighbors = newNeighbors;
	}
}
