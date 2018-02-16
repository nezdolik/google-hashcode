package graph;

/**
 * Created by nezdolik on 2018-02-16.
 */

public class Edge implements Comparable<Edge>{
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
