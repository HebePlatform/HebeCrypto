package hebe.http;

import hebe.crypto.Crypto;
import hebe.util.Convert;
import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

public class SignTransactionProcesses {

    public String signTransaction( String unsignedTransaction,String secretPhrase) {
        byte[] unsignedBytes = Convert.parseHexString(unsignedTransaction);


        byte[] signedTransaction = new byte[unsignedBytes.length];
        byte[] signature = sign(unsignedBytes,secretPhrase);
        byte [] signed = java.util.Arrays.copyOf(unsignedBytes ,96);
        byte [] signe2 = Arrays.copyOfRange(unsignedBytes ,160,unsignedBytes.length);
        byte[] bytes = ArrayUtils.addAll(signed, signature);
        byte[] signBytes = ArrayUtils.addAll(bytes, signe2);
        System.arraycopy(bytes, 0, signedTransaction, 96, 64);
        System.arraycopy(unsignedBytes, 0, signedTransaction, 0, unsignedBytes.length);
        System.arraycopy(signature, 0, signedTransaction, 96, 64);
        String sign =Convert.toHexString(signBytes);
        return sign;
    }

    public byte[] sign(byte[] bytes, String secretPhrase) {
        return Crypto.sign(bytes , secretPhrase);
    }
}
