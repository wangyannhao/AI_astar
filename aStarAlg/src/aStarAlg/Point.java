package aStarAlg;

public class Point {
	public int x,y,Dir;

	public Point(int colum, int row,int dir){
		x = colum;
		y = row;
		Dir = dir;
	}
	public Point(int colum, int row){
		x = colum;
		y = row;
	}

//	public boolean equals(Object object) {
//		if (!(object instanceof Point)) return false;
//		Point p = (Point) object;
//		if (p.x == this.x && p.y == this.y) return true;
//		return false;
//	}
}
