# Generating Containers

## The Keyczar Tool JAR
Main class

    org.keyczar.KeyczarTool

## HMAC Keyset
Generate the keyset (container)

    java -jar KeyczarTool.jar create --location=keyset-hmac --purpose=sign
    # or
    java -jar KeyczarTool.jar create --location=keyset-hmacSHA1 --purpose=sign --name=TestSHA1HMAC
    java -jar KeyczarTool.jar create --location=keyset-hmacRSA --purpose=sign --asymmetric=rsa
    java -jar KeyczarTool.jar create --location=keyset-hmacDSA --purpose=sign --asymmetric=dsa


## AES Symmetric Keyset
Generate the keyset (container)

    java -jar KeyczarTool.jar create --location=keyset-aes --purpose=crypt --name=TestAESCrypting
    
Add a primary key

    java -jar KeyczarTool.jar addkey --location=keyset-aes --status=primary
    
Add an active (non-primary) key

    java -jar KeyczarTool.jar addkey --location=keyset-aes
    

## RSA Asymmetric Keyset
Generate the keyset (container)

    java -jar KeyczarTool.jar create --location=keyset-rsa --purpose=crypt --asymmetric=rsa

Add a primary key

    java -jar KeyczarTool.jar addkey --location=keyset-rsa --status=primary

Add an active (non-primary) key

    java -jar KeyczarTool.jar addkey --location=keyset-rsa


## Exporting Public Keys

Export public keys to an output folder

    java -jar KeyczarTool.jar pubkey --location=keyset-aes --destination=keyset-aes-pubkeyexport


## Asymmetric
Create an asymmetric signing (DSA) keyset:

java -jar KeyczarTool.jar create --location=/path/to/keyset --purpose=sign --asymmetric=dsa

KeyczarTool addkey --location=/path/to/keyset --status=primary