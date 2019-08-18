package hebe.test;


import hebe.http.SignTransactionProcesses;
import hebe.crypto.Crypto;
import hebe.crypto.EncryptedData;
import hebe.util.Convert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class HebeCryPtoTest {

    @Test
    public void getConvertsRSToNumericAndBack() {
        long accountId = Convert.parseAccountId("HEBE-MRCC-2YLS-8M54-3CMAJ");
        String address = Convert.rsAccount(accountId);
        System.out.println(accountId);
        assertEquals(address, "HEBE-MRCC-2YLS-8M54-3CMAJ");
    }

    @Test
    public void getPublicKey() {
        byte[] publicKey = Crypto.getPublicKey("It was a bright cold day in April, and the clocks were striking thirteen.");
        String pkey = Convert.toHexString(publicKey);
        System.out.println(pkey);
        assertEquals(pkey, "1259ec21d31a30898d7cd1609f80d9668b4778e3d97e941044b39f0c44d2e51b");
    }

    @Test
    public void getAccountIdFromPkeyNumeric() {
        byte[] bytes = Convert.parseHexString("1259ec21d31a30898d7cd1609f80d9668b4778e3d97e941044b39f0c44d2e51b");
        byte[] publicKeyHash = Crypto.sha256().digest(bytes);
        String accountId = String.valueOf(Convert.fullHashToId(publicKeyHash));
        System.out.println(accountId);
        assertEquals(accountId, "1739068987193023818");
    }


    @Test
    public void getAccountIdFromPkey() {
        byte[] bytes = Convert.parseHexString("1259ec21d31a30898d7cd1609f80d9668b4778e3d97e941044b39f0c44d2e51b");
        byte[] publicKeyHash = Crypto.sha256().digest(bytes);
        long accountId = Convert.fullHashToId(publicKeyHash);
        String address = Convert.rsAccount(accountId);
        System.out.println(address);
        assertEquals(address, "HEBE-MRCC-2YLS-8M54-3CMAJ");
    }

    @Test
    public void signTransactionBytes(){
        String unsignHashString = "0010802c8805a0051dc37e55e94559b6e8ad45202ab8e87cbfd76fa2ee9920c4e12ee1d6535456179bac9ed2c24b5cdd00e1f5050000000000e1f50500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000007ed80e00c9449dcb24d260cf";
        String secretPhrase = "usually yard maybe limb peaceful curse serve peaceful nerve history crash bullet";
        SignTransactionProcesses signModel = new SignTransactionProcesses();
        String sign = signModel.signTransaction(unsignHashString, secretPhrase);
        System.out.println(sign);
        assertEquals(sign, "0010802c8805a0051dc37e55e94559b6e8ad45202ab8e87cbfd76fa2ee9920c4e12ee1d6535456179bac9ed2c24b5cdd00e1f5050000000000e1f505000000000000000000000000000000000000000000000000000000000000000000000000c34622d13d2962386825f923198cd7dd94e65ae67d97927dbe843939aa0a9e0efe37038d4ee0e55599344cb83f4f28207175075e7b2dfb84521d8689ee4dcaca000000007ed80e00c9449dcb24d260cf");
    }



    @Test
    public void encryptBytes(){
        String secretPhraseRecp = "It was a bright cold day in April, and the clocks were striking thirteen.";
        String publicKeyRecp = "1259ec21d31a30898d7cd1609f80d9668b4778e3d97e941044b39f0c44d2e51b";
        String secretPhraseSender = "test";
        String publicKeySender = "d9d5c57971eefb085e3abaf7a5a4a6cdb8185f30105583cdb09ad8f61886ec65";
        String message = "hello world";
        byte[] messageBytes = Convert.toBytes(message);
        byte[] theirPublicKey = Convert.parseHexString(publicKeyRecp);
        EncryptedData encrypt = EncryptedData.encrypt(messageBytes, secretPhraseSender, theirPublicKey);
        System.out.println(encrypt.toString());
        byte[] theirPublicKey1 = Convert.parseHexString( publicKeySender);
        EncryptedData encryptedData = new EncryptedData(encrypt.getData(),encrypt.getNonce());
        byte[] decrypt = encryptedData.decrypt(secretPhraseRecp, theirPublicKey1);
        String s1 = Convert.toString(decrypt);
        System.out.println(s1);

    }

    @Test
    public void createToken(){
        byte[] mystring = Convert.toBytes("hello world");
        byte[] mypassphrase = Convert.parseHexString("1259ec21d31a30898d7cd1609f80d9668b4778e3d97e941044b39f0c44d2e51b");
        byte[] bytes = Crypto.aesEncrypt(mystring, mypassphrase);
        String token = Convert.toHexString(bytes);
        System.out.println(token);
        byte[] deToken = Convert.parseHexString(token);
        byte[] bytes1 = Crypto.aesDecrypt(deToken, mypassphrase);
        String s = Convert.toString(bytes1);
        System.out.println(s);
    }


}
