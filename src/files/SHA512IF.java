package files;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SHA512IF {
	/**
	 * Calculates SHA-512 hash of given file.
	 * @param filename	Path of file as a string.
	 * @return	SHA-512 hash as a string. Null if file is nonexistent.
	 */
	String file(String filename) throws NoSuchAlgorithmException, IOException;
}
