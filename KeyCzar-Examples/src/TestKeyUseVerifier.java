import org.keyczar.Signer;
import org.keyczar.Verifier;
import org.keyczar.exceptions.KeyczarException;

/**
 * Programatically create keysets and keys
 */
public class TestKeyUseVerifier {
    public static void main(String[] args) throws KeyczarException {
        Verifier verifier = new Verifier("keyset-hmac-fromcodeDSA");
        String signature = "AD2A4LUwLAIUOqcBTVm3OS6gZmeiiWjdtndB7OECFAZ44-GMKLwKDEnwtZTIR_zdZBl-";
        boolean verified = verifier.verify("Some data to sign", signature);
        System.out.println("Verifying...");
        System.out.println(verified);
    }
}
