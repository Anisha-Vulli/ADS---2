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
        if (edge == 0) {
            System.out.println(edge);
        } else {
            Graph grp = new Graph(vertex + 1);
        while (sc.hasNextLine()) {
            String[] nodes = sc.nextLine().split(" ");
            grp.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
        }
        
        //System.out.println(grp);
        CC ccobj = new CC(grp);
        int[] idarray = ccobj.idarr();
        //System.out.println(Arrays.toString(idarray));
        int count = 0;
        int finalcnt = 0;
        int id = 0;
        for (int i = 0; i < grp.vertex(); i++) {
            if (grp.hasParallelEdges(i)) {
                count++;
            }
            int idcnt = 0;
            id = idarray[i];
            //System.out.println(id);
            for (int j = 0; j < idarray.length; j++) {
                if (id == idarray[j]) {
                    idcnt++;
                }
            }
            if (finalcnt < idcnt) {
                finalcnt = idcnt;   
            }
        }
        //int finalcnt = 0;
        // System.out.println(count);
        //System.out.println(finalcnt);
        System.out.println(count + finalcnt);

        }
        
    }
}