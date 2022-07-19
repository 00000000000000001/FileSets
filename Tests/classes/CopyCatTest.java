package classes;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import files.CopyCat;
import files.CopyCatIF;

class CopyCatTest {
	CopyCatIF copyCat;

	@BeforeEach
	void setUp() throws Exception {
		copyCat = new CopyCat();
	}

	@Test
	void copy_a_file() throws NoSuchAlgorithmException, IOException {
		MD5 md5 = new MD5();
		String dir = "./kopiert";
		String file = "File_kopiert";
		String target = dir + "/" + file;
		// Datei kopieren
		copyCat.cp("./File", target);
		// assertion
		assertEquals(md5.file("./File"), md5.file(target));
		// erstellten Ordner undkopierte Datei wieder löschen, damit der Test wiederholbar ist ;)
		copyCat.rm(dir);
		assertFalse(copyCat.exists(dir));
	}
	
	@Test
	void remove_a_file() throws NoSuchAlgorithmException, IOException {
		MD5 md5 = new MD5();
		// Datei kopieren
		copyCat.cp("./File", "./kopiert/File");
		// kopierte Datei wieder löschen, damit der Test wiederholbar ist ;)
		copyCat.rm("./kopiert/File");
		copyCat.rm("./kopiert");
		// assertion
		assertEquals(null, md5.file("./kopiert/File"));
		assertFalse(copyCat.exists("./kopiert"));
	}
	
	@Test
	void remove_a_dir() throws NoSuchAlgorithmException, IOException {
		String dir = "./mkdir_test";
		// Datei kopieren
		copyCat.mkdir(dir);
		assertTrue(copyCat.exists(dir));
		// kopierte Datei wieder löschen, damit der Test wiederholbar ist ;)
		copyCat.rm(dir);
		// assertion
		assertFalse(copyCat.exists(dir));
	}
	
	@Test
	void cp_returns_false_on_fail() throws NoSuchAlgorithmException, IOException {
		MD5 md5 = new MD5();
		// Datei kopieren
		// assertion
		assertEquals(false, copyCat.cp("./File_that_does_not_exist", "./kopiert/File"));
		assertEquals(null, md5.file("./kopiert/File"));
		assertFalse(copyCat.exists("./kopiert"));
	}
	
	@Test
	void rm_returns_false_on_fail() throws NoSuchAlgorithmException, IOException {
		assertEquals(false, copyCat.rm("./File_that_does_not_exist"));
	}
	
	@Test
	void exists_returns_true_if_file_exists() throws NoSuchAlgorithmException, IOException {
		assertTrue(copyCat.exists("./File"));
	}
	
	@Test
	void exists_returns_true_if_dir_exists() throws NoSuchAlgorithmException, IOException {
		assertTrue(copyCat.exists("./Files"));
	}
	
	@Test
	void exists_returns_false_if_dir_doesnt_exists() throws NoSuchAlgorithmException, IOException {
		assertFalse(copyCat.exists("./Files_that_doesnt_exist"));
	}
	
	@Test
	void exists_returns_false_if_file_doesnt_exists() throws NoSuchAlgorithmException, IOException {
		assertFalse(copyCat.exists("./File_that_doesnt_exist"));
	}
	
	@Test
	void create_dir_that_doesnt_exist() throws NoSuchAlgorithmException, IOException {
		String dir = "./dir_that_doesnt_exist";
		copyCat.mkdir(dir);
		assertTrue(copyCat.exists(dir));
		copyCat.rm(dir);
		assertFalse(copyCat.exists(dir));
		
	}
	
	@Test
	void fileOrDir_gets_file() {
		assertEquals('f', copyCat.fileOrDir("./File"));
	}
	
	@Test
	void fileOrDir_gets_dir() {
		assertEquals('d', copyCat.fileOrDir("./Files"));
	}
	
	@Test
	void fileOrDir_gets_nonexisting_path() {
		assertEquals(null, copyCat.fileOrDir("./nonexisting_path"));
	}

}
