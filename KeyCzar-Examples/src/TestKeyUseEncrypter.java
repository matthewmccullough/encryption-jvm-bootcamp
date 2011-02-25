import org.keyczar.Encrypter;
import org.keyczar.KeyczarTool;
import org.keyczar.exceptions.KeyczarException;

/**
 * Encrypt data
 */
public class TestKeyUseEncrypter {
    public static void main(String[] args) throws KeyczarException {
        Encrypter encrypter = new Encrypter("keyset-hmac-fromcodeRSATOENC");

        //Base64 encoded on output
        String ciphertext = encrypter.encrypt("This is a test from Matthew");
        System.out.println(ciphertext);
    }
}
