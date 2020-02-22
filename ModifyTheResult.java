
public class ModifyTheResult {
	//Modify method could not be accessed when it was not static !
	public static String Modify(String result) { 
		String[] afterDot=result.split("\\.");
		if(afterDot[1].contentEquals("0")) {
			result=afterDot[0];
		}
		return result;
	}
	
}
