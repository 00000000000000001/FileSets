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
		FileSetsForm.lMengen.setText(view.toString());

	}

}
