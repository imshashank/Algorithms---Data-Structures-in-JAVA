* Implementing a D-ary max heap in JAVA

import java.util.ArrayList;

/*
 * Name: Shashank Agarwal
 * CSE 5331
 * Foundation II: DS & Algo
 */

public class DaryMaxHeap {

	private Integer d;
    private Integer size;
    private ArrayList<Integer> heap;
    boolean flag=false;
	
	/*
	 * Constructor
	 * 
	 * Initialize instance variables, i.e. create an empty heap
	 * 
	 * @param n Maximum number of children per tree node, i.e. degree of the tree
	 * @throws IllegalArgumentException Parameter n is less than or equal to one
	 */
    
	public DaryMaxHeap(int n) {
		//creates a new array list which is used to store the heap
		if (n >= 2){
            d = new Integer(n);
            size = new Integer (0);
            heap = new ArrayList<Integer>();
    } else {
            throw new IllegalArgumentException("n must be > 1");
    }
	}
	
	/*
	 * Check if the heap is empty
	 * 
	 * @return True if the heap is empty, otherwise false
	 */
	public boolean isEmpty() {
		//checks if the heap is full or empty
		 if (this.size == 0){
             return true;
     } else {
             return false;
     }
	}
	
	/*
	 * Size of the heap
	 * 
	 * @return Number of nodes in the heap
	 */
	public int size() {
		// returns the size of the arraylist i.e the heap
		   return this.size;
	}
	
	/*
	 * Height of the heap, i.e. complete n-ary tree
	 * 
	 * @return Number of edges from root node to farthest leaf node
	 */
	public int height() {
		//used the below formula to calculate the height of the heap
		double h;
		h=  ((Math.log((size*(d-1))+1)/Math.log(d))-1);  
		int h1= (this.size + this.d - 2)/this.d;
		if (h1==0){
			return 0;
		}
		return (int) Math.ceil(h);
		
	}
	
	/*
	 * Peek at the max element
	 * 
	 * Retrieves, but does not remove, the max element in the heap
	 * 
	 * @throws RuntimeException Heap is empty
	 */
	public int peek()
	{ 
		//shows the top element
		 if (this.size > 0){
             return this.heap.get(0);                        
     } else {
             throw new RuntimeException ("Heap cannot be empty.");
     }
	}
	
	/*
	 * Add to the heap
	 * 
	 * Inserts the specified element into this heap.
	 * 
	 * @param e Element to insert
	 */
	public void add (int e) {
		 //increment size and add new value
        this.size++;
        this.heap.add(e);
        
        //set indices of current child (e) and its parent
        int child = this.size-1;
        int parent = (child-1)/this.d;
        
        //heapify the heap
        if (this.size > 1){
                //move the child down 
                while (e > this.heap.get(parent)){
                      
                	//the sift up action, swaps the parent and child
                        this.heap.set(child, this.heap.get(parent));
                        this.heap.set(parent, e);
                        
                        //make the child the new parent
                        child = parent;
                        if (child == 0){
                                break;
                        } else {
                        //get the parent of the given child
                        parent = (child-1)/this.d;
                        }
                }
        }
	}
	
	/*
	 * Poll the max element
	 * 
	 * Retrieves and removes the max element of the heap.
	 * 
	 * @throws RuntimeException Heap is empty
	 */
	public int poll() {
		 int parentIndex = 0;
	        int childIndex = 1;
	        Boolean fixed  = false;
	        
	        //remove the first element
	        int max = this.heap.remove(parentIndex);
	        this.size--;
	        
	        //this just moves all the elements up the array
	        if (this.size > 1){
	                //removes the empty element
	                this.heap.add(0, this.heap.remove(size-1));
	                //heapify
	                while (!fixed){
	                        //find largest child from all the children
	                        int maxChild = this.heap.get(childIndex);
	                        int maxChildpos = 0;
	                        for (int i = 1; (i < this.d) && (childIndex+i < this.size) ; i++){
	                                if (this.heap.get(childIndex+i) > maxChild){
	                                        maxChild = this.heap.get(childIndex+i);
	                                        maxChildpos = i;
	                                }
	                        }
	                        //compare the largest child with the parent and swap if the child is big
	                        if (this.heap.get(parentIndex) < maxChild){
	                                this.heap.set(childIndex + maxChildpos, this.heap.get(parentIndex));
	                                this.heap.set(parentIndex, maxChild);
	                        } else {
	                                //heapified
	                                fixed = true;
	                        }
	                }
	        }
	        return max;
	}
	
	/*
	 * Make the heap empty
	 */
	public void clear() {
		  this.size = 0;
          this.heap = new ArrayList<Integer>();
	}
	
	
	/*
	 * Array-based representation of the heap
	 * 
	 * @return An array-based representation in level order (see lecture notes) 
	 * 		   of this max heap.
	 * 		   If the heap is empty then return an array of length zero. 
	 * 		   This method never returns null.
	 */
	public int [] toArray() {
		//just converts the heap to array 
		  int[] array = new int[this.size];
          for (int i = 0; i < this.size; i++){
                  array[i] = this.heap.get(i);
                  System.out.println("The array at " + i + " is " + array[i]);
              }
          return array;
	}

	/*
	 * Conversion to d-ary min heap
	 * 
	 * @return An array-based representation in level order of a Min Heap 
	 * 		   containing all the elements in this heap.
	 *		   Use the linear time algorithm presented in lecture and the textbook to build a heap.
	 * 		   If the heap is empty then return an array of length zero. 
	 * 		   This method never returns null.
	 */
	public int [] toMinHeap() {
		//d is the ary ! parent= ((childindex-2/d)+1)
        //if parent less then child swap the child and parent
        
        
        //remove the greatest elements
        //place them at the end of the array and iterate
        //to the front of the array
        int [] temp = new int [this.size];
        int parent;
        int child;
        for(int i=0;i< this.size();i++)
        {
        temp[i]= heap.get(i);    
        }
        
        int t;
        /*
        for(int i=this.size-1;i>=0;i--){
        	child = i;
        	while (child != 0) {
        		if (child-1 !=0)	               	
        		{parent= (((child-1)/d));} //to prevent divide by zero exception
        		else parent =0;
        			if ( temp[parent] > temp[child] ){
        					t=temp[parent];
        					temp[parent]=temp[child];
        					temp[child]=t;
        			}
        			parent=((parent-1/d));
        			child=parent;
        			
        }}
        */
        //fixup to move minimum element to the root
        
        //traverse the last level from left to right
        //get nodes of last level
        
        int m=0;
        int n=0;
        for (int i=0;i<this.height();i++){
        	
        	m=(int) Math.pow(d, i);
        	n+=m;
        }
        System.out.println("Total nodes are " + n + " for h " + this.height() + " and d " +this.d);
        //starts from the left most child and compares that to the parent
        for(int i=n;i<this.size-1;i++){
        	child = i;
            while (child != 0) {
            if (child-1 !=0)	               	
            {parent= (((child-1)/d));}
            else parent =0;
        //System.out.println("parent is  " + parent + " of child " + child);
            if ( temp[parent] > temp[child] ){
        //    	System.out.println("the parent is " + temp[parent] + " and child is " + temp[child]);
            	t=temp[parent];
            	temp[parent]=temp[child];
            	temp[child]=t;
            	}
            parent=((parent-1/d));
            child=parent;
            }
        }
         
        //heapify the heap until it reaches root
        
        for(int i=this.size-1;i>=0;i--){
            child = i;
            while (child != 0) {
            if (child-1 !=0)	               	
            {parent= (((child-1)/d));}
            else parent =0;
            if ( temp[parent] > temp[child] ){
            	t=temp[parent];
            	temp[parent]=temp[child];
            	temp[child]=t;
            	}
            parent=((parent-1/d));
            child=parent;
            }
        }
        
        for(int i=0;i<this.size;i++){
        	System.out.println("the heapified array at " +i + " is " + temp[i]);
        }
        //a fix up for some elements 
        for(int i=this.size-1;i>=0;i--){
            child = i;
            while (child != 0) {
            if (child-1 !=0)	               	
            {parent= (((child-1)/d));}
            else parent =0;
            if ( temp[parent] > temp[child] ){
            	t=temp[parent];
            	temp[parent]=temp[child];
            	temp[child]=t;
            	}
            parent=((parent-1/d));
            child=parent;
            }
        }  
        
        
        for(int i=0;i<this.size;i++){
        	System.out.println("the min array at " +i + " is " + temp[i]);
        }
        
        return temp;
		
	}
	
	/*
	 * Sort elements in ascending order using the Heap Sort algorithm presented in lecture
	 * 
	 * @param heap An d-ary max heap
	 * @return An array of the elements in heap in ascending sorted order.
	 *		   A side effect of this function is that the input parameter heap contains a
	 *		   max heap with the values inserted in descending sorted order.
	 * 		   If the heap is empty then return an array of length zero. 
	 * 		   This method never returns null.
	  * @throws IllegalArgumentException Parameter heap is null 
	 */
	public static int [] heapsort(DaryMaxHeap heap) {
		 if (!(heap == null)){
             //sorted will be our output heap
             int [] sorted = new int [heap.size];
             
             //we just find the largest element that is the root i.e. use poll repeatedly
             int i = heap.size-1;
             for (i=heap.size-1;i>=0;i--){
            	 sorted[i] = heap.poll();
             }
             
             
             //we just add the sorted back to the heap
             i = sorted.length-1;
             for (i=sorted.length-1;i>=0;i--){
                     heap.add(sorted[i]);
             }
             return sorted;
     }
     throw new IllegalArgumentException ("null heap");
	}
}
