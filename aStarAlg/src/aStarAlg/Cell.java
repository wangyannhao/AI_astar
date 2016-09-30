package aStarAlg;

public class Cell {
    public int coordinateX, coordinateY;
    public char type;
    public double cost;
    public int index;

    public  Cell(int x, int y){
        coordinateX = x;
        coordinateY = y;
        type = '1';
        cost = 1.0;
        index = 0;
    }
    public void setcoordinate(int x, int y){
        coordinateX = x;
        coordinateY = y;
    }

    public void setcelltype(char t){
        type = t;
    }

    public void setcost(float c){
        cost = c;
    }

}
