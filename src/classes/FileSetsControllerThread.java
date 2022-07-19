package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileSetsControllerThread implements Runnable{
	FileSetsControllerIF fileSetsController;
	String path;
	
	
	
	public FileSetsControllerThread(FileSetsControllerIF fileSetsController, String path) {
		super();
		this.fileSetsController = fileSetsController;
		this.path = path;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("analyzing: " + path);
			fileSetsController.create(path);
			System.out.println("done: " + path);
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
