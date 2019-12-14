package hebe.util;


import party.loveit.bip44forjava.utils.MnemonicCode;

import java.security.SecureRandom;
import java.util.List;


public final class Bip44Utils {
    private Bip44Utils() {
    }

    /**
     * generateMnemonic 12 Words
     *
     * @param
     * @return
     * @throws Exception
     */
    public static String generateMnemonicWords() throws Exception {
        MnemonicCode mnemonicCode = new MnemonicCode();

        SecureRandom secureRandom = SecureRandomUtils.secureRandom();
        byte[] initialEntropy = new byte[16];
        secureRandom.nextBytes(initialEntropy);
        List<String> wd = mnemonicCode.toMnemonic(initialEntropy);
        if (wd == null || wd.size() != 12)
            throw new RuntimeException("generate word error");
        else {
            String join = String.join(" ",wd);
            return join;
        }
    }
}
