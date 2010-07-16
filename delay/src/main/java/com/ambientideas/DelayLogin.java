package com.ambientideas;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Use the SecureRandom java security class to generate
 * a more expensive, but cryptographically secure random number.
 */
public class DelayLogin 
{
  public static void main( String[] args ) throws IOException, InterruptedException {
	//Static map for username and incorrect login attempts
	Map wrongLoginAttemps = new HashMap();
	wrongLoginAttemps.put("matthewm", new Integer(0));
	
	boolean loginSuccess = false;
	String correctPassword = "1234";
	
    while (!loginSuccess) {
		//Prompt for username
    		System.out.println("Username?: ");
		Scanner input = new Scanner(System.in);
		String attemptedUsername = input.next();
		
		//Prompt for password
		System.out.println("Password?: ");
		String attemptedPassword = input.next();
		
		//If password is right
		if (attemptedPassword.equals(correctPassword)) {
			System.out.println("Password correct.");
			loginSuccess = true;
		}
		else {
			System.out.println("Password or username incorrect.");
			int count = 1;
			
			//Non-existent username guard
			if (wrongLoginAttemps.get(attemptedUsername) != null) {
				count = ((Integer)wrongLoginAttemps.get(attemptedUsername)).intValue() + 1;
			}
			wrongLoginAttemps.put(attemptedUsername, new Integer(count));
			
			//If previously incorrect, delay by a small amount before allowing
			//another login attempt
			System.out.println("Throttling by a factor of: " + count);
			long sleepTime = (long) (100 * Math.pow(2, count));
			Thread.sleep(sleepTime);
			System.out.println("Sleep time was, in milliseconds: " + sleepTime);
		}
    }
  }
}
