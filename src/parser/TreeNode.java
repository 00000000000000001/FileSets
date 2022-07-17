package parser;

public abstract class TreeNode {
	TreeNode left;
	TreeNode right;

	public TreeNode(TreeNode a, TreeNode b) {
		left = a;
		right = b;
	}
	public abstract String toString();
	public abstract Menge eval();
}
