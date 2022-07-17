package classes;

import java.util.HashMap;
import java.util.Map;

public class FileSetsModel extends AbstractPublisher{
	private char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int maxID = -1;
	// ID => MengeControllerIF
	private Map<Integer, MengeControllerIF> mengen = new HashMap<>();
	public static final String OPERATION_UNION = "union";
	public static final String OPERATION_DIFFERENCE = "difference";
	public static final String OPERATION_INTERSECTION = "intersection";
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		for (MengeControllerIF menge : getMengen().values()) {
			str.append(menge.toString());
			str.append('\n');
		}
		// Komma löschen, wenn String Zeichen hat
		if (str.length() > 0) {
			str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}
	
	public char[] getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
		update();
	}
	public int getMaxID() {
		return maxID;
	}
	public void setMaxID(int maxID) {
		this.maxID = maxID;
		update();
	}
	public Map<Integer, MengeControllerIF> getMengen() {
		return mengen;
	}
	public void setMengen(Map<Integer, MengeControllerIF> mengen) {
		this.mengen = mengen;
		update();
	}
	public static String getOperationUnion() {
		return OPERATION_UNION;
	}
	public static String getOperationDifference() {
		return OPERATION_DIFFERENCE;
	}
	public static String getOperationIntersection() {
		return OPERATION_INTERSECTION;
	}
	
	
}
