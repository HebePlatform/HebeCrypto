package hebe.util;

import java.security.SecureRandom;

/**
 * Utility class for working with SecureRandom implementation.
 *
 * <p>This is to address issues with SecureRandom on Android. For more information, refer to the
 * following <a href="https://github.com/web3j/web3j/issues/146">issue</a>.
 */
final  class SecureRandomUtils {

    private static final SecureRandom SECURE_RANDOM;

    static {
        SECURE_RANDOM = new SecureRandom();
    }

    public static SecureRandom secureRandom() {
        return SECURE_RANDOM;
    }

    // Taken from BitcoinJ implementation


    private SecureRandomUtils() { }
}
