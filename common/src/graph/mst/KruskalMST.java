package graph.mst;

import disjointset.DisjointSet;
import graph.common.Edge;
import graph.common.Graph;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {

    public static List<Edge> runMST(final Graph graph){

        Queue<Edge> edges = new PriorityQueue<>();
        edges.addAll(graph.E());


        DisjointSet disjointSet = new DisjointSet();

        graph.V().forEach(v -> disjointSet.makeSet(v));

        List<Edge> mst = new ArrayList<>();

        edges.forEach(
            currentEdge -> {

            Integer from = currentEdge.from();
            Integer to = currentEdge.to();

            Integer idFrom = disjointSet.findSet(from);
            Integer idTo = disjointSet.findSet(to);

            if (idFrom != idTo){
                mst.add(currentEdge);
                disjointSet.union(idFrom, idTo);
            }
        });

        return mst;
    }

    public static void main(String[] args){
        final int[] g1Def = {1, 2, 5, 2, 3, 3, 1, 3, 1};
        final Graph g1 = new Graph(g1Def);
        g1.print();
        List<Edge> mst1 = runMST(g1);
        System.out.println(mst1.toString());
        final int[] g2Def = {1, 2, 5};
        final Graph g2 = new Graph(g2Def);
        g2.print();
        List<Edge> mst2 = runMST(g2);
        System.out.println(mst2.toString());
        final int[] g3Def = {1, 2, 8, 1, 5, 10, 1, 3, 4, 2, 3, 2, 4, 5, 1, 3, 4, 5};
        final Graph g3 = new Graph(g3Def);
        g3.print();
        List<Edge> mst3 = runMST(g3);
        System.out.println(mst3.toString());
    }

}
