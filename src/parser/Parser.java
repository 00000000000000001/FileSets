package parser;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
	String equation;
	private String nextToken;
	public TreeNode resultTree;
	Scanner sc;
	
	public Parser(String string) {
		equation = string;
		sc = new Scanner(equation);
	}

	public void scanToken() {
		if (sc.hasNext()) {
			setNextToken(sc.next());
		} else {
			setNextToken("");
		}
	}
	
	public TreeNode parseS() {
		TreeNode a = parseF();
		while (true) {
			if (getNextToken().equals("+")) {
				scanToken();
				TreeNode b = parseF();
				a = new Union(a,b);
			} else if (getNextToken().equals("-")) {
				scanToken();
				TreeNode b = parseF();
				a = new Subtract(a,b);
			} else if (getNextToken().equals("&")) {
				scanToken();
				TreeNode b = parseF();
				a = new Intersect(a,b);
			} else {
				return a;
			}
		}
		
	}
	
	TreeNode parseF() {
		if (Pattern.matches("\\w+", getNextToken())) {
			TreeNode a =  new ID(getNextToken());
			scanToken();
			return a;
		} else if (getNextToken().equals("(")) {
			scanToken();
			TreeNode a = parseS();
			if (a == null) {
				return null;
			}
			if (getNextToken().equals(")")) {
				scanToken();
				return a;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Parser parser = new Parser("A + ( B - C ) & A");
		
		parser.scanToken();
		parser.resultTree = parser.parseS();
		
		if (parser.getNextToken() != "") {
			System.out.println("Input-String fehlerhaft. Enthält: " + parser.getNextToken());
		}
		
		System.out.println(parser.resultTree.toString());
		
		System.out.println(parser.resultTree.eval());
		
	}

	public String getNextToken() {
		return nextToken;
	}

	public void setNextToken(String nextToken) {
		this.nextToken = nextToken;
	}
	
}
