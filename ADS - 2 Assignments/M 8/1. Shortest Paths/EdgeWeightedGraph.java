/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * New line.
     */
    private static final String NEWLINE
    = System.getProperty("line.separator");
    /**
     * Vertices.
     */
    private final int ver;
    /**
     * Edges.
     */
    private int edge;
    /**
     * Adj array of bags.
     */
    private Bag<Edge>[] adj;

    /**
     * Initializes an empty edge-weighted graph with
     * {@code V} vertices and 0 edges.
     *
     * @param  vertex the number of vertices
     * @throws IllegalArgumentException if
     * {@code V < 0}
     */
    public EdgeWeightedGraph(final int vertex) {
        if (vertex < 0) {
            throw new IllegalArgumentException(
                "Number of vertices must be nonnegative");
        }
        this.ver = vertex;
        this.edge = 0;
        adj = (Bag<Edge>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    /**
     * Initializes a random edge-weighted graph
     * with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    // public EdgeWeightedGraph(int V, int E) {
    //     this(V);
    //     if (E < 0) {
    //     throw new IllegalArgumentException(
    //     "Number of edges must be nonnegative");
    //     }
    //     for (int i = 0; i < E; i++) {
    //         int v = StdRandom.uniform(V);
    //         int w = StdRandom.uniform(V);
    //         double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
    //         Edge e = new Edge(v, w, weight);
    //         addEdge(e);
    //     }
    // }



    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return the number of vertices in this edge-weighted graph
     */
    public int vertex() {
        return ver;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return the number of edges in this edge-weighted graph
     */
    public int e() {
        return edge;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * Validate a vertex.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (ver - 1));
        }
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless
     * both endpoints are between {@code 0} and {@code V-1}
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        edge++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param  v the vertex
     * @return the edges incident on vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-
     * weighted graph, use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < ver; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ver + " " + edge + NEWLINE);
        for (int v = 0; v < ver; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}

