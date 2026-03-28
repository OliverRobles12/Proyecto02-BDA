
package utilerias;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author oliro
 */
public class Encriptador {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "Jg3iK19x4hB8m4FY".getBytes();
    
    public static String convertToDatabaseColumn(String sensitiveData) {
        if(sensitiveData == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY, "AES"));
            return Base64.getEncoder().encodeToString(cipher.doFinal(sensitiveData.getBytes()));
        } catch (Exception ex) {
            throw new RuntimeException("Errir al cifrar: " + ex.getMessage());
        }
    }
    
}
