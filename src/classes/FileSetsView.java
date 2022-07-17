package classes;

public class FileSetsView extends View{

	@Override
	public void update() {
		// display sets
		view = new StringBuilder("");
		for (MengeControllerIF mengeController : ((FileSetsModel) model).getMengen().values()) {
			view.append(mengeController.getMengeModel().getName());
			view.append('\n');
		}
		// remove last line break
		if (view.length() > 0) {
			view.deleteCharAt(view.length() - 1);
		}
		FileSetsForm.lMengen.setText(view.toString());

	}

}
