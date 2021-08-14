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
                    case 'e' -> phrase[i] = 'a';
                    case 'i' -> phrase[i] = 'e';
                    case 'o' -> phrase[i] = 'i';
                    case 'u' -> phrase[i] = 'o';
                    case 'a' -> phrase[i] = 'u';
                    case 'E' -> phrase[i] = 'A';
                    case 'I' -> phrase[i] = 'E';
                    case 'O' -> phrase[i] = 'I';
                    case 'U' -> phrase[i] = 'O';
                    case 'A' -> phrase[i] = 'U';
                    case 'c' -> phrase[i] = 'b';
                    case 'd' -> phrase[i] = 'c';
                    case 'f' -> phrase[i] = 'd';
                    case 'g' -> phrase[i] = 'f';
                    case 'h' -> phrase[i] = 'g';
                    case 'j' -> phrase[i] = 'h';
                    case 'k' -> phrase[i] = 'j';
                    case 'l' -> phrase[i] = 'k';
                    case 'm' -> phrase[i] = 'l';
                    case 'n' -> phrase[i] = 'm';
                    case 'p' -> phrase[i] = 'n';
                    case 'q' -> phrase[i] = 'p';
                    case 'r' -> phrase[i] = 'q';
                    case 's' -> phrase[i] = 'r';
                    case 't' -> phrase[i] = 's';
                    case 'v' -> phrase[i] = 't';
                    case 'w' -> phrase[i] = 'v';
                    case 'x' -> phrase[i] = 'w';
                    case 'z' -> phrase[i] = 'x';
                    case 'b' -> phrase[i] = 'z';
                    case 'C' -> phrase[i] = 'B';
                    case 'D' -> phrase[i] = 'C';
                    case 'F' -> phrase[i] = 'D';
                    case 'G' -> phrase[i] = 'F';
                    case 'H' -> phrase[i] = 'G';
                    case 'J' -> phrase[i] = 'H';
                    case 'K' -> phrase[i] = 'J';
                    case 'L' -> phrase[i] = 'K';
                    case 'M' -> phrase[i] = 'L';
                    case 'N' -> phrase[i] = 'M';
                    case 'P' -> phrase[i] = 'N';
                    case 'Q' -> phrase[i] = 'P';
                    case 'R' -> phrase[i] = 'Q';
                    case 'S' -> phrase[i] = 'R';
                    case 'T' -> phrase[i] = 'S';
                    case 'V' -> phrase[i] = 'T';
                    case 'W' -> phrase[i] = 'V';
                    case 'X' -> phrase[i] = 'W';
                    case 'Z' -> phrase[i] = 'X';
                    case 'B' -> phrase[i] = 'Z';
                    case ',' -> phrase[i] = '.';
                    case ';' -> phrase[i] = ',';
                    case ':' -> phrase[i] = ';';
                    case '"' -> phrase[i] = ':';
                    case '_' -> phrase[i] = '"';
                    case '-' -> phrase[i] = '_';
                    case '=' -> phrase[i] = '-';
                    case '|' -> phrase[i] = '=';
                    case '.' -> phrase[i] = '|';
                }
            }
            phraseToDecrypt = String.valueOf(phrase);
        }

        return phraseToDecrypt;

    }

    /*
     * --------------------------------------------------------------------------------------------------
     * Decrypts the DoublePhrase controllers.Encrypt Method
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
