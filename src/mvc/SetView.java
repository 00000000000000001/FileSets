package mvc;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class SetView extends View{

	public SetView(JComponent component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		String text = ((SetModel) model).toString();
		((JTextArea) component).setText(text);
	}

}
