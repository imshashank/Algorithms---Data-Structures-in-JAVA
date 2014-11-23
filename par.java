/*
This is a classic combinatorial problem that manifests itself in many different ways. These problems are essentially identical:
Generating all possible ways to balance N pairs of parentheses (i.e. this problem)
Generating all possible ways to apply a binary operator to N+1 factors
Generating all full binary trees with N+1 leaves
Many others...
*/


public class Par{



public void printPar(int l,int r, char[] str, int count){


	if(r < l || l < 0){
		return;
	}
	
	if (r == 0 && l == 0){
		System.out.println("answer is ");
		System.out.println(str);
	}
	else {
	if(l >= 1){
		str[count]='(';
		printPar(l-1,r,str,count + 1);
	}
	if(r >= 1){
		str[count]=')';
		printPar(l,r-1,str,count + 1);
	}
	}
}

public void printAll(int count){
	int temp = count *2;
	char[] str = new char[temp];
	printPar(count,count,str,0);

}
public static void main (String[] Args){
	Par n = new Par();
	n.printAll(4);

}	
}

