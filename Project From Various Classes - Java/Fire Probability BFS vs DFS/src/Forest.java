import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Forest {
	int height;
	int width;
	TreePosition[][] grid;
	public Forest(int h, int w, double density){
		grid = new TreePosition[h][w];
		height=h;
		width=w;
	    for (int i = 0; i < grid.length; i++) 
	    {
            for (int j = 0; j < grid[i].length; j++) 
            {
            	double randomD = Math.random();
             	if (randomD <= density)
				{
             		grid[i][j] =new TreePosition(1,i,j);
				}
				else 
				{
					grid[i][j] = new TreePosition(0,i,j);
				}	
            }
        }
	  
	}
	public String toString() {
		StringBuilder gridString = new StringBuilder("");
		 for (int i = 0; i < height; i++) 
		 {
	            for (int j = 0; j < width; j++) 
	            {
	            	gridString.append(grid[i][j].getValue()+" ");
	            }
	            gridString.append("\n");    
	        }
	return gridString.toString();	
	}
	public boolean depthFirstSearch() {
		
		Stack<TreePosition> cellsToExplore = new Stack<>();
		TreePosition currentPos;
		
        for (int i = 0; i < grid[0].length; i++)
        {
        	if (grid[0][i].s == 1) 
			{
				cellsToExplore.push(grid[0][i]);
				grid[0][i].setTreeStatus(2); 
			}	
        }
        while (!cellsToExplore.isEmpty())
		{
			currentPos = cellsToExplore.pop();
			if (currentPos.y == height - 1)
			{
				return true;
			}

			if (currentPos.x + 1 < width&&grid[currentPos.y][currentPos.x + 1].getValue() == 1)
			{
				cellsToExplore.push(grid[currentPos.y][currentPos.x + 1]);
				grid[currentPos.y][currentPos.x + 1].setTreeStatus(2);
			}
			
			if (currentPos.y + 1 < height && 
					grid[currentPos.y + 1][currentPos.x].getValue() == 1)
			{
				cellsToExplore.push(grid[currentPos.y + 1][currentPos.x]);
				grid[currentPos.y + 1][currentPos.x].setTreeStatus(2);
			}
			
			if (currentPos.x - 1 >= 0 &&
					grid[currentPos.y][currentPos.x -1].getValue() == 1)
			{
				cellsToExplore.push(grid[currentPos.y][currentPos.x - 1]);
				grid[currentPos.y][currentPos.x - 1].setTreeStatus(2);
			}
		}
	return false;	
	}
	
	public boolean breadthFirstSearch() {
		Queue<TreePosition> cellsToExplore = new LinkedList<TreePosition>();
		TreePosition currentPos;
		
        for (int i = 0; i < grid[0].length; i++) {
        	if (grid[0][i].s == 1) 
			{
				cellsToExplore.add(grid[0][i]);
				grid[0][i].setTreeStatus(2); 
			}	
        }
        while (!cellsToExplore.isEmpty())
		{
			currentPos = cellsToExplore.remove();
			if (currentPos.y == height - 1)
			{
				return true;
			}

			if (currentPos.x + 1 < width&&grid[currentPos.y][currentPos.x + 1].getValue() == 1)
			{
				cellsToExplore.add(grid[currentPos.y][currentPos.x + 1]);
				grid[currentPos.y][currentPos.x + 1].setTreeStatus(2);
			}
			
			if (currentPos.y + 1 < height && 
					grid[currentPos.y + 1][currentPos.x].getValue() == 1)
			{
				cellsToExplore.add(grid[currentPos.y + 1][currentPos.x]);
				grid[currentPos.y + 1][currentPos.x].setTreeStatus(2);
			}
			
			if (currentPos.x - 1 >= 0 &&
					grid[currentPos.y][currentPos.x -1].getValue() == 1)
			{
				cellsToExplore.add(grid[currentPos.y][currentPos.x - 1]);
				grid[currentPos.y][currentPos.x - 1].setTreeStatus(2);
			}
		}	        
	return false;	
}
	private class TreePosition
	{
		private int x;
		private int y;
		private int s;

		public TreePosition(int status, int xPos, int yPos)
		{
			x = xPos;
			y = yPos;
			s = status;
		}		
		public void setTreeStatus(int status)
		{
			s = status;
		}
		public int getValue()
		{
			return s;
		}
	}
}
