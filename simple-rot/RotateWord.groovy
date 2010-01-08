public class RotateWord {
  public static void main (String[] args) {
    //char[] originalword = args[0]
    println "Rot(-3)  Word: " + encode(args[0], -3)
    println "Rot(-2)  Word: " + encode(args[0], -2)
    println "Rot(-1)  Word: " + encode(args[0], -1)
    println "Original Word: ${args[0]}"
    println "Rot(1)   Word: " + encode(args[0], 1)
    println "Rot(2)   Word: " + encode(args[0], 2)
    println "Rot(3)   Word: " + encode(args[0], 3)
    println "Rot(4)   Word: " + encode(args[0], 4)
    println "Rot(5)   Word: " + encode(args[0], 5)
    println "Rot(6)   Word: " + encode(args[0], 6)
    println "Rot(7)   Word: " + encode(args[0], 7)
  }
  
  public static String encode(String plainText, int rotationAmount) {
    // deal with the case that method is called with null argument
    if (plainText == null) return plainText;

    // encode plainText
    String encodedMessage = "";
    for (int i = 0; i < plainText.length(); i++) {
      char c = plainText.toLowerCase().charAt(i);
      encodedMessage += rotateChar(c, rotationAmount);
    }
    return encodedMessage;
  }
  
  private static char rotateChar(char c, int rotationAmount) {
    
    //a == 97
    //z == 122
    int num = (int)c
    int rotated = num + rotationAmount
    int adjusted = 0;
//    println "Num: ${num}"
//    println "Rotated: ${rotated}"
    if (rotated > 122)
      adjusted = rotated - 26
    else if (rotated < 97)
      adjusted = rotated + 26
    else
      adjusted = rotated
    char adjustedChar = (char)adjusted
//    println "Adjusted: ${adjusted}"
//    println "Returning: " + adjustedChar
    return adjustedChar
  }
}