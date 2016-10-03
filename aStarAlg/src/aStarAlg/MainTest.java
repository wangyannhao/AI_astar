package aStarAlg;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new Map();
		map.Read_map("/Users/admin/Desktop/AI/Output_map(submitted).txt");
		//map.generateMap();
		
		aStarSearch search = new aStarSearch(map, new Point(map.Start.x, map.Start.y),new Point(map.Goal.x, map.Goal.y));
		search.findPath();
		System.out.println(map.Start.x +" "+ map.Start.y);
		//map.Produce_map("D:/Github/Intro2AI/map2.txt");
	}

}
