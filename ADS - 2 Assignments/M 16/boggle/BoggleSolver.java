import java.util.*;
public class BoggleSolver {
    private TST<Integer> tst;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        //System.out.println("constructor ki vachindi");
        tst = new TST<Integer>();
        for (int i = 0; i < dictionary.length; i++) {
            tst.put(dictionary[i], i);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        //System.out.println("Valid words loki vachindi");
        
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                //marked[i][j] = true;
                //String letter = "" + board.getLetter(i, j);
                dfs(board, list, i, j, marked, "");
            }
        }
        return list;
    }

    public void dfs(BoggleBoard board, ArrayList<String> list, int i, int j, boolean[][] marked, String letter) {
        //System.out.println("dfs ki vachindi");
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
        if (tst.contains(letter) && letter.length() > 2) {
            if (!list.contains(letter)) {
                list.add(letter);   
            }
        }

        if (!tst.hasPrefix(letter)) {
            return;
        }
        marked[i][j] = true;
        for (int row = -1; row < 1; row++) {
            for (int col = -1; col < 1; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }

                if ((i + row >= 0) && (i + row < board.rows()) && (j + col >= 0) && (j + col < board.cols())) {
                    dfs(board, list, i + row, i + col, marked, letter);
                }
                
            }
        }

        marked[i][j] = false;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        System.out.println("Score techukundi");
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