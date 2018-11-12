/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Arrays import.
 */
import java.util.Arrays;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty constructor.
    }
    /**
     * Main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
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



