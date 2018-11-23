import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;
import java.util.Comparator;
public class CircularSuffixArray {
    private String input;
    private Integer[] indices;
    public CircularSuffixArray(String s) {
        if(s == null) {
            throw new IllegalArgumentException("Input index is invalid.");
        }
        input = s;
        indices = new Integer[length()];
        for(int i  = 0 ; i < indices.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                int firstIndex = first;
                int secondIndex = second;
                for(int i = 0; i < input.length(); i++) {
                    char a = input.charAt(firstIndex);
                    char b = input.charAt(secondIndex);
                    if( a < b) {
                        return -1;
                    } else if(a > b) {
                        return 1;
                    }
                    ++firstIndex;
                    if(firstIndex == input.length()) {
                        firstIndex = 0;
                    }
                    ++secondIndex;
                    if(secondIndex == input.length()) {
                        secondIndex = 0;
                    }
                 }
                 return 0;
            }
        });
    }

    public int length() {
        return input.length();
    }

    /**
     * returns index of ith sorted suffix
     *
     * @param i
     *            the index of the ith sorted suffix
     * @return
     */
    public int index(int i) {
        if (i < 0 || i >= input.length()) {
            throw new IllegalArgumentException("Input index is invalid.");
        }
        return indices[i];
    }
}

// import java.util.Arrays;
// import edu.princeton.cs.algs4.Queue;
// import edu.princeton.cs.algs4.SuffixArrayX;
// public class CircularSuffixArray {
//     private final SuffixArrayX sa;

//     public CircularSuffixArray(String s) {
//         if (s == null) {
//             throw new NullPointerException();
//         }
//         sa = new SuffixArrayX(s);
//         // bst = new BinarySearchST<Integer, String>();
//         // String str = "";
//         // original = new String[str.length()];
//         // int k = 0;
//         // int p = 0;
//         // bst.put(p, str);
//         // for (int i = 0; i < s.length(); i++) {
//         //     String temp = ""; k = k + 1;
//         //     temp = s.substring(k, s.length());
//         //     str = temp + s.substring(0, k);
//         //     original[i] = str;
//         //     bst.put(++p, str);
//         // }
//         // Arrays.sort(original);
//     }
//     /**
//      * Returns the length.
//      *
//      * @return     { description_of_the_return_value }
//      */
//     public int length() {
//         return sa.length();
//     }

//     /**
//      * returns index of ith sorted suffix
//      *
//      * @param i
//      *            the index of the ith sorted suffix
//      * @return
//      */
//     public int index(int i) {
//         return sa.index(i);
//         // for (Integer k : bst.keys()) {
//         //     if (original[i].equals(bst.get(k))) {
//         //         return k;
//         //     }
//         // }
//         // return 0;
//     }
// }
