package milestone1;

public class ProcessControlBlock {
	String ProcessID;
	status state;
	String PC;
	int[] boundries = new int[2]; 
	
	
	public ProcessControlBlock(String ProcessID, String PC, int start, int finish) {
		this.ProcessID = ProcessID;
		state = status.Ready;
		this.PC = PC;
		boundries[0] = start;
		boundries[1] = finish;
		
	}
}


