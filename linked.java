

import java.util.Collections;
import java.util.LinkedList;

public class linked {
	
	public static void main(String args[]) {

	   //Using pre-defined LinkedList library
		
	   LinkedList list1 = new LinkedList();

	   //adding elements to end of linked list
	   
	   list1.add("a");
	   list1.add("b");
	   list1.add("d");
	   list1.add("2");
	   list1.add("sd");
	   list1.add("c");
	   
	   // Size of list
	   System.out.println(list1.size());
	   
	   //Traversing and printing the whole list
	   	   for (int j=0; j < list1.size(); j++) {
           System.out.println("[" + j + "] - " + list1.get(j));
       }
	
	
	        int locationIndex = list1.indexOf("sd");
	        System.out.println("index of b:"+ locationIndex);
	        
	        //Create sorted list
	        Collections.sort(list1);
	        System.out.println("The sorted list"+list1);
	        
}
}

