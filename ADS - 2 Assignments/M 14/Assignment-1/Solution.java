import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		Scanner sc = new Scanner(System.in);
		//Your code goes here...
		TST<Integer> tst = new TST<Integer>();
		for (int i = 0; i < words.length; i++) {
			tst.put(words[i], i);
		}
		String prefix = sc.nextLine();
		for (String str : tst.keysThatMatch("." + prefix)) {
			System.out.println(str);
		}
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}