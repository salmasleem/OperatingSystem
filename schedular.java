package milestone1;
import java.io.IOException;
import java.util.*;

public class schedular {
	static Queue<String> readyQueue = new LinkedList<>();
	static Queue<String> blockedQueue = new LinkedList<>();
	static Queue<String> finishedQueue = new LinkedList<>();
	Queue<String[]> scheduledProg = new LinkedList<>();
	static Queue<String[]> allPrograms = new LinkedList<>();
	int timeSlice;
	static int CurrClock = 0;
	
	public schedular(int timeSlice, Queue<String[]> program) {
		this.timeSlice = timeSlice;
		this.scheduledProg = program;
		allPrograms = program;
		
	}
	
	public void display(Queue<String> q) {
		Queue<String> temp = new LinkedList<>();
		for(int i = 0; i < q.size();i++) {
			temp.add(q.peek());
			q.add(q.remove());
		}
		if(temp.isEmpty())
			System.out.println("None");
		else {
			while(!temp.isEmpty())
				System.out.print(temp.remove() + " ");
		}
	}
	
	public void display(String[] memory) {
		String[] temp;
		System.out.println("-----The Memory Contains Now-----");
		if(memory.length == -1)
			System.out.println("isEmpty!");
		for(int i = 0; i < memory.length; i++) {
			if(memory[i] == null)
				System.out.println("Word " + i + ": is empty!");
			else if(i == 0 || i == 20)
					System.out.println("Word " + i + " contains: Process Name is " + memory[i]);
				else if(i == 1 || i == 21)
					System.out.println("Word " + i + " contains: Process PC is " + memory[i]);
				else if(i == 2 || i == 21)
					System.out.println("Word " + i + " contains: Process State is: " + memory[i]);
				else if(i == 3 || i == 23)
					System.out.println("Word " + i + " contains: Process Boundaries are: " + memory[i]);
				else if (memory[i].equals("variable"))
					System.out.println("Word " + i + " contains: Empty Space for a Variable");
				else
					System.out.println("Word " + i + " contains: " + memory[i]);
		}
	}
	
	public void checkScheduled() throws IOException {
		for(int i=0; i < scheduledProg.size();i++) {
			if(Integer.parseInt(scheduledProg.peek()[1]) <= CurrClock) {
				modifyProcess.createProcess(scheduledProg.peek());
				readyQueue.add(scheduledProg.remove()[0]);
			}
			else
				scheduledProg.add(scheduledProg.remove());
		}
	}
	
	public void Execute() throws IOException {
			checkScheduled();
			System.out.println(readyQueue.size());
			while(readyQueue.size()>0 || blockedQueue.size()>0) {
				if(readyQueue.size()>0){
				System.out.println("-----We are now in" + CurrClock + "cycle-----");
				System.out.println("------------------------------------");
				display(readyQueue);
				System.out.println("are in ready queue and ");
				display(blockedQueue);
				System.out.println("are in blocked queue");
				display(finishedQueue);
				System.out.println("are finished.");
				System.out.println("------------------------------------");
				String currProg = readyQueue.remove();
				int position = SystemCalls.searchInMemory(currProg);
				if(position == -1) {
					modifyProcess.swapProcess(currProg);
					position = SystemCalls.searchInMemory(currProg);
				}

				for(int i = 0; i<timeSlice; i++) {
					
					if(Memory.memory[7 + position + Integer.parseInt(Memory.memory[position + 1])] == null) {
						Memory.memory[position + 2] = status.Finished.toString();
						System.out.println(currProg + "has finished");
						finishedQueue.add(currProg);
						modifyProcess.deleteProcess(currProg);
						break;	
					}
					
					Memory.memory[position + 2] = "running";
					display(Memory.memory);
					System.out.println("------------------------------------");
					String instruction = Memory.memory[7 + position + Integer.parseInt(Memory.memory[position + 1])];
					System.out.println("Program: " + currProg + "is executing");
					System.out.println("------------------------------------");
					System.out.println("Instruction: " + instruction + "is currently executed");
					Interpreter.differentiator(instruction, currProg); 
					CurrClock ++;
		
					String [] end = Memory.memory[position + 3].split(",");
					if(Integer.parseInt(end[1]) == (7 + position + Integer.parseInt(Memory.memory[position + 1])) ) {
						Memory.memory[position + 2] = status.Finished.toString();
						System.out.println(currProg + "has finished");
						finishedQueue.add(currProg);
						modifyProcess.deleteProcess(currProg);
						break;	
					}
					
					if(Memory.memory[position + 2].equals(status.Blocked.toString())) {
						Memory.memory[position + 1] = (Integer.parseInt(Memory.memory[position + 1]) + 1) + ""; 
						break;
					}
					
					Memory.memory[position + 1] = (Integer.parseInt(Memory.memory[position + 1]) + 1) + ""; 
					
				}
				checkScheduled();
				if(Memory.memory[position + 2] != null && Memory.memory[position + 2].equals(status.Ready.toString()))
					readyQueue.add(currProg);
				
		}
				else {
					display(Memory.memory);
					System.out.println("------------------------------------");
					System.out.println("We are now in" + CurrClock + "cycle");
					System.out.println("------------------------------------");
					display(readyQueue);
					System.out.println("are ready queue, ");
					display(blockedQueue);
					System.out.println("are blocked and ");
					display(finishedQueue);
					System.out.println("are finished.");
					System.out.println("------------------------------------");
					
					CurrClock ++;
					checkScheduled();
				}
			}
			
	}
	
	public static void main(String[] args) throws IOException{
		
		String[] Program_1 = {"Program_1", "0"};
        String[] Program_2 = {"Program_2", "1"};
        String[] Program_3 = {"Program_3", "4"};
 
        Queue<String[]> programs = new LinkedList<>();
        programs.add(Program_1);
        programs.add(Program_2);
        programs.add(Program_3);
        
        schedular s = new schedular(2,programs);
        s.Execute();
        
        
    }
	
	
}
 