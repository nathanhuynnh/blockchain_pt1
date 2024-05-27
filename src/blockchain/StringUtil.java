package blockchain;

import java.security.MessageDigest;

public class StringUtil {

	/*
	 * Uses SHA256 on a String then returns a unique digital fingerprint 
	 * as a String.
	 */
	public static String applySha256(String input) {
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Applying SHA256 to our input
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			
			//Will contain hash as a hexadecimal
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				
				hexString.append(hex);
			}
			
			return hexString.toString();
		} 
		
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
