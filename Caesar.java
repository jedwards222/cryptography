/**
 * Caesar.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using Caesar cipher
 * and when used in conjunction with relevant Tester file.
 */

public class Caesar {
	
	// Standard English Alphabet
	private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
											'h', 'i', 'j', 'k', 'l', 'm', 'n',
											'o', 'p', 'q', 'r', 's', 't', 'u',
											'v', 'w', 'x', 'y', 'z'};
	private boolean encrypting;
	private int shift;

	/**
	 * Constructor
	 * @param shiftDist is the desired shift of alphabet
	 * @param encryptingTime determines whether we are encrypting or decrypting: true if encrypting
	 */
	public Caesar(int shiftDist, boolean encryptingTime) {
		shift = shiftDist;
		encrypting = encryptingTime;
	}
	
	// Decide whether encrypting or decrypting, then perform it
	public char changeChar(char current) {
		if (encrypting) return this.encode(current);
		else return this.decode(current);
	}
	
	// Change plaintext into ciphertext
	private char encode(char current) {
        int index = 0;
        for(int i=0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == current) {
                index = i;
            }
        }
        
        // Check if the character is in the alphabet
        if (index == -1) return ' ';
        
        int desiredIndex = index + shift;
        while (desiredIndex >= ALPHABET.length) {
        	desiredIndex -= ALPHABET.length;
        }
        while (desiredIndex < 0) {
        	desiredIndex += ALPHABET.length;
        }
        return ALPHABET[desiredIndex];
	}
	
	// Change ciphertext into plaintext
	private char decode(char current) {
        int index = -1;
        for(int i=0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == current) {
                index = i;
            }
        }
        
        // Check if the character is in the alphabet
        if (index == -1) return ' ';
        
        int desiredIndex = index - shift;
        while (desiredIndex >= ALPHABET.length) {
        	desiredIndex -= ALPHABET.length;
        }
        while (desiredIndex < 0) {
        	desiredIndex += ALPHABET.length;
        }
        return ALPHABET[desiredIndex];
	}
}