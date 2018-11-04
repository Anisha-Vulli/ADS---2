/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * Digraph object.
     */
    private Digraph dgh;
    /**
     * Binary Search symbol table.
     */
    private BinarySearchST<Integer, Double> bst;
    /**
     * Constructs the object.
     *
     * @param      dph   The dph
     */
    PageRank(final Digraph dph) {
        this.dgh = dph;
        bst = new BinarySearchST<Integer, Double>();
    }
    /**
     * Computation of Page Rank.
     */
    public void pgCompu() {
        final double outdegree = 0;
        final double previousit = 0.25;
        final double pg = 0.0;
        for (int p = 0; p < dgh.vertex(); p++) {
            bst.put(p, (1.0 / dgh.vertex()));
        }

        for (int l = 0; l < dgh.vertex(); l++) {
            if (dgh.outdegree(l) == 0) {
                for (int m = 0; m < dgh.vertex(); m++) {
                    if (m != l) {
                        dgh.addEdge(l, m);
                    }
                }
            }
        }
        final int thousand = 1000;
        for (int k = 1; k < 999; k++) {
            for (int i = 0; i < dgh.vertex(); i++) {
                if (dgh.indegree(i) == 0) {
                    bst.put(i, 0.0);
                    break;
                }
            //ArrayList<Integer> list = dgh.adj(i);
            //double temp = 0.0;
            double finaltemp = 0.0;
                for (int j : dgh.reverse().adj(i)) {
                    //int cnt = 0;
                    //System.out.println("Outdegree : " + 
                    //i + " " + j  + " " + dgh.outdegree(j));
                    finaltemp = finaltemp + (bst.get(j) / dgh.outdegree(j));
                    //System.out.println(finaltemp);
                }
                bst.put(i, finaltemp);
            }
        }
    }
    /**
     * Display function.
     */
    public void display() {
        //System.out.println(dgh.toString());
        for (int i : bst.keys()) {
            String str = "";
            str = String.valueOf(i) + " - " + bst.get(i);
            System.out.println(str);
        }
    }
}
/**
 * Class for web search.
 */
class WebSearch {

}

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
        // read the first line of the input to get the
        //number of vertices
        int vertexcnt = Integer.parseInt(sc.nextLine());
        Digraph dgh = new Digraph(vertexcnt);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < vertexcnt; i++) {
            String[] nodes = sc.nextLine().split(" ");
            for (int j = 1; j < nodes.length; j++) {
                dgh.addEdge(Integer.parseInt(nodes[0]),
                    Integer.parseInt(nodes[j]));
            }
        }
        System.out.println(dgh);
        // Create page rank object and pass the graph object
        //to the constructor
        PageRank pr = new PageRank(dgh);
        pr.pgCompu();
        pr.display();
        // print the page rank object
        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path
        // to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}




