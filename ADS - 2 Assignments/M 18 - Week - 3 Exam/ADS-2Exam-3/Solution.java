/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Array List import.
 */
import java.util.ArrayList;
/**
 * Collections import.
 */
import java.util.Collections;
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

    // Don't modify this method.
    /**
     * Main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash
            = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.

    /**
     * Reading a file.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    /**
     * Loads a dictionary.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(
        final String file) {
        BinarySearchST<String, Integer>  st
        = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] words = toReadFile(file);
        for (int i = 0; i < words.length; i++) {
            String original = words[i].toLowerCase();
            if (st.contains(original)) {
                st.put(original, st.get(original) + 1);
            } else {
                st.put(original, 1);
            }
        }
        return st;
    }

}
/**
 * Class for t 9.
 */
class T9 {
    /**
     * TST object.
     */
    private TST<Integer> tst;
    /**
     * Binary symbol table for alphabets.
     */
    private BinarySearchST<String, Integer> bst;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    protected T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new TST<Integer>();
        bst = new BinarySearchST<String, Integer>();
        for (String str : st.keys()) {
            tst.put(str, st.get(str));
        }
    }

    // get all the prefixes that match with given prefix.

    /**
     * Gets all words.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
    /**
     * Potential words function.
     *
     * Complexity is N^2.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        ArrayList<String> wordlist = new ArrayList<>();
        bst.put("a", 2); bst.put("b", 2); bst.put("c", 2);
        bst.put("d", 2 + 1); bst.put("e", 2 + 1); bst.put("f", 2 + 1);
        bst.put("g", 2 + 2); bst.put("h", 2 + 2); bst.put("i", 2 + 2);
        bst.put("j", 2 + 2 + 1); bst.put("k", 2 + 2 + 1);
        bst.put("l", 2 + 2 + 1);
        bst.put("m", 2 + 2 + 2); bst.put("n", 2 + 2 + 2);
        bst.put("o", 2 + 2 + 2);
        bst.put("p", 2 + 2 + 2 + 1); bst.put("q", 2 + 2 + 2 + 1);
        bst.put("r", 2 + 2 + 2 + 1); bst.put("s", 2 + 2 + 2 + 1);
        bst.put("t", 2 * 2 * 2); bst.put("u", 2 * 2 * 2);
        bst.put("v", 2 * 2 * 2);
        bst.put("w", 2 + 2 + 2 + 2 + 1); bst.put("x", 2 + 2 + 2 + 2 + 1);
        bst.put("y", 2 + 2 + 2 + 2 + 1); bst.put("z", 2 + 2 + 2 + 2 + 1);
        // for (String each : tst.keys()) {
        //     String[] strarr = each.split("");
        //     String num = "";
        //     for (String str : strarr) {
        //         num = num + bst.get(str);
        //     }
        //     if (num.equals(t9Signature)) {
        //         wordlist.add(each);
        //     }
        // }
        // return wordlist;
        //String

        dfs("",0,wordlist,t9Signature.length(), t9Signature);
         //dfs("",0,wordlist,5,"43556");
        return wordlist;

    }
    /**
     * Gets the character.
     *
     * @param      i     { parameter_description }
     *
     * @return     The character.
     */
    public ArrayList<String> getChar(final int i) {
        ArrayList<String> vals = new ArrayList<String>();
        for (String s : bst.keys()) {
            if (bst.get(s) == i) {
                vals.add(s);
            }
        }
        return vals;
    }
    /**
     * DFS into the t9Signature code.
     *
     * @param      res          The resource
     * @param      i            { parameter_description }
     * @param      wordlist     The wordlist
     * @param      lenPattern   The length pattern
     * @param      t9Signature  The t 9 signature
     */
    public void dfs(final String res,final int i,final ArrayList<String> wordlist,
        final int lenPattern,final String t9Signature){
        String str = res;
        if(str.length() == lenPattern){
            if(tst.contains(str)){
                wordlist.add(str);
            }
        }
        if (str.length() >= lenPattern) {
            return;
        }
        
        ArrayList<String> characterArray = 
        getChar(Integer.parseInt(t9Signature.charAt(i) + ""));
        for(String c : characterArray){
            str += c + "";
            dfs(str, i+1, wordlist, lenPattern, t9Signature);
            str = str.substring(0, str.length()-1);
           //System.out.println(res);
        }

    }

    // return all possibilities(words), find top k with highest frequency.

    /**
     * Gets the suggestions.
     *
     * Complexity is N^2.
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(
        final Iterable<String> words, final int k) {
        // your code goes here
        ArrayList<String> wordlist = new ArrayList<>();
        MaxPQ<Integer> maxpq = new MaxPQ<>();
        for (String str : words) {
            maxpq.insert(tst.get(str));
        }
        for (int i = 0; i < k; i++) {
            int freq = maxpq.delMax();
            if (maxpq.isEmpty()) {
                break;
            }
            for (String s : words) {
                //maxpq.insert(tst.get(s));
                if (freq == tst.get(s)) {
                    wordlist.add(s);
                }
                
            }
        }
        Collections.sort(wordlist);
        return wordlist;
    }

    // final output
    // Don't modify this method.
    /**
     * t9 function.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}



