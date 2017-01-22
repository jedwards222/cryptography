/**
 * CaesarCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using Caesar cipher
 * and when used in conjunction with relevant Tester file.
 */

package ciphers;

public class CaesarCipher extends SubstitutionCipher {

  private int shift;

  /**
   * Constructor
   * @param shiftDist is the desired shift of alphabet
   * @param encryptingTime determines whether we are encrypting or decrypting: true if encrypting
   */
  public CaesarCipher(int shiftDist, boolean encryptingTime) {
    super(encryptingTime);
    shift = shiftDist;
  }

  // Change plaintext into ciphertext
  protected char encode(char current) {
    int index = -1;
    for(int i = 0; i < ALPHABET.length; i++) {
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
  protected char decode(char current) {
    int index = -1;
    for(int i = 0; i < ALPHABET.length; i++) {
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
