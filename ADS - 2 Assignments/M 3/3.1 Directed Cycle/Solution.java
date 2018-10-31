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
        Digraph dgh = new Digraph(vertex);
        while (sc.hasNextLine()) {
            String[] nodes = sc.nextLine().split(" ");
            dgh.addEdge(Integer.parseInt(nodes[0]),
                Integer.parseInt(nodes[1]));
        }
        //System.out.println(dgh);
        DirectedCycle dc = new DirectedCycle(dgh);
        if (dc.hasCycle()) {
            System.out.println("Cycle exists. ");
        } else {
            System.out.println("Cycle doesn't exists. ");
        }
    }
}


