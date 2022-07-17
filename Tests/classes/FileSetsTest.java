package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileSetsTest {
	FileSetsControllerIF fileSetsController;
	String rootDir = MengeController.PATH_SELF;

	@BeforeEach
	void setUp() throws Exception {
		fileSetsController = new FileSetsController();
	}
	
	@Test
	void created_set_has_name() throws NoSuchAlgorithmException, IOException {
		String expected = "A";
		assertEquals(expected, fileSetsController.create().getMengeModel().getName());
	}
	
	@Test
	void created_set_has_unique_name() throws NoSuchAlgorithmException, IOException {
		List<String> names = new LinkedList<>();
		for (int i = 0; i < 1000; ++i) {
			MengeModel mengeModel = fileSetsController.create().getMengeModel();
			assertFalse(names.contains(mengeModel.getName()));
			names.add(mengeModel.getName());
		}
	}
	
	@Test
	void apply_union_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");
		// Menge B
		MengeControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		MengeControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, "union");

		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String hash4 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", " + rootDir + "/Test/file_2),"
				+ "(" + hash4 + ", " + rootDir + "/file_0),"
				+ "(" + hash1 + ", " + rootDir + "/Test/file_1),"
				+ "(" + hash3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void apply_intersect_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");
		menge_A.add("./file_0_Kopie");
		// Menge B
		MengeControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		MengeControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, FileSetsModel.OPERATION_INTERSECT);
		
		String hash4 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String expected = "{"
				+ "(" + hash4 + ", " + rootDir + "/file_0_Kopie)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void apply_subtract_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");
		menge_A.add("./file_0_Kopie");
		// Menge B
		MengeControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		MengeControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, FileSetsModel.OPERATION_SUBTRACT);

		String hash1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		String hash2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
		String hash3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		String expected = "{"
				+ "(" + hash2 + ", " + rootDir + "/Test/file_2),"
				+ "(" + hash1 + ", " + rootDir + "/Test/file_1),"
				+ "(" + hash3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void get_set_by_name() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");		
		assertSame(menge_A, fileSetsController.get("A"));
	}
	
	@Test
	void get_set_by_false_name() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");		
		assertSame(null, fileSetsController.get("B"));
	}
	
	@Test
	void get_set_by_name_amongst_others() throws NoSuchAlgorithmException, IOException {
		// Menge A
		MengeControllerIF menge_A = fileSetsController.create("./Test");
		// Menge B
		MengeControllerIF menge_B = fileSetsController.create("./Test");
		assertSame(menge_B, fileSetsController.get("B"));
	}

}
