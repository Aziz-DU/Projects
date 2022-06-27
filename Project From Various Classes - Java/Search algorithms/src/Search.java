
public class Search {
	public static boolean algorithm_1(int[] array) {
		int count = 0;
		while(count <array.length) {
		    for (int i = 0; i < array.length; i++) {
		    	if(array[count] == -array[i] && array[count] != array[i]) {
		    		return true;
		    	}
		  }
		  count++;  
		}
 
	return false;
}
	
	public static boolean Algorithim_2(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int first = 0;
			int last = array.length -1;
			int x = -array[i];
			while(first<last) {
				 int middle= (first+last) / 2;
				 if(array[middle] == x && array[middle] != 0) {
						return true;
					}				 
				 else if(x < array[middle]) {
						last = middle;
					}
				else {
					first = middle +1;
				}
			}	
		}
		return false;
}
	public static boolean Algorithim_3(int[] array) {
		int i = 0;
		int j = array.length-1;
		while(i != j) {
			if((array[i] + array[j]) ==0) {
				return true;
			}
			else if((array[i] + array[j])< 0) {
				i++;
			}
			else if((array[i] + array[j])> 0) {
				j--;
			}
		}
		return false;
	}
}
