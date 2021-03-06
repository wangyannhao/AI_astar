
package aStarAlg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class PathFinding {
	public Map map;
	public Point Start;
	public Point Goal;
	public String type;
	public double weight;
	//******* need to change ********
	private Comparator<Cell> cellSorter = new Comparator<Cell>(){
		public int compare(Cell arg0, Cell arg1){
			if(arg1.fCost < arg0.fCost) return +1;
			if(arg1.fCost> arg0.fCost) return -1;
			return 0;
		}
	}; 

	private boolean pointInList(List<Cell> list, Point p){
		for (Cell n : list) {
			if(n.getx() == p.x && n.gety() == p.y) return true;
		}
		return false;
	}
	public PathFinding(Map m, Point s, Point g){

		map = m;
		Start = s;
		Goal = g;
	}
	public PathFinding(Map m, Point s, Point g,double w){

		map = m;
		Start = s;
		Goal = g;
		weight = w;
	}

	public List<Cell> findPath(){

		Binary_heap openList  = new Binary_heap(8);
		List<Cell> closedList = new ArrayList<Cell>();

		Cell current = new Cell(Start.x, Start.y);
		openList.insert(current);

		while(openList.position > 1 ){
			//Collections.sort(openList, cellSorter);
			current = openList.extractMin(); // get the cell with the smallest f(n)
			if (current.getx()==Goal.x && current.gety()==Goal.y){
				List<Cell> path = new ArrayList<Cell>();
				while(current.parent !=null){
					path.add(current);
					current = current.parent;
				}
				System.out.println("Path Found!!!");
				System.out.println("Path length = "+path.size());
				System.out.println("Path cost = "+ path.get(0).gCost);
				//openList.clear();
				closedList.clear();

				return path;

			}   
			//openList.remove(current);
			closedList.add(current);

			for (int i = 0;i<9;i++){
				if (i ==4 ) continue;
				int x = current.getx();
				int y = current.gety();

				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Point at = new Point(x + xi, y+yi);
				if(x+xi<0 || x+xi>159 || y+yi<0 || y+yi > 119) 
				{
					//System.out.println("at bound");
					continue;// this part check if this location is valid;
				}
				double gCost = current.gCost + getgCost(current, at);
				
				//double hCost = gethCost(at,Goal);
				Cell cellat = new Cell(x + xi , y + yi);
				cellat.parent = current;
				insertCost(cellat,gCost); 
				if (pointInList(closedList, at) && cellat.gCost >= current.gCost) continue;
				//System.out.println("function used");
				//if (pointInList(closedList, at) && cellat.gCost >= current.gCost) continue;
				if (!openList.find(at)) openList.insert(cellat);
				//if (!pointInList(openList,at) || cellat.gCost < current.gCost) openList.add(cellat);
			}
		}
		return null;
	}


	private double getgCost(Cell x, Point y) {
		// TODO Auto-generated method stub

		//=====================================================move diagonally=================================================
		if (Math.abs(x.getx()-y.x)==1 && Math.abs(x.gety()-y.y)==1){
			//*******from 2 to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='2') return Math.sqrt(8);
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='1') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='a') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='b') return Math.sqrt(8);
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='0') return 5000;
			//*******from 1 to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='2') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='1') return Math.sqrt(2);
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='a') return Math.sqrt(2);
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='b') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='0') return 10000;
			//*******from b to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='2') return Math.sqrt(8);
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='1') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='a') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='b') return Math.sqrt(8);
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='0') return 20000;
			//*******from a to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='2') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='1') return Math.sqrt(2);
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='a') return Math.sqrt(2);
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='b') return Math.sqrt(2)/2+Math.sqrt(8)/2;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='0') return 30000;		
			//*******from 0 to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='2') return 2333;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='1') return 2333;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='a') return 2333;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='b') return 2333;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='0') return 2333;		
		}else{
			//==========================================move horizontally or vertically=================================================
			//*******from 2 to others*********
			//// travel between hard to traverse map.cells
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='2') return 2;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='1') return 1.5;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='a') return 1.5;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='b') return 2;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='2'&& map.cell[y.x][y.y].type=='0') return 40000;
			//*******from 1 to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='2') return 1.5;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='1') return 1;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='a') return 1;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='b') return 1.5;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='1'&& map.cell[y.x][y.y].type=='0') {
				//System.out.println(x.getx() + "," + x.gety());
				return 50000;
			}
			//*******from b to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='2') return 2;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='1') return 1.5;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='a') return 0.325;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='b') return 0.5;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='b'&& map.cell[y.x][y.y].type=='0') return 60000;
			//*******from a to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='2') return 1.5;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='1') return 1;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='a') return 0.25;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='b') return 0.325;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='a'&& map.cell[y.x][y.y].type=='0') return 70000;	
			//*******from 0 to others*********
			//// travel between hard to traverse cells
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='2') return 2333;
			//// travel from htt to unblocked
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='1') return 2333;
			//// travel from htt to unblocked highway
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='a') return 2333;
			//// travel from htt to htt highway
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='b') return 2333;
			//// travel from htt to blocked
			if(map.cell[x.getx()][x.gety()].type=='0'&& map.cell[y.x][y.y].type=='0') return 2333;	
		}
		return 666;
	}

	public double gethCost(Point x, Point Goal){
		double dx = x.x-Goal.x;
		double dy = x.y-Goal.y;
		return Math.sqrt(dx*dx + dy*dy);
	}

	public abstract void insertCost(Cell cellat, double gCost);
}
