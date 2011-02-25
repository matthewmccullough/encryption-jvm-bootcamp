import org.keyczar.Encrypter;
import org.keyczar.Signer;
import org.keyczar.exceptions.KeyczarException;

/**
 * Programatically create keysets and keys
 */
public class TestKeyUseSigner {
    public static void main(String[] args) throws KeyczarException {
        Signer signer = new Signer("keyset-hmac-fromcodeDSA");
        final String signature = signer.sign("Some data to sign");
        System.out.println("Signature: " + signature);

        System.out.println("Verifying...");
        boolean verified = signer.verify("Some data to sign", signature);

        System.out.println(verified);
    }
}
