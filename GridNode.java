public class GridNode extends PathNode {

   private int row, col;
   private Terrain terrain;
   
   public GridNode(int row, int col) {
      super();
      this.row = row;
      this.col = col;
   }
   
   public void updateHCost(PathNode finish) {
      GridNode fin = (GridNode) finish;
      hCost = 10 * (Math.abs(fin.row - row) + Math.abs(fin.col - col));
   }
   
   public int getRow() {
      return row;
   }
   
   public int getCol() {
      return col;
   }
   
   public Terrain getTerrain() {
      return terrain;
   }
   
   public void setTerrain(Terrain terrain) {
      this.terrain = terrain;
   }
   
   public String toString() {
      return "(" + row + ", " + col + ")";
   }
}
