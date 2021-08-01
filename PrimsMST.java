import UTIL.QueueLinkedList;
import edu.princeton.cs.algs4.MinPQ;

public class PrimsMST {
    private boolean[] marked;
    private final QueueLinkedList<Edge> mst;
    private MinPQ<Edge> pq;

    public PrimsMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        mst = new QueueLinkedList<>();
        marked = new boolean[G.V()];
        visit(G,1);

        while(!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Iterable<Edge> mst() {
       return mst;
    }

    //Print weights
    public void printWeights() {
        for (Edge edge : mst) {
            System.out.print(edge.weight + "; ");
        }
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(10);
        Edge a = new Edge(1,2,1.7);
        Edge b = new Edge(1,3,1.6);
        Edge c = new Edge(2,3,2.3);
        Edge d = new Edge(3,4,1.0);
        Edge e = new Edge(4,5,1.8);
        Edge f = new Edge(2,5,1.9);
        Edge g = new Edge(4,6,1.2);
        Edge m = new Edge(3,6,1.5);
        Edge n = new Edge(5,7,1.3);
        Edge o = new Edge(5,9,3);
        Edge p = new Edge(6,9,2.0);
        Edge r = new Edge(9,8,1.4);
        Edge s = new Edge(7,8,2.2);

        graph.addEdge(a);
        graph.addEdge(b);
        graph.addEdge(c);
        graph.addEdge(d);
        graph.addEdge(e);
        graph.addEdge(f);
        graph.addEdge(g);
        graph.addEdge(m);
        graph.addEdge(n);
        graph.addEdge(o);
        graph.addEdge(p);
        graph.addEdge(r);
        graph.addEdge(s);

        PrimsMST mst = new PrimsMST(graph);
        mst.printWeights();
    }
}
