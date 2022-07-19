package files;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SHA256IF {
	/**
	 * Calculates SHA-256 hash of given file.
	 * @param filename	Path of file as a string.
	 * @return	SHA-256 hash as a string. Null if file is nonexistent.
	 */
	String file(String filename) throws NoSuchAlgorithmException, IOException;
}
