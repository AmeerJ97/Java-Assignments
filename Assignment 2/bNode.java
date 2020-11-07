package listFileJ;

public class bNode extends listFile{
	public static final bNode right = null;
	public static final bNode left = null;
	String data;
	bNode next;
	
	public bNode(String data1){
		data = data1;
	}
String compare2strings(String s1, String s2){
		if (s1.equals(s2)) return s1;
		if (s1.compareTo(s2)>0){
			return s1;
		}
		else{			
			if (s2.compareTo(s1)>0) return s2;		
		}
		return "";		
	}
}
