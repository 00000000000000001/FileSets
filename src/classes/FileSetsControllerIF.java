package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface FileSetsControllerIF {
	public SetControllerIF create() throws NoSuchAlgorithmException, IOException;
	
	public SetControllerIF create(String filename) throws NoSuchAlgorithmException, IOException;

	public SetControllerIF operation(SetControllerIF menge_A, SetControllerIF menge_B, String operation);
	
	public FileSetsView getFileSetsView();

	public SetControllerIF get(String name);

}
