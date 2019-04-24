package com.sdc.factor.entity.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AES对称加密算法，用于加密字段的处理
 *
 * @author Sean
 * @since 2019-04-05
 */
public class AesEncryptUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AesEncryptUtils.class);

    /**
     * 参数分别代表 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 算法名称
     */
    private static final String ENCRYPT_ALG = "AES";

    /**
     * 随机源长度
     */
    private static final int SOURCE_LENGTH = 128;
 
    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     */
    public static String encrypt(String content, String encryptKey) {
        String encryptedValue = "";
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ENCRYPT_ALG);
            kgen.init(SOURCE_LENGTH);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), ENCRYPT_ALG));
            byte[] b = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            // 采用base64算法进行转码,避免出现中文乱码
            encryptedValue = Base64.encodeBase64String(b);
        } catch (Exception e) {
            LOGGER.error("Fail to encrypt value " + String.valueOf(content) + " with encrypt key " + encryptKey, e);
        }
        return encryptedValue;
    }
 
    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     */
    public static String decrypt(String encryptStr, String decryptKey) {
        String decryptedValue = "";
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(ENCRYPT_ALG);
            kgen.init(SOURCE_LENGTH);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), ENCRYPT_ALG));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            decryptedValue = new String(decryptBytes);
        } catch (Exception e) {
            LOGGER.error("Fail to decrypt value " + String.valueOf(encryptStr) + " with key " + decryptKey, e);
        }
        return decryptedValue;
    }
}