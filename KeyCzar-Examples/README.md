# Generating Containers

## The Keyczar Tool JAR
Main class

    org.keyczar.KeyczarTool

## HMAC Keyset
Generate the keyset (container)

    java -jar KeyczarTool.jar create --location=keyset-hmac --purpose=sign


## AES Symmetric Keyset
Generate the keyset (container)

    java -jar KeyczarTool.jar create --location=keyset-aes --purpose=crypt --name=TestAESCrypting
    
Add a primary key

    java -jar KeyczarTool.jar addkey --location=keyset-aes --status=primary
    
Add an active (non-primary) key

    KeyczarTool addkey --location=/path/to/keyset


## Asymmetric
Create an asymmetric signing (DSA) keyset:

java -jar KeyczarTool.jar create --location=/path/to/keyset --purpose=sign --asymmetric=dsa

KeyczarTool addkey --location=/path/to/keyset --status=primary