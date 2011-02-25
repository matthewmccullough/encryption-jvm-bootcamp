import org.keyczar.KeyczarTool;
import org.keyczar.enums.Flag;
import org.keyczar.enums.KeyPurpose;

/**
 * Programatically create keysets and keys
 */
public class TestKeyGen {
    public static void main(String[] args) {
        // Create a keyset for HMAC signing keys
        // Can't call "create()" directly because it is a private method
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcodeSHA1", "--purpose=sign"});
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcodeRSA", "--purpose=sign", "--asymmetric=rsa"});
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcodeDSA", "--purpose=sign", "--asymmetric=dsa"});

        // Add an HMAC primary key
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeSHA1"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeSHA1", "--status=primary"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeSHA1", "--status=active"});

        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeRSA"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeRSA", "--status=primary"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeRSA", "--status=active"});

        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeDSA"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeDSA", "--status=primary"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeDSA", "--status=active"});

        //For encryption
        //Create keyset
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcodeRSATOENC", "--purpose=crypt", "--asymmetric=rsa"});
        org.keyczar.KeyczarTool.main(new String[]{"create", "--location=keyset-hmac-fromcodeECTOENC", "--purpose=crypt", "--asymmetric=ec"});
        //Gen keys
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeRSATOENC", "--status=primary"});
        org.keyczar.KeyczarTool.main(new String[]{"addkey", "--location=keyset-hmac-fromcodeECTOENC", "--status=primary"});

    }
}
