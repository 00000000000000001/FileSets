package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements MD5IF {
    private static MD5 md5;
    MessageDigest md;

    private MD5() throws NoSuchAlgorithmException {
        super();
        md = MessageDigest.getInstance("MD5");
    }

    public static MD5 getInstance() throws NoSuchAlgorithmException {
        if (md5 != null) {
            return md5;
        } else {
            return md5 = new MD5();
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
