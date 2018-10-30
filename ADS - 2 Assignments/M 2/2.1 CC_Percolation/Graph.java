/**
 * Interface for graph.
 */
interface GraphTheory {
    /**
     * Returns the count of vertex.
     *
     * @return     { description_of_the_return_value }
     */
    int vertex();
    /**
     * Returns the count of edge.
     *
     * @return     { description_of_the_return_value }
     */
    int edge();
    /**
     *
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * Returns the particular value in the list.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> list(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for graph theory.
 */
class Graph implements GraphTheory {
    /**
     * Vertices.
     */
    private int v;
    /**
     * Edges.
     */
    private int e;
    /**
     * Graph array.
     */
    private Bag<Integer>[] list;
    /**
     * Constructs the object.
     */
    Graph() {
        //Empty constructor.
    }
    /**
     * Constructs the object.
     *
     * @param      v1    The v 1
     */
    Graph(final int v1) {
        this.v = v1;
        this.e = 0;
        list = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v; i++) {
            list[i] = new Bag<Integer>();
        }
    }
    /**
     * Vertices count.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertex() {
        return v;
    }
    /**
     * Edges count.
     *
     * @return     { description_of_the_return_value }
     */
    public int edge() {
        return e;
    }
    /**
     * Adds an edge.
     * Complexity of add is 1.
     *
     * @param      v1     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v1, final int w) {
        if (v1 == w) {
            return;
        }

        if (!hasEdge(v1, w)) {
            e++;
            list[v1].add(w);
            list[w].add(v1);
        }
    }
    /**
     * Particular value in the list.
     * Complexity is 1.
     *
     * @param      v1     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> list(final int v1) {
        return list[v1];
    }
    /**
     * Determines if it has edge.
     * Checks if there is a connection between two nodes.
     * Complexity is N.
     * Checks throughout the list for the particular index.
     * For matrix it is 1.
     * For adjecency list it is E.
     *
     * @param      v1     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v1, final int w) {
        for (int k : list[v1]) {
            if (k == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * Displaying the given list in list format.
     * Complexity is N^2.
     *
     * @param      v1          { Vertices }
     * @param      e1          { Edges }
     * @param      tokens     The tokens
     *
     * @throws     Exception  { No edges Exception }
     */
    public void listdisplay(final int v1, final int e1,
        final String[] tokens) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            for (int i = 0; i < tokens.length; i++) {
            String str = "";
            str = tokens[i] + ": ";
            for (int k : list(i)) {
                str = str + tokens[k] + " ";
            }
            System.out.println(str);
            }
        }
    }
    /**
     * Display the given list in matix format.
     * Complexity is N^2.
     * The array used is a double dimensional array.
     *
     * @param      v1          { Vertices }
     * @param      e1          { Edges }
     *
     * @throws     Exception  { No edges }
     */
    public void matrixdisplay(final int v1,
        final int e1) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            int[][] disp = new int[v][v];
            for (int i = 0; i  < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v1; i++) {
                for (int j = 0; j < v1; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}