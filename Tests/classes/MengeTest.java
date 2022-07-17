package classes;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MengeTest {
	MengeControllerIF mengeController;

	@BeforeEach
	void setUp() throws Exception {
		mengeController = new MengeController();
	}

	@Test
	void empty_set_displayed_correctly() {
		String expected = "{}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_constructor_relative() throws NoSuchAlgorithmException, IOException {
		mengeController = new MengeController("./file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_constructor_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController = new MengeController("/Users/jonasmager/eclipse-workspace/FileSets/file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_constructor_relative() throws NoSuchAlgorithmException, IOException {
		mengeController = new MengeController("./Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_2),"
				+ "(" + hash1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + hash3 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_constructor_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController = new MengeController("/Users/jonasmager/eclipse-workspace/FileSets/Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_2),"
				+ "(" + hash1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + hash3 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_an_existing_set_relative() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_an_existing_set_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController.add("/Users/jonasmager/eclipse-workspace/FileSets/file_0");
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_an_existing_set() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_2),"
				+ "(" + hash1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + hash3 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void cant_add_the_same_file_twice() throws NoSuchAlgorithmException, IOException {
		String hash = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{(" + hash + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)}";
		mengeController.add("./file_0");
		mengeController.add("./file_0_Kopie");
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void remove_any_item_by_hash() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./Test");
		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_2),"
//				+ "(" + hash1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + hash3 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/Subfolder/file_3)"
				+ "}";
		mengeController.remove(hash1);
		assertEquals(expected, mengeController.toString());
	}

}
