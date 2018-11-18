/**
 * Array list import.
 */
import java.util.ArrayList;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    /**
     * TrieST object.
     */
    private TrieST tst;
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        //System.out.println("constructor ki vachindi");
        tst = new TrieST();
        for (int i = 0; i < dictionary.length; i++) {
            tst.add(dictionary[i]);
        }
    }

    /**
     * Gets all valid words.
     *
     * Complexity is N^2.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked =
                new boolean[board.rows()][board.cols()];
                dfs(board, list, i, j, marked, "");
            }
        }
        return list;
    }

    /**
     * dfs in the  grid.
     *
     * Complexity is N^2.
     *
     * @param      board   The board
     * @param      list    The list
     * @param      i       { parameter_description }
     * @param      j       { parameter_description }
     * @param      marked  The marked
     * @param      letter  The letter
     */
    public void dfs(final BoggleBoard board, final ArrayList<String> list,
        final int i, final int j, final boolean[][] marked,
        final String letter) {
        //System.out.println(letter);
        if (marked[i][j]) {
            return;
        }
        char ch = board.getLetter(i, j);
        String word = letter;
        if (ch == 'Q') {
            word += "QU";
        } else {
            word += ch;
        }
        if (!tst.hasPrefix(word)) {
            return;
        }
        if (tst.contains(word) && word.length() > 2) {
            if (!list.contains(word)) {
               list.add(word);
            }
        }
        marked[i][j] = true;
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }

                if ((i + row >= 0) && (i + row < board.rows())
                    && (j + col >= 0) && (j + col < board.cols())) {
                    dfs(board, list, i + row, j + col, marked, word);
                }
            }
        }

        marked[i][j] = false;
    }


    /**
     * Finding the score of the word.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        //System.out.println("Score techukundi");
        final int three = 3;
        final int five = 5;
        final int six = 6;
        final int eight = 8;
        final int eleven = 11;
        final int four = 4;
        final int seven = 7;
        if (tst.contains(word)) {

            if (word.length() >= 0 && word.length() <= 2) {
                return 0;
            }
            if (word.length() >= three && word.length() <= four) {
                return 1;
            }

            if (word.length() == five) {
                return 2;
            }

            if (word.length() == six) {
                return three;
            }

            if (word.length() == seven) {
                return five;
            }

            if (word.length() >= eight) {
                return eleven;
            }
        }
        return 0;
    }

}

