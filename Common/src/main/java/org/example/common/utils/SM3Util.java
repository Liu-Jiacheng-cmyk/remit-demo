package org.example.common.utils;
import org.bouncycastle.jcajce.provider.digest.SM3;

public class SM3Util {
    // 使用SM3进行密码加密
    public static String encrypt(String data) {
        SM3.Digest sm3Digest = new SM3.Digest();
        byte[] hash = sm3Digest.digest(data.getBytes());
        return bytesToHex(hash);
    }

    // 将字节数组转换为16进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
