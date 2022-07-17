package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MengeViewTest {
	FileSetsControllerIF fileSetsController;
	String rootDir = MengeController.PATH_SELF;

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
		String expected = "A";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_two_new_empty_sets() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create();
		fileSetsController.create();
		String expected = "A\nB";
		assertEquals(expected, fileSetsController.getFileSetsView().toString());
	}
	
	@Test
	void view_new_set_from_file() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create("./file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "A";
		assertEquals(expected, fileSetsController.getFileSetsView().view.toString());
	}
	
	@Test
	void view_new_set_from_dir() throws NoSuchAlgorithmException, IOException {
		fileSetsController.create("./Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "A";
		assertEquals(expected, fileSetsController.getFileSetsView().view.toString());
	}

}
