package com.zx.card.utils;

import com.zx.card.system.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public class AlgorithmUtil {

    private static Logger logger = LogManager.getLogger(AlgorithmUtil.class);

    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public AlgorithmUtil() {
    }

    public static void encrypt(User user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
//        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }

    public static String encrypt(String password,String salt){
        String newPassword = new SimpleHash(ALGORITHM_NAME, password,
                ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        return newPassword;

    }

    public static String encryptDESToString(String content, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(1, loadDesKey(key), iv);
            bytes = cipher.doFinal(content.getBytes("UTF-8"));
        } catch (Exception var5) {
            logger.error(var5);
        }

        return bytes2hex(bytes);
    }

    public static String decryptDESToString(String hexContent, String key) {
        byte[] bytes = null;

        try {
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            byte[] source = hex2byte(hexContent);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, loadDesKey(key), iv);
            bytes = cipher.doFinal(source);
        } catch (Exception var6) {
            logger.error(var6);
        }

        return bytes == null ? "" : new String(bytes);
    }


    public static String encryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes2hex(bytes);
    }

    public static String decryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception var4) {
            logger.error(var4);
        }

        return bytes == null ? "" : new String(bytes);
    }

    public static String md5(String source) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = source.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static String bytes2hex(byte[] bytes) {
        if (bytes == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            byte[] arr$ = bytes;
            int len$ = bytes.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte b = arr$[i$];
                String temp = Integer.toHexString(b & 255);
                if (temp.length() == 1) {
                    sb.append("0");
                }

                sb.append(temp);
            }

            return sb.toString();
        }
    }

    public static byte[] hex2byte(String hex) {
        byte[] digest = new byte[hex.length() / 2];

        for(int i = 0; i < digest.length; ++i) {
            String byteString = hex.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    private static SecretKey loadDesKey(String key) {
        SecretKey secretKey = null;

        try {
            byte[] keyBytes = key.getBytes("UTF-8");
            DESKeySpec dks = new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            secretKey = keyFactory.generateSecret(dks);
        } catch (Exception var5) {
            logger.error(var5);
        }

        return secretKey;
    }

    private static SecretKey loadKeyAES(byte[] bytes) {
        return new SecretKeySpec(bytes, "AES");
    }

    public static void main(String[] args) throws Exception {
    }
}
