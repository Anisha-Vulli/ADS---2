/**
 * Class for edge weighted digraph.
 */
public class EdgeWeightedDigraph {
    /**
     * New line.
     */
    private static final String NEWLINE
    = System.getProperty("line.separator");
    /**
     * number of vertices in this digraph.
     */
    private final int vertex;
    /**
     * number of edges in this digraph.
     */
    private int edge;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<DirectedEdge>[] adj;
    /**
     * indegree[v] = indegree of vertex v.
     */
    private int[] indegree;

    /**
     * Initializes an empty edge-weighted digraph
     * with {@code V} vertices and 0 edges.
     *
     * @param  vertexcnt the number of vertices
     * @throws IllegalArgumentException if
     * {@code V < 0}
     */
    public EdgeWeightedDigraph(final int vertexcnt) {
        if (V < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertex = vertexcnt;
        this.edge = 0;
        this.indegree = new int[vertex];
        adj = (Bag<DirectedEdge>[]) new Bag[vertex];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    /**
     * Initializes a random edge-weighted digraph with
     * {@code V} vertices and <em>E</em> edges.
     *
     * @param  ver the number of vertices
     * @param  edg the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedDigraph(final int ver, final int edg) {
        this(ver);
        if (edg < 0) {
         throw new IllegalArgumentException(
            "Number of edges in a Digraph must be nonnegative");
        }
        final int hundred = 100;
        final float pointnum = 0.01;
        for (int i = 0; i < edg; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = pointnum * StdRandom.uniform(hundred);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * Initializes an edge-weighted digraph from the
     * specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if the endpoints of
     * any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of
     * vertices or edges is negative
     */
    public EdgeWeightedDigraph(final In in) {
        this(in.readInt());
        int e = in.readInt();
        if (e < 0) {
            throw new IllegalArgumentException(
                "Number of edges must be nonnegative");
        }
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    /**
     * Initializes a new edge-weighted digraph that is a deep copy of {@code G}.
     *
     * @param  grp the edge-weighted digraph to copy
     */
    public EdgeWeightedDigraph(
        final EdgeWeightedDigraph grp) {
        this(grp.V());
        this.E = grp.E();
        for (int v = 0; v < grp.V(); v++) {
            this.indegree[v] = grp.indegree(v);
        }
        for (int v = 0; v < grp.V(); v++) {
            // reverse so that adjacency list
            //is in same order as original
            Stack<DirectedEdge> reverse
            = new Stack<DirectedEdge>();
            for (DirectedEdge e : grp.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge e : reverse) {
                adj[v].add(e);
            }
        }
    }

    /**
     * Returns the number of vertices
     * in this edge-weighted digraph.
     *
     * @return the number of vertices
     * in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this
     * edge-weighted digraph.
     *
     * @return the number of edges in this
     * edge-weighted digraph
     */
    public int E() {
        return E;
    }

    /**
     * throw an IllegalArgumentException unless
     * {@code 0 <= v < V}.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    /**
     * Adds the directed edge {@code e} to
     * this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless
     * endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(final DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }


    /**
     * Returns the directed edges incident from
     * vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex
     * {@code v} as an Iterable
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident
     * from vertex {@code v}.
     * This is known as the <em>outdegree</em> of
     * vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident
     * to vertex {@code v}.
     * This is known as the <em>indegree</em> of
     * vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted
     * digraph.
     * To iterate over the edges in this edge-weighted
     * digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph,
     * as an iterable
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of this edge
     * weighted digraph.
     *
     * @return the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency
     *         lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
