import UTIL.QueueLinkedList;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.UF;


public class KruskalMST {
    private final QueueLinkedList<Edge> mst = new QueueLinkedList<>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges())
            pq.insert(e);

        UF uf = new UF(G.V());
        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges() {
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

        KruskalMST mst = new KruskalMST(graph);
        mst.printWeights();
    }
}
