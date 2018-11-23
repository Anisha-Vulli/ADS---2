import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SuffixArrayX;
import edu.princeton.cs.algs4.BinarySearchST;
public class CircularSuffixArray {
    private final SuffixArrayX sa;

    public CircularSuffixArray(String s) {
        sa = new SuffixArrayX(s);
        // bst = new BinarySearchST<Integer, String>();
        // String str = "";
        // original = new String[str.length()];
        // int k = 0;
        // int p = 0;
        // bst.put(p, str);
        // for (int i = 0; i < s.length(); i++) {
        //     String temp = ""; k = k + 1;
        //     temp = s.substring(k, s.length());
        //     str = temp + s.substring(0, k);
        //     original[i] = str;
        //     bst.put(++p, str);
        // }
        // Arrays.sort(original);
    }
    /**
     * Returns the length.
     *
     * @return     { description_of_the_return_value }
     */
    public int length() {
        return sa.length();
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        return sa.index(i);
        // for (Integer k : bst.keys()) {
        //     if (original[i].equals(bst.get(k))) {
        //         return k;
        //     }
        // }
        // return 0;
    }
}
