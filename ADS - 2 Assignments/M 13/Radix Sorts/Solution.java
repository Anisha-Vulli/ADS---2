import java.util.Scanner;
import java.util.Arrays;

public class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String[] arr = new String[n];
		Quick3string q3s = new Quick3string();
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextLine();
		}
		q3s.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}