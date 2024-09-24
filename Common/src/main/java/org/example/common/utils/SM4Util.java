package org.example.common.utils;

import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class SM4Util {

    private static final String KEY = "1234567890abcdef";  // 16字节密钥，示例使用

    public static byte[] encrypt(byte[] data, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new SM4Engine()));
        cipher.init(true, new ParametersWithIV(new KeyParameter(KEY.getBytes()), iv));
        byte[] output = new byte[cipher.getOutputSize(data.length)];
        int len = cipher.processBytes(data, 0, data.length, output, 0);
        cipher.doFinal(output, len);
        return output;
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] iv) throws Exception {
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new SM4Engine()));
        cipher.init(false, new ParametersWithIV(new KeyParameter(KEY.getBytes()), iv));
        byte[] output = new byte[cipher.getOutputSize(encryptedData.length)];
        int len = cipher.processBytes(encryptedData, 0, encryptedData.length, output, 0);
        cipher.doFinal(output, len);
        return output;
    }
}
