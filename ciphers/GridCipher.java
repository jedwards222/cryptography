/**
 * GridCipher.java
 * @author James
 * December 11, 2016
 * Allows conversion between ciphertext and plaintext using a grid transposition
 * cipher when used in conjunction with relevant Tester file.
 */

package ciphers;

public class GridCipher {

  // Standard English Alphabet
  private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
                      'h', 'i', 'j', 'k', 'l', 'm', 'n',
                      'o', 'p', 'q', 'r', 's', 't', 'u',
                      'v', 'w', 'x', 'y', 'z'};
  private boolean encrypting;
  private int height;    // height of the grid

  /**
   * Constructor
   * @param gridHeight is the desired height of the grid and should be > 1
   * @param encryptingTime determines whether we are encrypting or decrypting: true if encrypting
   * Height is assigned a value of 1 so no encryption will occur if invalid height given.
   */
  public GridCipher(int gridHeight, boolean encryptingTime) {
    height = (gridHeight > 0) ? gridHeight : 1;
    encrypting = encryptingTime;
  }

  // Decide whether encrypting or decrypting, then peGridorm it
  public String changeMessage(String input) {
    if (encrypting) return this.encode(input);
    else return this.decode(input);
  }

  // Change plaintext into ciphertext
  private String encode(String input) {
    StringBuilder output = new StringBuilder();
    int length = input.length();
    // The output will be created by getting the elements from each row in turn
    for (int row = 0; row < height; row++) {
      for (int pos = row; pos < length; pos += height) {
        output.append(input.charAt(pos));
      }
    }
    return output.toString();
  }

  // Change ciphertext into plaintext
  private String decode(String input) {
    StringBuilder output = new StringBuilder();
    int length = input.length();
    final int rem = length % height;
    final int rows = (rem == 0) ? length/height : (length/height + 1);

    int rowsVar = rows;
    int remVar = rem;

    for (int pos = 0; pos < rows; pos += 1) {
      for (int col = pos; col < length; col += rowsVar) {
        remVar--;
        if (remVar == -1) {
          if (pos == rows - 1) break;
          rowsVar = length/height;
        }
        output.append(input.charAt(col));
      }
      // Reset variables to original values
      remVar = rem;
      rowsVar = rows;
    }
    return output.toString();
  }
}
