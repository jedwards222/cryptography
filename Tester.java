/**
 * Tester.java
 * @author James
 * June 3, 2016
 * Test program for the various ciphers in this project. Creates a cipher
 * object of the Caesar, Shifting, and Custom varieties. The file input.txt will
 * used as the input file for the cipher.
 *
 * TODO:
 *		1. add in feature for user to create cipher alphabet file
 *		2. try implementing FileChooser correctly
 *		3. output results to an output file, allow for clearing of input file?
 *		4. Allow user to choose only one cipher result
 */

import java.util.Scanner;
import javax.swing.*;
import java.io.*;
// import java.util.concurrent.TimeUnit;

public class Tester {
	public static void main(String[] args) throws IOException, FileNotFoundException {

		// Custom Cipher Alphabets
		char[] alpha = {'d', 'o', 'm', 'i', 'n', 'q', 'u', 'e', 'j',
				  'a', 's', 'l', 'v', 'y', 'f', 'r', 'z', 'x',
				  'w', 't', 'b', 'c', 'g', 'h', 'k', 'p'};
		char[] beta = {'n', 'b', 'm', 'v', 'g', 'q', 'z', 'x', 'a',
				  'j', 's', 'y', 'l', 'i', 'f', 'k', 'e', 'u',
				  'w', 't', 'o', 'c', 'd', 'h', 'r', 'p'};

		Scanner input = new Scanner(System.in);

		// Interact with the user
		String question = "Are you encrypting or decrypting the message?\n" +
												"Please type \"E\" or \"e\" if you are encrypting.\n" +
												"Any other input will be interpreted as a request for decryption.";
		System.out.println(question);
		String encryptInput = input.nextLine();
		boolean encrypt = (encryptInput.equals("E") || encryptInput.equals("e"));
		String response = encrypt ? "You have chosen to encrypt!" : "You have chosen to decrypt!";
		System.out.println(response);
/* This section commented as JFileChooser pop ups were failing to work properly
		response += "\nA popup will shortly open to allow you to choose the file to run the cipher on.";
		System.out.println(response);
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ie) {
			System.err.println("InterruptedException occurred when trying to sleep");
		}
*/

		// Create Encoders/Decoders
		String original = "";
		Caesar mystery0 = new Caesar(5, encrypt);
		String caesarReturn = "";
		CustomCipher mystery1 = new CustomCipher(alpha, encrypt);
		String customReturn = "";
		ShiftingCustomCipher mystery2 = new ShiftingCustomCipher(beta, encrypt, 1);
		String shiftingReturn = "";

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
    		caesarReturn += (mystery0.changeChar(nextString[a]));
    		customReturn += (mystery1.changeChar(nextString[a]));
    		shiftingReturn += (mystery2.changeChar(nextString[a]));
    		original += nextString[a];
    	}
    }
  	// Comment out to remove spaces between words and increase security
    System.out.println("Original Message: " + original);
  	System.out.println("Caesar Result: " + caesarReturn);
  	System.out.println("Custom Cipher Result: " + customReturn);
  	System.out.println("Shifting Cipher Result: " + shiftingReturn);
    input.close();
	}
}
