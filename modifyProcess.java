package milestone1;

import java.io.IOException;
import java.util.*;

public class modifyProcess {

	
	public static int getSize(String program) {
		int iL = SystemCalls.searchInMemory(program);
		int size = 0;
		for(int i = iL; i<iL+20; i++)
			if(Memory.memory[i] == null)
				break;
			else
				size++;
		return size;
	}
	
	public static void createProcess(String[] program) throws IOException {
		program prog = new program(program[0],"0", -1,-1);
		String file = program[0] + ".txt";
		Interpreter.readFile(file, prog.instructions);
		int size = prog.instructions.size() + 7;
		int start = 0;
		int end = 0;
		int freeSpace = Memory.freeSpace();
		if(freeSpace == 1)
				start = 20;
		if(freeSpace == -1)
		{
			if(Memory.memory[2].equals("running"))
				start = 20;
			System.out.println(prog.PCB.ProcessID + "replaced" + Memory.memory[0]);
			String toDisk = "";
			for(int i=0 ; i < getSize(Memory.memory[0]); i++)
				toDisk+= Memory.memory[i] + ":";
			
			disk.writeToDisk(toDisk);
			
		}
			end = start + size;
		 	prog.PCB.PC = 0 + "";
			prog.PCB.boundries[0] =  start;
			prog.PCB.boundries[1]  = end;
			Memory.memory[start++] = prog.PCB.ProcessID;
			Memory.memory[start++] = prog.PCB.PC;
			Memory.memory[start++] = prog.PCB.state.toString();
			String bound = prog.PCB.boundries[0] + "," + prog.PCB.boundries[1];
			Memory.memory[start++] = bound;
			
			for(int j = 0;j < 3; j++) {
				Memory.memory[start++] = "variable";
			}
			
			for(int j = 0;j < prog.instructions.size(); j++) {
				Memory.memory[start++] = prog.instructions.peek();
				prog.instructions.add(prog.instructions.remove());
			}
			
	}
	
	
	
	public static void swapProcess(String name) throws IOException {
		String swapped = "";
		String[] program  = disk.readFromDisk().split(":");
		int start = 0;
		int freeSpace = Memory.freeSpace();
		if(freeSpace == 1)
			start = 20;
		if(freeSpace == -1)
		{	
			swapped = Memory.memory[0];
			String toDisk = "";
			for(int i=0 ; i < getSize(swapped); i++) 
				toDisk+= Memory.memory[i] + ":";
				
			deleteProcess(swapped);
			disk.writeToDisk(toDisk);
		}
		
		for(int i = 0; i < program.length; i++)
			if(i == 3)
				Memory.memory[i] = start + "," + (start + program.length - 1);
			else
				Memory.memory[i] = program[i];
		
		if(swapped.equals(""))
			System.out.println(name + "is back to memory and has been swapped with nothing!");
		else 
			System.out.println(name + "is back to memory and has been swapped with " + swapped);
		
	}
	
	public static void deleteProcess(String name) throws IOException {
		int position = SystemCalls.searchInMemory(name);
		for(int i = position; i < position + 20; i++)
			Memory.memory[i] = null;
	}
	
	
}
