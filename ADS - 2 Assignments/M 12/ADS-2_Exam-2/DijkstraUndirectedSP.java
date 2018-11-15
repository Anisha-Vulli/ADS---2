/**
 * Class for dijkstra undirected sp.
 */
public class DijkstraUndirectedSP {
    /**
     * distTo[v] = distance  of shortest s->v path.
     */
    private double[] distTo;
    /**
     * edgeTo[v] = last edge on shortest s->v path.
     */
    private Edge[] edgeTo;
    /**
     * priority queue of vertices.
     */
    private IndexMinPQ<Double> pq;

    /**
     * Computes a shortest-paths tree from the source
     * vertex {@code s} to every
     * other vertex in the edge-weighted graph
     * {@code G}.
     * 
     * Complexity is E + V.
     *
     * @param  grp the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException
     * if an edge weight is negative
     * @throws IllegalArgumentException
     * unless {@code 0 <= s < V}
     */
    public DijkstraUndirectedSP(
        final EdgeWeightedGraph grp, final int s) {
        for (Edge e : grp.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                    "edge " + e + " has negative weight");
            }
        }

        distTo = new double[grp.vertex()];
        edgeTo = new Edge[grp.vertex()];

        validateVertex(s);

        for (int v = 0; v < grp.vertex(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(grp.vertex());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : grp.adj(v)) {
                relax(e, v);
            }
        }

        // check optimality conditions
        assert check(grp, s);
    }

    /**
     * relax edge e and update pq if changed.
     * 
     * Complexity is log E.
     *
     * @param      e     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * Returns the length of a shortest path between
     * the source vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return the length of a shortest path between
     * the source vertex {@code s} and
     *         the vertex {@code v}; {@code Double.
     *         POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     * 
     * Complexity is 1.
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path between
     * the source vertex {@code s} and
     * vertex {@code v}.
     *
     *
     *Complexity is 1.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path
     * between the source vertex
     *         {@code s} to vertex {@code v};
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * Returns a shortest path between the source
     * vertex {@code s} and vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path between the source
     * vertex {@code s} and vertex {@code v};
     *         {@code null} if no such path
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     * 
     * Complexity is E log V.
     */
    public Iterable<Edge> pathTo(final int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

    /**
     * check optimality conditions:(i) for all edges e = v-w:
     * distTo[w] <= distTo[v] + e.weight()(ii)
     * for all edge e = v-w on the SPT: distTo[w] == distTo[v]
     * + e.weight() 
     *
     * @param      g     { parameter_description }
     * @param      s     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(
        final EdgeWeightedGraph g, int s) {

        // check that edge weights are nonnegative
        for (Edge e : g.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < g.vertex(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v-w satisfy distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < g.vertex(); v++) {
            for (Edge e : g.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v-w on SPT satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < g.vertex(); w++) {
            if (edgeTo[w] == null) continue;
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) return false;
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}

    /**
     * Complexity is 1.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}