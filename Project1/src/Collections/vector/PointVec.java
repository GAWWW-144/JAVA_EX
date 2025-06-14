package Collections.vector;

import java.awt.Point;
import java.util.Vector;


public class PointVec extends VectorEx2 {

	public static void main(String[] args) {
		
		Vector<Point> PointVec = new Vector<Point>();
		PointVec.add(new Point(10, 20));
		PointVec.add(new Point(20, 30));
		PointVec.add(new Point(30, 40));
		
		double sumX = 0.0;
		for (Point  point : PointVec) {
			sumX += point.getX();
		}
		
		for (Point str : PointVec) {
			Vector<Integer> intVec1 = new Vector<Integer>();
		}
		

	}

}
