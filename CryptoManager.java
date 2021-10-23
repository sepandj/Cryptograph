

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		
		for(int counter=0; counter<plainText.length(); counter++) {
			if((plainText.charAt(counter)<LOWER_BOUND) || (plainText.charAt(counter)>UPPER_BOUND))
				return false;
		}
		
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		
		String encrypted = "";
		key%=RANGE;
		
		for(int counter=0; counter<plainText.length(); counter++) {
			
			if((key+plainText.charAt(counter))<=UPPER_BOUND) {
				char encryptedChar = (char) (plainText.charAt(counter) + key);
				encrypted += Character.toString(encryptedChar);
			}
			else {
				char encryptedChar = (char) ((plainText.charAt(counter) + key)-RANGE );
				encrypted += Character.toString(encryptedChar);
			}
		}
		
		return encrypted;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		
		String encrypted = "";
		
		for(int counter=0; counter<plainText.length(); counter++) {
			int encryptedChar = (plainText.charAt(counter) + bellasoStr.charAt(counter%bellasoStr.length()))%RANGE;
			if(encryptedChar<LOWER_BOUND)
				encryptedChar+=RANGE;
			
			encrypted+=(char)(encryptedChar);
			
		}
		
		return encrypted;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		
		String decrypted = "";
		key%=RANGE;
		
		for(int counter=0; counter<encryptedText.length(); counter++) {
			
			if((encryptedText.charAt(counter)-key)>=LOWER_BOUND){
				char decryptedChar = (char) (encryptedText.charAt(counter) - key);
				decrypted += Character.toString(decryptedChar);
			}
			else {
				char decryptedChar = (char) ((encryptedText.charAt(counter) - key)+RANGE );
				decrypted += Character.toString(decryptedChar);
			}
		}
		
		return decrypted;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		
		String decrypted = "";
		
		for(int counter=0; counter<encryptedText.length(); counter++) {
			int decryptedChar = (encryptedText.charAt(counter) - bellasoStr.charAt(counter%bellasoStr.length())+RANGE)%RANGE;
			
			if(decryptedChar<LOWER_BOUND)
				decryptedChar+=RANGE;
			
			decrypted += (char)(decryptedChar);
		}
		
		return decrypted;
	}
}
