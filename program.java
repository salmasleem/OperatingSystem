package milestone1;

import java.util.LinkedList;
import java.util.Queue;

public class program {
	Queue<variables> variables;
	Queue <String> instructions;
	ProcessControlBlock PCB;
	public program (String name , String pc, int start, int end) {
		variables = new LinkedList<>();
		instructions = new LinkedList<>();
		PCB = new ProcessControlBlock(name, pc, start, end);
	}
}
