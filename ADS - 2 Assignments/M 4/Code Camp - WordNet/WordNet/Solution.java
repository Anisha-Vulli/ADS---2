import java.io.File;
import java.util.Scanner;
class Solution {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		String synsets = sc.nextLine();
		String hypernyms = sc.nextLine();
		// try {
			WordNet wrdnet = new WordNet(synsets, hypernyms);
			String input = sc.nextLine();
			if (input.equals("Graph")) { 
				try {
					System.out.println(wrdnet.dgh);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			} else {

			}
		// } catch (Exception e) {
		// 	System.out.println(e);
		// }
	}
}