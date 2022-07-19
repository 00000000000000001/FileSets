package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SetControllerIF {
	public boolean contains(String hash);
	public void add(String name) throws NoSuchAlgorithmException, IOException;
	public void add(String key, String value);
	public String remove(String hash);
	public SetModel getMengeModel();
	public void setName(String name);
	
}
