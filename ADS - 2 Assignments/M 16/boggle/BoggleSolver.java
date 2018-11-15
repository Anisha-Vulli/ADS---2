import java.util.*;
public class BoggleSolver {
    TrieST tst;
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        //System.out.println("constructor ki vachindi");
        tst = new TrieST();
        for (int i = 0; i < dictionary.length; i++) {
            tst.add(dictionary[i]);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        //System.out.println("Valid words loki vachindi");
        
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                boolean[][] marked = new boolean[board.rows()][board.cols()];
                dfs(board, list, i, j, marked, "");
            }
        }
        return list;
    }

    public void dfs(BoggleBoard board, ArrayList<String> list, int i, int j, boolean[][] marked, String letter) {
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

                if ((i + row >= 0) && (i + row < board.rows()) && (j + col >= 0) && (j + col < board.cols())) {
                    dfs(board, list, i + row, j + col, marked, word);
                }
                
            }
        }

        marked[i][j] = false;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        //System.out.println("Score techukundi");
        if (tst.contains(word)) {
            switch (word.length()) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
            }
        } else {
            return 0;
        }
    }

}