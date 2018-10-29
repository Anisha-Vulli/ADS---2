import java.util.Scanner;

interface Graph {
    public int vertex();
    public int edge();
    public void addEdge(int v, int w);
    public Iterable<Integer> list(int v);
    public boolean hasEdge(int v, int w);
}

class GraphTheory implements Graph {
    private int v;
    private int e;
    private Bag<Integer>[] list;
    GraphTheory() {

    }
    public GraphTheory(int v1) {
        this.v = v1;
        this.e = 0;
        list = (Bag<Integer>[]) new Bag[v1];
        for (int i = 0; i < v; i++) {
            list[v] = new Bag<Integer>();
        }
    }

    public int vertex() {
        return v;
    }

    public int edge() {
        return e;
    }

    public void addEdge(int v, int w) {
        if (v == e) {
            return;
        }

        if (!hasEdge(v, w)) {
            e++;
        }

        list[v].add(w);
        list[w].add(v);
    }

    public Iterable<Integer> list(int v) {
        return list[v];
    }

    public boolean hasEdge(int v, int w) {
        for (int k : list[v]) {
            if (v == w) {
                return true;
            }
        }
        return false;
    }

    public void listdisplay(int v, int e, String[] tokens) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(vertex() + " vertices" + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertex() + " vertices" + ", " + edge() + " edges");
            for (int i = 0; i < tokens.length; i++) {
            String str = "";
            str = tokens[i] + ": ";
            for (int k : list(i)) {
                str = str + tokens[k] + " ";
            }
            System.out.println(str);
            }
        }
    }

    public void matrixdisplay(int v, int e) throws Exception {
        if (e <= 1 && v <= 1) {
            System.out.println(vertex() + " vertices" + ", " + edge() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(vertex() + " vertices" + ", " + edge() + " edges");
            int[][] disp = new int[v][v];
            for (int i = 0; i  < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

class Solution {
    Solution() {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GraphTheory grph = null;
        String type = sc.nextLine();
        int vertex = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        String[] keys = sc.nextLine().split(",");
        grph = new GraphTheory(vertex);
        while (sc.hasNextLine()) {
            String[] connect = sc.nextLine().split(" ");
            grph.addEdge(Integer.parseInt(connect[0]), Integer.parseInt(connect[1]));
        }

        switch (type) {
            case "List":
            try {
                grph.listdisplay(vertex, edges, keys);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                grph.matrixdisplay(vertex, edges);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            default:
            break;
        }
    }
}
