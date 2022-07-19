package classes;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MengeTest {
	SetControllerIF mengeController;
	String rootDir = SetController.PATH_SELF;

	@BeforeEach
	void setUp() throws Exception {
		mengeController = new SetController();
	}

	@Test
	void empty_set_displayed_correctly() {
		String expected = "{}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_constructor_relative() throws NoSuchAlgorithmException, IOException {
		mengeController = new SetController("./file_0");
//		String md5_0 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_0 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{"
				+ "(" + sha256_0 + ", " + rootDir + "/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_constructor_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController = new SetController("/Users/jonasmager/eclipse-workspace/FileSets/file_0");
//		String md5_0 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_0 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{"
				+ "(" + sha256_0 + ", /Users/jonasmager/eclipse-workspace/FileSets/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_constructor_relative() throws NoSuchAlgorithmException, IOException {
		mengeController = new SetController("./Test");
		String md5_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String md5_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String md5_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		String expected = "{"
				+ "(" + md5_1 + ", " + rootDir + "/Test/file_1),\n"
				+ "(" + md5_2 + ", " + rootDir + "/Test/file_2),\n"
				+ "(" + md5_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_constructor_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController = new SetController(rootDir + "/Test");
		String md5_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String md5_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String md5_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		String expected = "{"
				+ "(" + md5_1 + ", " + rootDir + "/Test/file_1),\n"
				+ "(" + md5_2 + ", " + rootDir + "/Test/file_2),\n"
				+ "(" + md5_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_an_existing_set_relative() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./file_0");
//		String md5_0 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_0 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{"
				+ "(" + sha256_0 + ", " + rootDir + "/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_file_to_an_existing_set_absolute() throws NoSuchAlgorithmException, IOException {
		mengeController.add(rootDir + "/file_0");
//		String md5_0 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_0 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{"
				+ "(" + sha256_0 + ", " + rootDir + "/file_0)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void add_a_folder_to_an_existing_set() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./Test");
		String md5_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String md5_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String md5_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		String expected = "{"
				+ "(" + md5_1 + ", " + rootDir + "/Test/file_1),\n"
				+ "(" + md5_2 + ", " + rootDir + "/Test/file_2),\n"
				+ "(" + md5_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void cant_add_the_same_file_twice() throws NoSuchAlgorithmException, IOException {
//		String md5_0 = "dcdf045fad6698a036cb97071796f6f2".toUpperCase();
		String sha256_0 = "d414c5050a369ccc1369d9d05817e7c633e81280e24073e894232c50e04edce2".toUpperCase();
		String expected = "{(" + sha256_0 + ", " + rootDir + "/file_0)}";
		mengeController.add("./file_0");
		mengeController.add("./file_0_Kopie");
		assertEquals(expected, mengeController.toString());
	}
	
	@Test
	void remove_any_item_by_hash() throws NoSuchAlgorithmException, IOException {
		mengeController.add("./Test");
		String md5_1 = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		String md5_2 = "5fd8f044f2c644eb4eb7b423e622a7043335fa764999f0b2cf0b5da0df420562".toUpperCase();
		String md5_3 = "1a012a4d58bed8cb3065d33cfe023280b72232972057f9f2e657d242b350ebd7".toUpperCase();
		String expected = "{"
				+ "(" + md5_2 + ", " + rootDir + "/Test/file_2),\n"
//				+ "(" + md5_1 + ", /Users/jonasmager/eclipse-workspace/FileSets/Test/file_1),"
				+ "(" + md5_3 + ", " + rootDir + "/Test/Subfolder/file_3)"
				+ "}";
		mengeController.remove(md5_1);
		assertEquals(expected, mengeController.toString());
	}

}
