import java.util.Scanner;
class Solution {
	Solution() {
		//Empty constructor.
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertex = Integer.parseInt(sc.nextLine());
		int edge = Integer.parseInt(sc.nextLine());
		Graph grp = new Graph(vertex);
		while (sc.hasNextLine()) {
			String[] nodes = sc.nextLine().split(" ");
			grp.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
		}
		//System.out.println(grp);
		DirectedCycle dc = new DirectedCycle(grp);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
	}
}