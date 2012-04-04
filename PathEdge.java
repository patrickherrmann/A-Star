public class PathEdge implements Comparable<PathEdge> {

   private PathNode node;
   private int cost;
   
   public PathEdge(PathNode node, int cost) {
      this.node = node;
      this.cost = cost;
   }
   
   public PathNode getPathNode() {
      return node;
   }
   
   public int getCost() {
      return cost;
   }
   
   public int compareTo(PathEdge pathEdge) {
      if (cost < pathEdge.cost) return -1;
      if (cost > pathEdge.cost) return 1;
      return 0;
   }
}
