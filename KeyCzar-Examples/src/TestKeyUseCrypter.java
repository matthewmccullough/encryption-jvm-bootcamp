import org.keyczar.Crypter;
import org.keyczar.Encrypter;
import org.keyczar.exceptions.KeyczarException;

/**
 * Programatically create keysets and keys
 */
public class TestKeyUseCrypter {
    public static void main(String[] args) throws KeyczarException {
        //Use the keys

        Crypter crypter = new Crypter("keyset-hmac-fromcodeRSATOENC");

        //Base64 encoded on output
        String plaintext = crypter.decrypt("AEntN-BIuDlJjsHOLfYGfc6T7DFqaBqC4dUXnpzuJ8J"+
                "_JfIHoClDqlXIz_nSkXpyYVfWLqCiRfpDTObCSJB7rkHfgU6OTGfCHYV3EaOBwMDNtslEo4"+
                "qpzgjJbcyNnbEK34giVW5szXtCvqiiAxhB9ZtSTFT8d2mQz8Scwc2Vd4S4OHvfVZ8oxRUpE"+
                "LZ3c9QahfV3RtcKu-S_gKqRq6nzMHO9Ek9P22ZwNLRUEgkr16gn8tkNEyew08bNA4n0bQWp"+
                "UJCfclhlPu1MTNzrepFgB-JjNEl-hFJUTc4jTga2Ntsn4PD8DaBhktZo5pE8rpSOD0NOqlu"+
                "5zUxwz4uRXbMv-5Gk8a8X");
        System.out.println(plaintext);
    }
}
