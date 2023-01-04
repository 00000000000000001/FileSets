package mvc;

import java.util.LinkedList;
import java.util.List;

public class FileSetsModel extends Publisher{
	private static FileSetsModel fileSetsModel;
	List<SetController> list = new LinkedList<>();
	private FileSetsModel() {
		super();
	}
	
	public static FileSetsModel getInstance() {

		if (fileSetsModel == null) {
			return fileSetsModel = new FileSetsModel();
		} else {
			return fileSetsModel;
		}
	}

	public List<SetController> getList() {
		return list;
	}

	public void setList(List<SetController> list) {
		this.list = list;
		update();
	}
	
	
	
}
