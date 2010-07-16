To generate a new key pair and add it to the keystore:
keytool -genkeypair -alias matthew -keystore mybigjks.keystore -keyalg "RSA"
keytool -genkeypair -alias matthew -keystore mybigjks.keystore -keyalg DSA


#To generate a new symmetric key and add it to the keystore:
 #Won't work due to jks keystore type
keytool -genseckey -alias matthew -keystore mybig.keystore -keyalg "DES"
 #Needs keystore type specified as JCEKS
keytool -genseckey -alias matthew -keystore mybig.keystore -keyalg "DES" -storetype JCEKS
keytool -genseckey -alias matthew -keystore mybig.keystore -keyalg "AES" -keysize 192 -storetype JCEKS

#To list the existing entries in the keystore:
keytool -list -keystore FILENAME

