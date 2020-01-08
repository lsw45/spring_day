package cn.itcast.practice.decrypt;

import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.cert.X509Certificate;
import java.util.Base64;

public class AesGcmExample {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 96;
    private static final int NONCE_LENGTH_BYTE = 12;
    private static final String AES_KEY = "91320402MA1UUEJ25Etnwy0288878888"; // APIv3 密钥
    private static final String TRANSFORMATION_PKCS1Padding = "RSA/ECB/PKCS1Padding";
    private static String aesgcmDecrypt(String aad, String iv, String cipherText) throws Exception
    {
        final Cipher cipher = Cipher.getInstance(ALGORITHM, "SunJCE");
        SecretKeySpec key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        cipher.updateAAD(aad.getBytes());
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
    }
    public static void main(String[] args) {
        final String associatedData = "mall_transaction"; // encrypt_certificate.associated_data
        final String nonce = "ozOhsln77kRB"; // encrypt_certificate.nonce
        final String cipherText = "Oq1f8IDGqmwh65TYOcnbOa+YM+zoIm/PZojsLLJKPOyXveuQtsS54Nh/RzU/FkKruR/C9qG8NUurry5HOctaf2p+KzBZNqGfdaT3FHDeIoyfDXCkaleZMApKLZ3qmHtNuoDtNUYhJXOIjVat8rXHG0++XwF5BVfPph4ClWujJheg62HSkiipknmt9Q3Mt2+ZwJx95LsJfIapnkhWo0qjj7YF6OS7vcWjilrrngpUKMifnGKv0QC4/z/McMo6Z6qAo7FtRa+YuR7who1YleWjuVGsDeotoP+9MmOfvPEDG2iS/RhH2Sn+RXDs1k0gOvp62BeX3vLEEYK/Ck/UMVL5fGDQso7/viN5cLG8Un5Uct9lx3beI/6Hqwv0nk4jb5nJ1XkI"; // encrypt_certificate.ciphertext
        try {
            String wechatpayCert = aesgcmDecrypt(associatedData, nonce, cipherText);
            System.out.println(wechatpayCert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}