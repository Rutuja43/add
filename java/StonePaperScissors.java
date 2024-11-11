import java.util.Random;
import java.util.Scanner;

public class StonePaperScissors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = { "Stone", "Paper", "Scissors" };
        int computerChoice = random.nextInt(3); // Randomly select 0, 1, or 2 for Stone, Paper, or Scissors

        System.out.println("Welcome to Stone, Paper, Scissors!");
        System.out.println("Enter your choice (Stone, Paper, or Scissors):");
        String userChoice = scanner.nextLine().trim();

        // Validate user input
        boolean validInput = false;
        for (String choice : choices) {
            if (choice.equalsIgnoreCase(userChoice)) {
                validInput = true;
                break;
            }
        }

        if (!validInput) {
            System.out.println("Invalid choice. Please enter Stone, Paper, or Scissors.");
            return;
        }

        System.out.println("Computer chose: " + choices[computerChoice]);

        // Determine the winner
        if (userChoice.equalsIgnoreCase(choices[computerChoice])) {
            System.out.println("It's a tie!");
        } else if ((userChoice.equalsIgnoreCase("Stone") && choices[computerChoice].equals("Scissors")) ||
                (userChoice.equalsIgnoreCase("Paper") && choices[computerChoice].equals("Stone")) ||
                (userChoice.equalsIgnoreCase("Scissors") && choices[computerChoice].equals("Paper"))) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }

        scanner.close();
    }
}
