import java.util.Scanner;
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
		while (sc.hasNextLine()) {
			String[] input = sc.nextLine().split(" ");
			Edge edg = new Edge(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Double.parseDouble(input[2]));
			ewg.addEdge(edg);
		}
		KruskalMST krush = new KruskalMST(ewg);
		System.out.format("%4f", krush.weight());
	}
}