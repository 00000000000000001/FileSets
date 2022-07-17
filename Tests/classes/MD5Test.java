package classes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MD5Test {
	MD5IF md5;

	@BeforeEach
	void setUp() throws Exception {
		md5 = new MD5();
	}

	@Test
	void md5_hash_of_a_file() throws NoSuchAlgorithmException, IOException {
		String filename = "/Users/jonasmager/eclipse-workspace/FileSets/Test/file_1";
		String expected = "52923b476602bdcb6506a6266637fc61".toUpperCase();
		assertEquals(expected, md5.file(filename));
	}

}
