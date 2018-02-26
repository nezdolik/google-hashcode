package graph.common;

/**
 * Created by nezdolik on 2018-02-16.
 */

public final class Edge implements Comparable<Edge>{
    private final Integer from;
    private final Integer to;
    private final int weight;

    public Edge(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String toString(){
        return "(" + from() + "-->" + to() + ", " + weight() + ")";
    }

    public int compareTo(Edge other){
        if (this.weight() == other.weight()){
            return 0;
        }

        if (this.weight() < other.weight()){
            return -1;
        }

        return 1;
    }

    public Integer from() {
        return from;
    }

    public Integer to() {
        return to;
    }


    public int weight() {
        return weight;
    }
}
