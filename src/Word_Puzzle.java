import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Word_Puzzle {
    public Integer puzzle_rows;
    public Integer puzzle_cols;
    public String[][] puzzle_matrix;
    public String[] puzzle_solutions;

    public Word_Puzzle(Integer puzzleRows, Integer puzzleCols) {
        puzzle_cols = puzzleCols;
        puzzle_rows = puzzleRows;
        puzzle_matrix = new String[puzzleRows][puzzleCols];
        puzzle_solutions = null;

        for (int rows = 0; rows < puzzleRows; rows++){
            for(int cols = 0; cols < puzzleCols; cols++){
                puzzle_matrix[rows][cols] = "-";
            }
        }
    }

    public Word_Puzzle(String filepath) {
        try {
            String main_file[] = Files.readString(Path.of(filepath)).split("\\n\\n");
            puzzle_matrix = parsePuzzleMatrix(main_file[0]);
            puzzle_solutions = parsePuzzleSolutions(main_file[1]);
            puzzle_rows = puzzle_matrix.length;
            puzzle_cols = puzzle_matrix[0].length;
        } catch (IOException e) {
            System.out.printf("ERROR when reading %s\n", filepath);
            System.exit(0);
        }
    }

    private static String[][] parsePuzzleMatrix(String puzzleMatrixString) {
        String tempPuzzleMatrix[] = puzzleMatrixString.split("\\n");
        String[][] puzzleMatrix = new String[tempPuzzleMatrix.length][tempPuzzleMatrix[0].split(" ").length];

        for(int row = 0; row < tempPuzzleMatrix.length; row++){
            puzzleMatrix[row] = tempPuzzleMatrix[row].split(" ");
        }
        return puzzleMatrix;        
    }

    private static String[] parsePuzzleSolutions(String puzzleSolutionsString) {
        return puzzleSolutionsString.split("\\n");
    }


    public void display() {
        System.out.println("Word Matrix: ");
        for (int i = 0; i < puzzle_rows; i++) {
            for (int j = 0; j < puzzle_cols; j++) {
                System.out.printf("%s ", puzzle_matrix[i][j]);
            }
            System.out.println("");
        }

        System.out.println("Word Solutions: ");
        for(int solNum = 0; solNum < puzzle_solutions.length; solNum++){
            System.out.println(puzzle_solutions[solNum]);
        }
    }

    public static void main(String[] args) {
        Word_Puzzle wp = new Word_Puzzle("test/test1.txt");
        wp.display();
    }
}
