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
     * Currently implemented:
     * 		a -> e | e -> i | i -> o | o -> u | u -> a
     * 		(Works only with the lowercase versions)
     * 			NOTE: Can easily be changed with changing of the switch cases
     * 
     * Testing Conclusions:
     * So far, this method is inconsistent at least from it's original form
     */
    public static String replacer(String phraseToEncrypt) {
        char[] phrase = phraseToEncrypt.toCharArray();

        for(int i = 0; i < phraseToEncrypt.length()-1; i++){

            switch (phrase[i]) {
                case 'a':
                    phrase[i] = 'e';
                    break;
                case 'e':
                    phrase[i] = 'i';
                    break;
                case 'i':
                    phrase[i] = 'o';
                    break;
                case 'o':
                    phrase[i] = 'u';
                    break;
                case 'u':
                phrase[i] = 'a';
                break;
            }
            phraseToEncrypt = String.valueOf(phrase);

        }


        return phraseToEncrypt;
    }

}
