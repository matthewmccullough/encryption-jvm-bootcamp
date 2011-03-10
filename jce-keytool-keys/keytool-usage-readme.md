# Keytool Usage
http://download.oracle.com/javase/6/docs/technotes/tools/windows/keytool.html

Passwords are either 123456 or password

## Types of Keystore
* JKS (the default)
* JCEKS
* PCKS12 (read only)

### Keystore File Format Facts
* A JKS keystore can only store private keys (as a part of public keys) and certificate entries
* JCEKS keystore can additionally store secret (symmetric) key entries such as AES and DES.
* There is read-only support for keystores of type PKCS12.  PKCS12 is most commonly used for importing of Netscape and MSIE browser certificates.


## Key Pair Generation
To generate a new key pair and add it to the keystore:
    keytool -genkeypair -keystore mybigjks.keystore -keyalg DSA
Or quoted:
    keytool -genkeypair -keystore mybigjks.keystore -keyalg "RSA"


## Symmetric Key Generation
The following commands generate new symmetric keys and add them to a keystore.

Fails due to defaulting to JKS keystore type
    keytool -genseckey -keystore mybig.keystore -keyalg "DES"

Succeeds with keystore type specified as JCEKS
    keytool -genseckey -keystore mybig.keystore -keyalg "DES" -storetype JCEKS

Symmetric key AES key generation with specific key size
    keytool -genseckey -keystore mybig.keystore -keyalg "AES" -keysize 192 -storetype JCEKS

Without an alias, keygen defaults to mykey
    keytool -genseckey -keystore mybig.keystore -keyalg "AES" -keysize 256 -storetype JCEKS
We can show the default alias of a key is "mykey" by listing the keys
    keytool -list -keystore mybig.keystore -storetype JCEKS
With an alias, we control the key's name in the store
    keytool -genseckey -alias matthew -keystore mybig.keystore -keyalg "AES" -keysize 256 -storetype JCEKS
    
## Default Keystore Filename
If no filename is given, keytool will operate against a file named `~/.keystore`