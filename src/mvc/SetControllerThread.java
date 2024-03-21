package mvc;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import files.FileCounter;
import gui.GUI;

public class SetControllerThread implements Runnable {
	private SetController setController;
	private String path;	
	

	public SetControllerThread(SetController setController, String path) {
		super();
		this.setController = setController;
		this.path = path;
	}



	@Override
	public void run() {
		try {
			int n = FileCounter.countFiles(path);
			int oldMax = GUI.getInstance().getProgressBar().getMaximum();
		    GUI.getInstance().getProgressBar().setMaximum(oldMax + n);
			
			setController.analyze(path);	
			
			int newMax = GUI.getInstance().getProgressBar().getMaximum();
			GUI.getInstance().getProgressBar().setMaximum(newMax - n);
			int newVal = GUI.getInstance().getProgressBar().getValue();
			GUI.getInstance().getProgressBar().setValue(newVal - n);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
