package com.babu.practice.otp;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * The main purpose of HMAC (Hash based Message Authentication Code) was to validate a message over unsecure channel, whether it was tampered or not,
 * here both sender and receiver uses shared secret key (of any length) that is used by sender to generate hmac and includes as part of the payload along with the
 * original data, the receiver to verify authenticity of message uses the data to generate the HMAC and matches it with the HMAC included in the message by the
 * sender, if it matches, message is authenticated else not
 * @author sisba01
 *
 */
public class HOTP {
	
	/**
	 * Max value allowed is 10 to the power 10, current value is for 6 digit OTP
	 */
	private static final int OTP_LENGTH = 1000000;
	/**
	 * HMAC signing algorithm
	 * The algorithm can be any one of : HmacSHA1, Hmac256, Hmac384, HmacSHA512
	 */
	private static final String HMAC_ALGO = "HmacSHA512";
	
	/**
	 * API to get four random bytes to generate an integer value from 20 byte hmac hash value
	 * @param rawHmac
	 * @return
	 */
	public static int getDynamicBinaryCode(byte[] rawHmac) {
		/**
		 * Get the last four bits of last byte of hmac for randomization of making integer with 
		 * consecutive four bytes from offset to offset+3
		 * 
		 * NOTE: offset cannot be greater than 15 and also the bytes generate by HmacSHA1 is always 20 bytes long
		 * so the offset to offset+3 will always be less than 20
		 */
		int offset = rawHmac[19] & 0xf;
		/**
		 * To form the 32 bit integer using 4 bytes from offset to offset+3 bits
		 * placed in order (all 8 bits from every byte)
		 * NOTE: Except MSB byte (1st byte) all byte values are taken as is,
		 * but the first byte is taken by avoiding the signed bit, making it positive number
		 */
		return (rawHmac[offset] & 0x7f) << 24
				| (rawHmac[offset+1] & 0xff) << 16
				| (rawHmac[offset+2] & 0xff) << 8
				| (rawHmac[offset+3] & 0xff);
	}
	
	/**
	 * API to generate 6 digit OTP using the shared secret and counter using HmacSHA1 algorithm
	 * NOTE: at most 10 digit OTP can be generated and may not beyond that
	 * for more info see this : https://tools.ietf.org/html/rfc4226#page-7
	 * @param secret
	 * @param counter
	 * @return
	 */
	public static int getHOTP(String secret, int counter) {
		try {
	        // Get an hmac_sha1 key from the raw key bytes
	        byte[] keyBytes = secret.getBytes();           
	        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_ALGO);

	        // Get an hmac_sha1 Mac instance and initialize with the signing key
	        Mac mac = Mac.getInstance(HMAC_ALGO);
	        mac.init(signingKey);

	        // Compute the hmac on input data bytes, by converting int to byte array
	        byte[] rawHmac = mac.doFinal(new byte[] {
	                (byte)(counter >> 24),
	                (byte)(counter >> 16),
	                (byte)(counter >> 8),
	                (byte)counter});
	        
	        /**
	         * Get an integer using four random bytes from hmac byte array
	         */
	        int dbc = getDynamicBinaryCode(rawHmac);
	        
	        /**
	         * Generate 6-digit OTP and return
	         */
	        return dbc % OTP_LENGTH;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * API to generate HOTP using OCRA signing key
	 * for more information see this: https://tools.ietf.org/html/rfc6287#page-9
	 * IMP: Here the secret and ocraSigningKey can be of any length, but the Hmac algo will always generates 160 bit (20 byte) hash
	 * @param secret
	 * @param ocraSigningKey
	 * @return
	 */
	public static int getHOTP(String secret, String ocraSigningKey) {
		try {
	        // Get an hmac_sha1 key from the raw key bytes
	        byte[] keyBytes = secret.getBytes();           
	        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_ALGO);

	        // Get an hmac_sha1 Mac instance and initialize with the signing key
	        Mac mac = Mac.getInstance(HMAC_ALGO);
	        mac.init(signingKey);

	        // Compute the hmac on input data bytes, by converting int to byte array
	        byte[] rawHmac = mac.doFinal(ocraSigningKey.getBytes());
	        
	        /**
	         * Get an integer using four random bytes from hmac byte array
	         */
	        int dbc = getDynamicBinaryCode(rawHmac);
	        
	        /**
	         * Generate 6-digit OTP and return
	         */
	        return dbc % OTP_LENGTH;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * API to generate TOTP using HOTP and using duration as a counter
	 * Here:
	 * INITIAL_TIMESTAMP : starting timestamp to consider, default value is 0
	 * TIME_STEPS : the range for which this OTP will be valid, currently considering in seconds
	 * Use duration as counter in HOTP to get the OTP
	 * 
	 * NOTE: the duration while verifying in server side can be considered with +1 and -1 values 
	 * @param sharedSecret
	 * @return
	 */
	public static int getTOTP(String sharedSecret) {
		final int INITIAL_TIMESTAMP = 0;
		final int TIME_STEPS = 30;
		
		int duration = (int) ((System.currentTimeMillis()/1000L - INITIAL_TIMESTAMP)/TIME_STEPS);
		
		System.out.println(duration);
		
		return getHOTP(sharedSecret, duration);
	}
	
	public static void main(String[] args) {
		final String sharedSecret = "5C807D14DB444C9766A18464C894FD969524A092";
		final String ocraSigningKey = "3kj3j35kjk35kkj7j85j4bjhj3h4h5h6kjkk4jk5k887k8kjjj89jjj234jkjrkljkllkjeekj09df89832rjifjiej90u9u39rujdkfdkvlmcklk4nkn5k4ntkk4n5d";
		
		/*for(int i=1;i<=5;i++) {
			System.out.println(getHOTP(sharedSecret, i));
		}
		
		System.out.println(getHOTP(sharedSecret, ocraSigningKey));*/
		
		System.out.println(getTOTP(sharedSecret));
		
	}

}
