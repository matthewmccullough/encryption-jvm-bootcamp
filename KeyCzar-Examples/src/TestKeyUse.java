import org.keyczar.Encrypter;
import org.keyczar.KeyczarTool;
import org.keyczar.exceptions.KeyczarException;

/**
 * Programatically create keysets and keys
 */
public class TestKeyUse {
    public static void main(String[] args) throws KeyczarException {
        //Use the keys

        Encrypter encrypter = new Encrypter("keyset-hmac-fromcodeRSATOENC");

        //Base64 encoded on output
        String ciphertext = encrypter.encrypt("This is a test from Matthew");
        System.out.println(ciphertext);
    }
}
