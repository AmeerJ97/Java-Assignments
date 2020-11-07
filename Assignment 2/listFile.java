package listFileJ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program reads a specified text file and echos it to standard
 * display.  You can use this as the basis of Assignment 2, adding the
 * necessary components to implement the program specifications.
 * @author ferrie
 *
 */

public class listFile{
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		
// Prompt user for a file name.  If no name is entered, terminate
// the program, otherwise attempt to open the file. If file open
// is not successful, prompt again for a new name.  Keep doing this
// until successful open, or a blank line is entered.
		
		System.out.println("Simple File Listing Program");
		Scanner sc = new Scanner(System.in);
		BufferedReader rd = null;
		
		while(rd == null) {
			System.out.print("Enter name of file to list: ");
			String filename = sc.nextLine();
			if (filename.equals("")) {
				System.out.println("Program terminated");
				System.exit(0);									// Exit
			}
// Try to open the specified file
			try {
				rd = new BufferedReader(new FileReader(filename));
			}
			catch (IOException ex) {
				System.out.println("Unable to open file, try again.");
			}
		}

// Read the file a line at a time into a string.  Print as read to the output display.
// Modify the code below as necessary.
		
		System.out.println("");
		try {
			while (true) {
				String line = rd.readLine();							// Read a line of text
				if (line == null) break;								// Exit if at end of file
				System.out.println(line);
				// Print (or do whatever) to
			}														// current line
		}
		catch (IOException ex) {
			System.out.println("I/O Error - program terminated");
			System.exit(-1);
		}
					
		System.out.println("\n\nProgram terminated");

	}
}
