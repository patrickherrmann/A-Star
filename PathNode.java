import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class PathNode implements Comparable<PathNode> {
   
   private List<PathEdge> edges = new ArrayList<PathEdge>();
   private PathEdge parent;
   
   protected int hCost = 0; // Heuristic
   protected int gCost = 0; // Movement cost
   protected int fCost = 0; // Pass through cost
   
   public static final Comparator<PathNode> H_COST_ORDER = new Comparator<PathNode>() {
      public int compare(PathNode p1, PathNode p2) {
         if (p1.hCost < p2.hCost) return -1;
         if (p1.hCost > p2.hCost) return 1;
         return 0;
      }
   };
   
   public abstract void updateHCost(PathNode finish);
   
   public void updateGCost(PathNode start) {
      if (this == start) {
         gCost = 0;
         return;
      }
      if (getParent() == null) return;
      gCost = getParent().getPathNode().getGCost() + getParent().getCost();
   }
   
   public void updateFCost(PathNode start, PathNode finish, double heuristicWeight) {
      updateHCost(finish);
      updateGCost(start);
      fCost = (int)(heuristicWeight * hCost + (1 - heuristicWeight) * gCost);
   }
   
   public List<PathEdge> getPathEdges() {
      return edges;
   }
   
   public int getHCost() {
      return hCost;
   }
   
   public int getGCost() {
      return gCost;
   }
   
   public int getFCost() {
      return fCost;
   }
   
   public PathEdge getParent() {
      return parent;
   }
   
   public void setParent(PathEdge parent) {
      this.parent = parent;
   }
   
   public int compareTo(PathNode pathNode) {
      if (fCost < pathNode.fCost) return -1;
      else if (pathNode.fCost < fCost) return 1;
      return 0;
   }
}
