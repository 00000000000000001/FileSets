package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface MD5IF {
	/**
	 * Calculates md5 hash of given file.
	 * @param filename	Path of file as a string.
	 * @return	MD5 hash as a string.
	 */
	String file(String filename) throws NoSuchAlgorithmException, IOException;
	
}
