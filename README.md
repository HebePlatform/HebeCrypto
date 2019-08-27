# HebeCrypto
The Java version of the HEBE tool ,You can download the jar to use locally

Usage:
<dependency>
    <groupId>com.Hebe</groupId>
    <artifactId>HebeCrypto</artifactId>
    <scope>system</scope>
    <systemPath>${basedir}/src/main/resources/libs/HebeCrypto-1.0.jar</systemPath>
</dependency>


Functions(Look for examples in test)

secretPhraseToPublicKey(secretPhrase)
Returns public key of a given passphrase

publicKeyToAccountId(publicKey, numeric)
Returns account ID of a given public key. Set the second parameter to true to get numeric account ID instead of RS format

secretPhraseToAccountId(secretPhrase, numeric)
Returns account ID of a given passphrase. Set the second parameter to true to get numeric account ID instead of RS format.

signTransactionBytes(unsignedTransactionBytes, secretPhrase)
Signs a hex string of unsigned transaction bytes (e.g. as received from NRS API) with the provided passphrase and returns it.

createToken(string, secretPhrase)
Generates a hebe cryptographic token

parseToken(token, string)
Parses a hebe cryptographic token. Returns an object with the keys isValid, timestamp, publicKey and accountRS.

encryptMessage(plainText, recipientPublicKey, senderSecretPhrase)
Returns an object with the keys nonce and message

decryptMessage(cipherText, nonce, senderPublicKey, recipientSecretPhrase)
Returns the deciphered text as string

rsConvert(address)
Converts address in numeric format to Reed-Solomon format, or vice versa. Returns an object with the keys account and accountRS
