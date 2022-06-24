package milestone1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class disk {
	public static void writeToDisk(String process){
		try {
		      FileWriter myWriter = new FileWriter("Disk.txt");
		      myWriter.write(process);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file " + process);
		      System.out.println("-----The Disk Contains Now-----");
		      System.out.println(readFromDisk());
		  	  System.out.println("------------------------------------");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	public static String readFromDisk() throws IOException{
	    String currentLine = "";
	    String x = "";
	    FileReader fileReader= new FileReader("Disk.txt");
	    BufferedReader br = new BufferedReader(fileReader);
	    while ((x = br.readLine()) != null) {
	        
	        currentLine= currentLine + x;
	        
	    }
	    br.close();
	    
	    return currentLine;
	}
	
 
}
