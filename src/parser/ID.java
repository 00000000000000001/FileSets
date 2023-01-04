package parser;

import mvc.FileSetsController;
import mvc.SetController;

public class ID extends TreeNode {
	String name;

	public ID(TreeNode a, TreeNode b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}
	
	public ID(String name) {
		this(null, null);
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}

	@Override
	public SetController eval() {
		return FileSetsController.getInstance().get(name);
	}
}
