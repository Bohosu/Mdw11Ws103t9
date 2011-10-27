package model;

import java.security.MessageDigest;

public class HashUtil {

    public static final String HASH_TYPE = "SHA-1";

    public static String SHA1AsString(String pass) {
        return SHA1AsString(pass.getBytes());
    }

    public static String SHA1AsString(byte[] pass) {
        return hex(SHA1(pass));
    }

    public static String SHA1AsString(char[] pass) {
        return SHA1AsString(String.valueOf(pass));
    }

    private static byte[] SHA1(byte[] pass) {
        byte[] mac = new byte[20];
        try {
            MessageDigest sha = MessageDigest.getInstance(HASH_TYPE);
            sha.update(pass);
            mac = sha.digest();
        } catch (Exception e) {}
        return mac;
    }

    private static String hex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(Character.forDigit((b & 240) >> 4, 16));
            sb.append(Character.forDigit((b & 15), 16));
        }
        return sb.toString();
    }

 }