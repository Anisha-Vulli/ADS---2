/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty constructor.
    }
    /**
     * Main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertex = Integer.parseInt(sc.nextLine());
        int edge = Integer.parseInt(sc.nextLine());
        Graph grp = new Graph(vertex);
        while (sc.hasNextLine()) {
            String[] nodes = sc.nextLine().split(" ");
            grp.addEdge(Integer.parseInt(nodes[0]),
                Integer.parseInt(nodes[1]));
        }
        //System.out.println(grp);
        DirectedCycle dc = new DirectedCycle(grp);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}

