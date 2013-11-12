
import java.util.ArrayList;

public class Node
{
    private int[]		_coord;
    private int			_triggerDate;
    private ArrayList<Urgency>	_urgencyList;

    public Node(int x, int y)
    {
	_coord[0] = x;
	_coord[1] = y;
    }
}