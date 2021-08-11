package Encryption;

public class Decrypt {

	
	/* ----------------------------------------------------------------------------------------------
	 * Decrypts the Replacer Encrypt Method
	 * 
	 * !NOTE: If any changes are made to the Replacer method the inverse MUST be made to this method
	 * ----------------------------------------------------------------------------------------------
	 */
	public static String decryptReplacer(String phraseToDecrypt) {
		
        if(phraseToDecrypt == null || phraseToDecrypt.isBlank()) {
            phraseToDecrypt = "";
        } else {
        
        char[] phrase = phraseToDecrypt.toCharArray();

        for (int i = 0; i <= phraseToDecrypt.length()-1; i++) {

            switch (phrase[i]) {
                case 'e':
                    phrase[i] = 'a';
                    break;
                case 'i':
                    phrase[i] = 'e';
                    break;
                case 'o':
                    phrase[i] = 'i';
                    break;
                case 'u':
                    phrase[i] = 'o';
                    break;
                case 'a':
                    phrase[i] = 'u';
                    break;
            }
        }
        phraseToDecrypt = String.valueOf(phrase);
        }

            return phraseToDecrypt;

    }
	
	/* 
	 * --------------------------------------------------------------------------------------------------
	 * Decrypts the DoublePhrase Encrypt Method
	 * 
	 * Method halves the length of an inputed String
	 * 
	 * Warning: 
	 * 		If the phrase has not been encrypted by DoublePhrase this method WILL corrupt the data!
	 * 			
	 * 			Orignal: John Cena 
	 * 			Result : John
	 * --------------------------------------------------------------------------------------------------
	 */ 
	public static String decryptDoublePhrase(String phraseToDecrypt) {

        phraseToDecrypt = phraseToDecrypt.substring(0,phraseToDecrypt.length()/2);
        return phraseToDecrypt;
    }
	
	 /*
     *	This method reverts the shift changed of the "Shift" encode method
     *	NOTE: Will change ANY of the special characters(on the top row of your keyboard) into their number equivalents
     *	Example: ! -> 1 | @ -> 2 | 3 -> #
     */
    public String decryptShiftChars(String phraseToDecrypt)
    {
        if(phraseToDecrypt == null || phraseToDecrypt.isBlank()) {
            phraseToDecrypt = "";
        } else {

            char[] phrase = phraseToDecrypt.toCharArray();

            for (int i = 0; i <= phraseToDecrypt.length()-1; i++) {

                switch (phrase[i]) {

                    case '!':
                        phrase[i] = '1';
                        break;
                    case '@':
                        phrase[i] = '2';
                        break;
                    case '#':
                        phrase[i] = '3';
                        break;
                    case '$':
                        phrase[i] = '4';
                        break;
                    case '%':
                        phrase[i] = '5';
                        break;
                    case '^' :
                        phrase[i] = '6';
                        break;
                    case '&':
                        phrase[i] = '7';
                        break;
                    case '*':
                        phrase[i] = '8';
                        break;
                    case '(':
                        phrase[i] = '9';
                        break;
                    case ')':
                        phrase[i] = '0';
                        break;
                    case '~':
                        phrase[i] = '`';
                        break;
                    case '_':
                        phrase[i] = '-';
                        break;
                    case '+':
                        phrase[i] = '=';
                        break;
                    case 'D':
                        phrase[i] = 'x';
                        break;
                }
            }
            phraseToDecrypt = String.valueOf(phrase);
        }

        return phraseToDecrypt;
    }
    
}
