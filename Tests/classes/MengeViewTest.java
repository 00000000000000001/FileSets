package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MengeViewTest {
	FileSetsControllerIF fileSetsController;

	@BeforeEach
	void setUp() throws Exception {
		fileSetsController = new FileSetsController();
	}

	@Test
	void view_empty_fileSets() {
		String expected = "";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_new_empty_set() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create();
		String expected = "{}";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_two_new_empty_sets() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create();
		fileSetsController.create();
		String expected = "{}\n"
				+ "{}";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_new_set_from_file() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create("./file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)}";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_new_set_from_dir() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create("./Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_2),"
				+ "(" + hash1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + hash3 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}

}
