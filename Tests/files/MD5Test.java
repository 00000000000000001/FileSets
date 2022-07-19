package files;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MD5Test {
	HashIF hash;
	
	@BeforeEach
	void setUp() throws Exception {
		hash = new Hash();
	}

	@Test
	void md5_hash_of_a_file() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_1";
		String expected = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		assertEquals(expected, hash.getMd5().file(filename));
	}
	
	@Test
	void returns_null_when_file_nonexistent() throws NoSuchAlgorithmException, IOException {
		String filename = "./Test/file_fivemillion";
		String expected = null;
		assertEquals(expected, hash.getMd5().file(filename));
	}

}
