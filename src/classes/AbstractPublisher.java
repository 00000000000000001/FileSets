package classes;

import java.util.ArrayList;
import java.util.List;


abstract class AbstractPublisher {
	private List<View> views = new ArrayList<View>();

	public void addView(View view) {
		this.views.add(view);
	}

	public void update() { // update all views
		for (View view : views)
			view.update();
	}
}
