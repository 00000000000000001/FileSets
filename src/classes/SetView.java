package classes;

public class SetView extends View{

	@Override
	public void update() {
		// TODO display set
		view = new StringBuilder("");
		view.append(((SetModel) this.model).toString());

		view = new StringBuilder("");
		for (String val : ((SetModel) model).getDict().values()) {
			view.append(val);
			view.append('\n');
		}
		// ether way is not threadsafe
//		FileSetsForm.lMenge.setText(view.toString());
		FileSetsForm.lMenge.setText(null);
		FileSetsForm.lMenge.append(view.toString());
	}

}
