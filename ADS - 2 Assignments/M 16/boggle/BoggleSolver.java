import java.util.*;
public class BoggleSolver {
    private TST<Integer> tst;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        tst = new TST<Integer>();
        for (int i = 0; i < dictionary.length; i++) {
            tst.put(dictionary[i], i);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        boolean[][] marked = new boolean[board.rows()][board.cols()];
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                marked[i][j] = true;
                String letter = "" + board.getLetter(i, j);
                dfs(board, list, i, j, marked, letter);
            }
        }
        return new Bag<String>();
    }

    public void dfs(BoggleBoard board, ArrayList<String> list, int i, int j, boolean[][] marked, String letter) {
        if (tst.contains(letter) && letter.length() > 2) {
            list.add(letter);
        }

        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                if (marked[i][j]) {
                    continue;
                }

                marked[i][j] = true;
                letter += board.getLetter(row, col);
                dfs(board, list, row, col, marked, letter);
            }
        }
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word.length() == 4 || word.length() == 3) {
            return 1;
        } else if (word.length() == 5) {
            return 2;
        } else if (word.length() == 6) {
            return 3;
        } else if (word.length() == 7) {
            return 5;
        } else if (word.length() >= 8) {
            return 11;
        }
        return 0;
    }

}