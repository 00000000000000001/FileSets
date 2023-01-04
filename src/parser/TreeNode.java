package parser;

import mvc.FileSetsModel;
import mvc.SetController;

public abstract class TreeNode {
	FileSetsModel fileSetsModel;
	TreeNode left;
	TreeNode right;

	public TreeNode(TreeNode a, TreeNode b) {
		left = a;
		right = b;
	}
	public abstract String toString();
	public abstract SetController eval();
}
