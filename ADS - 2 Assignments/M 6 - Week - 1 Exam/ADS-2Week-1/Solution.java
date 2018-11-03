import java.util.*;
class PageRank {
    private Digraph dgh;
    private BinarySearchST<Integer, Double> bst;
    PageRank(Digraph dph) {
        this.dgh = dph;
        bst = new BinarySearchST<Integer, Double>();
    }

    public void pgCompu() {
        double outdegree = 0;
        double previousit = 0.25;
        double pg = 0.0;
        for (int p = 0; p < dgh.V(); p++) {
            bst.put(p, previousit);
        }
        for (int k = 0; k < 1000; k++) {
            for (int i = 0; i < dgh.V(); i++) {
            //ArrayList<Integer> list = dgh.adj(i);
            double temp = 0.0;
            double finaltemp = 0.0;
                for (int j : dgh.adj(i)) {
                    //int cnt = 0;
                    temp = bst.get(j) / dgh.outdegree(j);
                    finaltemp = finaltemp + temp;
                    //System.out.println(finaltemp);
                }
                bst.put(i, finaltemp);
            }
        }
    }

    public void display() {
        System.out.println(dgh.toString());
        for (int i : bst.keys()) {
            String str = "";
            str = String.valueOf(i) + "-->" + bst.get(i);
            System.out.println(str);
        }
    }
}

class WebSearch {

}


public class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty constructor.
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // read the first line of the input to get the number of vertices
        int vertexcnt = Integer.parseInt(sc.nextLine());
        Digraph dgh = new Digraph(vertexcnt);
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        for (int i = 0; i < vertexcnt; i++) {
            String[] nodes = sc.nextLine().split(" ");
            for (int j = 1; j < nodes.length; j++) {
                dgh.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[j]));   
            }
        }
        // Create page rank object and pass the graph object to the constructor
        PageRank pr = new PageRank(dgh);
        pr.pgCompu();
        pr.display();
        // print the page rank object
        
        // This part is only for the final test case
        
        // File path to the web content
        String file = "WebContent.txt";
        
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        
    }
}
