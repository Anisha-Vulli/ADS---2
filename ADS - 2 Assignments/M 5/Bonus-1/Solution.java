import java.util.*;
final class Solution {
	private Solution() {
		//Empty constructor.
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] val = sc.nextLine().split(" ");
		int vertex = Integer.parseInt(val[0]);
		int edge = Integer.parseInt(val[1]);
		Graph grp = new Graph(vertex + 1);
		while (sc.hasNextLine()) {
			String[] nodes = sc.nextLine().split(" ");
			grp.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
		}
		//System.out.println(grp);
		CC ccobj = new CC(grp);
		int[] idarray = ccobj.idarr();
		System.out.println(ccobj.count());
	}
}