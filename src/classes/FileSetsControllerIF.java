package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface FileSetsControllerIF {
	public MengeControllerIF create() throws NoSuchAlgorithmException, IOException;
	
	public MengeControllerIF create(String filename) throws NoSuchAlgorithmException, IOException;

	public MengeControllerIF operation(MengeControllerIF menge_A, MengeControllerIF menge_B, String operation);
	
	public FileSetsView getFileSetsView();

	public MengeControllerIF get(String name);

}
