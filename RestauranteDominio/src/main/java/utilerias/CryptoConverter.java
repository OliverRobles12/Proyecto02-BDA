
package utilerias;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author oliro
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "Jg3iK19x4hB8m4FY".getBytes();
        
    @Override
    public String convertToDatabaseColumn(String sensitiveData) {
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

    @Override
    public String convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY, "AES"));
            return new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (Exception ex) {
            throw new RuntimeException("Errir al descifrar: " + ex.getMessage());
        }
    }
    
}
