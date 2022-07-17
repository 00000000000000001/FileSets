package classes;

public class Sandbox {
	char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	int maxID = -1;

	public static void main(String[] args) {
		Sandbox sandbox = new Sandbox();
		// TODO Auto-generated method stub
		/*
		 * 0 => A
		 * 1 => B
		 * ...
		 * 25 => Z
		 * 26 => AA
		 * 27 => BB
		 * ...
		 * 51 = ZZZ
		 * 52 => AAA
		 * ...
		 */
		StringBuilder name;
		while (sandbox.maxID < 51) {
			name = new StringBuilder("");
			++sandbox.maxID;
			char c = sandbox.alphabet[( sandbox.maxID % 26 )];
			name.append(c);
			for (int i = 0; i < ( sandbox.maxID / 26 ); ++i) {
				name.append(c);
			}
			System.out.println(name);
		}
		
		
	}

}
