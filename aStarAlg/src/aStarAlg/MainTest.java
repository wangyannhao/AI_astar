package aStarAlg;

import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new Map();
		map.Read_map("test.txt");
		//map.generateMap();
		
		aStarSearch search = new aStarSearch(map, map.Start, map.Goal);
		List<Cell> path = search.findPath();
		
		//System.out.println(map.Start.x +" "+ map.Start.y);
		//map.Produce_map("D:/Github/Intro2AI/map2.txt");
	}

}
