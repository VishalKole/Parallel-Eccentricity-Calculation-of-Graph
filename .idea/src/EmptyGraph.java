import java.util.NoSuchElementException;

/**
 * Class EmptyGraph encapsulates an undirected graph consisting of a number of
 * vertices but no edges.
 *
 * @author  Alan Kaminsky
 * @version 16-Oct-2017
 */
public class EmptyGraph
	implements GraphSpec
	{

	private int V; // Number of vertices

	/**
	 * Construct a new empty graph.
	 *
	 * @param  V  Number of vertices, V &ge; 1.
	 *
	 * @exception  IllegalArgumentException
	 *     (unchecked exception) Thrown if V is out of range.
	 */
	public EmptyGraph
		(int V)
		{
		// Verify preconditions.
		this.V = V;
		if (V < 1)
			throw new IllegalArgumentException (String.format
				("EmptyGraph(): V = %d illegal", V));
		}

	/**
	 * Returns the number of vertices in this graph, V.
	 */
	public int V()
		{
		return V;
		}

	/**
	 * Returns the number of edges in this graph, E.
	 */
	public int E()
		{
		return 0;
		}

	/**
	 * Obtain the next edge in this graph. This method must be called
	 * repeatedly, E times, to obtain all the edges. Each time this method is
	 * called, it stores, in the v1 and v2 fields of object e, the vertices
	 * connected by the next edge. Each vertex is in the range 0 .. V-1.
	 *
	 * @param  edge  Edge object in which to store the vertices.
	 *
	 * @exception  NoSuchElementException
	 *     (unchecked exception) Thrown if this method is called more than E
	 *     times.
	 */
	public void nextEdge
		(Edge e)
		{
		throw new NoSuchElementException();
		}

	}
