/**
 * Class for cc.
 */
public class CC {
    /**
     * marked[v] = has vertex v been marked?.
     */
    private boolean[] marked;
    /**
     * id[v] = id of connected component containing v.
     */
    private int[] id;
    /**
     * size[id] = number of vertices in given component.
     */
    private int[] size;
    /**
     * number of connected components.
     */
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graph G) {
        marked = new boolean[G.ve()];
        id = new int[G.ve()];
        size = new int[G.ve()];
        for (int v = 0; v < G.ve(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * depth-first search for a Graph.
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph G, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * Returns the component id of the connected
     * component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of the connected
     * component containing vertex {@code v}
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in 
     * the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices in the 
     * connected component containing vertex {@code v}
     * @throws IllegalArgumentException unless 
     * {@code 0 <= v < V}
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected
     * components in the graph {@code G}.
     *
     * @return the number of connected
     * components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v}
     * and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v}
     * and {@code w} are in the same
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     * @throws IllegalArgumentException unless
     * {@code 0 <= w < V}
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(final int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}