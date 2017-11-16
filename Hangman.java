/* Good Work
 * Score 18
 */
import java.util.*;

class Hangman {
    ArrayList<String> words;  // access should be private
    String wordToGuess;
    boolean[] guessStatus;
    ArrayList<String> correctList;
    ArrayList<String> wrongList;
    String[] picture;
    int round;
    

    public Hangman() {
        round = 1;
        String[] list = {"spiderman", "superman", "wonderwoman"};
        words = new ArrayList<>(Arrays.asList(list));
        
        correctList = new ArrayList<>();
        wrongList = new ArrayList<>();

        picture = new String[]{
            "----------",
            "|        |",
            "|",
            "|",
            "|",
            "|",
            "|",
            "|",
            "|--------------------\n",
        };
    }


    
    // Randomly chooses a word from the list.
    public void chooseWord() {
        wordToGuess =  words.get((int) (Math.random() * words.size()));
        guessStatus = new boolean[wordToGuess.length()];
    }    

    // handle the guess and add the letter to correctList or WrongList.
    public boolean handleGuess(String guess) {// not handling the guess where word has multiple guessed letters. 
        // eg: Word = Tall, guess is l, you have to reveal all the occurences of l
        for (int i = 0; i < wordToGuess.length(); i++ ) {
            String cur = Character.toString(wordToGuess.charAt(i));
            if (guess.equals(cur) && !guessStatus[i]) {
                correctList.add(guess);
                guessStatus[i] = true;
                return true;
            }
        }

        wrongList.add(guess);
        return false;
    } 
    
    // return true if user wins the game.
    public boolean gameWon() {
        for(int i = 0; i < guessStatus.length; i++) {
            if(guessStatus[i] == false) {
                return false;
            }
        }
        return true;
    } 

    // exit the program after the game is over.
    public void gameOver() {

    }

    
    // print hangman after every guess.
    public void printHangman() {
        int wrongGuess = wrongList.size();
        switch (wrongGuess) {
            case 1: picture[2] = "|        O";
                    break;
            case 2: picture[3] = "|        |";
                    break;
            case 3: picture[4] = "|     ---";
                    break;
            case 4: picture[4] = "|     --- ---";
                    break;
            case 5: picture[5] = "|       /";
                    picture[6] = "|      /";
                    break;
            case 6: picture[5] = "|       / \\";
                    picture[6] = "|      /   \\";
                    break;
            case 7: picture[7] = "|    --";
                    break;
            case 8: picture[7] = "|    --     --";
                    break;
            default: break;
        }
        for(String p : picture) {
            System.out.println(p);
        }
    }
    public String takeGuess() {
        String guess= "";
        while (guess.length() != 1 ) {
            System.out.println("Just one letter each time:");
            Scanner input = new Scanner(System.in);
            guess = input.nextLine().toLowerCase();
        }
        return guess;
    }

   
    // Starts the game.
    public void playGame() {
        chooseWord();
        String guess = "";
        while ( true ) {
            // NOTE: these two line clean the console. This works on
            //  almost all UNIX terminals and terminal emulators. The
            //   Windows cmd.exe does not interprete ANSI escape codes.
            // So please run this program in bash if you use Windows.
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("======== Round " + round + " ========");
            if (!guess.equals("")) {
                if (handleGuess(guess)) { // you have print all the previously guessed letters. you are printing only one
                    System.out.println("Your last guess is "+ guess + ", which is correct!");
                } else {
                    System.out.println("Your last guess is "+ guess + ", which is wrong!");
                }
                System.out.println("Remaining chances: " + (8 - wrongList.size()));
            } else {
                System.out.println("Welcome to HangMan Game!");
            }
            System.out.print("Word on guessing: ");
            displayWord();
            printHangman();

            if ( wrongList.size() == 8 || gameWon() ) {
                break;
            }
            
            System.out.print("Please input your guess.");
            guess = takeGuess();
            round++;
        }


        if (gameWon()) {
            System.out.println("Congratulation! You win!");
        } else {
            System.out.println("Game over");
        }


    }


    // display the correctly guessed letters and hide the remaining with dashes.
    public void displayWord() {
        int len = wordToGuess.length();
        char[] wordArr = wordToGuess.toCharArray();
        for (int i = 0; i < len; i++) {
            if (guessStatus[i]) {
                System.out.print(wordArr[i]);
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        boolean tryAgain = true;
        while (tryAgain) {
            Hangman game = new Hangman();
            game.playGame();
            String ans = "";
            while ( !ans.equals("y") && !ans.equals("n")) {
                System.out.println("Would you like to try again? Input y / n");
                Scanner input = new Scanner(System.in);
                ans = input.nextLine().toLowerCase();
            }
            if (ans.equals("n")) {
                tryAgain = false;
            } 
        } 
    }
}
