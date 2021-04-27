package com.capsilon.automation.aus.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class Encryptor {

    private static final String RSA_ECB_PKCS_1_PADDING = "RSA/ECB/PKCS1Padding";
    private static final String CANNOT_GENERATE_DV_OTC_DATA = "Cannot generate DV OTC data";
    private static final String AES = "AES";
    private static final String DECRYPT_CDV_TOKEN_ERROR = "DecryptCdvToken error";
    private static final String TRANSFORMATION_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String ENCRYPT_CDV_TOKEN_ERROR = "EncryptCdvToken error";
    private static final String FINGER_PRINTS_TEMPLATE = "{\"otcData\":{\"CDvToken\":\"\","
            + "\"deviceInfo\":[{\"entry\":[{\"string\":[\"identifierForVendor\",\"%s\"]},"
            + "{\"string\":[\"application\",\"%s\"]},{\"string\":[\"version\",\"version_num\"]}]}]}}";
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    static {
        Security.addProvider(provider);
    }

    public static String decryptToken(String encryptedData, String privateKey) {
        try {
            Cipher rsaCipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING, provider);
            rsaCipher.init(Cipher.DECRYPT_MODE, readPrivateKey(new ByteArrayInputStream(privateKey.getBytes())));
            byte[] abc = rsaCipher.doFinal(Hex.decodeHex(encryptedData.toCharArray()));
            return new String(abc);
        } catch (Exception var5) {
            throw new OtcGeneratorException(DECRYPT_CDV_TOKEN_ERROR, var5);
        }
    }

    public static String encryptToken(String dataToBeEncrypted, String publicKey) {
        try {
            Cipher rsaCipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING, provider);
            rsaCipher.init(Cipher.ENCRYPT_MODE, readPublicKey(new ByteArrayInputStream(publicKey.getBytes(StandardCharsets.UTF_8))));
            return new String(Hex.encodeHex(rsaCipher.doFinal(dataToBeEncrypted.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception var4) {
            throw new OtcGeneratorException(ENCRYPT_CDV_TOKEN_ERROR, var4);
        }
    }

    public static String getOtcData(String otcData, String randomSalt, String userPassword) {
        String otc1MD5 = toMD5(otcData);
        String uc1MD5 = toMD5(userPassword);
        String otc2MD5 = toMD5(otc1MD5 + uc1MD5);
        String otcDataEncryptionKey = toMD5(otc2MD5 + randomSalt);
        SecretKeySpec otc1AESKey = new SecretKeySpec(otcDataEncryptionKey.getBytes(StandardCharsets.UTF_8),
                AES);

        try {
            Cipher otc1AESCipher = Cipher.getInstance(TRANSFORMATION_ALGORITHM, provider);
            otc1AESCipher.init(1, otc1AESKey);
            return new String(Hex.encodeHex(otc1AESCipher.doFinal(otcData.getBytes(StandardCharsets.UTF_8))));
        } catch (GeneralSecurityException var10) {
            throw new OtcGeneratorException(CANNOT_GENERATE_DV_OTC_DATA, var10);
        }
    }

    public static String getCredentialData(String otcData, String randomSalt, String userPassword, String publicKey, String certId) {
        String salt;
        if (StringUtils.isEmpty(randomSalt)) {
            salt = "";
        } else {
            salt = randomSalt;
        }

        String otc1MD5 = toMD5(otcData);
        String rs1MD5 = toMD5(salt);
        String uc1MD5 = toMD5(userPassword);
        return prepareCredentialData(otc1MD5, rs1MD5, uc1MD5, publicKey, certId);
    }

    private static String prepareCredentialData(String otc1MD5, String rs1MD5, String uc1MD5,
                                                String publicKey, String certId) {
        Key eotC1AESKey = new SecretKeySpec(uc1MD5.getBytes(StandardCharsets.UTF_8), AES);

        try {
            String plainEOTC1 = otc1MD5 + rs1MD5;
            Cipher eotC1AESCipher = Cipher.getInstance(TRANSFORMATION_ALGORITHM, provider);
            eotC1AESCipher.init(1, eotC1AESKey);
            Cipher eotC1Cipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING, provider);
            eotC1Cipher.init(1, readPublicKey(new ByteArrayInputStream(publicKey.getBytes())));
            return certId
                    + new String(Hex.encodeHex(eotC1Cipher.doFinal(eotC1AESCipher.doFinal(plainEOTC1.getBytes(StandardCharsets.UTF_8)))));
        } catch (GeneralSecurityException var10) {
            throw new OtcGeneratorException(CANNOT_GENERATE_DV_OTC_DATA, var10);
        }
    }

    private static PrivateKey readPrivateKey(InputStream inputStream) {
        try {
            PrivateKey var3;
            try (PEMReader pemReader = new PEMReader(new InputStreamReader(inputStream))) {
                var3 = ((KeyPair) pemReader.readObject()).getPrivate();
            }
            return var3;
        } catch (IOException var8) {
            throw new OtcGeneratorException(var8);
        }
    }

    private static PublicKey readPublicKey(InputStream inputStream) {
        try {
            PublicKey var3;
            try (PEMReader pemReader = new PEMReader(new InputStreamReader(inputStream))) {
                var3 = (PublicKey) pemReader.readObject();
            }
            return var3;
        } catch (IOException var8) {
            throw new OtcGeneratorException(var8);
        }
    }

    private static String toMD5(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String generateFingerPrint(final String applicationName) {
        return String.format(FINGER_PRINTS_TEMPLATE, String.valueOf(System.currentTimeMillis()), applicationName);
    }

    public static class OtcGeneratorException extends RuntimeException {
        public OtcGeneratorException() {
        }

        public OtcGeneratorException(String message) {
            super(message);
        }

        public OtcGeneratorException(String message, Throwable cause) {
            super(message, cause);
        }

        public OtcGeneratorException(Throwable cause) {
            super(cause);
        }

        public OtcGeneratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

}

