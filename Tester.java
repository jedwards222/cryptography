/**
 * Tester.java
 * @author James
 * June 3, 2016
 * Test program for the various ciphers in this project. Creates a cipher
 * object of the Caesar, Shifting, and Custom varieties. The user selects the
 * file to be encrypted.
 */

import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JPopupMenu;
import java.io.*;

public class Tester {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		Scanner input = new Scanner(System.in);
		
		// Collect initial input data
		/*
		System.out.println("Please type the name of the cipher being used");
		String cipherName = input.nextLine();
		System.out.println("Are you encrypting (type E) or decrypting (type D)?");
		String encrypting = input.nextLine();
		*/
		
		// Custom Cipher Alphabets
		char[] alpha = {'d', 'o', 'm', 'i', 'n', 'q', 'u', 'e', 'j', 
				  'a', 's', 'l', 'v', 'y', 'f', 'r', 'z', 'x', 
				  'w', 't', 'b', 'c', 'g', 'h', 'k', 'p'};
		char[] beta = {'n', 'b', 'm', 'v', 'g', 'q', 'z', 'x', 'a', 
				  'j', 's', 'y', 'l', 'i', 'f', 'k', 'e', 'u', 
				  'w', 't', 'o', 'c', 'd', 'h', 'r', 'p'};
		
		
		// Needs work: figure out how to give user option to 
		// determine whether to encode/decode, which cipher to use
		JPopupMenu selections = new JPopupMenu();
		selections.setSize(100, 50);
		
		// Create Encoders/Decoders
		String original = "";
		Caesar mystery0 = new Caesar(5, true);
		String caesarReturn = "";
		CustomCipher mystery1 = new CustomCipher(alpha, false);
		String customReturn = "";
		ShiftingCustomCipher mystery2 = new ShiftingCustomCipher(beta, true, 1);
		String shiftingReturn = "";
		
		 // Put up a file chooser window to select a file to read. 
	    JFileChooser chooser = new JFileChooser("/Users/James/Documents/workspace/cryptography/src/");
	    int status = chooser.showOpenDialog(null);

	    
	    // This section chooses a file to read and encode
	    boolean fileOpened = true;    // OK unless proven otherwise
	    do {
	      // Get a file from the file chooser.  If the user hits the Cancel button,
	      // end the program.
	      while (status != JFileChooser.APPROVE_OPTION) {
	        if (status == JFileChooser.CANCEL_OPTION)
	          System.exit(0);
	        else {
	          System.out.println("No file chosen.  Try again.");
	          status = chooser.showOpenDialog(null);
	        }
	      }

	      // We have a file from the file chooser.  We should be able to create a
	      // Scanner to read the file.
	      File inputFile = chooser.getSelectedFile();
	      try {
	        input = new Scanner(inputFile);
	      }
	      catch (FileNotFoundException e) {
	        fileOpened = false;
	        System.out.println("Could not open file. Try again.");
	      }
	    }
	    while (!fileOpened);

	    
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