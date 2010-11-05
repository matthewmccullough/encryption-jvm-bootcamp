#Retrieves the test file from the server
java -Djavax.net.ssl.trustStore=keys/sample.keystore com.ambientideas.encryption.HTTPClient
# If needed, add:
#  -Djavax.net.ssl.trustStorePassword=somepassword