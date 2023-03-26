package bttp;

public class BttpEncoder {
	public static String encode(String s) {
		return s.replace("\n", "##");
	}
	
	public static String decode(String s) {
		return s.replace("##", "\n");
	}
}
