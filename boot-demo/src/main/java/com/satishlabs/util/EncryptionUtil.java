package com.satishlabs.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	private static final String ALGORITHM = "AES";

    private static SecretKeySpec getAESKey(String key) {
        byte[] keyBytes = key.getBytes();
        byte[] paddedKey = new byte[16]; // 16 bytes for 128-bit AES
        System.arraycopy(keyBytes, 0, paddedKey, 0, Math.min(keyBytes.length, paddedKey.length));
        return new SecretKeySpec(paddedKey, ALGORITHM);
    }

    public static String encrypt(String data, String secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getAESKey(secretKey));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    public static String decrypt(String encryptedData, String secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getAESKey(secretKey));
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}
