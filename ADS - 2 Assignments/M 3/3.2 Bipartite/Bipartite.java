/**
 * Class for bipartite.
 */
public class Bipartite {
    /**
     * boolean variable.
     */
    private boolean isBipartite;   // is the graph bipartite?
    /**
     * boolean array.
     */
    private boolean[] color;
    /**
     * boolean array.
     */
    private boolean[] marked;
    /**
     * integer array.
     */
    private int[] edgeTo;
    /**
     * stack.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether an undirected graph is bipartite and finds either a
     * bipartition or an odd-length cycle.
     *Time Complexity : O(N).
     * @param  g the graph
     */
    public Bipartite(final Graph g) {
        isBipartite = true;
        color  = new boolean[g.vertex()];
        marked = new boolean[g.vertex()];
        edgeTo = new int[g.vertex()];

        for (int v = 0; v < g.vertex(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
        assert check(g);
    }
    /**
     * dfs method.
     *Time Complexity : O(N^2).
     * @param      g  graph.
     * @param      v  integer variable.
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }

    /**
     * Returns true if the graph is bipartite.
     *Time Complexity : O(N).
     * @return {@code true} if the graph is bipartite; {@code false} otherwise
     */
    public boolean isBipartite() {
        return isBipartite;
    }
    /**
     * Returns the side of the bipartite that vertex {@code v} is on.
     *
     * @param  v the vertex
     * @return the side of the bipartition that vertex
     * {@code v} is on; two vertices
     *         are in the same side of the bipartition
     *         if and only if they have the
     *         same color
     * @throws IllegalArgumentException unless {@code
     * 0 <= v < V}
     * @throws UnsupportedOperationException if this
     *  method is called when the graph
     *         is not bipartite
     */
    public boolean color(final int v) {
        validateVertex(v);
        if (!isBipartite) {
            throw new UnsupportedOperationException(
                "graph is not bipartite");
        }
        return color[v];
    }

    /**
     * Returns an odd-length cycle if the graph is not bipartite, and
     * {@code null} otherwise.
     *Time Complexity : O(N).
     * @return an odd-length cycle if the graph is not bipartite
     *         (and hence has an odd-length cycle), and {@code null}
     *         otherwise
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }
    /**
     * check method.
     *
     * @param      g  graph.
     *Time Complexity : O(N^2).
     * @return true/false.
     */
    private boolean check(final Graph g) {
        // graph is bipartite
        if (isBipartite) {
            for (int v = 0; v < g.vertex(); v++) {
                for (int w : g.adj(v)) {
                    if (color[v] == color[w]) {
                        System.err.printf(
            "edge %d-%d with %d and %d in same side of bipartition\n",
                             v, w, v, w);
                        return false;
                    }
                }
            }
        } else {
            // verify cycle
            int first = -1, last = -1;
            for (int v : oddCycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf(
                    "cycle begins with %d and ends with %d\n",
                     first, last);
                return false;
            }
        }

        return true;
    }
    /**
     * validate vertex method.
     *Time Complexity : O(N).
     * @param      v  integer variable.
     */
    private void validateVertex(final int v) {
        int ve = marked.length;
        if (v < 0 || v >= ve) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (ve - 1));
        }
    }
}


