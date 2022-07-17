package parser;

import classes.FileSetsController;
import classes.MengeControllerIF;

public abstract class TreeNode {
	FileSetsController fileSetsController;
	TreeNode left;
	TreeNode right;

	public TreeNode(TreeNode a, TreeNode b) {
		left = a;
		right = b;
	}
	public abstract String toString();
	public abstract MengeControllerIF eval();
}
