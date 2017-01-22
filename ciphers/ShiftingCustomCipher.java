/**
 * ShiftingCustomCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using a custom cipher
 * that alters itself through encoding/decoding when used in conjunction
 * with relevant Tester file.
 */

package ciphers;

public class ShiftingCustomCipher extends SubstitutionCipher {

  private char[] cipher;
  private int charactersRead;
  private int shiftFreq;

  public ShiftingCustomCipher(char[] cipherAlphabet, boolean encryptingTime, int shiftFrequency) {
    super(encryptingTime);
    cipher = cipherAlphabet;
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

  // Change plaintext into ciphertext
  protected char encode(char current) {
    int index = 0;
    charactersRead++;
    if (charactersRead % shiftFreq == 0) {
      changeCipher();
    }
    for(int i = 0; i < ALPHABET.length; i++) {
      if (ALPHABET[i] == current) {
        index = i;
      }
    }
    return cipher[index];
  }

  // Change ciphertext into plaintext
  protected char decode(char current) {
    int index = 0;
    charactersRead++;
    if (charactersRead % shiftFreq == 0) {
      changeCipher();
    }
    for (int i = 0; i < cipher.length; i++) {
      if (cipher[i] == current)
        index = i;
    }
    return ALPHABET[index];
  }
}
