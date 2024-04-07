package Wordle.ui;
//import  Wordle.domain.*;
import Wordle.logic.*;
import java.util.Scanner;

public class UserInterface {
    protected  WordleLogic logic;
    private Scanner scanner;


    public UserInterface () {
        intro();
        this.scanner = new Scanner(System.in);
        this.logic = new WordleLogic();
        this.start();
    }


    public void intro() {
        System.out.println("=================================");
        System.out.println("WELCOME TO F-WORDLE\n " +
                "Guess today's five letter <<word of The Day>> in 6 tries and you win");
        System.out.println("=================================");
        System.out.println("""
                How to play:\s
                PLAY WITH CAPS LOCK ON:\n
                \t green indicates that the letter is correct and in the correct position.
                      yellow means it is in the answer but not in the right position.
                      while gray indicates it is not in the answer at all.""");
    }

    public void start() {
        int count = 0;


        while (count < 6) {
            System.out.println("Enter 5 letter word.");
            String read = scanner.nextLine();

            /* Ensures input is a 5-letter word and is not entered in lower case: */
            while(read.length() != 5 || read.equals(read.toLowerCase())) {
                System.out.println("Invalid input: Only 5 letter words in ALL CAPS"
                + "\nEnter word again.");
                read = scanner.nextLine();
            }

            boolean checkword = this.logic.checkWord(read);

            if (checkword && count == 0) {
                System.out.println("Unreal guess. You got it in " + count + 1 + " tries.");
                playAgain();
                break;
            } else if (checkword && count != 0) {
                System.out.println("Correct guess, You got today's word in " + (count + 1) + " tries." );
                playAgain();
                break;
            }else {
                this.getMessage(read);
                System.out.println(this.logic.builder +"\n " );
                //resets String builder to an empty string
                this.logic.clearString();
            }

            count++;
        }

        if (count == 6) {
            System.out.println("Sorry :( Word Of The Day is : " + this.logic.getWord());
            this.playAgain();
        }
    }

    public void getMessage(String input) {
        String string = "";
        string = this.logic.matchIndex(input);
        System.out.println(string);
    }

    public void playAgain() {
        System.out.println("Would you like to play again?"
            +"Enter YES/NO.");
        String answer = scanner.nextLine().toUpperCase();
        if (answer.equals("YES")) {
            UserInterface newInterface = new UserInterface();
        }
        else System.out.println("Thanks for playing. Auf Widersehen");
        System.exit(1);
    }
}
