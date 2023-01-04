package mvc;

import javax.swing.JComponent;

abstract class View {
	Publisher model;
	JComponent component;
	
	public View(JComponent component) {
		super();
		this.component = component;
	}

	public void subscribe(Publisher model) {
		this.model = model;
		model.addView(this);
		update();
	}

	public abstract void update();
}
