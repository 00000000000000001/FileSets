package files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SHA512Test {
	HashIF hash;
	
	@BeforeEach
	void setUp() throws Exception {
		hash = new Hash();
	}

	@Test
	void hash_of_a_file() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_1";
		String expected = "3d22b3b0a63c0449ee902463451c6548409453f922f54824f7876e90f30e1c97e8006b44cceedebf79c62c3192c49b1894f67aa0283067c3e3dea18ee6dc889d".toUpperCase();
		assertEquals(expected, hash.getSha512().file(filename));
	}
	
	@Test
	void returns_null_when_file_nonexistent() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_that_is_nonexistent";
		String expected = null;
		assertEquals(expected, hash.getSha512().file(filename));
	}

}
