import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class RectGraph extends GridGraph {

   private Point[] moveableDirections = MoveableDirections.QUEEN;
   
   public RectGraph(boolean isDynamicEdges, int rows, int cols) {
      super(isDynamicEdges, rows, cols);
   }
   
   public Point[] getMoveableDirections() {
      return moveableDirections;
   }
   
   public void setMoveableDirections(Point[] moveableDirections) {
      this.moveableDirections = moveableDirections;
   }
   
   public int getMovementCost(Point p) {
      if (p.x == 0 || p.y == 0) return 10;
      return 14;
   }
   
   public void updateEdges(PathNode pathNode) {
      GridNode node = (GridNode) pathNode;
      node.getPathEdges().clear();
      if (node.getTerrain() == Terrain.BLOCKED) return;
      for (Point dir : moveableDirections) {
         Point adjPnt = new Point(node.getRow() + dir.x, node.getCol() + dir.y);
         if (isInBounds(adjPnt.x, adjPnt.y)) {
            GridNode gnode = getGridNode(adjPnt.x, adjPnt.y);
            if (gnode.getTerrain() != Terrain.BLOCKED) {
               node.getPathEdges().add(new PathEdge(gnode, getMovementCost(dir)));
            }
         }
      }
   }
   
   public void print(List<PathNode> path) {
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            if (nodes[r][c].getTerrain() == Terrain.BLOCKED) {
               System.out.print("#");
            } else if (path.contains(nodes[r][c])) {
               System.out.print("*");
            } else {
               System.out.print(".");
            }
            System.out.print(" ");
         }
         System.out.println();
      }
   }
}
