
public class Stack {

	private int stackTop = 0;
	
	//Stack of size 10
	
	int[] stack = new int [10];
	
	//Check for empty Stack
	
	public boolean empty(){
		if (stackTop <= 0 ){
			return true;	
		}
			return false;
	
	}

	//Check for Stack full
	public boolean full(){
		if (stackTop >= 10)
		{
			System.out.println("Stack is full");
			return true;
			}
			
			return false;
	}
	
	//Popping elements from Stack
	
	public int pop(){
		if (this.empty())
			System.out.println("Stack Empty");

		stackTop = stackTop -1;
		return stack[stackTop + 1];
	}
	
	//Pushing elements on Stack
	public void push(int n){
		if (this.full())
			System.out.println("Stack is full");
		stackTop=+1;
		
		stack[stackTop] = n;
		
	}
	
	public static void main(String args[])
	{
		
		//Creating a new Stack
		Stack o1 = new Stack();
		
		o1.push(10);
		
		o1.push(11);
		
		System.out.println(o1.pop());
						
		
	}
}

