import java.util.List;

public class Driver {
   
   public static void main(String[] args) {
      RectGraph rg = new RectGraph(true, 20, 40);
      
      for (int i = 0; i < 18; i++) {
         for (int j = 2; j < 40; j += 3) {
            if (j % 2 == 0) {
               rg.placeObstacle(i, j);
            } else {
               rg.placeObstacle(19 - i, j);
            }
         }
      }
      
      rg.print();
      
      PathNode start, finish;
      start = rg.getGridNode(0, 39);
      finish = rg.getGridNode(0, 0);
      
      try {
         List<PathNode> path = rg.findBestPath(start, finish);
         System.out.println("Path found!");
         rg.print(path);
      } catch (PathNotFoundException e) {
         System.out.println("No path to target. Closest path:");
         rg.print(e.getClosestPath());
      }
   }
}
