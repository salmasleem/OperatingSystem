package milestone1;
import java.io.*;
import java.util.*;

public class Interpreter {
	static String current;

	
	 public static void readFile(String path, Queue<String> queue) throws IOException{
	        String currentLine = "";
	        FileReader fileReader= new FileReader(path);
	        BufferedReader br = new BufferedReader(fileReader);
	        while ((currentLine = br.readLine()) != null) {
	            queue.add(currentLine);
	            
	        }
	        
	        br.close();
	    }
	    public static void differentiator(String instruction, String processName) throws IOException {
	    	
	    	String[] arrOfInstruction = instruction.split(" ");
	    	switch (arrOfInstruction[0]) {
	    	case "assign": if(arrOfInstruction.length == 3)
	    						SystemCalls.assign(processName, arrOfInstruction[1]);
	    					else
	    						SystemCalls.assign(processName, arrOfInstruction[1],arrOfInstruction[3]);
	    					break;
	    	case "assignVar": SystemCalls.assignVar(processName, arrOfInstruction[1],arrOfInstruction[2]);break;
	    	case "semWait" : mutexes.semWait(arrOfInstruction[1], processName); break;
	    	case "semSignal":mutexes.semSignal(arrOfInstruction[1], processName); break;
	    	case "printFromTo": PrintFromTo.printFromTo(processName, arrOfInstruction[1],arrOfInstruction[2]); break;
	    	case "print": SystemCalls.print(processName, arrOfInstruction[1]); break;
	    	case "readFile": SystemCalls.readFile(arrOfInstruction[1], processName); break;
	    	case "writeFile":SystemCalls.writeFile(arrOfInstruction[1],arrOfInstruction[2], processName);
	    		}
	    }
	    
	    
	
}
