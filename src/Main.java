import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner consoleInput = new Scanner(System.in);

        String filePath;
        String queryAnswer;
        Boolean repeat;

        do {

            System.out.printf("Input Filepath: ");
            filePath = consoleInput.nextLine();

            // Load File
            Word_Puzzle wp = new Word_Puzzle(filePath);

            // Display Puzzle Info to Console
            wp.display();
            System.out.println();
            System.out.printf("Solving %d x %d Word Puzzle...\n", wp.puzzle_rows, wp.puzzle_cols);

            // Timer Start
            long startTime = System.nanoTime();

            // Solve Puzzle
            Puzzle_Solver solvedPuzzle = new Puzzle_Solver(wp);

            // Timer Ends
            long endTime = System.nanoTime();

            // Display Result
            float duration = (endTime - startTime) / 1000000;
            System.out.printf("Solved in %.2f ms\n", duration);
            solvedPuzzle.display();

            do {
                System.out.printf("Solve another puzzle? (Y|N): ");
                queryAnswer = consoleInput.nextLine();
            } while (!Objects.equals(queryAnswer, "Y") && !Objects.equals(queryAnswer, "N"));

            repeat = Objects.equals(queryAnswer, "Y");
        } while (repeat);

        System.out.println("Thank You! Exiting Program...");
        consoleInput.close();
    }
}
