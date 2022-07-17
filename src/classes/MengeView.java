package classes;

public class MengeView extends View{

	@Override
	public void update() {
		// TODO display set
		view = new StringBuilder("");
		view.append(((MengeModel) this.model).toString());

		view = new StringBuilder("");
		for (String val : ((MengeModel) model).getDict().values()) {
			view.append(val);
			view.append('\n');
		}
		FileSetsForm.lMenge.setText(view.toString());
		
	}

}
