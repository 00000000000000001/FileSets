package parser;

import classes.FileSetsForm;
import classes.SetControllerIF;

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
	public SetControllerIF eval() {
		// TODO Auto-generated method stub
		return FileSetsForm.fileSetsController.get(name);
	}
}
