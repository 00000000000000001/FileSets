package files;

import java.security.NoSuchAlgorithmException;

public interface HashIF {
	public MD5IF getMd5() throws NoSuchAlgorithmException;
	public SHA256IF getSha256() throws NoSuchAlgorithmException;
	public SHA512IF getSha512() throws NoSuchAlgorithmException;
}
