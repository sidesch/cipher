/**
 * Using arguments, this will either encrypt or decrypt using the Caesar cipher and a given
 * message from the command line.
 *
 * @author Sarah Deschamps
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * A class consisting of a static function that uses the Caesar cipher to encode
 * and decode given text.
 */
public class AllCaesar {
  /**
   * A static method used to run the program loop.
   *
   * @param args
   *    Command line arguments.
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
      // if there are not exactly two arguments
    } else {
      PrintWriter pen = new PrintWriter(System.out, true);
      char[] arr = args[1].toCharArray();
      String code = args[0];
      String str = args[1];
      if (!code.equals("encode") && !code.equals("decode")) {
        System.err.println("Error: Invalid option \"" + code + "\"."
                           + "Valid options are \"encode\" or \"decode\".");
        return;
      } // if the first command line argument is not encode or decode
      if (!CipherUtils.allLetters(arr)) {
        System.err.println("Error: String contains characters other than lowercase letters.");
        return;
        // if the message contains anything other than lowercase letters
      } else {
        if (code.equals("encode")) {
          for (char ch = 'a'; ch <= 'z'; ch++) {
            pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(str, ch));
          } // for each lowercase character
          pen.close();
          // if the user wants to encrypt
        } else {
          for (char ch = 'a'; ch <= 'z'; ch++) {
            pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(str, ch));
          } // for each lowercase character
          pen.close();
        } // if the user wants to decrypt
      } // if there are no errors in the command line arguments
    } // if there are exactly two arguments
  } // main(String[] args)
} // class AllCaesar
