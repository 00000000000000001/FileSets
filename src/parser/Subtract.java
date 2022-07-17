package parser;

import classes.FileSetsForm;
import classes.MengeControllerIF;

public class Subtract extends TreeNode {
	
	public Subtract(TreeNode a, TreeNode b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		return "(" + left.toString() + " - " + right.toString() + ")"; // recursive calls to print the sub-trees
	}

	@Override
	public MengeControllerIF eval() {
		return FileSetsForm.fileSetsController.operation(left.eval(), right.eval(), "subtract");
	}
}
