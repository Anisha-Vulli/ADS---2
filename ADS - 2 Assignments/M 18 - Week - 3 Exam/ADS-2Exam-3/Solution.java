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
public class Solution {

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
    public T9(BinarySearchST<String, Integer> st) {
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
        bst.put("j", 2 + 2 + 1); bst.put("k", 2 + 2 + 1); bst.put("l", 2 + 2 + 1);
        bst.put("m", 6); bst.put("n", 6); bst.put("o", 6);
        bst.put("p", 7); bst.put("q", 7); bst.put("r", 7); bst.put("s", 7);
        bst.put("t", 8); bst.put("u", 8); bst.put("v", 8);
        bst.put("w", 9); bst.put("x", 9); bst.put("y", 9); bst.put("z", 9);
        for (String each : tst.keys()) {
            String[] strarr = each.split("");
            String num = "";
            for (String str : strarr) {
                if (str.equals("a") || str.equals("b") || str.equals("c")) {
                    num = num + bst.get(str);
                }
                if (str.equals("d") || str.equals("e") || str.equals("f")) {
                    num = num + bst.get(str);
                }
                if (str.equals("g") || str.equals("h") || str.equals("i")) {
                    num = num + bst.get(str);
                }
                if (str.equals("j") || str.equals("k") || str.equals("l")) {
                    num = num + bst.get(str);
                }
                if (str.equals("m") || str.equals("n") || str.equals("o")) {
                    num = num + bst.get(str);
                }
                if (str.equals("p") || str.equals("q") ||
                    str.equals("r") || str.equals("s")) {
                    num = num + bst.get(str);
                }
                if (str.equals("t") || str.equals("u") || str.equals("v")) {
                    num = num + bst.get(str);
                }
                if (str.equals("w") || str.equals("x") ||
                    str.equals("y") || str.equals("z")) {
                    num = num + bst.get(str);
                }
            }
            if (num.equals(t9Signature)) {
                wordlist.add(each);
            }
        } 
        return wordlist;
    }

    // return all possibilities(words), find top k with highest frequency.

    /**
     * Gets the suggestions.
     *
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
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}

