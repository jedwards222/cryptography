/**
 * CustomCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using a custom cipher
 * and when used in conjunction with relevant Tester file.
 */

public class CustomCipher {

	// Standard English Alphabet
	private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	                                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	                                        'u', 'v', 'w', 'x', 'y', 'z'};
	/*
	private static final char[] CIPHERALPHABET = {'d', 'o', 'm', 'i', 'n', 'q', 'u', 'e', 'j',
												  'a', 's', 'l', 'v', 'y', 'f', 'r', 'z', 'x',
												  'w', 't', 'b', 'c', 'g', 'h', 'k', 'p', ' '};
	*/

	private boolean encrypting;
	private char[] cipher;

	public CustomCipher(char[] cipherAlphabet, boolean encryptingTime) {
		cipher = cipherAlphabet;
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
        for (int i=0; i< cipher.length; i++)
            if (cipher[i] == current)
                index = i;
        return ALPHABET[index];
	}
}
