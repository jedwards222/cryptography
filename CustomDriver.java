/**
 * CustomDriver.java
 * @author James
 * December 15, 2016
 * Driver program specifically for custom cipher to test it more rigorously than
 * the combined testing file.
 * Specifically, the encryptDecrypt and decryptEncrypt StringBuilders are used
 * to confirm that encryption does the exact opposite of decryption.
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class CustomDriver {
  public static void main(String[] args) throws IOException, FileNotFoundException {

    Scanner input = new Scanner(System.in);

    // Interact with the user to gather their desired conditions
    String question = "\nPlease give a valid cipher alphabet. Restrictions:" +
                      "\nInclude every letter in the English alphabet once." +
                      "\nRepeated letters ignored. Missing letters will be" +
                      "\nadded to the end of the given cipher alphabet.";
    System.out.println(question);
    String alphabet = input.next();

// Determine if the cipher alphabet is valid as is, or if changes are required

    char[] alpha = fillAlphabet(alphabet);

    boolean valid = true;

    String response = valid ? "Your alphabet was valid!" :
      "Some changes were made to your alphabet to make it valid.";
    System.out.println(response);

    // Create separate cipher objects for decryption and encryption
    CustomCipher encrypter = new CustomCipher(alpha, true);
    CustomCipher decrypter = new CustomCipher(alpha, false);

    // StringBuilders are used instead of Strings for efficiency
    StringBuilder original = new StringBuilder();
    StringBuilder encryptReturn = new StringBuilder();
    StringBuilder decryptReturn = new StringBuilder();
    StringBuilder decryptEncrypt = new StringBuilder();
    StringBuilder encryptDecrypt = new StringBuilder();

    // Create a Scanner to read the input file.
    File inputFile = new File("input.txt");
    try {
      input = new Scanner(inputFile);
    }
    catch (FileNotFoundException e) {
      System.out.println("Could not open file. Try again.");
    }

    // Now the file has been opened. Read it until we read the last word
    while (input.hasNext()) {
      char[] nextString = input.next().toCharArray();
      for (int a = 0; a < nextString.length; a++) {
        char encrypted = encrypter.changeChar(nextString[a]);
        encryptReturn.append(encrypted);
        encryptDecrypt.append(decrypter.changeChar(encrypted));

        char decrypted = decrypter.changeChar(nextString[a]);
        decryptReturn.append(decrypted);
        decryptEncrypt.append(encrypter.changeChar(decrypted));

        original.append(nextString[a]);
      }
    }

    // Check if decryption is doing opposite of encryption
    boolean correct = (original.toString().equals(decryptEncrypt.toString()) &&
                       original.toString().equals(encryptDecrypt.toString()));
    String correctResponse = correct ?
      "Decryption and Encryption are working correctly" :
      "There is an issue with decryption and encryption";

    // Give user results
    System.out.println("Original Message: " + original.toString());
    System.out.println("Custom encrypt result: " + encryptReturn.toString());
    System.out.println("Custom decrypt result: " + decryptReturn.toString());
    System.out.println(correctResponse);
    input.close();
  }

  private static char[] fillAlphabet(String input) {
    boolean[] letterSeen = new boolean[26];
    char[] alphabet = new char[26];
    String lower = input.toLowerCase();

    int alphaIndex = 0;
    int length = lower.length();
    for (int i = 0; i < length; i++) {
      int charNum = lower.charAt(i) - 'a';
      // Check a letter is being examined
      if (charNum >= 0 && charNum < 26) {
        if (!letterSeen[charNum]) {
          letterSeen[charNum] = true;
          alphabet[alphaIndex] = lower.charAt(i);
          alphaIndex++;
        }
      }
      if (alphaIndex >= 26) {
        return alphabet;
      }
    }
    // If this point is reached, not every letter in the alphabet has been
    // included in the cipher alphabet yet. Add them:
    for (int i = 0; i < 26; i++) {
      if (!letterSeen[i]) {
        alphabet[alphaIndex] = (char)(i + 'a');
        alphaIndex++;
        if (alphaIndex >= 26) {
          return alphabet;
        }
      }
    }
    return alphabet;
  }
}
