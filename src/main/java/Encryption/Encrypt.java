package Encryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encrypt{


	private static Base64.Encoder encoder = Base64.getEncoder();
	
	public static String encrpytData(String data)
	{
		String strEncrypted = encoder.encodeToString(data.getBytes(StandardCharsets.UTF_8));
		
		
		return strEncrypted;
	}

}