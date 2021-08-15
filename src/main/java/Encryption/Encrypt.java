package Encryption;

import java.util.Base64;

public class Encrypt{


	private Base64.Encoder encoder = Base64.getEncoder();
	
	public String encrpytData(String data)
	{
		String strEncrypted = encoder.encodeToString(data.getBytes());
		
		
		return strEncrypted;
	}

}