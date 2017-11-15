import java.util.NoSuchElementException;

/**
 * Class TreeGraph encapsulates an undirected graph consisting of a complete
 * binary tree of vertices.
 *
 * @author  Alan Kaminsky
 * @version 16-Oct-2017
 */
public class TreeGraph
	implements GraphSpec
	{

	private int V;          // Number of vertices
	private int E;          // Number of edges
	private int toGenerate; // Next edge to generate

	/**
	 * Construct a new tree graph.
	 *
	 * @param  L  Number of tree levels, L &ge; 1.
	 *
	 * @exception  IllegalArgumentException
	 *     (unchecked exception) Thrown if L is out of range.
	 */
	public TreeGraph
		(int L)
		{
		// Verify preconditions.
		if (L < 1)
			throw new IllegalArgumentException (String.format
				("TreeGraph(): L = %d illegal", V));

		// Initialize fields.
		this.V = (1 << L) - 1;
		this.E = V - 1;
		toGenerate = 1;
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
		return E;
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
		if (toGenerate == V)
			throw new NoSuchElementException();
		e.v1 = (toGenerate + 1)/2 - 1;
		e.v2 = toGenerate;
		++ toGenerate;
		}

	}
