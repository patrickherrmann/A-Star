import java.util.ArrayList;
import java.util.List;

public class PathNotFoundException extends Exception {
   
   private List<PathNode> closestPath = new ArrayList<PathNode>();
   
   public PathNotFoundException() {
      super();
   }
   
   public PathNotFoundException(List<PathNode> closestPath) {
      super();
      this.closestPath = closestPath;
   }
   
   public List<PathNode> getClosestPath() {
      return closestPath;
   }
}
