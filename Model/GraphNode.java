import java.util.ArrayList;


public class GraphNode {

    private Node			data;
    private ArrayList<Road>		roads;
	
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
	
    //****************
    //	Set Functions
    //****************
	
    public void			setData(Node newData) {
	this.data = newData;
    }
	
    public void			addRoad(Road newRoad) {
	this.roads.add(newRoad);
    }
}
