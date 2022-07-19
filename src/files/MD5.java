package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5 implements MD5IF {

	@Override
	public String file(String filename) throws NoSuchAlgorithmException, IOException {
		String checksum = null;
		
		if (Files.exists(Paths.get(filename))) {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(Files.readAllBytes(Paths.get(filename)));
			byte[] digest = md.digest();
			checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
		}
		
		return checksum;
	}

}
