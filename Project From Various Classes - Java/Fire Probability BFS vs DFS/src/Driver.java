public class Driver {
	
	// After running the functions multiple times, I found that the depth first search function and the breadth first search function have almost the same run time
	//although for the sake of accuracy, BFS always runs faster by a very low margin 
	//  2218 vs 2184
	//  2269 vs 2161
	//again that's such a low of a difference that i might as well say they were almost identical and that low difference was creaeted by external sources and not 
	//by the implementation of the functions(by external sources im referring to background applications or stuff of that sort).

	public static void main(String[] args) {
		
		long start = System.nanoTime();
		//double dps = FireProbability.highestDensityDFS();// left these commented alongside the print below in case you want to check the density value the functions return
		FireProbability.highestDensityDFS();
		long end = System.nanoTime();
		double durationInM= (end-start)/1000000.0;
		System.out.println("Time for the depth first search function is: " + durationInM);
		//System.out.println("The function returned this density value: " + dps);
		
		System.out.println();
		
		start = System.nanoTime();
		//double fps = FireProbability.highestDensityBFS();
		FireProbability.highestDensityBFS();
		end = System.nanoTime();
		durationInM= (end-start)/1000000.0;
		System.out.println("Time for the breadth first search function is: " + durationInM);
		//System.out.println( "The function returned this density value: "+fps );
		
	}
	
}
 