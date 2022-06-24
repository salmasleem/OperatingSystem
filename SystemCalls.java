package milestone1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SystemCalls {
	
public static void assign (String processName, String vName) { 
	
		System.out.println("Please enter a value");
		Scanner sc = new Scanner(System.in);
		String value = sc.nextLine();
		String inst = "assignVar "+ vName + " " + value;
		int start = searchInMemory(processName);
		int pc = Integer.parseInt(Memory.memory[start+1]);
		int position = pc + start + 7;
		Memory.memory[position] = inst;
		Memory.memory[start + 1] = (pc-1) + "";
	}
	 		
public static void assign (String processName, String vName, String value) throws IOException {
	String val = readFile(value, processName);
	String inst = "assignVar "+ vName + " " + val;
	int start = searchInMemory(processName);
	int pc = Integer.parseInt(Memory.memory[start+1]);
	pc += start + 7;
	Memory.memory[pc] = inst;
	Memory.memory[start + 1] = (pc-1-7) + "";
}

public static void assignVar(String processName, String VariableName, String Value) {
	writeToMemory(processName,VariableName, Value);
}

public static void print(String processName, String variableName) {
	print(readFromMemory(processName, variableName));
}

public static void print(String v1) {
	System.out.println(v1);
}

public static String readFile(String variableName, String processName) throws IOException{
	String fileName = readFromMemory(processName, variableName) + ".txt";
    String currentLine = "";
    String x = "";
    FileReader fileReader= new FileReader(fileName);
    BufferedReader br = new BufferedReader(fileReader);
    while ((x = br.readLine()) != null) {
        
        currentLine= currentLine + x + ";";
        
    }
    br.close();
    
    return currentLine;
}

public static void writeFile(String variableName, String value, String processName){
	String fileName = readFromMemory(processName, variableName) + ".txt";
	String fileValue = readFromMemory(processName, value);
	CreateFile(fileName);
	try {
	      FileWriter myWriter = new FileWriter(fileName);
	      myWriter.write(fileValue);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
}

public static void CreateFile(String fileName) {
	    try {
	     
	      File myObj = new File(fileName);
	      if (myObj.createNewFile()) {
	        System.out.println("File created: " + myObj.getName());
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }

public static int searchInMemory(String process) {
	if(Memory.memory[0] != null && Memory.memory[0].equals(process))
		return 0;
	else if(Memory.memory[20] != null && Memory.memory[20].equals(process))
		return 20;
	else 
		return -1;
}

public static void writeToMemory(String processName, String VariableName, String Value) {
	int start = searchInMemory(processName);
	start+=4;
	
	for(int i = start; i < start+3; i++) {

		System.out.println(Memory.memory[i]);
		if(Memory.memory[i].equals("variable")) {
			Memory.memory[i] = VariableName + ";" + Value;
			break;
		}
	}
		
}

public static String readFromMemory(String processName, String VariableName) {
	String[] read;
	int start = searchInMemory(processName);
	start+=4;
	
	for(int i = start; i < start+3; i++) {
		read = Memory.memory[i].split(";");
		if(read[0].equals(VariableName))
			return read[1];
	}
	
	return "";
	}
		

}
