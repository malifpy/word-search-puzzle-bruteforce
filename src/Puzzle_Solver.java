import java.util.Objects;

public class Puzzle_Solver {

    public String[][] solvedPuzzle;
    public Integer compNumber;

    public Puzzle_Solver(Word_Puzzle WP) {
        compNumber = 0;
        solvedPuzzle = emptyPuzzle(WP.puzzle_rows, WP.puzzle_cols);
        for (int rowIdx = 0; rowIdx < WP.puzzle_rows; rowIdx++) {
            for (int colIdx = 0; colIdx < WP.puzzle_cols; colIdx++) {
                compNumber += cellCompare(WP, solvedPuzzle, rowIdx, colIdx);
            }
        }

    }

    private static String[][] emptyPuzzle(Integer rowNum, Integer colNum) {
        String[][] resultPuzzle = new String[rowNum][colNum];
        for (int rowIdx = 0; rowIdx < rowNum; rowIdx++) {
            for (int colIdx = 0; colIdx < colNum; colIdx++) {
                resultPuzzle[rowIdx][colIdx] = "-";
            }
        }
        return resultPuzzle;

    }

    private static Integer min(Integer intA, Integer intB) {
        return Math.min(intA, intB);
    }

    private static Integer min(Integer intA, Integer intB, Integer intC) {
        return Math.min(intA, Math.min(intB, intC));
    }

    private static String reverseString(String str) {
        String rString = "";
        for (int idx = 0; idx < str.length(); idx++) {
            rString = str.charAt(idx) + rString;
        }
        return rString;
    }

    private static String getHorizontalString(Word_Puzzle WP, Integer initX, Integer initY, Integer charNum) {
        String res = "";
        for (int charIdxOffset = 0; charIdxOffset < min(charNum, WP.puzzle_cols - initX); charIdxOffset++) {
            res += WP.puzzle_matrix[initY][initX + charIdxOffset];
        }
        return res;
    }

    private static String getVerticalString(Word_Puzzle WP, Integer initX, Integer initY, Integer charNum) {
        String res = "";
        for (int charIdxOffset = 0; charIdxOffset < min(charNum, WP.puzzle_rows - initY); charIdxOffset++) {
            res += WP.puzzle_matrix[initY + charIdxOffset][initX];
        }
        return res;
    }

    private static String getNegativeDiagonalString(Word_Puzzle WP, Integer initX, Integer initY, Integer charNum) {
        String res = "";
        for (int charIdxOffset = 0; charIdxOffset < min(charNum, WP.puzzle_rows - initY,
                WP.puzzle_cols - initX); charIdxOffset++) {
            res += WP.puzzle_matrix[initY + charIdxOffset][initX + charIdxOffset];
        }
        return res;
    }

    private static String getPositiveDiagonalString(Word_Puzzle WP, Integer initX, Integer initY, Integer charNum) {
        String res = "";
        for (int charIdxOffset = 0; charIdxOffset < min(charNum, WP.puzzle_rows - initY,
                initX); charIdxOffset++) {
            res += WP.puzzle_matrix[initY + charIdxOffset][initX - charIdxOffset];
        }
        return res;
    }

    private static StringCompare horizontalCompare(Word_Puzzle WP, Integer initRow, Integer initCol, String toCompare,
            String[][] resultPuzzle) {
        String str = getHorizontalString(WP, initCol, initRow, toCompare.length());

        StringCompare normalComp = new StringCompare(str, toCompare);
        StringCompare reverseComp = new StringCompare(str, reverseString(toCompare));

        StringCompare compareResult = new StringCompare(normalComp.compNum + reverseComp.compNum,
                normalComp.compResult || reverseComp.compResult);

        if (compareResult.compResult) {
            for (int idxOffset = 0; idxOffset < toCompare.length(); idxOffset++) {
                resultPuzzle[initRow][initCol + idxOffset] = WP.puzzle_matrix[initRow][initCol + idxOffset];
            }
        }

        return compareResult;
    }

    private static StringCompare verticalCompare(Word_Puzzle WP, Integer initRow, Integer initCol, String toCompare,
            String[][] resultPuzzle) {
        String str = getVerticalString(WP, initCol, initRow, toCompare.length());

        StringCompare normalComp = new StringCompare(str, toCompare);
        StringCompare reverseComp = new StringCompare(str, reverseString(toCompare));

        StringCompare compareResult = new StringCompare(normalComp.compNum + reverseComp.compNum,
                normalComp.compResult || reverseComp.compResult);

        if (compareResult.compResult) {
            for (int idxOffset = 0; idxOffset < toCompare.length(); idxOffset++) {
                resultPuzzle[initRow + idxOffset][initCol] = WP.puzzle_matrix[initRow + idxOffset][initCol];
            }
        }

        return compareResult;
    }

    private static StringCompare negativeDiagonalCompare(Word_Puzzle WP, Integer initRow, Integer initCol,
            String toCompare,
            String[][] resultPuzzle) {
        String str = getNegativeDiagonalString(WP, initCol, initRow, toCompare.length());

        StringCompare normalComp = new StringCompare(str, toCompare);
        StringCompare reverseComp = new StringCompare(str, reverseString(toCompare));

        StringCompare compareResult = new StringCompare(normalComp.compNum + reverseComp.compNum,
                normalComp.compResult || reverseComp.compResult);

        if (compareResult.compResult) {
            for (int idxOffset = 0; idxOffset < toCompare.length(); idxOffset++) {
                resultPuzzle[initRow + idxOffset][initCol + idxOffset] = WP.puzzle_matrix[initRow + idxOffset][initCol
                        + idxOffset];
            }
        }

        return compareResult;
    }

    private static StringCompare positiveDiagonalCompare(Word_Puzzle WP, Integer initRow, Integer initCol,
            String toCompare,
            String[][] resultPuzzle) {
        String str = getPositiveDiagonalString(WP, initCol, initRow, toCompare.length());

        StringCompare normalComp = new StringCompare(str, toCompare);
        StringCompare reverseComp = new StringCompare(str, reverseString(toCompare));

        StringCompare compareResult = new StringCompare(normalComp.compNum + reverseComp.compNum,
                normalComp.compResult || reverseComp.compResult);

        if (compareResult.compResult) {
            for (int idxOffset = 0; idxOffset < toCompare.length(); idxOffset++) {
                resultPuzzle[initRow + idxOffset][initCol - idxOffset] = WP.puzzle_matrix[initRow + idxOffset][initCol
                        - idxOffset];
            }
        }

        return compareResult;
    }

    private static Integer cellCompare(Word_Puzzle originalWP, String[][] resultPuzzle, Integer rowIdx,
            Integer colIdx) {
        Integer compNumber = 0;
        for (int keywordIdx = 0; keywordIdx < originalWP.puzzle_solutions.length; keywordIdx++) {
            String keyword = originalWP.puzzle_solutions[keywordIdx];
            compNumber += horizontalCompare(originalWP, rowIdx, colIdx, keyword, resultPuzzle).compNum;
            compNumber += verticalCompare(originalWP, rowIdx, colIdx, keyword, resultPuzzle).compNum;
            compNumber += negativeDiagonalCompare(originalWP, rowIdx, colIdx, keyword, resultPuzzle).compNum;
            compNumber += positiveDiagonalCompare(originalWP, rowIdx, colIdx, keyword, resultPuzzle).compNum;
        }

        return compNumber;
    }

    public void display() {
        System.out.println("Solved Puzzle: ");
        for (int rowNum = 0; rowNum < solvedPuzzle.length; rowNum++) {
            for (int colNum = 0; colNum < solvedPuzzle[0].length; colNum++) {
                System.out.printf("%s ", solvedPuzzle[rowNum][colNum]);
            }
            System.out.println("");
        }

        System.out.printf("Total Character Comparison: %d Comparison(s)\n", compNumber);
    }

}

class StringCompare {
    public Integer compNum;
    public Boolean compResult;

    public StringCompare(String strA, String strB) {
        compNum = 0;
        compResult = Objects.equals(strA.length(), strB.length());
        Integer compMax = Math.min(strA.length(), strB.length());
        while (compResult && compNum < compMax) {
            compResult = Objects.equals(strA.charAt(compNum), strB.charAt(compNum));
            compNum++;
        }
    }

    public StringCompare(Integer comparisonNum, Boolean comparisonResult) {
        compNum = comparisonNum;
        compResult = comparisonResult;
    }
}
