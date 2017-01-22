/**
 * ShiftingDriver.java
 * @author James
 * January 21, 2017
 * Driver program specifically for shifting custom cipher to test it more rigorously than
 * the combined testing file.
 * Specifically, the encryptDecrypt and decryptEncrypt StringBuilders are used
 * to confirm that encryption does the exact opposite of decryption.
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import ciphers.ShiftingCustomCipher;

public class ShiftingDriver {
  public static void main(String[] args) throws IOException, FileNotFoundException {

    Scanner input = new Scanner(System.in);

    String title = "==================================================\n" +
                   "=== Welcome to the ShiftingCustomCipher Tester ===\n" +
                   "==================================================";
    String q1 = "\nPlease give a valid shift frequency. Restrictions:" +
                "\nMust be an integer greater than 0. Invalid input will be" +
                "\ngiven a value of 2.";
    System.out.println(title);
    System.out.println(q1);

    int freq = input.nextInt();
    boolean validFreq = (freq > 0);
    if (!validFreq) freq = 2;

    String response1 = validFreq ? "Your frequency was valid!" :
      "Your frequency was invalid. Frequency 2 used instead.";
    System.out.println(response1);

    // Interact with the user to gather their desired conditions
    String q2 = "\nPlease give a valid cipher alphabet. Restrictions:" +
                "\nInclude every letter in the English alphabet once." +
                "\nRepeated letters ignored. Missing letters will be" +
                "\nadded to the end of the given cipher alphabet.";
    System.out.println(q2);
    String alphabet = input.next();

// Determine if the cipher alphabet is valid as is, or if changes are required

    Result returnedValues = fillAlphabet(alphabet);
    char[] alpha = returnedValues.chars;
    boolean valid = returnedValues.bool;

    String response2 = valid ? "Your alphabet was valid!" :
      "Some changes were made to your alphabet to make it valid.";
    System.out.println(response2);

    // Create separate cipher objects for decryption and encryption
    ShiftingCustomCipher encrypter = new ShiftingCustomCipher(alpha, true, freq);
    ShiftingCustomCipher decrypter = new ShiftingCustomCipher(alpha, false, freq);

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
    System.out.println("ShiftingCustom encrypt result: " + encryptReturn.toString());
    System.out.println("ShiftingCustom decrypt result: " + decryptReturn.toString());
    System.out.println(correctResponse);
    input.close();
  }

  private static Result fillAlphabet(String input) {
    boolean[] letterSeen = new boolean[26];
    char[] alphabet = new char[26];
    String lower = input.toLowerCase();
    boolean allValid = true;

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
        } else allValid = false; // Alphabet invalid if letter included >1 time
      } else allValid = false; // Alphabet invalid if non letter included
      if (alphaIndex >= 26) {
        return new Result(alphabet, allValid);
      }
    }
    // If this point is reached, not every letter in the alphabet has been
    // included in the cipher alphabet yet. Add them:
    allValid = false;
    for (int i = 0; i < 26; i++) {
      if (!letterSeen[i]) {
        alphabet[alphaIndex] = (char)(i + 'a');
        alphaIndex++;
        if (alphaIndex >= 26) {
          return new Result(alphabet, allValid);
        }
      }
    }
    return new Result(alphabet, allValid);
  }

  // small helper class used to return pair of values
  public static class Result {
    public final char[] chars;
    public final boolean bool;

    public Result(char[] c, boolean b) {
      this.chars = c;
      this.bool = b;
    }
  }

}
