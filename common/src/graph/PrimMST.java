package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class PrimMST{
    private Map<Integer,List<Edge>> graph;

    public PrimMST(final int[] graphDef){
        this.graph = new HashMap<>();
        initGraph(graphDef);
    }

    private void initGraph(final int[] graphDef){
        int ptr = 0;
        while (ptr+2 < graphDef.length){
            addEdge(graphDef[ptr], graphDef[ptr+1], graphDef[ptr+2]);
            addEdge(graphDef[ptr+1], graphDef[ptr], graphDef[ptr+2]);
            ptr+=3;
        }
    }

    private void addEdge(int from, int to, int weight){
        if (!graph.containsKey(from)){
            graph.put(from, new ArrayList<>());
        }
        graph.get(from).add(new Edge(from, to, weight));
    }

    public List<Edge> runMST(Integer source){
      if (!graph.containsKey(source)){
          throw new IllegalArgumentException("Vertex not in the graph!");
      }
      Set<Integer> visited = new HashSet<>();
      Queue<Edge> toProcess = new PriorityQueue<>();
      List<Edge> mst = new ArrayList<>();
      visited.add(source);
      toProcess.addAll(graph.get(source));
      while (visited.size() < graph.size()){
            final Edge minEdge = toProcess.poll();
            mst.add(minEdge);
            for (Edge e : graph.get(minEdge.to)){
                if (e.to != minEdge.from){
                    toProcess.add(e);
                }
            }
            visited.add(minEdge.to);
      }

      return mst;

    }

    public static void main(String[] args){
        final int[] g1Def = {1, 2, 5, 2, 3, 3, 1, 3, 1};
        final PrimMST g1 = new PrimMST(g1Def);
        g1.print();
        List<Edge> mst1 = g1.runMST(1);
        System.out.println(mst1.toString());
        final int[] g2Def = {1, 2, 5};
        final PrimMST g2 = new PrimMST(g2Def);
        g2.print();
        List<Edge> mst2 = g2.runMST(1);
        System.out.println(mst2.toString());
        final int[] g3Def = {1, 2, 8, 1, 5, 10, 1, 3, 4, 2, 3, 2, 4, 5, 1, 3, 4, 5};
        final PrimMST g3 = new PrimMST(g3Def);
        g3.print();
        List<Edge> mst3 = g3.runMST(1);
        System.out.println(mst3.toString());
    }

    private class Edge implements Comparable<Edge>{
        Integer from;
        Integer to;
        int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

          public String toString(){
              return "(" + from + "-->" + to + ", " + weight + ")";
          }

          public int compareTo(Edge other){
            if (this.weight == other.weight){
              return 0;
            }

            if (this.weight < other.weight){
              return -1;
            }

            return 1;
          }
    }

    public void print(){
      for (Integer v : graph.keySet()){
          System.out.print(v + ": ");
          for (Edge e : graph.get(v)){
              System.out.print(e + ", ");
          }
          System.out.println();
      }
    }
}
