import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		Scanner sc = new Scanner(System.in);
		//Your code goes here...
		TST<Integer> tst = new TST<Integer>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				String s = words[i].substring(j);
				if (!tst.contains(s)) {
					tst.put(s, i);
				}
			}
		}
		String prefix = sc.nextLine();
		for (String str : tst.keysWithPrefix(prefix)) {
			System.out.println(str);
		}
	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}