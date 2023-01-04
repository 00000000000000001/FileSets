package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class SHA512 implements SHA512IF {
	private static SHA512 sha512;
	MessageDigest md;
	
	private SHA512() throws NoSuchAlgorithmException {
		super();
		md = MessageDigest.getInstance("SHA-512");
	}
	
	public static SHA512 getInstance() throws NoSuchAlgorithmException {
		if (sha512 != null) {
			return sha512;
		} else {
			return sha512 = new SHA512();
		}
	}
	
	@Override
	public String file(String filename) throws NoSuchAlgorithmException, IOException {
		String checksum = null;
		
		if (Files.exists(Paths.get(filename))) {
			
			
			md.update(Files.readAllBytes(Paths.get(filename)));
			byte[] digest = md.digest();
			checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
			
		}
		
		return checksum;
	}

}
