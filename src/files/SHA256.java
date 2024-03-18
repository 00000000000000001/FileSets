package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements SHA256IF {
    private static SHA256 sha256;
    MessageDigest md;

    private SHA256() throws NoSuchAlgorithmException {
        super();
        md = MessageDigest.getInstance("SHA-256");
    }

    public static SHA256 getInstance() throws NoSuchAlgorithmException {
        if (sha256 != null) {
            return sha256;
        } else {
            return sha256 = new SHA256();
        }
    }

    @Override
    public String file(String filename) throws NoSuchAlgorithmException, IOException {
        String checksum = null;

        if (Files.exists(Paths.get(filename))) {
            md.update(Files.readAllBytes(Paths.get(filename)));
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                hexString.append(String.format("%02X", digest[i]));
            }
            checksum = hexString.toString();

        }

        return checksum;
    }

}
