package com.babu.practice.java.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

	/**
	 * API to generate MD5 hash and returns the hexadecimal number algo can be :
	 * MD5, SHA-1, SAH-256, SHA-384, SHA-512
	 * 
	 * @param secret
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashWithoutSalt(String secret, String algo) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(secret.getBytes());
			byte[] hashVal = md.digest();
			StringBuilder sbr = new StringBuilder("");
			for (int i = 0; i < hashVal.length; i++) {
				// Take the positive integer only
				sbr.append(Integer.toString(hashVal[i] & 0xff, 16));
			}
			return sbr.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static String hashWithSalt(String secret, String algo, byte[] salt) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(salt);
			byte[] hashVal = md.digest(secret.getBytes());
			StringBuilder sbr = new StringBuilder("");
			for (int i = 0; i < hashVal.length; i++) {
				// Take the positive integer only
				sbr.append(Integer.toString(hashVal[i] & 0xff, 16));
			}
			return sbr.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			String secret = "password";
			System.out.println(hashWithoutSalt(secret, "SHA-256"));
			System.out.println(hashWithSalt(secret, "SHA-256", "12345".getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
