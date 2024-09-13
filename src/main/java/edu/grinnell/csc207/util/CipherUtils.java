/**
 * A useful collection of functions for use when doing ciphers.
 *
 * @author Sarah Deschamps
 */

package edu.grinnell.csc207.util;

/**
 * Class containing utility functions for encoding and decoding.
 */
public class CipherUtils {
  /**
   * The number of letters in the alphabet.
   */
  public static final int NUM_LETTERS = 26;
  /**
   * Turns a lowercase character into an integer starting from 0 to 25.
   *
   * @param letter
   *    Character to be turned integer.
   *
   * @return the integer related to the given character.
   */
  private static int letter2int(char letter) {
    int base = (int) 'a';
    int num = (int) letter - base;
    return num;
  } // letter2int(char letter)

  /**
   * Turns an integer from 0 to 25 into a lowercase character.
   *
   * @param i
   *    Integer to be turned character.
   *
   * @return the character related to the given integer.
   */
  private static char int2letter(int i) {
    int base = (int) 'a';
    int num = base + i;
    return (char) num;
  } // int2letter(int i)

  /**
   * Encrypts a message using the Caesar cipher and a given key.
   *
   * @param str
   *    The message to be encrypted.
   *
   * @param letter
   *    The one-character key.
   *
   * @return an encrypted String message.
   */
  public static String caesarEncrypt(String str, char letter) {
    int key = letter2int(letter);
    char[] encrypted = new char[str.length()];
    for (int i = 0; i < str.length(); i++) {
      char encryptChar = int2letter(modifiedMod(letter2int(
                                                str.charAt(i)) + key, NUM_LETTERS));
      encrypted[i] = encryptChar;
    } // for each letter in the string
    String newMessage = new String(encrypted);
    return newMessage;
  } // caesarEncrypt(String str, char letter)

  /**
   * Decrypts a given message using a given key with the Caesar cipher.
   *
   * @param str
   *    The message to be decrypted.
   *
   * @param letter
   *    The key used to decrypt.
   *
   * @return a decrypted String message.
   */
  public static String caesarDecrypt(String str, char letter) {
    int key = letter2int(letter);
    char[] decrypted = new char[str.length()];
    for (int i = 0; i < str.length(); i++) {
      char decryptChar = int2letter(modifiedMod(letter2int(
                                                str.charAt(i)) - key, NUM_LETTERS));
      decrypted[i] = decryptChar;
    } // for each letter in the string
    String newMessage = new String(decrypted);
    return newMessage;
  } // caesarDecrypt(String str, char letter)

  /**
   * Encrypts a message using the Vigenere cipher using a given key.
   *
   * @param str
   *    The message to be encrypted.
   *
   * @param key
   *    The key used to encrypt the message.
   *
   * @return the encrypted String message.
   */
  public static String vigenereEncrypt(String str, String key) {
    int[] numkey = vigenereKey(key);
    char[] newmessage = new char[str.length()];
    for (int i = 0; i < key.length(); i++) {
      if (i < str.length()) {
        for (int j = i; j < str.length(); j += key.length()) {
          newmessage[j] = int2letter(modifiedMod(letter2int(
                                                 str.charAt(j)) + numkey[i], NUM_LETTERS));
        } // for every letter in the string spaced out by the key's length
      } // if i is less than the string length
    } // for each letter in the key
    String message = new String(newmessage);
    return message;
  } // vigenereEncrypt(String str, String key)

  /**
   * Decrypts a message using the Vigenere Cipher method and a given key.
   *
   * @param str
   *    The message to decrypt.
   *
   * @param key
   *    The key used to decrypt the message.
   *
   * @return the decrypted String message.
   */
  public static String vigenereDecrypt(String str, String key) {
    int[] numkey = vigenereKey(key);
    char[] newmessage = new char[str.length()];
    for (int i = 0; i < key.length(); i++) {
      for (int j = i; j < str.length(); j += key.length()) {
        newmessage[j] = int2letter(modifiedMod(letter2int(str.charAt(j)) - numkey[i], NUM_LETTERS));
      } // for every letter in the string spaced out by the key's length
    } // for each letter in the key
    String message = new String(newmessage);
    return message;
  } // vigenereDecrypt(String str, String key)

  /**
   * Finds the remainder of division, however for negative numbers it wraps around
   * the modulus.
   *
   * @param n
   *    The integer that will undergo the modified mod.
   *
   * @param modulus
   *    The integer around which the first parameter will wrap around (the modulus).
   *
   * @return an integer between 0 and the modulus.
   */
  private static int modifiedMod(int n, int modulus) {
    int result = n % modulus;
    if (result < 0) {
      result += modulus;
    } // if the result is negative, wrap around
    return result;
  } // modifiedMod(int n, int modulus)

  /**
   * Creates an array of integers from a String.
   *
   * @param key
   *    The string to be converted into a list of integers.
   *
   * @return a list of integers with the same length as the given String.
   */
  private static int[] vigenereKey(String key) {
    int[] numkey = new int[key.length()];
    for (int i = 0; i < key.length(); i++) {
      numkey[i] = letter2int(key.charAt(i));
    } // for each letter in the key
    return numkey;
  } // vigenereKey(String key)

  /**
   * Determines whether or not a char array only consists of lowercase letters.
   *
   * @param arr
   *    An array of characters.
   * @return true if it only contains lowercase letters, false otherwise.
   */
  public static boolean allLetters(char[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if ((arr[i]) < 'a' || (arr[i]) > 'z') {
        return false;
      } // if the letter is not lowercase
    } // for each letter in the array
    return true;
  } // allLetters(char[] arr)
} // class CipherUtils
