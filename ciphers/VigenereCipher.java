/**
 * ShiftingCustomCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using a custom cipher
 * that alters itself through encoding/decoding when used in conjunction
 * with relevant Tester file.
 */

package ciphers;

public class VigenereCipher extends SubstitutionCipher {

  private String keyword;
  private int charactersRead;
  private char[] cipher;

  public VigenereCipher(String key, boolean encryptingTime) {
    super(encryptingTime);
    keyword = key;
    charactersRead = 0;
    cipher = new char[26];
  }

  /**
   * changeCipher shifts the cipher alphabet to the given letter
   */
  private void changeCipher(char keyChar) {
    int index = (int)(keyChar - 'a');
    char[] temp = Arrays.copyOfRange(ALPHABET, 0, index);
    for (int i = index; i < ALPHABET.length; i++) {
      cipher[i-length] = ALPHABET[i];
    }
    for (int j = 0; j < index; j++) {
      cipher[cipher.length - index = temp[index]];
    }
  }

  // Change plaintext into ciphertext
  protected char encode(char current) {
    int index = 0;
    charactersRead++;
    int startPos = charactersRead % keyword.length();
    changeCipher(keyword.charAt(startPos));

    for(int i=0; i<ALPHABET.length; i++) {
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
    int startPos = charactersRead % keyword.length();
    changeCipher(keyword.charAt(startPos));
    
    for (int i=0; i< cipher.length; i++) {
      if (cipher[i] == current)
        index = i;
    }
    return ALPHABET[index];
  }
}
