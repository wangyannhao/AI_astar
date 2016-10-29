
package aStarAlg;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int coordinateX, coordinateY;
    public char type;
    public double fCost;
    public int index;
    public Cell parent;
    public double gCost;
    public double hCost;
    public boolean expanded = false;
    public List<Cell> children = new ArrayList<Cell>();
    public List<Cell> bp = new ArrayList<Cell>();
    public  List<Double> gCostList =new ArrayList<Double>(5);
    public List<Double> hCostList =new ArrayList<Double>(5);
    public List<Double> Key=new ArrayList<Double>(5);
    public double key[] = new double[5];
    
    public Cell(){
    	
    }
    public  Cell(int x, int y){
        coordinateX = x;
        coordinateY = y;
        type = '1';
        index = 0;
        for (int i =0;i<5;i++){
        	gCostList.add((double) 1000000);
        	hCostList.add((double) 1000000);
        	Key.add((double) 1000000);
        	bp.add(new Cell());
        }
    }
    public void setcoordinate(int x, int y){
        coordinateX = x;
        coordinateY = y;
    }

    public void setcelltype(char t) {
        type = t;
    }
    

    
    public int getx() {
    	return coordinateX;
    }
    
    public int gety(){
    	return coordinateY;
    }
    
    public void setgCost(int i,double gCost){
    	gCostList.set(i,gCost);

    	
    }
    
    public double getgCost(int i){
    	return gCostList.get(i);
    }
    
    public void sethCost(int i , double hCost){
    	hCostList.set(i,hCost);
    }
    
    public double gethCost(int i){
    	return hCostList.get(i);
    }
    public void setKey(int i,double value){
    	Key.set(i, value);
    }
    public Double getKey(int i){
    	return Key.get(i);
    }
}
