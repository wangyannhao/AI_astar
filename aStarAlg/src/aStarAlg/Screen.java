
package aStarAlg;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.List;



public class Screen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Screen ()
	{
		
	}
	public void paint(Graphics g)
	{

		Map map = new Map();
		map.Read_map("test.txt");
		//map.generateMap();
		uniformCostSearch search = new uniformCostSearch(map, map.Goal,map.Start );
		List<Cell> path = search.findPath();
		for (int i = 0;i<160;i++)
    	{
    		for (int j = 0;j<120;j++)
    		{
    			if (map.cell[i][j].type == '1')
    			{
    				g.setColor(Color.lightGray);
    				g.drawRect(5*i, 5*j, 5, 5);
    			}
    			else if (map.cell[i][j].type == '2')
    			{
    				g.setColor(Color.gray);
    				g.fillRect(5*i, 5*j, 5,5);
    			}
    			else if (map.cell[i][j].type == 'a')
    			{
    				g.setColor(new Color(72,170,237));
    				g.fillRect(5*i, 5*j, 5,5);

    			}
    			else if (map.cell[i][j].type == 'b')
    			{
    				g.setColor(new Color(53,80,159));
    				g.fillRect(5*i, 5*j, 5,5);
    				
    			}
    			else if (map.cell[i][j].type == '0')
    			{
    				g.setColor(Color.black);
    				g.fillRect(5*i, 5*j, 5,5);
    			}
    		}
    	}

		for (int i = 0; i< path.size();i++){
			g.setColor(Color.red);
			g.fillRect(5*path.get(i).coordinateX, 5*path.get(i).coordinateY, 5,5);
		}
		g.setColor(Color.green);
		g.fillRect(90*5, 103*5, 5, 5);
	}
}
