package Wordle.logic;
import Wordle.domain.*;

import java.util.ArrayList;
import java.util.Random;

public class WordleLogic {
    public StringBuilder builder;
    public StringBuilder stringBuilder;
    private final String wordOfTheDay;
    private final Random rand;
//    int index = -1;

    public WordleLogic() {
        stringBuilder = new StringBuilder();
        rand = new Random();
        wordOfTheDay = getWordOfTheDay();
        builder = new StringBuilder("LETTERS NOT IN WORD: #");
    }

    public String getWordOfTheDay() {
        MyDictionary myDictionary = new MyDictionary();

        //returns a random int as (index) to get word from the arraylist
        int random = rand.nextInt(myDictionary.getSize());
        return myDictionary.getIndex(random);
    }

    /*logic to check words and indexes: Players have six attempts to guess a five-letter word,
     with feedback given for each guess in the form of colored tiles indicating when letters match or
      occupy the correct position.
      After every guess, each letter is marked as either green, yellow or gray:
      green indicates that the letter is correct and in the correct position,
      yellow means it is in the answer but not in the right position,
      while gray indicates it is not in the answer at all.

     */

    public boolean checkWord(String word) {
//        boolean check = false;
        String otherWord = word.toUpperCase();
        return otherWord.equals(wordOfTheDay);
    }


    public boolean charIsInWord(char c) {
        // checks if a character appears in the word
        for ( int i = 0; i < wordOfTheDay.length(); i++) {
            if (wordOfTheDay.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }



    public String matchIndex(String input) {
        //checks for letters that appear in words but at the wrong index
        // arraylist is used to use List interface' indexOf method
        char[] inputArray = input.toCharArray();
        char[] charsOfTheDay = wordOfTheDay.toCharArray();

        for (int i = 0; i < charsOfTheDay.length; i++) {
            char letter = inputArray[i];

            if(charIsInWord(letter)) {
                int indexInWord = wordOfTheDay.indexOf(letter);
                if(indexInWord == i) {
                    stringBuilder.append(letter).append(" is in the correct position.").append("\n");
                } else {
//                    sanitizeBuilder(letter);
                    stringBuilder.append(letter).append(" is in word but wrong index \n");
                }
            } else {
                //prevents repetition of characters, letter is assigned on first iteration
                // on every iteration we check if letter already exists.
                sanitizeBuilder(letter);
            }
        }


        return stringBuilder.toString();

    }
    public String getWord() {
        return  wordOfTheDay;
    }

    public void sanitizeBuilder(char s) {
        String newString = builder.toString();
        String[] sanitizedString = newString.split(": ");

        if (sanitizedString.length > 1) {
            char[] characters = sanitizedString[1].toCharArray();
            boolean exists = false;

            //for loop to iterate through all characters
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] == s) {
                    exists = true;
                    break;
                }
            }
            if (!exists) builder.append(s).append(" ");

        }

    }

    //public void to clear string builder
    //It is called by the method in user interface message after every full iteration,
    public void clearString() {
        stringBuilder.delete(0,stringBuilder.length()-1);
    }


}
