package graph.common;

import graph.common.Edge;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nezdolik on 2018-02-16.
 */
public class Graph {

    private Map<Integer,List<Edge>> graph;

    public Graph(final int[] graphDef){
        this.graph = new HashMap<>();
        initGraph(graphDef);
    }

    public boolean containsVertex(Integer vertex){
        return graph.containsKey(vertex);
    }

    public List<Edge> getEdges(Integer vertex){
        return graph.get(vertex);
    }

    public int N(){
        return graph.size();
    }

    public Collection<Edge> E(){
        return graph.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public Collection<Integer> V(){
        return graph.keySet();
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
