package classes;

abstract class View {
	AbstractPublisher model;
	public StringBuilder view = new StringBuilder("");
	public void subscribe(AbstractPublisher model) {
		this.model = model;
		model.addView(this);
		update();
	}

	public abstract void update();

	@Override
	public String toString() {
		return view.toString();
	}
	
	
}
