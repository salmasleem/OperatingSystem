package milestone1;
import java.util.*;

public class Memory {
	static String[] memory = new String[40];
	
	
	public static int freeSpace() {
		
		if(memory[0] == null)
			return 0;
		else if (memory[20] == null)
			return 1;
		else
			return -1;
	}
	
	
}