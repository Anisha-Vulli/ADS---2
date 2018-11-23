import java.util.Arrays;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int lgR = 8;
    public static void encode() {
        final int radix = 256;
        char[] index = new char[radix];
        for (int i = 0; i < index.length; i++) {
            index[i] = (char) i;
        }
        String s = BinaryStdIn.readString();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            int indexpos = 0;
            for (int k = 0; k < index.length; k++) {
                if (c == index[k]) {
                    indexpos = k;
                    BinaryStdOut.write(k, lgR);
                    break;
                }
            }
            char[] temp = new char[radix];
            temp[0] = c;
            int m = 1;
            for (int l = 0; l < index.length; l++) {
                if (l != indexpos) {
                    temp[m++] = index[l];
                } else {
                    continue;
                }
            }
            index = temp;
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        String s = BinaryStdIn.readString();
        final int radix = 256;
        char[] index = new char[radix];
        for (int i = 0; i < index.length; i++) {
            index[i] = (char) i;
        }

        for (int j = 0; j < s.length(); j++) {
            int c = s.charAt(j);
            char indexpos = 0;
            for (int k = 0; k < index.length; k++) {
                if (c == k) {
                    indexpos = index[k];
                    BinaryStdOut.write(indexpos, lgR);
                    break;
                }
            }
            char[] temp = new char[radix];
            temp[0] = indexpos;
            int m = 1;
            for (int l = 0; l < index.length; l++) {
                if (l != c) {
                    temp[m++] = index[l];
                } else {
                    continue;
                }
            }
            index = temp;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
