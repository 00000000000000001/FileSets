package parser;

import classes.FileSetsForm;
import classes.SetControllerIF;

public class Union extends TreeNode {
	

	public Union(TreeNode a, TreeNode b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		return "(" + left.toString() + " + " + right.toString() + ")"; // recursive calls to print the sub-trees
	}

	@Override
	public SetControllerIF eval() {
		return FileSetsForm.fileSetsController.operation(left.eval(), right.eval(), "union");
	}
}
