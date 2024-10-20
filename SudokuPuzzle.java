// import java.util.*;

// public class SudokuPuzzle {

//     // Check if it's safe to place number n in board[r][c]
//     public boolean isSafe(int[][] b, int r, int c, int n) {
//         // Check row
//         for (int d = 0; d < b.length; d++) {
//             if (b[r][d] == n) {
//                 return false;
//             }
//         }

//         // Check column
//         for (int r1 = 0; r1 < b.length; r1++) {
//             if (b[r1][c] == n) {
//                 return false;
//             }
//         }

//         // Check sub-grid
//         int sqt = (int) Math.sqrt(b.length);
//         int boxRowSt = r - r % sqt;
//         int boxColSt = c - c % sqt;

//         for (int r1 = boxRowSt; r1 < boxRowSt + sqt; r1++) {
//             for (int d = boxColSt; d < boxColSt + sqt; d++) {
//                 if (b[r1][d] == n) {
//                     return false;
//                 }
//             }
//         }

//         // If no conflicts, it's safe to place the number
//         return true;
//     }

//     // Solves the Sudoku puzzle using backtracking  solveSudoku is a method define within the class
//     public boolean solveSudoku(int[][] b, int num) {
//         int r = -1;  // initilize with -1 bcz the algorithm hasn't found empty set yet
//         int c = -1;
//         boolean isVacant = true;  // this will check wheather there is an empty set initily we set it ture i.e assumes the grid is fully filled

//         // Find the next vacant cell the 0 will indicate that both cells are empty
//         for (int i = 0; i < num; i++) {
//             for (int j = 0; j < num; j++) {
//                 if (b[i][j] == 0) {
//                     r = i;
//                     c = j;
//                     isVacant = false;  // The isVacant flag is set to false to indicate that an empty cell has been found, and the loop breaks out early to avoid further unnecessary checks.
//                     break;
//                 }
//             }
//             if (!isVacant) {
//                 break;
//             }
//         }

//         // If no empty cell is left, the puzzle is solved
//         if (isVacant) {
//             return true;
//         }

//         // Try all numbers from 1 to num
//         for (int no = 1; no <= num; no++) {
//             if (isSafe(b, r, c, no)) {
//                 b[r][c] = no;
//                 if (solveSudoku(b, num)) {
//                     return true;    
//                 } else {
//                     b[r][c] = 0; // Backtrack  --> Backtracking happens when the algorithm tries a number but later finds out that it leads to a dead end.
//                 }
//             }
//         }

//         return false; // Trigger backtracking
//     }

//     // Displays the Sudoku board
//     public void display(int[][] b, int n) {
//         for (int i = 0; i < n; i++) {
//             for (int d = 0; d < n; d++) {
//                 System.out.print(b[i][d] + " ");
//             }
//             System.out.println();
//         }
//     }

//     // Main method
//     public static void main(String[] args) {
//         int[][] b = new int[][] {
//             { 7, 0, 0, 0, 0, 0, 2, 0, 0 },
//             { 4, 0, 2, 0, 0, 0, 0, 0, 3 },
//             { 0, 0, 0, 2, 0, 1, 0, 0, 0 },
//             { 3, 0, 0, 1, 8, 0, 0, 9, 7 },
//             { 0, 0, 9, 0, 7, 0, 6, 0, 0 },
//             { 6, 5, 0, 0, 3, 2, 0, 0, 1 },
//             { 0, 0, 0, 4, 0, 9, 0, 0, 0 },
//             { 5, 0, 0, 0, 0, 0, 1, 0, 6 },
//             { 0, 0, 6, 0, 0, 0, 0, 0, 8 }
//         };

//         // Create a SudokuPuzzle object
//         SudokuPuzzle obj = new SudokuPuzzle();
//         int size = b.length;

//         // Display the initial grid
//         System.out.println("The grid is:");
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 System.out.print(b[i][j] + " ");
//             }
//             System.out.println();
//         }

//         // Solve and display the solution
//         System.out.println();
//         if (obj.solveSudoku(b, size)) {
//             System.out.println("The solution of the grid is:");
//             obj.display(b, size);
//         } else {
//             System.out.println("There is no solution available.");
//         }
//     }
// }


// -------*---------
// Overall Flow:
// Find an empty cell: The algorithm looks for the next empty cell in the grid.
// Try placing numbers: It tries placing numbers from 1 to 9 in that cell.
// Check validity: It checks if placing each number is valid using the isSafe() method.
// Recursion: If placing a number is valid, it recursively tries to solve the rest of the puzzle.
// Backtrack if necessary: If a number placement doesn't lead to a solution, it backtracks by resetting the cell and trying the next number.
// Solution found: If all cells are filled with valid numbers, the puzzle is solved.
// -------*---------

import java.util.*;

public class SudokuPuzzle {
    
    // Check if placing num at board[r][c] is valid
    private boolean isSafe(int[][] board, int r, int c, int num) {
        int sqSize = (int) Math.sqrt(board.length);
        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == num || board[i][c] == num || 
                board[r - r % sqSize + i / sqSize][c - c % sqSize + i % sqSize] == num) {
                return false;
            }
        }
        return true;
    }

    // Solves the Sudoku puzzle using backtracking
    private boolean solve(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, r, c, num)) {
                            board[r][c] = num;
                            if (solve(board)) return true;
                            board[r][c] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // Display the solved board
    private void display(int[][] board) {
        for (int[] row : board) {
            for (int num : row) System.out.print(num + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {7, 0, 0, 0, 0, 0, 2, 0, 0},
            {4, 0, 2, 0, 0, 0, 0, 0, 3},
            {0, 0, 0, 2, 0, 1, 0, 0, 0},
            {3, 0, 0, 1, 8, 0, 0, 9, 7},
            {0, 0, 9, 0, 7, 0, 6, 0, 0},
            {6, 5, 0, 0, 3, 2, 0, 0, 1},
            {0, 0, 0, 4, 0, 9, 0, 0, 0},
            {5, 0, 0, 0, 0, 0, 1, 0, 6},
            {0, 0, 6, 0, 0, 0, 0, 0, 8}
        };

        SudokuPuzzle sp = new SudokuPuzzle();
        System.out.println("Initial Grid:");
        sp.display(board);
        
        if (sp.solve(board)) {
            System.out.println("\nSolved Grid:");
            sp.display(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
