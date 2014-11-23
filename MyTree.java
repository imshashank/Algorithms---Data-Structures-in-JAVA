import java.util.*;

class MyTree{
	
MyTree left = null;
MyTree right = null;
MyTree parent = null;
int key = 0;

public MyTree(int k){
	key = k;
}

public void addLeft(MyTree left){
	this.left = left;
	this.parent = left;
}

public void addRight(MyTree right){
	this.right = right;
	this.parent = right;
}

public MyTree getParent(){
	return this.parent;
}


public boolean addToTree(MyTree child){
	Queue<MyTree> q = new LinkedList<MyTree>();

	q.add(this);
	MyTree temp= null;
	while(!q.isEmpty()){
	try{
	temp = q.poll();
	//System.out.println(temp.key);
	if (temp.left != null){
		q.add(temp.left);
	}
	else{
		temp.left = child;
		return true;
	}
	if (temp.right != null){
		q.add(temp.right);
		}
		else{
		temp.right = child;
		return true;
		}
	}catch (Exception e){
		//System.out.println(e);
	}
}
return false;
}

/*
public void printTree(){
	MyTree t = this;
	{
	System.out.println(this.key);
	this.visit +=1;
	}
	
	if (this.left!=null){
		MyTree temp1 = t.left;
		if (temp1.visit != 1){
		//System.out.println("left");
		temp1.visit += 1;
		}
		temp1.printTree();
	}
	
	
	if (this.right!=null){
		MyTree temp2 = t.right;
		//System.out.println(temp2.visit);
		if (temp2.visit != 1){
		temp2.visit += 1;
		}
		temp2.printTree();
	}
	

}
*/

public int getHeight(MyTree n){

if (n== null){
	return -1;
}
else
return Math.max(getHeight(n.right),getHeight(n.left))+1;

}

public void inOrder(){
	if (this.left!=null){
	this.left.inOrder();
	}
	System.out.println(this.key);
	if (this.right!=null){
	this.right.inOrder();
	}
}


public void succ(MyTree n){

	if (this.left!=null){
	this.left.inOrder();
	}
	System.out.println(this.key);
	if (this.right!=null){
	this.right.inOrder();
	}
}


 public int getWidth(MyTree n, int level){
 	if (n == null) return 0;
 	if (level == 1) return 1;
 	else if (level > 1){
 		return getWidth(n.left,level-1)+getWidth(n.right,level-1);
 	}
 	return 0;
 }



public int getMaxWidth(MyTree n){
	int max =0;
	int width= 0;
	for (int i =1;i< =getHeight(n);i++){
		System.out.println("Level " + i);
		width = getWidth(n,i);
		if(width > max){
			max = width;
		}
	}
	return width;
}

public void printAllLevel(MyTree n){

	for (int i =0;i<=getHeight(n);i++){
		System.out.println("Level " + i);
		printLevel(n,i);
	}
}
public void printLevel(MyTree n,int level){
	

	if (level ==1 && n!=null){
		System.out.println(n.key);
		return;
	}else if(level >1 && n.left != null){
		printLevel(n.left,level-1);
	}

	if (level >1 && n.right != null){
		printLevel(n.right,level-1);
	}
	

}


public void BST(MyTree n){
	//System.out.println(this.key);
	//System.out.println(n.key);
	if (this.key > n.key){
		if(this.left == null){
		this.left = n ;
		}
		else this.left.BST(n);
	}
	else {
		if(this.right == null){
		this.right = n ;
		}
		else this.right.BST(n);
	}
}


public MyTree  leftRotate(MyTree n){
	MyTree temp = n;
	MyTree temp2 = n.right;
	temp.left = n.left;
	temp.right = n.right.left;
	temp2.left = temp;
	n = temp2;
	return n;
}
public static void main(String[] Args){

MyTree root = new MyTree(5);
MyTree n1 = new MyTree(1);
MyTree n2 = new MyTree(2);
MyTree n3 = new MyTree(3);
//MyTree n4 = new MyTree(4);
MyTree n5 = new MyTree(6);
MyTree n6 = new MyTree(7);
MyTree n7 = new MyTree(8);
//MyTree n8 = new MyTree(9);

/*
MyTree n2 = new MyTree(String.valueOf("n2"));
MyTree n3 = new MyTree(String.valueOf("n3"));
MyTree n4 = new MyTree(String.valueOf("n4"));
MyTree n5 = new MyTree(String.valueOf("n5"));
MyTree n6 = new MyTree(String.valueOf("n6"));
MyTree n7 = new MyTree(String.valueOf("n7"));
*/

Queue<MyTree> q = new LinkedList<MyTree>();


root.BST(n1);
root.BST(n2);
root.BST(n3);
//root.BST(n4);
root.BST(n5);
root.BST(n6);
root.BST(n7);
//root.BST(n8);



/*
root.addToTree(n1);
root.addToTree(n2);
root.addToTree(n3);
root.addToTree(n4);
root.addToTree(n5);
root.addToTree(n6);
root.addToTree(n7);
*/
//root.inOrder();

int h =root.getHeight(root);
System.out.println("height " + h);
System.out.println("levels " );
root.printAllLevel(root);

root = root.leftRotate(root);
 h =root.getHeight(root);
System.out.println("height " + h);
System.out.println("levels " );
root.printAllLevel(root);

int w =root.getMaxWidth(root);
System.out.println("width " + w);

q.add(root);
MyTree temp= null;
while(!q.isEmpty()){
	try{
	temp = q.poll();
	System.out.println(temp.key);
	if (temp.left != null){
		q.add(temp.left);
	}
	if (temp.right != null){
		q.add(temp.right);
		}
	}catch (Exception e){
		System.out.println(e);
	}
}

/*
root.addLeft(n1);
root.addRight(n2);
n1.addLeft(n3);
n1.addRight(n4);
n2.addLeft(n5);
n3.addLeft(n6);
//traverse
//MyTree temp = root;

//temp.preOrder();

q.add(root);
MyTree temp= null;
while(!q.isEmpty()){
	try{
	temp = q.poll();
	System.out.println(temp.key);
	if (temp.left != null){
		q.add(temp.left);
	}
	if (temp.right != null){
		q.add(temp.right);
		}
	}catch (Exception e){
		System.out.println(e);
	}
}
//System.out.println(temp.key);
//temp.inOrder();
/*
while (temp.left!=null){
System.out.println(temp.key);
temp.visit=1;
temp = temp.left;
}

//go up
temp = temp.parent.right;
while (temp.right!=null){

System.out.println(temp.key);


}

//traverse
MyTree temp = root;
//print left
while (temp.left!=null){
System.out.println(temp.key);
temp = temp.left;
}
System.out.println(temp.key);

//print right
MyTree temp = root;
while (temp.right!=null){
System.out.println(temp.key);
temp = temp.right;
}
System.out.println(temp.key);







*/

}
}
