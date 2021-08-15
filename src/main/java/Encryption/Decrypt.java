package Encryption;

import java.util.Base64;

public class Decrypt {

	
private static Base64.Decoder dec = Base64.getDecoder();
	
	/*
	 * 	IF you receive an "Illegal Argument Exception" or other type of error from this method:
	 * 	-> 	Then the data you have sent here has not been encrypted and this method WILL fail.
	 */
	public static String decryptData(String data)
	{
		 String decodedData = new String(dec.decode(data));
		 
		 return decodedData;
	}
}