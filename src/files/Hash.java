package files;

public class Hash implements HashIF {
	private MD5IF md5;
	private SHA256IF sha256;
	private SHA512IF sha512;

	@Override
	public MD5IF getMd5() {
		if (md5 == null) {
			return md5 = new MD5();
		}
		return md5;
	}

	@Override
	public SHA256IF getSha256() {
		if (sha256 == null) {
			return sha256 = new SHA256();
		}
		return sha256;
	}
	
	@Override
	public SHA512IF getSha512() {
		if (sha512 == null) {
			return sha512 = new SHA512();
		}
		return sha512;
	}

	
	
}
