/**
 * A simple rotation ciper helper.
 *
 * USAGE: groovy RotateWord.groovy <yourword>
 */
public class RotateWord {
  public static void main (String[] args) {
    String originalword = args[0]
    println "Rot(-3)  Word: " + rotateAllChars(originalword, -3)
    println "Rot(-2)  Word: " + rotateAllChars(originalword, -2)
    println "Rot(-1)  Word: " + rotateAllChars(originalword, -1)
    println "Original Word: ${originalword}"
    println "Rot(1)   Word: " + rotateAllChars(originalword, 1)
    println "Rot(2)   Word: " + rotateAllChars(originalword, 2)
    println "Rot(3)   Word: " + rotateAllChars(originalword, 3)
    println "Rot(4)   Word: " + rotateAllChars(originalword, 4)
    println "Rot(5)   Word: " + rotateAllChars(originalword, 5)
    println "Rot(6)   Word: " + rotateAllChars(originalword, 6)
    println "Rot(7)   Word: " + rotateAllChars(originalword, 7)
  }
  
  /**
   * Rotate the entire String by the specified rotation amount.
   */
  public static String rotateAllChars(String plainText, int rotationAmount) {
    if (plainText == null)
      throw new IllegalArgumentException("plainText can't be null")

    String encodedMessage = ""
    
    //Loop through each character in the plaintext, rotating each one
    for (int i = 0; i < plainText.length(); i++) {
      //TODO: Improve to handle upper and lower case letters
      char c = plainText.toLowerCase().charAt(i)
      encodedMessage += rotateChar(c, rotationAmount)
    }
    
    return encodedMessage
  }
  
  /**
   * Rotate one character by the specified amount
   */
  private static char rotateChar(char c, int rotationAmount) {
    //a == 97
    //z == 122
    int num = (int)c
    int rotated = num + rotationAmount
    int adjusted
    
    //Handle roll-around wrapping
    if (rotated > 122)
      adjusted = rotated - 26
    else if (rotated < 97)
      adjusted = rotated + 26
    else
      adjusted = rotated
    
    char adjustedChar = (char)adjusted
    return adjustedChar
  }
}