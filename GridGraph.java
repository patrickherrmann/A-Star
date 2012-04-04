import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class GridGraph extends PathGraph {

   protected GridNode[][] nodes;
   protected int rows, cols;
   
   protected Point[] moveableDirections;
   
   public GridGraph(boolean isDynamicEdges, int rows, int cols) {
      super(isDynamicEdges);
      this.rows = rows;
      this.cols = cols;
      initializeNodes();
   }
   
   private void initializeNodes() {
      nodes = new GridNode[rows][cols];
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            nodes[r][c] = new GridNode(r, c);
         }
      }
      
      if (!isDynamicEdges()) {
         updateEdges();
      }
   }
   
   public boolean containsPathNode(PathNode pathNode) {
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            if (nodes[r][c] == pathNode) return true;
         }
      }
      return false;
   }
   
   public GridNode getGridNode(int row, int column) {
      return nodes[row][column];
   }
   
   public boolean isInBounds(int row, int col) {
      return (row >= 0 && row < rows && col >= 0 && col < cols);
   }
   
   public void placeObstacle(int row, int col) {
      getGridNode(row, col).setTerrain(Terrain.BLOCKED);
   }
   
   public abstract int getMovementCost(Point p);
   
   public abstract void updateEdges(PathNode pathNode);
   
   public void updateEdges() {
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols; c++) {
            updateEdges(nodes[r][c]);
         }
      }
   }
   
   public void print() {
      print(new ArrayList<PathNode>());
   }
   
   public abstract void print(List<PathNode> path);
   
}
