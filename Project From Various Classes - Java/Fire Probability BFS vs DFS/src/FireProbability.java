
public class FireProbability {
	
	public static double probabilityOfFireSpreadDFS(double density) {
		
		int fireSpreadCount = 0;
		for (int i = 0; i < 10000; i++)
		{
			Forest f = new Forest(20,20, density);
			if (f.depthFirstSearch())
			{
				fireSpreadCount++;
			}
			
		}
		return  fireSpreadCount/10000.0;	
	}
	public static double probabilityOfFireSpreadBFS(double density) {
		
		int fireSpreadCount = 0;
		for (int i = 0; i < 10000; i++)
		{
			Forest f = new Forest(20,20, density);
			if (f.breadthFirstSearch())
			{
				fireSpreadCount++;
			}
			
		}
		return  fireSpreadCount/10000.0;	
	}

	public static double highestDensityDFS() {
		double lowDensity = 0.0;
		double highDensity = 1.0;
		double density = 0; 
		
		for (int i = 0; i < 20; i++)
		{
			density = (highDensity+lowDensity)/2;
			double p = probabilityOfFireSpreadDFS(density);
			if (p < 0.5)
			{
				lowDensity = density;
			}
			else
			{
				highDensity = density;
			}
		}
		return density;
	}
	public static double highestDensityBFS() {
		double lowDensity = 0.0;
		double highDensity = 1.0;
		double density = 0; 
		
		for (int i = 0; i < 20; i++)
		{
			density = (highDensity+lowDensity)/2;
			double p = probabilityOfFireSpreadBFS(density);
			if (p < 0.5)
			{
				lowDensity = density;
			}
			else
			{
				highDensity = density;
			}
		}
		return density;
	}

}
