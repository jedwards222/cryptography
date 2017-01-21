/**
 * GridDriver.java
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
import ciphers.GridCipher;

public class GridDriver {
  public static void main(String[] args) throws IOException, FileNotFoundException {

    Scanner input = new Scanner(System.in);

    // Welcome message to user
    String title = "========================================\n" +
                   "=== Welcome to the GridCipher Tester ===\n" +
                   "========================================";
    String message = "Your input file is being analyzed...";
    System.out.println(title);
    System.out.println(message);

    // StringBuilder used instead of String for efficiency
    StringBuilder original = new StringBuilder();

    // Create a Scanner to read the input file.
    File inputFile = new File("input.txt");
    try {
      input = new Scanner(inputFile);
      System.out.println("\nYour input file is valid.");
    }
    catch (FileNotFoundException e) {
      System.err.println("Could not open file. Try again.");
    }

    // Now the file has been opened. Read it until we read the last word
    while (input.hasNext()) {
      original.append(input.next());
    }
    input.close();

    // Interact with the user to gather their desired conditions
    String question = "\nPlease give a valid grid height. Restrictions:" +
                      "\nHeight must be greater than 1 and not larger than" +
                      "\nthe length of the message. Invalid input will be" +
                      "\nreplaced with a value of 2.";
    System.out.println(question);

    input = new Scanner(System.in);


    // Check for valid height and set it to 2 if invalid
    int height = input.nextInt();
    boolean valid = (height > 1 && height < original.length());
    if (!valid) height = 2;

    String response = valid ? "Your height was valid!" :
      "Your height was invalid. Height 2 used instead.";
    System.out.println(response);

    // Create separate cipher objects for decryption and encryption
    GridCipher encrypter = new GridCipher(height, true);
    GridCipher decrypter = new GridCipher(height, false);

    String encrypted = encrypter.changeMessage(original.toString());
    String encryptDecrypt = decrypter.changeMessage(encrypted.toString());

    String decrypted = decrypter.changeMessage(original.toString());
    String decryptEncrypt = encrypter.changeMessage(decrypted.toString());

    // Check if decryption is doing opposite of encryption
    boolean correct = (original.toString().equals(decryptEncrypt.toString()) &&
                       original.toString().equals(encryptDecrypt.toString()));
    String correctResponse = correct ?
      "Decryption and Encryption are working together correctly" :
      "There is an issue with decryption and encryption";

    // Give user results
    System.out.println("Original Message: " + original.toString());
    System.out.println("GridCipher encrypt result: " + encrypted.toString());
    System.out.println("GridCipher decrypt result: " + decrypted.toString());
    System.out.println(correctResponse);
    System.out.println("========================================");
    input.close();
  }
}
