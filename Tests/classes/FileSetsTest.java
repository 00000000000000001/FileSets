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
	String rootDir = SetController.PATH_SELF;

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
			SetModel mengeModel = fileSetsController.create().getMengeModel();
			assertFalse(names.contains(mengeModel.getName()));
			names.add(mengeModel.getName());
		}
	}
	
	@Test
	void apply_union_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		SetControllerIF menge_A = fileSetsController.create("./Test");
		// Menge B
		SetControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		SetControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, "union");

//		String md5_1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
//		String md5_2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
//		String md5_3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
//		String md5_4 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		
		String sha256_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String sha256_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String sha256_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		String sha256_4 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		
		String expected = "{"
				+ "(" + sha256_1 + ", " + rootDir + "/Test/file_1),\n"
				+ "(" + sha256_2 + ", " + rootDir + "/Test/file_2),\n"
				+ "(" + sha256_4 + ", " + rootDir + "/file_0),\n"
				+ "(" + sha256_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void apply_intersect_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		SetControllerIF menge_A = fileSetsController.create("./Test");
		menge_A.add("./file_0_Kopie");
		// Menge B
		SetControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		SetControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, FileSetsModel.OPERATION_INTERSECT);
		
//		String md5_4 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_4 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{"
				+ "(" + sha256_4 + ", " + rootDir + "/file_0_Kopie)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void apply_subtract_operation_to_file_sets() throws NoSuchAlgorithmException, IOException {
		// Menge A
		SetControllerIF menge_A = fileSetsController.create("./Test");
		menge_A.add("./file_0_Kopie");
		// Menge B
		SetControllerIF menge_B = fileSetsController.create("./file_0");
		// A & B
		SetControllerIF menge_C = fileSetsController.operation(menge_A, menge_B, FileSetsModel.OPERATION_SUBTRACT);

//		String md5_1 = "52923b476602bdcb6506a6266637fc61".toUpperCase();
//		String md5_2 = "81c4e909473316aa7c01cb25e6fbf1ca".toUpperCase();
//		String md5_3 = "43a3ebb60b443b6768b665e5ee56595e".toUpperCase();
		
		String sha256_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String sha256_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String sha256_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		
		String expected = "{"
				+ "(" + sha256_1 + ", " + rootDir + "/Test/file_1),\n"
				+ "(" + sha256_2 + ", " + rootDir + "/Test/file_2),\n"
				+ "(" + sha256_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		
		
		assertEquals(expected, menge_C.toString());
	}
	
	@Test
	void get_set_by_name() throws NoSuchAlgorithmException, IOException {
		// Menge A
		SetControllerIF menge_A = fileSetsController.create("./Test");		
		assertSame(menge_A, fileSetsController.get("A"));
	}
	
	@Test
	void get_set_by_false_name() throws NoSuchAlgorithmException, IOException {
		// Menge A
		fileSetsController.create("./Test");		
		assertSame(null, fileSetsController.get("B"));
	}
	
	@Test
	void get_set_by_name_amongst_others() throws NoSuchAlgorithmException, IOException {
		// Menge A
		fileSetsController.create("./Test");
		// Menge B
		SetControllerIF menge_B = fileSetsController.create("./Test");
		assertSame(menge_B, fileSetsController.get("B"));
	}

}
