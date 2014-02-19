/*
 * Name: Shashank Agarwal
 * Class: CSE 5331
 * Foundations II : DS & Algo
 */
import java.util.ArrayList;
import java.util.Stack;

public class Builder
{
	private ArrayList<String[]> line = new ArrayList<String[]>();

	private boolean flag=false;
	// insert constants (if any)
	private ParseException e = new ParseException();
	private UnknownTargetException u = new UnknownTargetException();
	private String file;
	private String delims;
	private String delims1;
	private String[] tokens ;
	private Stack<String> dependencies = new Stack();
	private Stack<String> dependencies1 = new Stack();

  
    /**
     * 
     * @param makefile the incoming file
     * @throws ParseException
     * @throws UnknownTargetException
     * @throws CycleDetectedException
     */
    public Builder(String makefile) throws ParseException,
            UnknownTargetException, CycleDetectedException {
			// throws exception if null
    	if (makefile == null){
    		ParseException e1 =new ParseException();
    		throw e1;
    	}
    	file = makefile;
    	delims = "[\n]+";
    	delims1 = "[:]+";
    	tokens = file.split(delims);
    	//check number of colons
    	for (int i = 0; i < tokens.length; i++){
    	int count = 0;
    	for(int m =0; m < tokens[i].length(); m++)
    	    	if(tokens[i].charAt(m) == ':')
    	    		count++;
    		if(count!=2){
    			throw e;
    		}
    	}

    	//check Duplicate targets
    	for(int a =0; a< tokens.length; a++){
            for(int b =a+1; b< tokens.length; b++){
            	String temp1= tokens[a];            	
            	temp1 =temp1.replace("\n", "");
            	String temp2= tokens[b];            	
            	temp2 = temp2.replace("\n", "");
                if(temp1.equals(temp2)){
                	throw e;
                    }
            }
    	}

    	//add all words between colons to "line" list
    	for (int i = 0; i < tokens.length; i++){
    		String[] word = tokens[i].split(delims1);
           	line.add(word);
    	}
    	    	
    	//d holds each line
    	//check for pase errors
    	for(String[] d:line) {
    		for (int i = 0; i < d.length; i++){
    		String[] temp = d[i].split("[ ]+");
    		for(int m=0; m<temp.length; m++) {
    			try{
    				if (temp[m]=="Edit")
    				{    			
    					System.out.println("edit /n");
    					if (temp[m-1]!=temp[m+1])
    					{   		
    						throw e;
    					}
    				}
    			}
    			catch(ParseException e){}
    		}
    		}
    		}
        
  		
    	//unknown target exception
    	//traverses each target of each line and verifies that the dependency exists
    	{
    		
    	for(int n=0;n<tokens.length;n++){
    		String[] t = tokens[n].split(delims1);    		
    		if(t.length == 3 && !t[0].equals("MyApp.jar")){
    		for(int m=0;m<tokens.length;m++){
    			String[] target = tokens[m].split(delims1);
    			String tar = target[0].replaceAll("\\s+","");
    			//removes spaces
    			String dep = t[1].replaceAll("\\s+","");
    			if(tar.equals(dep)){
    				flag = true;
    			}
    		}
    		if (flag==false){
    			throw new UnknownTargetException();
    		}
    		}
    	}
    	}
    	
    	
    	//CycleDetectedException
    	//use the same logic as make target and traverses the whole graph 
    	///and if the count is greater then the total numbers of lines in makefile it throws exception
    	
    	String depend;
    	boolean flag1 = true;
    	boolean flag2 = false;
    	int count =0;
    	for(int n=0;n<tokens.length;n++){
			System.out.println("lines are " + tokens[n] );
		}
    	
    	for(int n1=0;n1<tokens.length;n1++){
    		String[] t4 = tokens[n1].split(delims1);
    		System.out.println("\n t4 is " +t4[0]);
    		dependencies1.push(t4[0]);	
    	while(flag1 && !dependencies1.empty() ){
    		depend = dependencies1.pop();
    		
    	for(int n=0;n<tokens.length;n++){
    		String[] t = tokens[n].split(delims1);
    		String tar = t[0].replaceAll("\\s+","").replaceAll("\\s+","");
    		//finds the dependency and tries to locate the line
    		if (depend.equals(tar)){
    			if(t.length==3){
    			depend=t[1];
    			String[] temp = depend.split("[ ]+");
    			if (temp.length>1){
    				flag2=true;
    				//pushes each dependency on stack
    				dependencies1.push(temp[1]);
    				dependencies1.push(temp[0]);
    			}
    			else 
    				dependencies1.push(t[1]);
    			}
    			else {
    				//if no dependency then set depend as null
    				depend="null";
    				if (!flag2)
    				flag1=false;
    			}}
    		}
    	count++;
    	
    	if (dependencies1.isEmpty()){
    		flag1=false;
    	}
    	if(count > tokens.length+1){
    		throw new CycleDetectedException();
    	}
    	}}
    	
    	
    }
    
    
	/**
     * 
     * @param targetName the target
     * @return result the made target
     */
    public ArrayList<String> makeTarget(String targetName) {
    	ArrayList<String> list = new ArrayList<String>();
    	ArrayList<String> operations = new ArrayList<String>();
    	Stack<String> ops = new Stack<String>();
    	
    		  
    	boolean flag1=true;
    	boolean flag2=false;
    	String operation= null;
    	if (targetName==null){
    		return list;
    	}
		int count=0;
		
		
		String depend = targetName;
		dependencies.push(targetName);
		
    	while(flag1 && !dependencies.empty() ){
    		depend = dependencies.pop();
    	
    	for(int n=0;n<tokens.length;n++){
    		String[] t = tokens[n].split(delims1);
    		String tar = t[0].replaceAll("\\s+","");
    	
    		if (depend.equals(tar)){
    	
    			count =n;
    			if(t.length==3){
    			depend=t[1];
    			String[] temp = depend.split("[ ]+");
    			if (temp.length>1){
    	
    				flag2=true;
    				dependencies.push(temp[1]);
    				dependencies.push(temp[0]);
    			}
    			else 
    				dependencies.push(t[1]);
    			operation = t[2];
    	
    			operations.add(operation);
    			}
    			else {
    				operation = t[1];
    				depend="null";
    	
    				operations.add(operation);
    				if (!flag2)
    				flag1=false;
    			}}
    		}
    	count++;
    	//exit the loop when no dependency left
    	if (dependencies.isEmpty()){
    		flag1=false;
    	}
    	}
    	
    	
    	for (int i=0;i <operations.size() ;i++){
    		ops.add(operations.get(i));
    	}
    	ArrayList<String> result = new ArrayList<String>();    	
    	while (!ops.empty()){
    		String temp =ops.pop();
    		result.add(temp);
    	}
    	
    	
    	for (int i=0;i <result.size() ;i++){
    		for(int x=i;x <result.size() ;x++){
    			if (result.get(i).equals(result.get(x)) && i!=x){
    				result.remove(x);	
    			}
    			
    		}
    	}
    	

    	for (int i=0;i <result.size() ;i++){
    		for(int x=i;x <result.size() ;x++){
    			if (result.get(i).equals(result.get(x)) && i!=x){
    				result.remove(x);	
    			}
    		}
    	}
    	
    	for (int i=0;i <result.size() ;i++){
    		list.add(result.get(i));
    	}
    	
    	
        return list;
    }
}
