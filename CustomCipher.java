/**
 * CustomCipher.java
 * @author James
 * June 3, 2016
 * Allows conversion between ciphertext and plaintext using a custom cipher
 * and when used in conjunction with relevant Tester file.
 */

public class CustomCipher extends SubstitutionCipher {

  private char[] cipher;

  public CustomCipher(char[] cipherAlphabet, boolean encryptingTime) {
    super(encryptingTime);
    cipher = cipherAlphabet;
  }

  // Change plaintext into ciphertext
  protected char encode(char current) {
    int index = 0;
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
    for (int i=0; i< cipher.length; i++) {
      if (cipher[i] == current) {
        index = i;
      }
    }
    return ALPHABET[index];
  }
}
