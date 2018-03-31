package com.beidou.ybz.accountbook.util;


import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by ${Mr.yang} on 2017/2/17.
 */

public class DESedeUtil {
    /**
    * 算法名称-三重DES
    */
    public static final String KEY_ALGORITHM_DESEDE = "DESede";

    /**
     * 算法名称-MD5
     */
    public static final String KEY_ALGORITHM_MD5 = "MD5";

    /**
     * 算法名称/加密模式/填充方式
     */
    public static final String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";


    /**
     * 测试
     */
//    public static final String  key="1234567890abc12345678901234567890";//测试
//    public static final String  iv="01234567";


    /**
     * 仿真
     */
//    public static final String  key="1234567890abc12345678901234567891";//测试
//    public static final String  iv="01234568";


    /**
     * 正式
     */
    public static String  secretKey ="";//生产
    public static String  secretIv ="";
    public static String  secretKeyId ="";

    public static String getSecretKey() {
        return secretKey;
    }

    public static String getSecretIv() {
        return secretIv;
    }

    public static void setSecretKey(String secretKey) {
        DESedeUtil.secretKey = secretKey;
    }

    public static void setSecretIv(String secretIv) {
        DESedeUtil.secretIv = secretIv;
    }

    public static String getSecretKeyId() {
        return secretKeyId;
    }

    public static void setSecretKeyId(String secretKeyId) {
        DESedeUtil.secretKeyId = secretKeyId;
    }

    /**
     * 生成加密KEY
     * @return
     * @throws Exception
     * byte[]
     */
    public static byte[] genkey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM_DESEDE);
        keyGenerator.init(112);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 获取加密KEY
     * @param key
     * @return
     * @throws
     * @throws NoSuchAlgorithmException
     * @throws
     * Key
     */
    public static Key getKey(byte[] key) throws Exception {
        DESedeKeySpec des = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM_DESEDE);
        SecretKey secretKey = keyFactory.generateSecret(des);
        return secretKey;
    }

    /**
     *
     * @Title: encrypt
     * @Description: 加密
     * @param @param data
     * @param @param keyStr
     * @param @param iv
     * @param @throws Exception
     * @return String
     * @throws
     */
    public static String encrypt(String plaintext, String keyStr, String iv) throws Exception {
        Key key = getKey(keyStr.getBytes());

        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, key, ips);
//        return Base64.encode(cipher.doFinal(plaintext.getBytes("utf-8")));
        return Base64.encode(cipher.doFinal(plaintext.getBytes("utf-8"))).replaceAll("[\\s*\t\n\r]", "");
    }

    /**
     * 获取加密后的请求体
     * @param json
     * @return
     */
    public static String getRequestAfter3DES(String key,String iv ,String json) throws Exception {
        Log.d("", "getRequestAfter3DES: "+json);
        return DESedeUtil.encrypt(json, key,iv);
    }

    /**
     * 获取签名后的请求体
     * @param encMsg
     * @return
     */
    public static String getRequestAfterSign(String encMsg) throws Exception {
//        Log.d(TAG, "getRequestAfterSign: "+json);
        return DESedeUtil.sign(encMsg.getBytes());
    }

    /**
     *
     * @Title: decrypt
     * @Description: 解密
     * @param encStr
     * @param keyStr
     * @param iv
     * @param @throws Exception
     * @return String
     */
    public static String decrypt(String encStr, String keyStr, String iv) throws Exception {
        if (encStr==null){
            return null;
        }
        Key key = getKey(keyStr.getBytes());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key, ips);
        return new String(cipher.doFinal(Base64.decode(encStr)), "utf-8");
    }

    /**
     * 签名
     * @param data 待签名明文字节数组
     * @return String 签名后的字符串
     * @throws NoSuchAlgorithmException
     *
     *
     */
    public static String sign(byte[] data) throws NoSuchAlgorithmException{

        String encryptStr = new String(Hex.encodeHex(DigestUtils.md5(data)));
        // String encryptStr = DigestUtils.md5Hex(data);
//        return Base64.encode(encryptStr.getBytes());
        return encryptStr;
    }

    /**
     * 签名验证
     * @param data 解密后的明文字节数组
     * @param signStr 签名后的字符串
     * @return boolean 是否验证通过，true：是，false：否
     */
    public static boolean veriSign(byte[] data, String signStr) throws NoSuchAlgorithmException {
        String encryptStr = new String(Hex.encodeHex(DigestUtils.md5(data)));
        String plainTextSignStr = Base64.encode(encryptStr.getBytes());
        if(signStr.equals(plainTextSignStr)) {
            return true;
        }
        return false;
    }
    /**
     * @return md5 生成
     */
    public static String getMD5String(byte[] data) throws NoSuchAlgorithmException {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(data);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);

    }
    /**
     * 测试入口
     * @param args
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * void
     */
    public static void main(String[] args) throws Exception {

        String plaintext = "hello,world!";
        // 加密
        String encStr = encrypt("{\"body\":[{\"inviteCode\":\"22222\",\"isAgreeProtoFlag\":\"1\",\"loginPwd\":\"222222\",\"mobile\":\"15555928936\",\"validCode\":\"2222\"}],\"header\":[{\"clientVerson\":\"1\",\"deviceId\":\"4998b73370c1a691\",\"ip\":\"192.168.20.115\",\"requestTime\":\"2017年03月07日20:17:13\",\"systemVersion\":\"9.3.5\",\"termType\":\"ANDROID\",\"tokenId\":\"2667EBF8-F258-4DD4-9FA7-2F4F9ABEBEB0\"}]}", "1234567890abc12345678901234567890", "01234567");

        System.out.println("加密后：" + encStr);
//        Log.d(TAG, "加密后加密后加密后加密后加密后加密后: "+encStr);

        // 解密
        String decStr = decrypt(encStr, "1234567890abc12345678901234567890", "01234567");

        String str = "123456";
        sign(str.getBytes());

        System.out.println("解密后：" + decStr);

        String plaitext2 = "{\"idNo\":\"1988\",\"password\":\"thisispassword地主\"}";
        System.out.println("签名后 = " + sign(plaitext2.getBytes()));
    }
}
