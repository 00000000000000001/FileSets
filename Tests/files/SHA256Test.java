package files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SHA256Test {
	HashIF hash;
	
	@BeforeEach
	void setUp() throws Exception {
		hash = new Hash();
	}

	@Test
	void hash_of_a_file() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_1";
		String expected = "2b099725ffc1fbb920779295b9b39796ac058e2025340654031e471ea174b31a".toUpperCase();
		assertEquals(expected, hash.getSha256().file(filename));
	}
	
	@Test
	void returns_null_when_file_nonexistent() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_that_is_nonexistent";
		String expected = null;
		assertEquals(expected, hash.getSha256().file(filename));
	}

}
