package aStarAlg;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;

public class Screen extends JPanel{
	public Screen ()
	{
		
	}
	public void paint(Graphics g)
	{
		Map hehe = new Map();
		hehe.Read_map("/Users/admin/Desktop/AI/Output_map(submitted).txt");
		hehe.Produce_map("test.txt");
		//hehe.generateMap();

		//hehe.Produce_map();
		Random random = new Random();
		int ran = random.nextInt(130);
		for (int i = 0;i<160;i++)
    	{
    		for (int j = 0;j<120;j++)
    		{
    			if (hehe.cell[i][j].type == '1')
    			{
    				g.setColor(Color.lightGray);
    				g.drawRect(5*i, 5*j, 5, 5);
    			}
    			else if (hehe.cell[i][j].type == '2')
    			{
    				g.setColor(Color.gray);
    				g.fillRect(5*i, 5*j, 5,5);
    			}
    			else if (hehe.cell[i][j].type == 'a')
    			{
    				g.setColor(new Color(72,170,237));
    				g.fillRect(5*i, 5*j, 5,5);

    			}
    			else if (hehe.cell[i][j].type == 'b')
    			{
    				g.setColor(new Color(53,80,159));
    				g.fillRect(5*i, 5*j, 5,5);
    				
    			}
    			else if (hehe.cell[i][j].type == '0')
    			{
    				g.setColor(Color.black);
    				g.fillRect(5*i, 5*j, 5,5);
    				
    			}
    			
    		}
    	}
		
		
	}
}
