package milestone1;

public class PrintFromTo {

	public static void printFromTo(String processName, String variableName1, String variableName2) {
		int v1 = Integer.parseInt(SystemCalls.readFromMemory(processName, variableName1));
		int v2 = Integer.parseInt(SystemCalls.readFromMemory(processName, variableName2));
		if (v1 > v2) {
			int temp = v1;
			v1 = v2;
			v2 = temp;
		}
		while (v1 <= v2) {
			
			SystemCalls.print(v1 + "");
			v1++;
		}
		
		
		
	}
}
