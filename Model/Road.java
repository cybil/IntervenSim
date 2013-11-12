
public class Road
{
    private int		_length;
    private Node	_dest;
    private Node	_begin;

    public Road(int length, Node dest, Node begin)
    {
	_length = length;
	_dest = dest;
	_begin = begin;
    }
}