import UTIL.MyBag;

public class EdgeWeightedGraph {
   private final int V;
   private final MyBag<Edge>[] adj;

   public EdgeWeightedGraph(int V) {
      this.V = V;
      adj = (MyBag<Edge>[]) new MyBag[V];
      for (int v = 0; v < V; v++) {
         adj[v] = new MyBag<Edge>();
      }
   }

   public void addEdge(Edge e) {
      int v = e.either(), w = e.other(v);
      adj[v].add(e);
      adj[w].add(e);
   }

   public Iterable<Edge> adj(int v) {
      return adj[v];
   }

   public Iterable<Edge> edges() {
      MyBag<Edge> list = new MyBag<>();
      for (int v = 0; v < V; v++) {
         int selfloops = 0;
         for (Edge e : adj(v)) {
            if (e.other(v) > v) {
               list.add(e);
            }else if (e.other(v) == v) {
               if (selfloops % 2 == 0) list.add(e);
               selfloops++;
            }
         }
      }
      return list;
   }

   public int V() {
      return V;
   }

}
