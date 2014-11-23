/*
Largest subarray sum of an array
*/

import java.util.*;

public class maxArr{


public static void main(String[] Args){
	int[] x = {-2, -5, 6, -2, -3, 1, 5, -6};


	int max_here = 0;
	int max_so_far = 0;

	for (int i =0; i< x.length;i++){
		max_here = Math.max(x[i],max_here+x[i]);
		max_so_far = Math.max(max_here,max_so_far);
	}

	System.out.println(max_so_far);
}
}
