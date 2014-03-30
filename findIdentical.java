import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

public class identicalFile {

	/**
	 * author: Shashank
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 */
	
	 public static Hashtable<String, String> files  = new Hashtable<String, String>();
  
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		//add the directory to be checked for identical files, it  check the sub-directories
		System.out.println(getFiles("Z:\\eclipse"));
	}
	public static String[] getFiles(String path) throws NoSuchAlgorithmException, IOException{
	
		
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles();
	String hash_val;
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	String filename= listOfFiles[i].getName();
	    	//skipping files JAVA program can't read and files starting with "."
	      if (listOfFiles[i].isFile() && listOfFiles[i].canRead() && filename.charAt(0) != '.') {
	    	  //add to hashtable using the md5checksum as key
	    	 // System.out.println(listOfFiles[i].getAbsolutePath());
	    	  hash_val=gethash(listOfFiles[i].getAbsolutePath());
	    	  //checks is the key already exists in the hashtable
	    	  if(files.get(hash_val)==null){
	    	  files.put(hash_val, listOfFiles[i].getName());
	    	  }
	    	  else System.out.println("Duplicate file at:" + listOfFiles[i].getAbsolutePath());
	        //System.out.println("File " + listOfFiles[i].getName());
	      } else if (listOfFiles[i].isDirectory()) {
	        //System.out.println("Directory " + listOfFiles[i].getName());
	        getFiles(listOfFiles[i].getAbsolutePath());
	        
	      }
	    }
		return null;
}
	
	public static String gethash(String filename) throws IOException, NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA1");
		
	    FileInputStream fis = null;
		try {
			fis = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    byte[] dataBytes = new byte[1024];
	 
	    int nread = 0; 
	 
	    while ((nread = fis.read(dataBytes)) != -1) {
	      md.update(dataBytes, 0, nread);
	    };
	 
	    byte[] mdbytes = md.digest();
	 
	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	   // System.out.println("Digest(in hex format):: " + sb.toString());
		return sb.toString();
	} 
}

