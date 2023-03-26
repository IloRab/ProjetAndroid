package bttp;

public class BttpEncoder {
	public static String encode(String s) {
		return s.replace("\n", "##");
	}
	
	public static String decode(String s) {
		return s.replace("##", "\n");
	}

	public static String end_sequence() {
		return "<end>";
	}

	public static String clear_end_sequence(String s){
		return s.replace(end_sequence(), "");
	}
}
