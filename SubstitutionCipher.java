/**
 * SubstitutionCipher.java
 * @author James
 * December 15, 2016
 * Abstract class for substitution type ciphers, including Caesar, Custom, and
 * Shifting Custom ciphers
 */

public abstract class SubstitutionCipher {

  // Standard English Alphabet
  protected static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                                            'h', 'i', 'j', 'k', 'l', 'm', 'n',
                                            'o', 'p', 'q', 'r', 's', 't', 'u',
                                            'v', 'w', 'x', 'y', 'z'};
  protected boolean encrypting;

  /**
   * Constructor
   * @param encryptingTime is true if encrypting, false if decrypting
   */
  public SubstitutionCipher(boolean encryptingTime) {
    encrypting = encryptingTime;
  }

  // Decide whether encrypting or decrypting, then perform it
  public char changeChar(char current) {
    if (encrypting) return this.encode(current);
    else return this.decode(current);
  }

  // Change plaintext into ciphertext
  protected abstract char encode(char current);

  // Change ciphertext into plaintext
  protected abstract char decode(char current);
}
