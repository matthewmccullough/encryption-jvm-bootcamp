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
    java -jar KeyczarTool.jar create --location=keyset-hmacEC --purpose=sign --asymmetric=ec
    java -jar KeyczarTool.jar create --location=keyset-hmacECCRYPT --purpose=crypt --asymmetric=ec

Then adding keys

    java -jar KeyczarTool.jar addkey --location=keyset-hmacSHA1 --status=primary
    java -jar KeyczarTool.jar addkey --location=keyset-hmacRSA --status=primary
    java -jar KeyczarTool.jar addkey --location=keyset-hmacDSA --status=primary
    java -jar KeyczarTool.jar addkey --location=keyset-hmacEC --status=primary
    java -jar KeyczarTool.jar addkey --location=keyset-hmacECCRYPT --status=primary

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

    java -jar KeyczarTool.jar pubkey --location=keyset-rsa --destination=keyset-rsa-pubkeyexport

## Promoting and Demoting Keys
Promote active version 1 to primary:
    java -jar KeyczarTool.jar promote --location=keyset-rsa --version=1

• Demote primary version 1 back to active:
    java -jar KeyczarTool.jar demote --location=keyset-rsa --version=1

• Demote active version 1 to inactive:
    java -jar KeyczarTool.jar demote --location=keyset-rsa --version=1

• Revoke the inactive version 1:
    java -jar KeyczarTool.jar revoke --location=keyset-rsa --version=1


## Asymmetric
Create an asymmetric signing (DSA) keyset:

    java -jar KeyczarTool.jar create --location=keyset-dsa --purpose=sign --asymmetric=dsa
    
Populate the keyset with a DSA key

    java -jar KeyczarTool.jar addkey --location=keyset-dsa --status=primary