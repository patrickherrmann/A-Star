import java.awt.Point;

public class MoveableDirections {

   public static final Point[] QUEEN = {
      new Point(-1, 0), 
      new Point(-1, 1), 
      new Point(0, 1), 
      new Point(1, 1), 
      new Point(1, 0), 
      new Point(1, -1), 
      new Point(0, -1), 
      new Point(-1, -1)
   };
   
   public static final Point[] ROOK = {
      new Point(-1, 0), 
      new Point(0, 1), 
      new Point(1, 0), 
      new Point(0, -1), 
   };
   
   public static final Point[] BISHOP = { 
      new Point(-1, 1), 
      new Point(1, 1),  
      new Point(1, -1), 
      new Point(-1, -1)
   };
   
   public static final Point[] KNIGHT = {
      new Point(-2, 1), 
      new Point(-2, -1), 
      new Point(-1, 2), 
      new Point(1, 2), 
      new Point(2, 1), 
      new Point(2, -1), 
      new Point(1, -2), 
      new Point(-1, -2)
   };
   
}
