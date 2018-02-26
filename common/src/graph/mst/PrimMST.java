package graph.mst;

import graph.common.Edge;
import graph.common.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class PrimMST {

    public static List<Edge> runMST(Graph graph, Integer source){
      if (!graph.containsVertex(source)){
          throw new IllegalArgumentException("Vertex not in the graph!");
      }
      Set<Integer> visited = new HashSet<>();
      Queue<Edge> toProcess = new PriorityQueue<>();
      List<Edge> mst = new ArrayList<>();
      visited.add(source);
      toProcess.addAll(graph.getEdges(source));
      while (visited.size() < graph.N()){
            final Edge minEdge = toProcess.poll();
            mst.add(minEdge);
            for (Edge e : graph.getEdges(minEdge.to())){
                if (e.to() != minEdge.from()){
                    toProcess.add(e);
                }
            }
            visited.add(minEdge.to());
      }

      return mst;

    }

    public static void main(String[] args){
        final int[] g1Def = {1, 2, 5, 2, 3, 3, 1, 3, 1};
        final Graph g1 = new Graph(g1Def);
        g1.print();
        List<Edge> mst1 = runMST(g1, 1);
        System.out.println(mst1.toString());
        final int[] g2Def = {1, 2, 5};
        final Graph g2 = new Graph(g2Def);
        g2.print();
        List<Edge> mst2 = runMST(g2, 1);
        System.out.println(mst2.toString());
        final int[] g3Def = {1, 2, 8, 1, 5, 10, 1, 3, 4, 2, 3, 2, 4, 5, 1, 3, 4, 5};
        final Graph g3 = new Graph(g3Def);
        g3.print();
        List<Edge> mst3 = runMST(g3, 1);
        System.out.println(mst3.toString());
    }

}
