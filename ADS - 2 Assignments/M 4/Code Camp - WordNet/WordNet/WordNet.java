import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class WordNet {

    private BinarySearchST<String, ArrayList<Integer>> bst;
    private BinarySearchST<Integer, String> binst;
    private Digraph dgh;
    private SAP sap;
    private boolean hasCycle = false;
    private boolean hasMultipleroots = false;

    public Digraph getDigraph() {
        return this.dgh;
    }

    public boolean gethasCycle() {
        return this.hasCycle;
    }

    public boolean gethasMultipleroots() {
        return this.hasMultipleroots;
    }

    // constructor takes the name of the two input files
    public WordNet(String syn, String hyper) throws Exception {
        try {
            File synsetsfile = new File("Files/" + syn);
        Scanner sf = new Scanner(synsetsfile);
        File hyperfile = new File("Files/" + hyper);
        Scanner hf = new Scanner(hyperfile);
        bst = new BinarySearchST<String, ArrayList<Integer>>();
        binst = new BinarySearchST<Integer, String>();
        while (sf.hasNextLine()) {
            String[] inputline = sf.nextLine().split(",");
            String[] words = inputline[1].split(" ");
            for (int i = 0; i < words.length; i++) {
                if (bst.contains(words[i])) {
                    ArrayList wordlist = bst.get(words[i]);
                    wordlist.add(Integer.parseInt(inputline[0]));
                    //bst.put(wordlist, words[i]);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(Integer.parseInt(inputline[0]));
                    bst.put(words[i], list);
                }
            }
        }

        dgh = new Digraph(bst.size());
        while (hf.hasNextLine()) {
            String[] inputline = hf.nextLine().split(",");
            for (int i = 1; i < inputline.length; i++) {
                dgh.addEdge(Integer.parseInt(inputline[0]), Integer.parseInt(inputline[i]));
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }

    DirectedCycle dc = new DirectedCycle(dgh);
    if (dc.hasCycle()) {
        hasCycle = true;
    }
       
    }

    public void checkMultipleRoots() {
        int roots = 0;
        for (int i = 0; i < dgh.V(); i++) {
            if (dgh.outdegree(i) == 0) {
                roots++;
            }
        }

        if (roots != 1) {
            hasMultipleroots = true;
            System.out.println("Multiple roots");
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return bst.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        // for (String str : bst.keys()) {
        //     if (str.equals(word)) {
        //         return true;
        //     }
        // }
        return true;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        ArrayList id1 = bst.get(nounA);
        ArrayList id2 = bst.get(nounB);
        sap = new SAP(dgh);
        return sap.length(id1, id2);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        ArrayList id1 = bst.get(nounA);
        ArrayList id2 = bst.get(nounB);
        sap = new SAP(dgh);
        int ans = sap.ancestor(id1, id2);
        return binst.get(ans);
    }

    // do unit testing of this class
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     String synsets = sc.nextLine();
    //     String hypernyms = sc.nextLine();
    //     WordNet wrdnet = new WordNet(synsets, hypernyms);
        
    // }
}
