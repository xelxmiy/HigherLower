package higherlower;
import java.util.*;
import javax.swing.*;
/**
 * @author a.belski
 */
public class HigherLower {
    
    Random random = new Random();
    private static int min = 0;
    private static int max = 100;
    public static int prevGuess;
    private static String HigherLower;
    private static Object[] options1 = {"higher", "lower", "correct!"};
    
    public static void main(String[] args) {
        
        JOptionPane.showMessageDialog(null,
                "Welcome to Higher/Lower!\n pick a number between 0-100 (inclusive)\nand i will try to guess it in at most 7 guesses!",
                "HigherLower", 
                JOptionPane.INFORMATION_MESSAGE);
        
        JPanel panel = new JPanel();  
        panel.add(new JLabel("I guess... " + Guess(max)));
        while(prevGuess != -1) {
            int result = JOptionPane.showOptionDialog(null, panel, "am i correct? ",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
            
        }
    }
    //makes our guess using the previous guess, and the location were the number could be
    public static int Guess(int min, int max,int previousGuess, String HigherLower) {
        int guess;
        if (HigherLower.equals("higher")) {
           min = previousGuess;
           guess = ((max - min) / 2) + min;
           prevGuess = guess;
           return guess;        
        }
        if (HigherLower.equals("lower")) {
        max = previousGuess;
        guess = ((max - min) / 2) + min;
        prevGuess = guess;
        return guess;    
        }   
        else {
            return -1;
        }
    } 
    //overload for the first time we guess, it should always return 50; 
    public static int Guess(int max) {
        int guess;
        guess = max /2;
        prevGuess = guess;
        return guess;   
    }
}
