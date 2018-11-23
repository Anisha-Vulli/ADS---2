import java.util.Arrays;
import java.util.LinkedList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int lgR = 8;

    public static void encode() {
        LinkedList<Integer> ascii = new LinkedList<Integer>();
        final int R = 256;
        for(int i = 0; i < R; i++) {
            ascii.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            int ch = BinaryStdIn.readChar();
            int index = ascii.indexOf(ch);
            BinaryStdOut.write(index, lgR);
            ascii.remove(index);
            ascii.add(0, ch);
        }
        BinaryStdOut.close();
        // final int radix = 256;
        // char[] index = new char[radix];
        // for (int i = 0; i < index.length; i++) {
        //     index[i] = (char) i;
        // }
        // String s = BinaryStdIn.readString();
        // for (int j = 0; j < s.length(); j++) {
        //     char c = s.charAt(j);
        //     int indexpos = 0;
        //     for (int k = 0; k < index.length; k++) {
        //         if (c == index[k]) {
        //             indexpos = k;
        //             BinaryStdOut.write(k, lgR);
        //             break;
        //         }
        //     }
        //     char[] temp = new char[radix];
        //     temp[0] = c;
        //     int m = 1;
        //     for (int l = 0; l < index.length; l++) {
        //         if (l != indexpos) {
        //             temp[m++] = index[l];
        //         } else {
        //             continue;
        //         }
        //     }
        //     index = temp;
        // }
        // BinaryStdOut.close();
    }

    public static void decode() {
        LinkedList<Integer> vals = new LinkedList<Integer>();
        final int R = 256;
        for(int i = 0; i < R; i++) {
            vals.add(i);
        }
        while(!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readChar();
            int  c = vals.get(index);
            BinaryStdOut.write(c, lgR);
            vals.remove(index);
            vals.add(0, c);
        }
        BinaryStdOut.close();
        // String s = BinaryStdIn.readString();
        // final int radix = 256;
        // char[] index = new char[radix];
        // for (int i = 0; i < index.length; i++) {
        //     index[i] = (char) i;
        // }

        // for (int j = 0; j < s.length(); j++) {
        //     int c = s.charAt(j);
        //     char indexpos = 0;
        //     for (int k = 0; k < index.length; k++) {
        //         if (c == k) {
        //             indexpos = index[k];
        //             BinaryStdOut.write(indexpos, lgR);
        //             break;
        //         }
        //     }
        //     char[] temp = new char[radix];
        //     temp[0] = indexpos;
        //     int m = 1;
        //     for (int l = 0; l < index.length; l++) {
        //         if (l != c) {
        //             temp[m++] = index[l];
        //         } else {
        //             continue;
        //         }
        //     }
        //     index = temp;
        // }
        // BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
