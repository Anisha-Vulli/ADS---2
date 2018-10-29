/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    public int vertex();
    public int edge();
    public void addEdge(int v, int w);
    public Iterable<Integer> list(int v);
    public boolean hasEdge(int v, int w);
}
/**
 * Class for graph theory.
 */
class GraphTheory implements Graph {
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
    GraphTheory() {
        //Empty constructor.
    }
    /**
     * Constructs the object.
     *
     * @param      v1    The v 1
     */
    public GraphTheory(int v1) {
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
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(int v, int w) {
        if (v == w) {
            return;
        }

        if (!hasEdge(v, w)) {
            e++;
        }

        list[v].add(w);
        list[w].add(v);
    }
    /**
     * Particular value in the list.
     * Complexity is 1.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> list(int v) {
        return list[v];
    }
    /**
     * Determines if it has edge.
     * Checks if there is a connection between two nodes.
     * Complexity is N.
     * Checks throughout the list for the particular index.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w) {
        for (int k : list[v]) {
            if (k == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * Displaying the given list in list format.
     * Complexity is N.
     *
     * @param      v          { Vertices }
     * @param      e          { Edges }
     * @param      tokens     The tokens
     *
     * @throws     Exception  { No edges Exception }
     */
    public void listdisplay(int v, int e, String[] tokens) throws Exception {
        if (e <= 1 && v <= 1) {
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
     * @param      v          { Vertices }
     * @param      e          { Edges }
     *
     * @throws     Exception  { No edges }
     */
    public void matrixdisplay(int v, int e) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertex() + " vertices"
                + ", " + edge() + " edges");
            int[][] disp = new int[v][v];
            for (int i = 0; i  < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    Solution() {

    }
    /**
     * Main function.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GraphTheory grph = null;
        String type = sc.nextLine();
        int vertex = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        String[] keys = sc.nextLine().split(",");
        grph = new GraphTheory(vertex);
        while (sc.hasNextLine()) {
            String[] connect = sc.nextLine().split(" ");
            grph.addEdge(Integer.parseInt(connect[0]),
                Integer.parseInt(connect[1]));
        }

        switch (type) {
            case "List":
            try {
                grph.listdisplay(vertex, edges, keys);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                grph.matrixdisplay(vertex, edges);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            default:
            break;
        }
    }
}
