package milestone1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class mutexes {
	static userInput userInput = new userInput(1,"program1");
	static userOutput userOutput = new userOutput(1,"program1");
	static file file = new file(1, "program1");
 
 static Queue <String> userInputBlockedQueue = new LinkedList<>();
 static Queue <String> userOutputBlockedQueue= new LinkedList<>();
 static Queue <String> fileBlockedQueue = new LinkedList<>();
 
public static void semWait(String resource, String currProcess) {
	
	int start = SystemCalls.searchInMemory(currProcess);
	if (resource.equals("userInput")) {
		if (milestone1.userInput.available==1) {
			milestone1.userInput.available = 0;
			milestone1.userInput.with= Memory.memory[start];
			Memory.memory[start+2]= status.Ready.toString();
		}
		else {
			schedular.blockedQueue.add(currProcess);
			userInputBlockedQueue.add(currProcess);
			Memory.memory[start+2] = status.Blocked.toString();
		}
	}
	if (resource.equals("userOutput")) {
		if (milestone1.userOutput.available==1) {
			milestone1.userOutput.available =0;
			milestone1.userOutput.with= Memory.memory[start];
			Memory.memory[start+2]= status.Ready.toString();
		}
		else {
			schedular.blockedQueue.add(currProcess);
			userOutputBlockedQueue.add(currProcess);
			Memory.memory[start+2] = status.Blocked.toString();
		}
	}
	if (resource.equals("file")) {
		if (milestone1.file.available==1) {
			milestone1.file.available =0;
			milestone1.file.with= Memory.memory[start];
			Memory.memory[start+2]= status.Ready.toString();
		}
		else {
			schedular.blockedQueue.add(currProcess);
			fileBlockedQueue.add(currProcess);
			Memory.memory[start+2] = status.Blocked.toString();
		}
	}
}

public static void semSignal(String resource, String currProcess) throws IOException {
	int start = SystemCalls.searchInMemory(currProcess);
	if (resource.equals("userInput") && Memory.memory[start].equals(milestone1.userInput.with)) {
		if(!userInputBlockedQueue.isEmpty()) {
			String blockedProc = userInputBlockedQueue.remove();
			milestone1.userInput.with = blockedProc;
			int found = SystemCalls.searchInMemory(blockedProc);
			if(found == -1) {
				String temp = disk.readFromDisk();
				String[] change = temp.split(":");
				change[2] = status.Ready.toString();
				temp = "";
				for(int i=0 ; i < change.length; i++)
					temp+= change[i] + ":";
				disk.writeToDisk(temp);
			}
			else
				Memory.memory[found + 2] = status.Ready.toString();
			schedular.readyQueue.add(blockedProc);
			while(true) {
				String temp = schedular.blockedQueue.remove();
				if(temp.equals(blockedProc)) {
					break;
				}
				else
					schedular.blockedQueue.add(temp);
			}
		}
		else 
			milestone1.userInput.available = 1;
	}
	else if (resource.equals("userOutput") && Memory.memory[start].equals(milestone1.userOutput.with)) {
		if(!userOutputBlockedQueue.isEmpty()) {
			String blockedProc = userOutputBlockedQueue.remove();
			milestone1.userOutput.with = blockedProc;
			int found = SystemCalls.searchInMemory(blockedProc);
			if(found == -1) {
				String temp = disk.readFromDisk();
				String[] change = temp.split(":");
				change[2] = status.Ready.toString();
				temp = "";
				for(int i=0 ; i < change.length; i++)
					temp+= change[i] + ":";
				disk.writeToDisk(temp);
			}
			else
				Memory.memory[found + 2] = status.Ready.toString();
			schedular.readyQueue.add(blockedProc);
			while(true) {
				String temp = schedular.blockedQueue.remove();
				if(temp.equals(blockedProc)) {
					break;
				}
				else
					schedular.blockedQueue.add(temp);
			}
		}
		else 
			milestone1.userOutput.available = 1;
	}
	
	else if (resource.equals("file") && Memory.memory[start].equals(milestone1.file.with)) {
		if(!fileBlockedQueue.isEmpty()) {
			String blockedProc = fileBlockedQueue.remove();
			milestone1.file.with = blockedProc;
			int found = SystemCalls.searchInMemory(blockedProc);
			if(found == -1) {
				String temp = disk.readFromDisk();
				String[] change = temp.split(":");
				
				change[2] = status.Ready.toString();
				temp = "";
				for(int i=0 ; i < change.length; i++)
					temp+= change[i] + ":";
				disk.writeToDisk(temp);
			}
			else
				Memory.memory[found + 2] = status.Ready.toString();
			schedular.readyQueue.add(blockedProc);
			while(true) {
				String temp = schedular.blockedQueue.remove();
				if(temp.equals(blockedProc)) {
					break;
				}
				else
					schedular.blockedQueue.add(temp);
			}
		}
		else 
			milestone1.file.available = 1;
	}
}

}

