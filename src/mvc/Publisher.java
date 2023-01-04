package mvc;

import java.util.ArrayList;
import java.util.List;

public abstract class Publisher {
	private List<View> views = new ArrayList<View>();

	public void addView(View view) {
		this.views.add(view);
	}

	public void update() { // update all views
		for (View view : views)
			view.update();
	}
}
