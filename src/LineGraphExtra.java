import edu.rit.util.Random;
import java.util.NoSuchElementException;

/**
 * Class LineGraphExtra encapsulates an undirected graph consisting of a line of
 * vertices, with one or more extra edges between randomly chosen vertices.
 *
 * @author  Alan Kaminsky
 * @version 16-Oct-2017
 */
public class LineGraphExtra
	implements GraphSpec
	{

	private int V;          // Number of vertices
	private int E;          // Number of edges (not including extras)
	private int extraE;     // Number of extra edges
	private int toGenerate; // Next edge to generate
	private Random prng;    // Pseudorandom number generator

	/**
	 * Construct a new line graph.
	 *
	 * @param  V       Number of vertices, V &ge; 2.
	 * @param  extraE  Number of extra edges, extraE &ge; 0.
	 * @param  seed    Random seed.
	 *
	 * @exception  IllegalArgumentException
	 *     (unchecked exception) Thrown if V or extraE is out of range.
	 */
	public LineGraphExtra
		(int V,
		 int extraE,
		 long seed)
		{
		// Verify preconditions.
		this.V = V;
		if (V < 2)
			throw new IllegalArgumentException (String.format
				("LineGraphExtra(): V = %d illegal", V));
		this.extraE = extraE;
		if (extraE < 0)
			throw new IllegalArgumentException (String.format
				("LineGraphExtra(): extraE = %d illegal", extraE));

		// Initialize fields.
		E = V - 1;
		toGenerate = 0;
		prng = new Random (seed);
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
		return E + extraE;
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
		if (toGenerate < V - 1)
			{
			e.v1 = toGenerate;
			e.v2 = toGenerate + 1;
			++ toGenerate;
			}
		else if (toGenerate < V - 1 + extraE)
			{
			e.v1 = prng.nextInt (V);
			do
				e.v2 = prng.nextInt (V);
			while (Math.abs (e.v1 - e.v2) <= 1);
			++ toGenerate;
			}
		else
			{
			throw new NoSuchElementException();
			}
		}

	}
