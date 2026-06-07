import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Core score-tracking metrics
        int totalRounds = 0;
        int roundsWon = 0;
        int totalAttemptsTaken = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        boolean playAgain = true;
        
        // Loop controlling multiple rounds
        while (playAgain) {
            totalRounds++;
            int min = 1;
            int max = 100;
            
            // Generate a random number within the specified range
            int targetNumber = random.nextInt(max - min + 1) + min;
            
            // Limit the number of attempts
            int maxAttempts = 10;
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\n--- Round " + totalRounds + " ---");
            System.out.println("I have generated a random number between " + min + " and " + max + ".");
            System.out.println("You have a maximum of " + maxAttempts + " attempts to guess it.");
            
            // Repeat until correct or out of attempts
            while (attempts < maxAttempts) {
                // Prompt the user to enter their guess
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                
                // Input validation to avoid application crashes on non-integer input
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input from scanner buffer
                    continue;
                }
                
                int userGuess = scanner.nextInt();
                attempts++;
                
                // Compare guess and provide structural feedback
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }
            
            // Corrected tracking logic: Always add the attempts made in this round to the grand total
            totalAttemptsTaken += attempts;
            
            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + targetNumber);
            }
            
            // Query for consecutive gameplay sessions
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }
        
        // Display the user's final score metrics
        System.out.println("\n================ GAME OVER ================");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won:          " + roundsWon);
        System.out.println("Total Guesses Made:  " + totalAttemptsTaken);
        
        double winRate = totalRounds > 0 ? ((double) roundsWon / totalRounds) * 100 : 0;
        System.out.printf("Win Accuracy Rate:   %.2f%%\n", winRate);
        System.out.println("===========================================");
        System.out.println("Thank you for completing Task 1!");
        
        scanner.close();
    }}

