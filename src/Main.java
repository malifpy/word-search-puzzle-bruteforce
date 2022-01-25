import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner consoleInput = new Scanner(System.in);

        System.out.printf("Input Filepath: ");
        String filePath = consoleInput.nextLine();

        String queryAnswer;
        Boolean repeat;

        do {
            // Load File
            Word_Puzzle wp = new Word_Puzzle(filePath);

            // Display Puzzle Info to Console
            System.out.printf("Solving %d x %d Word Puzzle\n", wp.puzzle_rows, wp.puzzle_cols);

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

        System.out.println("Thank You for Using My Program!");
        consoleInput.close();
    }
}
