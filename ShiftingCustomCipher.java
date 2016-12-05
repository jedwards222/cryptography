/**
 * ShiftingCustomCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using a custom cipher
 * that alters itself through encoding/decoding when used in conjunction 
 * with relevant Tester file.
 */

public class ShiftingCustomCipher {
	
	// Standard English Alphabet
	private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	                                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	                                        'u', 'v', 'w', 'x', 'y', 'z'};

	private boolean encrypting;
	private char[] cipher;
	private int charactersRead;
	private int shiftFreq;
	
	public ShiftingCustomCipher(char[] cipherAlphabet, boolean encryptingTime, int shiftFrequency) {
		cipher = cipherAlphabet;
		encrypting = encryptingTime;
		shiftFreq = shiftFrequency;
		charactersRead = 0;
	}
	
	/**
	 * changeCipher shifts the cipher alphabet to the left one index
	 */
	private void changeCipher() {
		char temp = cipher[0];
		for (int i = 1; i < cipher.length; i++) {
			cipher[i-1] = cipher[i];
		}
		cipher[cipher.length - 1] = temp;
	}
	
	// Decide whether encrypting or decrypting, then perform it
	public char changeChar(char current) {
		if (encrypting) return this.encode(current);
		else return this.decode(current);
	}
	
	// Change plaintext into ciphertext
	private char encode(char current) {
        int index = 0;
    	charactersRead++;
    	if (charactersRead % shiftFreq == 0) {
    		changeCipher();
    	}
        for(int i=0; i<ALPHABET.length; i++) {
            if (ALPHABET[i] == current) {
                index = i;
            }
        }
        return cipher[index];
	}
	
	// Change ciphertext into plaintext
	private char decode(char current) {
        int index = 0;
    	charactersRead++;
    	if (charactersRead % shiftFreq == 0) {
    		changeCipher();
    	}
        for (int i=0; i< cipher.length; i++) 
            if (cipher[i] == current)
                index = i;
        return ALPHABET[index];
	}
}