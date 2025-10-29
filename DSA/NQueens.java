import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {

    // Main method to solve the N-Queens problem
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        
        // Array to store the positions of queens
        // board[row] = column index where the queen is placed in that row
        int[] board = new int[n];
        
        // Call helper function to solve the problem
        solveNQueensHelper(n, 0, board, solutions);
        
        return solutions;
    }

    /**
     * Backtracking helper method to place queens row by row.
     * @param n - Size of the board (n x n)
     * @param row - Current row where the queen is to be placed
     * @param board - Array where each index represents a row, and the value represents the column of the queen in that row
     * @param solutions - List to store all valid solutions
     */
    private void solveNQueensHelper(int n, int row, int[] board, List<List<String>> solutions) {
        // Base case: If all queens are placed
        if (row == n) {
            // Convert board configuration to a list of strings
            solutions.add(createBoard(board));
            return;
        }
        
        // Try placing a queen in each column of the current row
        for (int col = 0; col < n; col++) {
            // If placing queen in (row, col) is valid, place it
            if (isValid(board, row, col)) {
                board[row] = col;  // Place queen in the current row
                // Move to the next row
                solveNQueensHelper(n, row + 1, board, solutions);
                // Backtrack: Remove the queen from the current row (not needed because board[row] is overwritten)
            }
        }
    }

    /**
     * Check if it's safe to place a queen at (row, col)
     * @param board - Current board state
     * @param row - Row index where the queen is to be placed
     * @param col - Column index where the queen is to be placed
     * @return true if the position is safe, false otherwise
     */
    private boolean isValid(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            // Check if there's a queen in the same column or on the diagonals
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Convert the board array into a list of strings.
     * Each string represents a row of the board.
     * 'Q' represents a queen and '.' represents an empty space.
     * @param board - The board configuration
     * @return a List of strings representing the chessboard
     */
    private List<String> createBoard(int[] board) {
        List<String> boardState = new ArrayList<>();
        for (int row : board) {
            StringBuilder sb = new StringBuilder();
            // Build each row string
            for (int col = 0; col < board.length; col++) {
                if (col == row) sb.append('Q'); // Place a queen at the given column
                else sb.append('.'); // Empty space
            }
            boardState.add(sb.toString());
        }
        return boardState;
    }

    // Main method to take user input and print the solution in the desired format
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        Scanner scanner = new Scanner(System.in);
        
        // Ask user for the value of n
        System.out.print("Enter the value of n for the N-Queens problem: ");
        int n = scanner.nextInt();
        
        // Check if n is valid
        if (n < 1) {
            System.out.println("Please enter a valid value of n (n >= 1).");
            return;
        }
        
        // Solve the N-Queens problem
        List<List<String>> solutions = nQueens.solveNQueens(n);
        
        // Print the solutions with each solution printed on a new line
        for (List<String> solution : solutions) {
            System.out.print("[");
            for (int i = 0; i < solution.size(); i++) {
                System.out.print("\"" + solution.get(i) + "\"");
                if (i < solution.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        
        scanner.close();
    }
}
