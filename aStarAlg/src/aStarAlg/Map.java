package aStarAlg;
import java.util.Random;
import java.util.*;

public class Map {
	
	public Map()
	{

				
		
	}
	
	public static Cell[][] cell ;

    
    public void hardCellgenerate()
    {
        int[] x = new int[8];
        int[] y = new int[8];
        /* set the boundaries for x,y generated */
        int x_min = 15;
        int y_min = 15;
        int x_max = 105;
        int y_max = 145;
        /* generate 8 random coordinate */
        for(int i = 0; i < 8; i++ ){
            x[i] = x_min + (int) (Math.random() * (x_max - x_min) + 1);
            y[i] = y_min + (int) (Math.random() * (y_max - y_min) + 1);
        }
        /*  generate hard traverse cells */
        for(int i = 0; i < 8; i++)
        {
            int x_start = x[i] - 15;
            int x_end = x[i] + 15;
            int y_start = y[i] - 15;
            int y_end = y[i] + 15;
            for(int j = y_start; j < y_end; j++)
            {
                for(int k = x_start; k < x_end; k++)
                {
                    if(Math.random() < 0.5)
                    {
                        cell[j][k].type = '2';
                        cell[j][k].cost = 2.0;
                    }
                }
            }
        }
    }
    
    public boolean generateHighway()
    {	
    	boolean a = random(0.5f,0.5f);
    	boolean b = random(0.5f,0.5f);
    	//boolean a = true ;
    	//boolean b = true;
		int maxDistance = 0;
		int distanceToBound = 0; 
    	List<Point> highway = new ArrayList<Point>();

    	//*********************************************** UP BOUNDARY ********************************************
    	if (a&&b)
    	{
    		//up boundary
        	
    		int startPosition = (int) Math.ceil(Math.random()*160);
    		Point Position = new Point(startPosition,0,3);
    		highway.add(Position);
    		int x = startPosition;
    		int y = 0;
    		//first 20 cells, random moves without moving back
    		int i = 0;
    		while (highway.get(highway.size()-1).x!=0 && highway.get((highway.size()-1)).x!=159&&highway.get((highway.size()-1)).y !=119 && i<20 )
    		{
    			int dir = chooseDir(highway.get(highway.size()-1).Dir);
    			//int dir=3;
    			if (dir==1)
    			{
    				x = x + 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if (dir == 2)
    			{
    				x = x-1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if(dir == 3)
    			{
    				y = y +1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if (dir == 4)
    			{
    				i = i-1;
    			}
    			i = i+1;
    		}

    		if (maxDistance<20)
    		{
    			return false;
    		}
    		
    		// 60% 20% way of choosing direction
    		while(highway.get((highway.size()-1)).x!=0 && highway.get((highway.size()-1)).x!=159 && highway.get((highway.size()-1)).y!=0&&highway.get((highway.size()-1)).y !=119)
    		{
    			
    			int dir = chooseDir2(highway.get((highway.size()-1)).Dir);
    			if (dir==1)
    			{
    				x = x + 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    					System.out.println(123);
    				//	return false;//hit self, have to regenerate;
    					
    				}
    			}else if (dir == 2)
    			{
    				x = x - 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    				//	return false;//hit self, have to regenerate;
    					 
    				}
    			}else if(dir == 3)
    			{
    				y= y + 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				distanceToBound = distanceToBound+1;
    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				
    				{
    				//	return false;
    					 
    				}
    			}else if (dir == 4)
    			{
    				y= y + 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				distanceToBound = distanceToBound+1;
    				if (!ifHitSelf(highway,highway.get((highway.size()-1))))
    				
    				{
    				//	return false;
    					 
    				}
    			}
    			
    			
    			
    		}
    		
    		if (maxDistance <100)
    		{
    			return false;
    		}
    	}else if (a&&!b)
    	{
    		//*********************************************** RIGHT BOUNDARY ********************************************
    		int startPosition = (int) Math.ceil(Math.random()*120);
    		Point Position = new Point(159,startPosition,2);
    		highway.add(Position);
    		int x = 119;
    		int y = startPosition;
    		int i = 0;
    		while (highway.get((highway.size()-1)).x!=0 && highway.get((highway.size()-1)).y!=0&&highway.get((highway.size()-1)).y !=119 && i<20 )
    		{
    			int dir = chooseDir(highway.get(highway.size()-1).Dir);
    			if (dir==1)
    			{
    				i = i-1;
    			}else if (dir == 2)
    			{
    				x = x-1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if(dir == 3)
    			{
    				y=y+1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if (dir == 4)
    			{

    				y=y-1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}
    			i = i+1;
    		}
    		if (maxDistance<20)
    		{
    			return false;
    		}
    		int count = 0;
    		// 60% 20% way of choosing direction
    		while(highway.get(highway.size()-1).x!=0 && highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0&&highway.get(highway.size()-1).y !=119)
    		{
    			int dir = chooseDir2(highway.get((highway.size()-1)).Dir);
    			count=count+1;
    			if (dir==1)
    			{
    				x = x+1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;

    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    					//return false;//hit self, have to regenerate;
    					
    				}
    			}else if (dir == 2)
    			{
    				x = x-1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;

    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    					//return false;//hit self, have to regenerate;
    					 
    				}
    			}else if(dir == 3)
    			{
    				y=y+1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    					//return false;
    					 
    				}
    			}else if (dir == 4)
    			{
    				y=y-1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get((highway.size()-1))))
    				{
    					//return false;
    					 
    				}
    			}
    		}
    		if (maxDistance <100)
    		{
    			//return false;
    		}
    		
    	}else if (!a&&b){
    		//*********************************************** DOWN BOUNDARY ********************************************
    		int startPosition = (int) Math.ceil(Math.random()*160);
    		highway.add(new Point(startPosition,119,4));
    		int x = startPosition;
    		int y = 119;
    		//first 20 cells, random moves without moving back
    		int i = 0;
    		while (highway.get(highway.size()-1).x!=0 && highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0&& i<20 )
    		{
    			int dir = chooseDir(highway.get(highway.size()-1).Dir);
    			if (dir==1)
    			{
    				x = x + 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if (dir == 2)
    			{
    				x = x - 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    			}else if(dir == 3)
    			{
    				i = i-1;
    			}else if (dir == 4)
    			{

    				y = y - 1;
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				distanceToBound = distanceToBound+1;
    			}
    			i =i+1;
    		}
    		if (maxDistance<20)
    		{
    			return false;
    		}
    			// 60% 20% way of choosing direction
        		while(highway.get(highway.size()-1).x!=0 && highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0&&highway.get(highway.size()-1).y !=119)
        		{
        			int dir1 = chooseDir2(highway.get(highway.size()-1).Dir);
        			if (dir1==1)
        			{
        				x = x + 1;
        				highway.add(new Point(x,y,dir1));
        				maxDistance = maxDistance+1;
        				if (ifHitSelf(highway,highway.get(highway.size()-1)))
        				{
        					//return false;//hit self, have to regenerate;
        					
        				}
        			}else if (dir1 == 2)
        			{
        				x = x - 1;
        				highway.add(new Point(x,y,dir1));
        				maxDistance = maxDistance+1;
        				if (ifHitSelf(highway,highway.get(highway.size()-1)))
        				{
        					//return false;//hit self, have to regenerate;
        					 
        				}
        			}else if(dir1 == 3)
        			{
        				y = y + 1;
        				highway.add(new Point(x,y,dir1));
        				maxDistance = maxDistance+1;
        				distanceToBound = distanceToBound-1;
        				if (ifHitSelf(highway,highway.get(highway.size()-1)))
        				{
        					//return false;
        					 
        				}
        			}else if (dir1 == 4)
        			{
        				y = y - 1;
        				highway.add(new Point(x,y,dir1));
        				maxDistance = maxDistance+1;
        				distanceToBound = distanceToBound+1;
        				if (ifHitSelf(highway,highway.get(highway.size()-1)))
        				{
        					//return false;
        					 
        				}
        			}
        		}
        		if (maxDistance <100)
        		{
        			return false;
        		}
    		
    	}else if (!a&&!b){
    		//*********************************************** LEFT BOUNDARY ********************************************
    		int startPosition = (int) Math.ceil(Math.random()*120);
    		highway.add( new Point(0,startPosition,1));
    		int x = 0;
    		int y = startPosition;
    		int i = 0;
    		while ( highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0 && highway.get(highway.size()-1).y !=119 && i<20 )
    		{
    			int dir = chooseDir(highway.get(highway.size()-1).Dir);
    			if (dir==1)
    			{
    				x = x+1;
    				highway.add(new Point(x,y,1));
    				maxDistance = maxDistance+1;
    			}else if (dir == 2)
    			{
    				i=i-1;
    			}else if(dir == 3)
    			{
    				y = y +1;
    				highway.add(new Point(x,y,3));
    				maxDistance = maxDistance+1;
    			}else if (dir == 4)
    			{

    				y = y -1;
    				highway.add(new Point(x,y,4));
    				maxDistance = maxDistance+1;
    			}
    			i = i+1;
    		}
    		if (maxDistance<20)
    		{
    			//return false;
    		}
    		while(highway.get(highway.size()-1).x!=0 && highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0&&highway.get(highway.size()-1).y !=119)
    		{
    			int dir1 = chooseDir2(highway.get(highway.size()-1).Dir);
    			
    			if (dir1==1)
    			{
    				x =x+1;
    				highway.add(new Point(x,y,1));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get(highway.size()-1)))
    				{
    					//return false;//hit self, have to regenerate;
    					
    				}
    			}else if (dir1 == 2)
    			{
    				x = x - 1;
    				highway.add(new Point(x,y,2));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get(highway.size()-1)))
    				{
    					//return false;//hit self, have to regenerate;
    					
    				}
    			}else if(dir1 == 3)
    			{
    				y = y+1;
    				highway.add(new Point(x,y,3));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get(highway.size()-1)))
    				{
    					//return false;
    					 
    				}
    			}else if (dir1 == 4)
    			{
    				y = y-1;
    				highway.add(new Point(x,y,4));
    				maxDistance = maxDistance+1;
    				if (ifHitSelf(highway,highway.get(highway.size()-1)))
    				{
    					//return false;
    					 
    				}
    			}
    		}
    		if (maxDistance <100)
    		{
    			//return false;
    		}
    	}
    	
    	for (int i = 0;i<highway.size();i++)
    	{	
    		if (cell[highway.get(i).x][highway.get(i).y].type == '1')
    		{
    			cell[highway.get(i).x][highway.get(i).y].setcelltype('a'); 
    			
    		}else if (cell[highway.get(i).x][highway.get(i).y].type == '2')
    		{
    			cell[highway.get(i).x][highway.get(i).y].setcelltype('b'); 
    			
    		}
    	}
    	Point a1 = highway.get(highway.size()-1);
    	return true;
    }
    
    public int chooseDir(int dir)
    {	
    	
    	double a = Math.random();
    	if(dir==1)
    	{
    		if (a<0.3333)
    		{
    			return dir;
    		}else if(a>0.3333&&a<0.6667){
    			return 4;
    		}else
    		{
    			return 3;
    		}
    	}else if (dir ==2)
    	{
    		if (a<0.3333)
    		{
    			return dir;
    		}else if(a>0.3333&&a<0.6667){
    			return 3;
    		}else 
    		{
    			return 4;
    		}
    	}else if (dir ==3)
    	{
    		if (a<0.3333)
    		{
    			return dir;
    		}else if(a>0.3333&&a<0.6667){
    			return 1;
    		}else 
    		{
    			return 2;
    		}
    	}else
    	{
    		if (a<0.3333)
    		{
    			return dir;
    		}else if(a>0.3333&&a<0.6667){
    			return 1;
    		}else 
    		{
    			return 2;
    		}
    	}
    }
    
    public int chooseDir2(int dir)
    {
    	double a = Math.random();
    	if(dir==1)
    	{
    		if (a<0.999)
    		{
    			return dir;
    		}else if(a>0.999&&a<0.99999){
    			return 4;
    		}else if (a>0.99999)
    		{
    			return 3;
    		}
    	}else if (dir ==2)
    	{
    		if (a<0.999)
    		{
    			return dir;
    		}else if(a>0.999&&a<0.99999){
    			return 3;
    		}else if (a>0.99999)
    		{
    			return 4;
    		}
    	}else if (dir ==3)
    	{
    		if (a<0.999)
    		{
    			return dir;
    		}else if(a>0.999&&a<0.99999){
    			return 1;
    		}else if (a>0.99999)
    		{
    			return 2;
    		}
    	}else if (dir ==4)
    	{
    		if (a<0.999)
    		{
    			return dir;
    		}else if(a>0.999&&a<0.99999){
    			return 1;
    		}else if (a>0.99999)
    		{
    			return 2;
    		}
    	}
    	return dir;
    }
    
    public boolean ifHitSelf(List<Point> h,Point p)
    {
    	for (int i = 0;i<h.size();i++)
    	{
    		if (h.get(i).x == p.x && h.get(i).y == p.y)
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    //Initiate Map, all cells to type 1
    public void initiate()
    {	
    	cell = new Cell[160][120];
    	for (int i = 0;i<160;i++)
    	{
    		for (int j = 0;j<120;j++)
    		{
    			cell[i][j] = new Cell(i,j) ;
    			
    		}
    	}

    }
    

    public boolean random(float a, float b)
    {
    	float x = a/(a+b);
    	if (Math.random() <x)
    	{
    		return true;
    	}else {
    		return false;
    	}
    }

}
