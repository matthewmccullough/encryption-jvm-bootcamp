import org.keyczar.KeyczarTool;
import org.keyczar.enums.Flag;
import org.keyczar.enums.KeyPurpose;

/**
 * Programatically create keysets and keys
 */
public class TestKeyGen {
    public static void main(String[] args) {
        // Create a keyset for HMAC signing keys
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcode", "--purpose=sign"});
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcode", "--purpose=sign"});
        --asymmetric=dsa
        // Can't call "create()" directly because it is a private method

        // Add an HMAC primary key
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcode"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcode", "--status=primary"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcode", "--status=active"});
    }
}
