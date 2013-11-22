import java.awt.Image;
import java.util.ArrayList;

public class Map {

  private Graph	graph = new Graph();
  private float	scale = 1;
  private Vehicule	vehicule = new Vehicule();
  //Background Image
  private Image	image = null;
  private int		zoom = 100;

  //******************
  //	Constructor
  //******************

  public Map() {

  }

  //***************
  //	Add
  //***************

  public boolean		addNode(int x, int y) {
    this.graph.creatNode(x, y);
    return true;
  }

  public boolean		addNodeUrgency(int[] coord, Urgency.EUrgencyState state, float triggDate) {
    if (this.graph.getNode(coord) != null) {
      Urgency	urg = new Urgency(state, triggDate, 0, 5);
      this.graph.getNode(coord).addUrgency(urg);
    }
    return false;
  }

  public boolean		addRoad(int[] coordNode1, int[] coordNode2) {
    this.graph.creatRoad(this.graph.getNode(coordNode1), this.graph.getNode(coordNode2));
    return true;
  }

  public boolean		creatVehicule(int[] coord) {
    this.vehicule.setCoord(coord);
    return true;
  }

  //***************
  //	Delete
  //***************

  public boolean		deleteNode(int[] coord) {
    this.graph.deleteNode(coord);
    return true;
  }

  public boolean		deleteRoad(int[] coord1, int[] coord2) {
    this.graph.deleteRoad(this.graph.getNode(coord1), this.graph.getNode(coord2));
    return true;
  }

  //***************
  //	Other
  //***************

  /*!
  ** Return all the GraphNode of the graph
  */
  public ArrayList<GraphNode>	getAllGraphNodes()
  {
    return (graph.getAllNodes());
  }

  public boolean		editNodeCoord(int[] oldCoord, int[] newCoord) {
    if (this.graph.getNode(oldCoord) != null) {
      this.graph.getNode(oldCoord).setCoord(newCoord);
      return true;
    }
    return false;
  }

  public boolean		editTreatmentTime(int[] coord, float time, int id) {
    if (this.graph.getNode(coord) != null) {
      this.graph.getNode(coord).setUrgencyTime(id, time);
      return true;
    }
    return false;
  }

  public boolean		editAttachPoint(int[] coord, boolean state) {
    if (this.graph.getNode(coord) != null) {
      this.graph.getNode(coord).setAttachPoint(new AttachPoint(state, this.vehicule));
      return true;
    }
    return false;
  }

  //**
  // * Getters
  //**

  public int			getNbNode() {
    return graph.getNbNode();
  }

  public int			getNbRoad() {
    return graph.getNbRoad();
  }

  public String		getFormatMap() {
    String		str = "";

    // ** A DEFINIR AVEC MISTY !!!!

    return str;
  }

  public ArrayList<Node>	getNodeUrgency() {
    return graph.getNodeUrgency();
  }

  //**
  // * Setters
  //**

  public boolean	setZoom(int newZoom) {
    this.zoom = newZoom;
    return true;
  }

  public void		setVehiculePath(ArrayList<Node> path) {
    this.vehicule.setPath(path);
  }


  //**
  // * DISPLAY
  //**
  public void		display()
  {
    System.out.println("Nombre noeud : " + this.graph.getNbNode());
    System.out.println("Nombre de route : " + this.graph.getNbRoad());
    System.out.println("Noeuds :");
    ArrayList<GraphNode>	nodes = this.graph.getAllNodes();

    for (GraphNode g : nodes)
    {
      System.out.print("Coord: " + g.getData().getCoord()[0]
		       + " - " + g.getData().getCoord()[1]);
      System.out.println("  " + (g.getData().hasUrgency() == true
				 ? "URGENCY (" + g.getData().getNbUrgency() + ") => " + g.getData().getNextUrgency().getTriggerDate()
				 : "Normal"));
      System.out.println("  Voisins:");
      for (GraphNode n : g.getNeighbors())
      {
	System.out.println("Total Neigh/Neighbors(" +  n.getNeighbors().size() + ")");
	System.out.println("    Coord: " + n.getData().getCoord()[0]
			   + " - " + n.getData().getCoord()[1]);
      }
      System.out.println("");
    }
  }

}
