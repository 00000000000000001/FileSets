package mvc;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import gui.GUI;
import gui.StatusBars;

public class SetControllerThread implements Runnable {
	private SetController setController;
	private String path;
	private StatusBars statusBars = StatusBars.getInstance(GUI.getBottom_progessBar());
	
	

	public SetControllerThread(SetController setController, String path) {
		super();
		this.setController = setController;
		this.path = path;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			GUI.getInstance();
			StatusBars.getInstance(GUI.getBottom_progessBar()).start(this);
			System.out.println("analysing: " + path);
			setController.analyze(path);
			StatusBars.getInstance(GUI.getBottom_progessBar()).stop(this);
			System.out.println("done: " + path);
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
