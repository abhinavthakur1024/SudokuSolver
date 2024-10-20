public class Solution {
    public boolean helper(char[][] board, int row , int col){        //  this is our recursive function and we do recussion with help of row and column

        int newrow=0;
        int newcol=0;

        if(col!= board.length-1){    // means not at last 
            newrow = row;
            newcol= col +1;
        } else {

            newrow = row +1;
            newcol = 0;
        }

        
    }

    public void solveSudoku(char[][] board){

        helper(board, 0,0);

    }
}
