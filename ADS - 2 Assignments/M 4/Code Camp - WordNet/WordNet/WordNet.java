import java.util.*;
import java.io.File;
public class WordNet {

    private BinarySearchST<String, ArrayList<Integer>> bst;
    Digraph dgh;

    // constructor takes the name of the two input files
    public WordNet(String syn, String hyper) throws Exception {
        File synsetsfile = new File("D:\\MSIT\\IT\\ADS---2\\ADS - 2 Assignments\\M 4\\Code Camp - WordNet\\WordNet\\Files\\" + syn);
        Scanner sf = new Scanner(synsetsfile);
        File hyperfile = new File("D:\\MSIT\\IT\\ADS---2\\ADS - 2 Assignments\\M 4\\Code Camp - WordNet\\WordNet\\Files\\" + hyper);
        Scanner hf = new Scanner(hyperfile);
        bst = new BinarySearchST<String, ArrayList<Integer>>();
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
       
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return bst.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        for (String str : bst.keys()) {
            if (str.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {

    // }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB) {

    // }

    // do unit testing of this class
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     String synsets = sc.nextLine();
    //     String hypernyms = sc.nextLine();
    //     WordNet wrdnet = new WordNet(synsets, hypernyms);
        
    // }
}
