package encryption;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SymetricEncryptionTest {

    static SecretKey generateKey() throws NoSuchAlgorithmException {
        var keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    static IvParameterSpec generateInitializationVector() {
        byte[] initVector = new byte[16];
        var secureRandom = new SecureRandom();
        secureRandom.nextBytes(initVector);
        return new IvParameterSpec(initVector);
    }

    static byte[] encrypt(String input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }

    static String decrypt(byte[] input, SecretKey secretKey, IvParameterSpec ivParameterSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return new String(cipher.doFinal(input));
    }

    @Test
    public void test_encrypt_and_decrypt_using_symetric_key() throws Exception {
        var myKey = generateKey();
        var myIvSpec = generateInitializationVector();
        var input = "mys3cr3tP@sswordNevaDie";
        var encrypted = encrypt(input, myKey, myIvSpec);
        var decrypted = decrypt(encrypted, myKey, myIvSpec);
        System.out.println(decrypted);
    }


}
