import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public abstract class PathGraph {

   /* Dictates if the whole graph will be processed initially (false), 
   or if each node will be processed as it goes, adjusting to changes. */
   private boolean isDynamicEdges;
   
   /* From 0 to 1, 0 being guarantee of best path, but slower (Dijkstra's algorithm),
   and 1 being a best first search with the fastest run time. */
   private double heuristicWeight = 0.5;
   
   public PathGraph(boolean isDynamicEdges) {
      this.isDynamicEdges = isDynamicEdges;
   }
   
   public boolean isDynamicEdges() {
      return isDynamicEdges;
   }
   
   public double getHeuristicWeight() {
      return heuristicWeight;
   }
   
   public void setHeuristicWeight(double heuristicWeight) {
      if (heuristicWeight < 0) {
         heuristicWeight = 0;
      } else if (heuristicWeight > 1) {
         heuristicWeight = 1;
      }
      this.heuristicWeight = heuristicWeight;
   }
   
   public abstract void updateEdges(PathNode pathNode);
   
   public abstract boolean containsPathNode(PathNode pathNode);
   
   public List<PathNode> findBestPath(PathNode start, PathNode finish) throws PathNotFoundException {
      if (start == null || finish == null) throw new PathNotFoundException();
      
      if (!containsPathNode(start) || !containsPathNode(finish)) throw new PathNotFoundException();
      
      List<PathNode> open = new LinkedList<PathNode>();
      List<PathNode> closed = new LinkedList<PathNode>();
      
      PathNode current;
      
      open.add(start);
      
      while (!closed.contains(finish)) {
         if (open.isEmpty()) {
            if (closed.size() < 2) throw new PathNotFoundException();
            Collections.sort(closed, PathNode.H_COST_ORDER);
            throw new PathNotFoundException(tracePath(start, closed.get(1)));
         }
         
         Collections.sort(open);
         closed.add(current = open.remove(0));
         
         if (isDynamicEdges()) {
            updateEdges(current);
         }
         
         for (PathEdge edge : current.getPathEdges()) {
            PathNode node = edge.getPathNode();
            if (!closed.contains(node)) {
               if (open.contains(node)) {
                  if (current.getGCost() + edge.getCost() < node.getGCost()) {
                     node.setParent(new PathEdge(current, edge.getCost()));
                     node.updateFCost(start, finish, heuristicWeight);
                  }
               } else {
                  open.add(node);
                  node.setParent(new PathEdge(current, edge.getCost()));
                  node.updateFCost(start, finish, heuristicWeight);
               }
            }
         }
      }
      
      return tracePath(start, finish);
   }
   
   private List<PathNode> tracePath(PathNode start, PathNode finish) {
      List<PathNode> path = new ArrayList<PathNode>();
      PathNode current = finish;
      
      while (current != start) {
         path.add(current);
         current = current.getParent().getPathNode();
      }
      
      path.add(current);
      Collections.reverse(path);
      
      return path;
   }
}
