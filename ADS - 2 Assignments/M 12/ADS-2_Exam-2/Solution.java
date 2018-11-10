/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
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
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertex = Integer.parseInt(sc.nextLine());
        int edge = Integer.parseInt(sc.nextLine());
        Edge e;
        EdgeWeightedGraph ewg =
        new EdgeWeightedGraph(vertex);
        for (int i = 0; i < edge; i++) {
            String[] tokens = sc.nextLine().split(" ");
            e = new Edge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]), Float.parseFloat(tokens[2]));
            ewg.addEdge(e);
        }
        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(ewg.toString());
            //Print the Graph Object.
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] input = sc.nextLine().split(" ");
            int source = Integer.parseInt(input[0]);
            int dest = Integer.parseInt(input[1]);
            DijkstraUndirectedSP djk = new DijkstraUndirectedSP(ewg, source);
            if (djk.hasPathTo(dest)) {
                System.out.println(djk.distTo(Integer.parseInt(input[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one
            // where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] paths = sc.nextLine().split(" ");
            source = Integer.parseInt(paths[0]);
            int via = Integer.parseInt(paths[1]);
            int destination = Integer.parseInt(paths[2]);
            DijkstraUndirectedSP dusp = new DijkstraUndirectedSP(ewg, source);
            if (dusp.hasPathTo(destination)) {
                Queue<Integer> q = new Queue<Integer>();
                for (Edge ed : dusp.pathTo(via)) {
                    String[] edges = ed.toString().split(" ");
                    String[] vertices = edges[0].split("-");
                    int m = 0;
                    int n = 0;
                    for (Integer k : q) {
                        if (Integer.parseInt(vertices[1]) == k) {
                            m = 1;
                        }
                        if (Integer.parseInt(vertices[1]) == k) {
                            n = 1;
                        }
                    }
                    if (n == 0) {
                        q.enqueue(Integer.parseInt(vertices[1]));
                    }
                    if (m == 0) {
                        q.enqueue(Integer.parseInt(vertices[0]));
                    }
                }
                DijkstraUndirectedSP dusp1 = new DijkstraUndirectedSP(ewg, via);
                for (Edge edg : dusp1.pathTo(destination)) {
                    String[] edgess = edg.toString().split(" ");
                    String[] vertexs = edgess[0].split("-");
                    int p = 0;
                    int r = 0;
                    for (Integer j : q) {
                        if (Integer.parseInt(vertexs[0]) == j) {
                            p = 1;
                        }
                        if (Integer.parseInt(vertexs[1]) == j) {
                            r = 1;
                        }
                    }
                    if (p == 0) {
                        q.enqueue(Integer.parseInt(vertexs[0]));
                    }
                    if (r == 0) {
                        q.enqueue(Integer.parseInt(vertexs[1]));
                    }
                }
                System.out.println(dusp.distTo(via)
                    + dusp1.distTo(destination));
                while (!q.isEmpty()) {
                    System.out.print(q.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}



