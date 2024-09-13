/**
 * A program that encrypts and decrypts command line arguments.
 *
 * @author Sarah Deschamps
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Class that uses encryption and decryption on command line arguments.
 */
public class Cipher {
  /**
   * The number of command line arguments the program requires.
   */
  public static final int NUM_ARGUMENTS = 4;
  /**
   * A static method that takes four arguments that tell the program to either encrypt or
   * decrypt, which method to use (Caesar or Vigenere), and the message and key.
   * @param args
   *    Command line arguments
   */
  public static void main(String[] args) {
    if (args.length != NUM_ARGUMENTS) {
      System.err.println("Error: Expected 4 parameters, received " + args.length);
      return;
    } // if there are not exactly four parameters

    // initializing index values for each arg
    int codeIndex = -1;
    int cipherIndex = -1;
    int strIndex = -1;
    int keyIndex = -1;

    PrintWriter pen = new PrintWriter(System.out, true);
    for (int i = 0; i < args.length; i++) {
      if (codeIndex < 0 && args[i].equals("-encode") || args[i].equals("-decode")) {
        codeIndex = i;
        // if the user used the encode or decode tags
      } else if (cipherIndex < 0 && args[i].equals("-caesar") || args[i].equals("-vigenere")) {
        cipherIndex = i;
      } // if the user used the caesar or vigenere tags
    } // for each argument

    if (codeIndex < 0) {
      System.err.println("Error: No valid action specified. Legal values are "
                         + "'-encode' and '-decode'");
      return;
    } // if there is no encode or decode tag
    if (cipherIndex < 0) {
      System.err.println("Error: no type of cipher given");
      return;
    } // if there is no Cipher or Vigenere tag

    for (int i = 0; i < args.length; i++) {
      if (i != codeIndex && i != cipherIndex) {
        if (strIndex < 0) {
          strIndex = i;
          // if a string has not been assigned yet
        } else {
          keyIndex = i;
          break;
        } // if a string has been assigned
      } // if this index is not already a tag
    } // for each argument

    if (!(CipherUtils.allLetters(args[strIndex].toCharArray()))) {
      System.err.println("Error: strings must be only lowercase letters");
      return;
    } // if the string contains anything other than lowercase letters

    if (!(CipherUtils.allLetters(args[keyIndex].toCharArray()))) {
      System.err.println("Error: keys must be only lowercase letters");
      return;
    } // if the key contains anything other than lowercase letters
    String str = args[strIndex];

    if (args[cipherIndex].equals("-caesar")) {
      if (args[keyIndex].length() != 1) {
        System.err.println("Error: Caesar ciphers require a one-character key");
        return;
      } // if the key is more than one character
      char key = args[keyIndex].charAt(0);
      if (args[codeIndex].equals("-encode")) {
        pen.println(CipherUtils.caesarEncrypt(str, key));
        // if the user wants to encode
      } else {
        pen.println(CipherUtils.caesarDecrypt(str, key));
      } // if the user wants to decode
      // if Caesar
    } else {
      if (args[keyIndex].equals("")) {
        System.err.println("Error: Empty keys are not permitted");
        return;
      } // if the key is empty
      String key = args[keyIndex];
      if (args[codeIndex].equals("-encode")) {
        pen.println(CipherUtils.vigenereEncrypt(str, key));
        // if the user wants to encode
      } else {
        pen.println(CipherUtils.vigenereDecrypt(str, key));
      } // if the user wants to decode
    } // Vigenere cipher
    pen.close();
  } // main(String[] args)
} // class Cipher
