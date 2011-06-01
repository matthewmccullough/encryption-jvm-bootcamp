package com.ambientideas.cryptography;

import java.util.Random;

public class HashUtils {

	public static String hash(String password) {
		return "hashed" + password + "hashed";
	}
	
	public static String randomSalt() {
		return new Random().nextInt() + "";
	}
}
