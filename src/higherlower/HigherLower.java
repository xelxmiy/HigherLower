package higherlower;

import javax.swing.*;

/**
 * HigherLower - using a binary search algorithm to guess the number a user is 
 * choosing
 * This took 3 tries to make and was the most difficult one...d
 * HigherLower - a game which guesses a number the user picks in their head!
 * this one was weirdly hard and took 3 tries
 * 
 * @author Adam Belski
 * @version 3.3.0
 * @since 09-Feb-2023
 */

public class HigherLower {   
    ///init vars 
    private static int guessCounter = 1;
    private static int min = 0;
    private static int max = 101;
    private static int prevGuess = max / 2;
    private static String HigherLower;
    private static String[] args;
    private final static Object[] options = {"higher", "lower", "correct!"};

    public static void main(String[] args) {
        //for debbuging, please ignore
        Thread newThread = new Thread(() -> {
            while (true) {
                System.out.println("min: " + min);
                System.out.println("max: " + max);
                System.out.println("prevguess: " + prevGuess);
                System.out.println("HigherLower: " + HigherLower);
                System.out.println("guessnumber: " + guessCounter);
            }
        });
        newThread.start();

        JOptionPane.showMessageDialog(null,
                "Welcome to Higher/Lower!\n"
                + " pick a number between 0-100 "
                + "(inclusive)\nand i will try to guess it in at most 7 guesses!"
                + "\n(as long as you're being honest)",
                "HigherLower",
                JOptionPane.INFORMATION_MESSAGE);

        //first guess is always 50
        JPanel panel = new JPanel();
        panel.add(new JLabel("I guess... 50!"));
        //i think the switch here looks ugly asf but i'd rather the program not yell at me 
        
         int result = JOptionPane.showOptionDialog(null,
               panel, "am i correct? ",
             JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE,
                  null, options, null);
        //i think the switch here looks bad but the program is yelling at me 
        switch (result) {
            case JOptionPane.YES_OPTION:
                HigherLower = "higher";
                break;
            case JOptionPane.NO_OPTION:
                HigherLower = "lower";
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "first try eh? i'm pretty good huh!? ;)",
                        "first try!",
                        JOptionPane.INFORMATION_MESSAGE);
                guessCounter++;
                prevGuess = -1;
                StartGuessRoutine();
                break;
        }
        //since we didn't technically call guess before, we manually guess here
        StartGuessRoutine();
    }                    
         
    //start guessing and repeatedly getting inputs
    public static void StartGuessRoutine() {
        //while we haven't gussed the number, continously try and guess it.
        //-1 is just used as a place holder for the win condition
        while (prevGuess != -1) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("I guess... " + Guess(prevGuess, HigherLower) + "!"));
            int result = JOptionPane.showOptionDialog(null,
                    panel, "am i correct? ",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, options, null);
            System.out.println(result);
            System.out.println(JOptionPane.YES_OPTION);
            //get our result
            switch (result) {
                case JOptionPane.YES_OPTION:
                    HigherLower = "higher";
                    break;
                case JOptionPane.NO_OPTION:
                    HigherLower = "lower";
                    break;
                default:
                    prevGuess = -1;
                    break;
            }
        }
        //thank you message
        Object[] options1 = {"again!", "quit"};
        int result = JOptionPane.showOptionDialog(null,
                "Thank you for playing! that took " + guessCounter + " guesse(s)!", "Thank you for playing!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, options1, null);

        // if the player wants to re-play then  reset all counters and run main again
        if (result == JOptionPane.YES_OPTION) {
            guessCounter = 1;
            min = 0;
            max = 101;
            prevGuess = max / 2;
            HigherLower = "";
            main(args);
        } else {
            System.exit(0);
        }
    }

    //makes our guess using the previous guess, and the location were the number could be
    public static int Guess(int previousGuess, String HigherLower) {
        int guess;
        guessCounter++;
        //if selected option is higher
        switch (HigherLower) {
            case "higher":
                min = previousGuess;
                guess = (int) Math.ceil(((max - min) / 2) + min);
                prevGuess = guess;
                return guess;
            case "lower":
                max = previousGuess;
                guess = (max + min) / 2;
                prevGuess = guess;
                return guess;
            default:
                prevGuess = -1;
                return prevGuess;
        }
    }
}
