package files;
import java.io.IOException;

public interface CopyCatIF {
	
	/**
	 * Copies a src file to destination.
	 * @param src	File to be copied.
	 * @param dest	Destination to copy to.
	 */
	boolean cp(String source, String target) throws IOException;

	/**
	 * Removes a File of given source.
	 * @param src	File to be removed.
	 */
	boolean rm(String source);
	
	/**
	 * Checks if given path exists.
	 * @param path	Wether a path to a file or a directory.
	 * @return	True if path exists.
	 */
	boolean exists(String path);

	/**
	 * Creates a directory by given parameter.
	 * @param dir	The directory to be created.
	 */
	void mkdir(String dir) throws IOException;
	
	/**
	 * Determines if given path is a file or a direction
	 * @param path
	 * @return	'f' for file, 'd' for directory, null if nonexistent
	 */
	Character fileOrDir(String path);

}
