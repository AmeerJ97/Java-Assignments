package dec2base;
import acm.program.*;
public class dec2base extends ConsoleProgram{
	public void run(){
		while (true){//Loop to keep program running
		int number = readInt(" Enter Number to be Converted:");// decimal to be converted
		int base = readInt("Enter Base to Convert to:"); 
		if (number < 0) break;
		System.out.println("Java Base Conversion demo:");
		System.out.println (number + " is represented in base-"+base +  
				 " as " + reverseString(decimal2base(number, base)));
		}		
	}
	private String decimal2base(int n, int b){
		String LUT = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		int lastQ = n; 
		String result = "";
		while (lastQ > 0){
			int Q = lastQ / b;
			result += LUT.charAt(lastQ % b);
			lastQ = Q;
			}
		return result;
		}
	private String reverseString(String result){
		String reversed = "";
		for (int i = result.length() -1  ; i >= 0 ; i--){
			reversed = reversed + result.charAt(i);
		}
		return reversed;		
	}
}
