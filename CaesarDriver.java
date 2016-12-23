/**
 * CaesarDriver.java
 * @author James
 * December 15, 2016
 * Driver program specifically for Caesar cipher to test it more rigorously than
 * the combined testing file.
 * Specifically, the encryptDecrypt and decryptEncrypt StringBuilders are used
 * to confirm that encryption does the exact opposite of decryption.
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class CaesarDriver {
  public static void main(String[] args) throws IOException, FileNotFoundException {

    Scanner input = new Scanner(System.in);

    // Interact with the user to gather their desired conditions
    String title = "========================================\n" +
                   "===== Welcome to the Caesar Tester =====\n" +
                   "========================================";
    String question = "What shift do you desire for the Caesar Cipher?" +
                      "\nType a positive integer. Invalid input treated as 1.";
    System.out.println(title);
    System.out.println(question);
    int shiftInput = input.nextInt();
    int shift = shiftInput >= 0 ? shiftInput % 26 : 1;
    String response = "You have chosen a shift of " + shift + "!";
    System.out.println(response);

    // Create separate cipher objects for decryption and encryption
    CaesarCipher encrypter = new CaesarCipher(shift, true);
    CaesarCipher decrypter = new CaesarCipher(shift, false);

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
    System.out.println("Caesar encrypt result: " + encryptReturn.toString());
    System.out.println("Caesar decrypt result: " + decryptReturn.toString());
    System.out.println(correctResponse);
    input.close();
  }
}
