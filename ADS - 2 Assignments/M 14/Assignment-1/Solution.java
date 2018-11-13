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
        String[] words = loadWords();
        Scanner sc = new Scanner(System.in);
        //Your code goes here...
        TST<Integer> tst = new TST<Integer>();
        /**
         * Putting the words into tst.
         *
         * Complexity is N^2.
         */
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                String s = words[i].substring(j);
                if (!tst.contains(s)) {
                    tst.put(s, i);
                }
            }
        }
        String prefix = sc.nextLine();
        /**
         * Printing all the 9
         */
        for (String str : tst.keysWithPrefix(prefix)) {
            System.out.println(str);
        }
    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}




