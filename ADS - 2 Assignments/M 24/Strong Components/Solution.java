import java.util.*;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges");
        int vertex = sc.nextInt();
        int edge = sc.nextInt();
        int condition = (vertex * (vertex - 1)) / 2;
        if (edge <= condition) {
            System.out.println("For " + vertex + " no. of vertices we have " + edge + " number of edges");
            DigraphGenerator dg = new DigraphGenerator();
            System.out.println("Enter the number for the type of graph");
            int casenum = sc.nextInt();
            switch (casenum) {
                case 1:
                    KosarajuSharirSCC kscc = new KosarajuSharirSCC(dg.complete(vertex));
                    int components = kscc.count();
                    System.out.println("For complete graph, No. of strng components are " + components);
                    //break;
                case 2:
                    kscc = new KosarajuSharirSCC(dg.simple(vertex, edge));
                    components = kscc.count();
                    System.out.println("For simple graph, No. of strng components are " + components);
                    //break;
                case 3:
                    kscc = new KosarajuSharirSCC(dg.path(vertex));
                    components = kscc.count();
                    System.out.println("For path graph, No. of strng components are " + components);
                    //break;
                case 4:
                    kscc = new KosarajuSharirSCC(dg.cycle(vertex));
                    components = kscc.count();
                    System.out.println("For cycle graph, No. of strng components are " + components);
                    //break;
                case 5:
                    kscc = new KosarajuSharirSCC(dg.eulerianPath(vertex, edge));
                    components = kscc.count();
                    System.out.println("For Eulierian path graph, No. of strng components are " + components);
                    //break;
                case 6:
                    kscc = new KosarajuSharirSCC(dg.eulerianCycle(vertex, edge));
                    components = kscc.count();
                    System.out.println("For Eulierian cycle graph, No. of strng components are " + components);
                    //break;
                case 7:
                    kscc = new KosarajuSharirSCC(dg.binaryTree(vertex));
                    components = kscc.count();
                    System.out.println("For binary tree, No. of strng components are " + components);
                    //break;
                case 8:
                    kscc = new KosarajuSharirSCC(dg.tournament(vertex));
                    components = kscc.count();
                    System.out.println("For tournament, No. of strng components are " + components);
                    //break;
                case 9:
                    kscc = new KosarajuSharirSCC(dg.dag(vertex, edge));
                    components = kscc.count();
                    System.out.println("For DAG, No. of strng components are " + components);
                    //break;
                case 10:
                    kscc = new KosarajuSharirSCC(dg.rootedInDAG(vertex, edge));
                    components = kscc.count();
                    System.out.println("For rooted-in DAG, No. of strng components are " + components);
                    //break;
                case 11:
                    kscc = new KosarajuSharirSCC(dg.rootedOutDAG(vertex, edge));
                    components = kscc.count();
                    System.out.println("For rooted-out DAG, No. of strng components are " + components);
                    //break;
                case 12:
                    kscc = new KosarajuSharirSCC(dg.rootedInTree(vertex));
                    components = kscc.count();
                    System.out.println("For rooted-in tree, No. of strng components are " + components);
                    //break;
                case 13:
                    kscc = new KosarajuSharirSCC(dg.rootedOutTree(vertex));
                    components = kscc.count();
                    System.out.println("For rooted-out tree, No. of strng components are " + components);
                    break;
                default:
                    System.out.println("Wrong case number");
                    break;
            } 
        } else {
            System.out.println("Number of edges for vertices " + vertex + " must be less than " + condition);
        }
    }
}