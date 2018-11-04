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
    private BinarySearchST<Integer, Double> prev;
    private BinarySearchST<Integer, Double> current;
    /**
     * Constructs the object.
     *
     * @param      dph   The dph
     */
    PageRank(final Digraph dph) {
        this.dgh = dph;
        prev = new BinarySearchST<Integer, Double>();
        current = new BinarySearchST<Integer, Double>();
    }
    /**
     * Computation of Page Rank.
     */
    public void pgCompu() {
        final double outdegree = 0;
        final double previousit = 0.25;
        final double pg = 0.0;
        for (int p = 0; p < dgh.vertex(); p++) {
            prev.put(p, (1.0 / dgh.vertex()));
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
        for (int k = 1; k < 990; k++) {
            for (int i = 0; i < dgh.vertex(); i++) {
                //caluculation(i);
                double finaltemp = caluculation(i);
                current.put(i, finaltemp);
            }
            for (int i : current.keys()) {
                prev.put(i, current.get(i));
            }
        }
        
    }

    public double caluculation(int i) {
        if (dgh.indegree(i) == 0) {
            prev.put(i, 0.0);
            return 0.0;
        }
        double finaltemp = 0.0;
        for (int j : dgh.reverse().adj(i)) {
            //System.out.println("index " + j);
            //System.out.println("value " + bst.get(j) + " " + dgh.outdegree(j));
            finaltemp = finaltemp + (prev.get(j) / dgh.outdegree(j));
        }
        //System.out.println(finaltemp);
        return finaltemp;
    }

    public double getPR(int v) {
        return prev.get(v);
    }
    /**
     * Display function.
     */
    public void display() {
        //System.out.println(dgh.toString());
        for (int i : prev.keys()) {
            String str = "";
            str = String.valueOf(i) + " - " + prev.get(i);
            System.out.println(str);
        }
    }
}
/**
 * Class for web search.
 */
class WebSearch {
    PageRank pr;
    BinarySearchST<String, Bag<Integer>> bst;
    WebSearch(PageRank probj, String filename) {
        pr = probj;
        In file = new In(filename);
        bst = new BinarySearchST<String, Bag<Integer>>();
        while (file.hasNextLine()) {
            String[] input = file.readLine().split(":");
            for (String str : input[1].split(" ")) {
                if (bst.contains(str)) {
                    Bag test = bst.get(str);
                    test.add(Integer.parseInt(input[0]));
                    bst.put(str, test);
                } else {
                    bst.put(str, new Bag<Integer>());
                    Bag test = bst.get(str);
                    test.add(Integer.parseInt(input[0]));
                    bst.put(str, test);
                }
            }
        }
    }

    public int iAmFeelingLucky(String input) {
        if (!bst.contains(input)) {
            return -1;
        }
        Double maxpr = -1.0;
        int maxid = -1;
        Bag<Integer> bag = bst.get(input);
        for (Integer inte : bag) {
            if (pr.getPR(inte) > maxpr) {
                maxpr = pr.getPR(inte);
                maxid = inte;
            }
        }
        return maxid;
    }

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
        WebSearch ws = new WebSearch(pr, file);

        while (sc.hasNextLine()) {
            String[] queries = sc.nextLine().split("=");
            System.out.println(ws.iAmFeelingLucky(queries[1]));
        }
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}




