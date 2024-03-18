package files;

import java.security.NoSuchAlgorithmException;

public class Hash implements HashIF {
	private static Hash hash;
	
	
	

	private Hash() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static Hash getInstance() {
		if (hash == null) {
			return hash = new Hash();
		} else {
			return hash;
		}
	}

	@Override
	public MD5IF getMd5() throws NoSuchAlgorithmException {
		return MD5.getInstance();
	}

	@Override
	public SHA256 getSha256() throws NoSuchAlgorithmException {
		return SHA256.getInstance();
	}
	
	@Override
	public SHA512 getSha512() throws NoSuchAlgorithmException {
		return SHA512.getInstance();
	}
	
	
}
