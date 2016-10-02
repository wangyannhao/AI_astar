package aStarAlg;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Screen extends JPanel{
	public Screen ()
	{
		
	}
	public void paint(Graphics g)
	{
		Map hehe = new Map();
		hehe.initiate();
		hehe.hardCellgenerate();
		hehe.generateHighway();
		
		for (int i = 0;i<160;i++)
    	{
    		for (int j = 0;j<120;j++)
    		{
    			if (Map.cell[i][j].type == '1')
    			{
    				g.setColor(Color.lightGray);
    				g.fillRect(5*i, 5*j, 5, 5);
    			}
    			else if (Map.cell[i][j].type == '2')
    			{
    				g.setColor(Color.gray);
    				g.fillRect(5*i, 5*j, 5,5);
    			}
    			else if (Map.cell[i][j].type == 'a')
    			{
    				g.setColor(Color.green);
    				g.fillRect(5*i, 5*j, 5,5);

    			}
    			else if (Map.cell[i][j].type == 'b')
    			{
    				g.setColor(Color.blue);
    				g.fillRect(5*i, 5*j, 5,5);
    				System.out.println("yes");
    			}
    			
    		}
    	}
		
		
	}
}
