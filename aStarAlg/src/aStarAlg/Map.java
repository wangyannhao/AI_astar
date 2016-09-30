package aStarAlg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import java.util.*;
import java.io.*;

public class Map {
	public List<Point> highwaylist = new ArrayList<Point>();

	public Map()
	{

		initiate();
		hardCellgenerate();
		int lines = 1;
		while(lines < 5)
			{
				if (generateHighway(lines))
				{

					lines = lines+1;
				}
			}
		generateBlockedCell();
		
	}

	public static Cell[][] cell ;

	public final int Row = 120;
	public final int Column = 160;
    
    public void hardCellgenerate()
    {
        int[] x = new int[8];
        int[] y = new int[8];
        /* set the boundaries for x,y generated */
        int x_min = 15;
        int y_min = 15;
        int x_max = 105;
        int y_max = 145;
        Random random1 = new Random();
        Random random2 = new Random();
        int deltax = random1.nextInt(130);
        int deltay = random1.nextInt(90);
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
    
    public boolean generateHighway(int index)
    {	

    	boolean a = random(0.5f,0.5f);
    	boolean b = random(0.5f,0.5f);
    	Random random1 = new Random();
    	int bound = random1.nextInt(4);

		int maxDistance = 0;
		int distanceToBound = 0; 
    	List<Point> highway = new ArrayList<Point>();

    	//*********************************************** UP BOUNDARY ********************************************
    	if (bound==0)
    	{
    		//up boundary
        	Random random = new Random();
    		int startPosition = random.nextInt(160);
    		Point Position = new Point(startPosition,0,3);
    		highway.add(Position);
    		int x = startPosition;
    		int y = 0;
    		//first 20 cells, random moves without moving back
    		int i = 0;
    		while (highway.get(highway.size()-1).x!=0 && highway.get((highway.size()-1)).x!=159&&highway.get((highway.size()-1)).y !=119 && i<20 )
    		{
    			//int dir=3;

    				y = y +1;
    				highway.add(new Point(x,y,3));
    				maxDistance = maxDistance+1;

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
    				for (int c = 0;c<20;c++)
    				{
    					
    				x = x + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;

    				if (ifHitSelf(highway,highway.get((highway.size()-1)))) {
                        return false;
                    }

    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}else if (dir == 2)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				x = x - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;

    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				}
    			}else if(dir == 3)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;

    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}else if (dir == 4)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    			
    				}
    			}

    		}
    		
    		if (maxDistance <100)
    		{
                return false;
    		}
    	}else if (bound==1)
    	{
    		//*********************************************** RIGHT BOUNDARY ********************************************
        	Random random = new Random();
    		int startPosition = random.nextInt(120);
    		Point Position = new Point(159,startPosition,2);
    		highway.add(Position);
    		int x = 159;
    		int y = startPosition;
    		int i = 0;
    		while (highway.get((highway.size()-1)).x!=0 && highway.get((highway.size()-1)).y!=0&&highway.get((highway.size()-1)).y !=119 && i<20 )
    		{

    			x = x-1;
    			highway.add(new Point(x,y,2));
    			maxDistance = maxDistance+1;
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
    				for (int c = 0;c<20;c++)
    				{
    					
    				x = x + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}else if (dir == 2)
    			{
    				for (int c = 0;c<20;c++)
    				{
    					x = x - 1;
        				if (ifHitSelf(highway,new Point(x,y,dir)))
        				{
        					return false;//hit self, have to regenerate;
        				}
        				highway.add(new Point(x,y,dir));
        				maxDistance = maxDistance+1;
        				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
    					{
    						c=20;
    					}
        				
        				}
    			}else if(dir == 3)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}else if (dir == 4)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}

    		}
    		
    		if (maxDistance <100)
    		{
    			return false;
    		}
    		
    	}else if (bound==2){
    		//*********************************************** DOWN BOUNDARY ********************************************
        	Random random = new Random();
    		int startPosition = random.nextInt(160);
    		highway.add(new Point(startPosition,119,4));
    		int x = startPosition;
    		int y = 119;
    		//first 20 cells, random moves without moving back
    		int i = 0;
    		while (highway.get(highway.size()-1).x!=0 && highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0&& i<20 )
    		{
    				y = y - 1;
    				highway.add(new Point(x,y,4));
    				maxDistance = maxDistance+1;

    			i =i+1;
    		}
    		if (maxDistance<20)
    		{
    			return false;
    		}
    		while(highway.get((highway.size()-1)).x!=0 && highway.get((highway.size()-1)).x!=159 && highway.get((highway.size()-1)).y!=0&&highway.get((highway.size()-1)).y !=119)
    		{
    			int dir = chooseDir2(highway.get((highway.size()-1)).Dir);
    			
    			if (dir==1)
    			{
    				for (int c = 0;c<20;c++)
    				{
    					
    				x = x + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}

    				}
    			}else if (dir == 2)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				x = x - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				}
    			}else if(dir == 3)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					
    				}
    				}
    			}else if (dir == 4)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}

    		}
    		
        		if (maxDistance <100)
        		{
        			return false;
        		}
    		
    	}else if (bound==3){
    		//*********************************************** LEFT BOUNDARY ********************************************
        	Random random = new Random();
    		int startPosition = random.nextInt(120);
    		highway.add( new Point(0,startPosition,1));
    		int x = 0;
    		int y = startPosition;
    		int i = 0;
    		while ( highway.get(highway.size()-1).x!=159 && highway.get(highway.size()-1).y!=0 && highway.get(highway.size()-1).y !=119 && i<20 )
    		{
    				x = x+1;
    				highway.add(new Point(x,y,1));
    				maxDistance = maxDistance+1;
    				i= i+1;
    		}
    		if (maxDistance<20)
    		{
    			return false;
    		}
    		while(highway.get((highway.size()-1)).x!=0 && highway.get((highway.size()-1)).x!=159 && highway.get((highway.size()-1)).y!=0&&highway.get((highway.size()-1)).y !=119)
    		{
    			int dir = chooseDir2(highway.get((highway.size()-1)).Dir);
    			

    			if (dir==1)
    			{
    				for (int c = 0;c<20;c++)
    				{
    					
    				x = x + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}

    				}
    			}else if (dir == 2)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				x = x - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				}
    			}else if(dir == 3)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y + 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}else if (dir == 4)
    			{
    				for (int c = 0;c<20;c++)
    				{
    				y= y - 1;
    				if (ifHitSelf(highway,new Point(x,y,dir)))
    				{
    					return false;//hit self, have to regenerate;
    				}
    				highway.add(new Point(x,y,dir));
    				maxDistance = maxDistance+1;
    				if (highway.get((highway.size()-1)).x==0 || highway.get((highway.size()-1)).x==159 || highway.get((highway.size()-1)).y==0||highway.get((highway.size()-1)).y ==119)
					{
						c=20;
					}
    				
    				}
    			}

    		}
    		
    		if (maxDistance <100)
    		{
    			return false;
    		}
    	}
    	int aa =0;
    	for (int i = 0;i<highway.size();i++)
    	{
            aa = aa+1;
    		if (cell[highway.get(i).x][highway.get(i).y].type == '1')
    		{
    			cell[highway.get(i).x][highway.get(i).y].setcelltype('a');
				cell[highway.get(i).x][highway.get(i).y].index = index;
                highwaylist.add(new Point(highway.get(i).x,highway.get(i).y,highway.get(i).Dir));

    			
    		}else if (cell[highway.get(i).x][highway.get(i).y].type == '2')
    		{
    			cell[highway.get(i).x][highway.get(i).y].setcelltype('b');
				cell[highway.get(i).x][highway.get(i).y].index = index;
    			highwaylist.add(new Point(highway.get(i).x,highway.get(i).y,highway.get(i).Dir));
    		}
    	}
    	
    	return true;
    }
    

    
    public int chooseDir2(int dir)
    {
    	Random random = new Random();
    	int ran = random.nextInt(100);
    	if(dir==1)
    	{
    		if (ran<60)
    		{
    			return dir;
    		}else if(ran>60&&ran<80){
    			return 4;
    		}else if (ran>80)
    		{
    			return 3;
    		}
    	}else if (dir ==2)
    	{
    		if (ran<60)
    		{
    			return dir;
    		}else if(ran>60&&ran<80){
    			return 3;
    		}else if (ran>80)
    		{
    			return 4;
    		}
    	}else if (dir ==3)
    	{
    		if (ran<60)
    		{
    			return dir;
    		}else if(ran>60&&ran<80){
    			return 1;
    		}else if (ran>80)
    		{
    			return 2;
    		}
    	}else if (dir ==4)
    	{
    		if (ran<60)
    		{
    			return dir;
    		}else if(ran>60&&ran<80){
    			return 1;
    		}else if (ran>80)
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
    		if (h.get(i).x == p.x && h.get(i).y == p.y )
    		{
    			return true;
    		}
    		
    	}
    	for (int i = 0;i<highwaylist.size();i++)
    	{
    		if (highwaylist.get(i).x == p.x && highwaylist.get(i).y == p.y )
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
    public void generateBlockedCell()
    {
    	int a = 0;
    	for (int i =0;i<160;i++)
    	{
    		for (int j = 0; j<120;j++)
    		{
    			Random ran = new Random();
    			int x = ran.nextInt(10);
    			if (x<=1 && cell[i][j].type!='a'&& cell[i][j].type!='b')
    			{
    				cell[i][j].type = '0';
    				a = a+1;
    			}
    		}
    	}
    	System.out.println(a);
    }

    public boolean random(float a, float b)
    {
    	float x = a/(a+b);
    	if (Math.random() < x)
    	{
    		return true;
    	}else {
    		return false;
    	}
    }

	public void Produce_map(){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Output_map.txt"));
			out.write(Row + "," + Column + "\n");
            for(int i = 0; i < Row; i ++){
				for(int j = 0; j < Column; j++){
					if(cell[j][i].index == 1)
					{
						if(j == (Column - 1))
						{
						    out.write(cell[j][i].type + "" + cell[j][i].index + "\n");
						} else {
						out.write(cell[j][i].type + "" + cell[j][i].index + ",");
						}
					} else if(cell[j][i].index == 2){
                        if(j == (Column - 1))
                        {
                            out.write(cell[j][i].type + "" + cell[j][i].index + "\n");
                        } else {
                            out.write(cell[j][i].type + "" + cell[j][i].index + ",");
                        }
					}else if(cell[j][i].index == 3){
                        if(j == (Column - 1))
                        {
                            out.write(cell[j][i].type + "" + cell[j][i].index + "\n");
                        } else {
                            out.write(cell[j][i].type + "" + cell[j][i].index + ",");
                        }
                    }else if(cell[j][i].index == 4){
                        if(j == (Column - 1))
                        {
                            out.write(cell[j][i].type + "" + cell[j][i].index + "\n");
                        } else {
                            out.write(cell[j][i].type + "" + cell[j][i].index + ",");
                        }
                    }else {
                        if(j == (Column - 1))
                        {
                            out.write(cell[j][i].type + "\n");
                        } else {
                            out.write(cell[j][i].type + ",");
                        }
                    }
				}
			}

			out.close();
		} catch (IOException e) {}
	}

}
