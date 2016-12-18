/**
 * CaesarDriver.java
 * @author James
 * December 15, 2016
 * Driver program specifically for Caesar cipher to test it more rigorously than
 * the combined testing file.
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.*;

public class CaesarDriver {
  public static void main(String[] args) throws IOException, FileNotFoundException {

    Scanner input = new Scanner(System.in);

    // Interact with the user
    String question = "What shift do you desire for the Caesar Cipher?" +
                      "\nType a positive integer. Invalid input treated as 1.";
    System.out.println(question);
    int shiftInput = input.nextInt();
    int shift = shiftInput >= 0 ? shiftInput % 26 : 1;
    String response = "You have chosen a shift of " + shift + "!";
    System.out.println(response);

    // Create Encoders/Decoders
    StringBuilder original = new StringBuilder();
    CaesarCipher encrypter = new CaesarCipher(shift, true);
    StringBuilder encryptReturn = new StringBuilder();
    CaesarCipher decrypter = new CaesarCipher(shift, false);
    StringBuilder decryptReturn = new StringBuilder();

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
        encryptReturn.append(encrypter.changeChar(nextString[a]));
        decryptReturn.append(decrypter.changeChar(nextString[a]));
        original.append(nextString[a]);
      }
    }

    // Comment out to remove spaces between words and increase security
    System.out.println("Original Message: " + original.toString());
    System.out.println("Caesar encrypt result: " + encryptReturn.toString());
    System.out.println("Caesar decrypt result: " + decryptReturn.toString());
    input.close();
  }
}
