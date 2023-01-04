package parser;

import mvc.FileSetsController;
import mvc.SetController;

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
	public SetController eval() {
		return FileSetsController.getInstance().operation(left.eval(), right.eval(), "subtract");
	}
}
