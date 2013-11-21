public class Main {
  public static void main(String[] args)
  {
    Map		map = new Map();

    map.addNode(0, 0);
    map.addNode(1, 3);
    map.addNode(6, 4);
    map.addNode(52, 0);
    map.addNode(8, 4);
    map.addNode(52, 3);
    map.addNode(5, 0);
    map.addNode(2, 1);
    map.addNode(6, 7);

    int[]		c1 = {0, 0};
    int[]		c2 = {1, 3};
    int[]		c3 = {6, 4};
    int[]		c4 = {52, 0};
    int[]		c5 = {8, 4};
    int[]		c6 = {52, 3};
    int[]		c7 = {5, 0};
    int[]		c8 = {2, 1};
    int[]		c9 = {6, 7};

    map.addRoad(c1, c2);
    map.addRoad(c1, c3);
    map.addRoad(c1, c4);

    map.addRoad(c6, c2);
    map.addRoad(c5, c8);
    map.addRoad(c9, c2);
    map.addRoad(c9, c5);

    map.addRoad(c1, c5);

    map.addNodeUrgency(c1, Urgency.EUrgencyState.SLEEPING, 105);
    map.addNodeUrgency(c1, Urgency.EUrgencyState.SLEEPING, 50);

    // map.deleteNode(c5);

    // map.deleteRoad(c6, c2);

    map.display();
    // AdjMatriceGen	obj = new AdjMatriceGen(map.graph.getAllNodes());
    // obj.GetAdjMatrice();
  }
}
