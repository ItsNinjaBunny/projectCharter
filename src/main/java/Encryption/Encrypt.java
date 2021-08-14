package Encryption;

public class Encrypt{



    /*
     *  This method changes numbers into their "Shift" character on a keyboard
     *  Current Example: 1 -> ! | 2 -> @ | 3 -> #
     *
     *  Testing Conclusions:
     *  WARNING: IF there are both special characters AND numeric characters it will corrupt the original data
     *  Example: "123 !@#" --> "!@# !@#"
     *  This causes loss of the original values when it comes time to decrypt this encryption method
     *
     *  Fix for the corruption issue:
     *  	-Allow this method to work vice versa (Ex. [1 -> !] and upon another run of this method  [! -> 1]
     *  This method should be put into the replacer method after the Skeleton for this Project is complete and ideally before Prototype.
     */
    public String shiftChars(String phraseToEncrypt)
    {
        if(phraseToEncrypt == null || phraseToEncrypt.isBlank()) {
            phraseToEncrypt = "";

        } else {

            char[] phrase = phraseToEncrypt.toCharArray();

            for (int i = 0; i <= phraseToEncrypt.length()-1; i++) {

                switch (phrase[i]) {
                    case '1':
                        phrase[i] = '!';
                        break;
                    case '2':
                        phrase[i] = '@';
                        break;
                    case '3':
                        phrase[i] = '#';
                        break;
                    case '4':
                        phrase[i] = '$';
                        break;
                    case '5':
                        phrase[i] = '%';
                        break;
                    case '6' :
                        phrase[i] = '^';
                        break;
                    case '7':
                        phrase[i] = '&';
                        break;
                    case '8':
                        phrase[i] = '*';
                        break;
                    case '9':
                        phrase[i] = '(';
                        break;
                    case '0':
                        phrase[i] = ')';
                        break;
                    case '`':
                        phrase[i] = '~';
                        break;
                    case '-':
                        phrase[i] = '_';
                        break;
                    case '=':
                        phrase[i] = '+';
                        break;
                    case 'x':
                        phrase[i] = 'D';
                        break;
                }
            }
            phraseToEncrypt = String.valueOf(phrase);
        }


        return phraseToEncrypt;
    }



    /* ----------------------------------------------
     * Method doubles the length of inputed String
     * Example: John Cena -> John CenaJohn Cena
     *
     * Testing Conclusions:
     * 	Ran into no errors so far in testing
     * ----------------------------------------------
     */
    public static String doublePhrase(String phraseToEncrypt) {
        phraseToEncrypt = phraseToEncrypt + phraseToEncrypt;
        return phraseToEncrypt;
    }



    /*
     * Replaces any char with a new one
     *
     * Currently implemented:
     * 		a -> e | e -> i | i -> o | o -> u | u -> a
     *      b -> c | c -> d | d -> f | f -> g | g -> h  , etc.
     * 			NOTE: Can easily be changed with changing of the switch cases
     *
     * Testing Conclusions:
     *		Ready to Go
     */
    public static String replacer(String phraseToEncrypt) {
        char[] phrase = phraseToEncrypt.toCharArray();

        for(int i = 0; i <= phraseToEncrypt.length()-1; i++){

            switch (phrase[i]) {
                case 'a' -> phrase[i] = 'e';
                case 'e' -> phrase[i] = 'i';
                case 'i' -> phrase[i] = 'o';
                case 'o' -> phrase[i] = 'u';
                case 'u' -> phrase[i] = 'a';
                case 'A' -> phrase[i] = 'E';
                case 'E' -> phrase[i] = 'I';
                case 'I' -> phrase[i] = 'O';
                case 'O' -> phrase[i] = 'U';
                case 'U' -> phrase[i] = 'A';
                case 'b' -> phrase[i] = 'c';
                case 'c' -> phrase[i] = 'd';
                case 'd' -> phrase[i] = 'f';
                case 'f' -> phrase[i] = 'g';
                case 'g' -> phrase[i] = 'h';
                case 'h' -> phrase[i] = 'j';
                case 'j' -> phrase[i] = 'k';
                case 'k' -> phrase[i] = 'l';
                case 'l' -> phrase[i] = 'm';
                case 'm' -> phrase[i] = 'n';
                case 'n' -> phrase[i] = 'p';
                case 'p' -> phrase[i] = 'q';
                case 'q' -> phrase[i] = 'r';
                case 'r' -> phrase[i] = 's';
                case 's' -> phrase[i] = 't';
                case 't' -> phrase[i] = 'v';
                case 'v' -> phrase[i] = 'w';
                case 'w' -> phrase[i] = 'x';
                case 'x' -> phrase[i] = 'z';
                case 'z' -> phrase[i] = 'b';
                case 'B' -> phrase[i] = 'C';
                case 'C' -> phrase[i] = 'D';
                case 'D' -> phrase[i] = 'F';
                case 'F' -> phrase[i] = 'G';
                case 'G' -> phrase[i] = 'H';
                case 'H' -> phrase[i] = 'J';
                case 'J' -> phrase[i] = 'K';
                case 'K' -> phrase[i] = 'L';
                case 'L' -> phrase[i] = 'M';
                case 'M' -> phrase[i] = 'N';
                case 'N' -> phrase[i] = 'P';
                case 'P' -> phrase[i] = 'Q';
                case 'Q' -> phrase[i] = 'R';
                case 'R' -> phrase[i] = 'S';
                case 'S' -> phrase[i] = 'T';
                case 'T' -> phrase[i] = 'V';
                case 'V' -> phrase[i] = 'W';
                case 'W' -> phrase[i] = 'X';
                case 'X' -> phrase[i] = 'Z';
                case 'Z' -> phrase[i] = 'B';
                case '.' -> phrase[i] = ',';
                case ',' -> phrase[i] = ';';
                case ';' -> phrase[i] = ':';
                case ':' -> phrase[i] = '"';
                case '"' -> phrase[i] = '_';
                case '_' -> phrase[i] = '-';
                case '-' -> phrase[i] = '=';
                case '=' -> phrase[i] = '|';
                case '|' -> phrase[i] = '.';
            }
            phraseToEncrypt = String.valueOf(phrase);

        }


        return phraseToEncrypt;
    }

}