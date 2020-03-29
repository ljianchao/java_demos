package cn.jc.algorithm;

import java.util.HashSet;

public class ValidSudoku {

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("结果为" + isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0 || board.length != board[0].length) {
            return false;
        }
        HashSet<Character> row,col,block;
        int n = board.length;

        for (int i = 0; i < n; i++) {
            row = new HashSet<Character>();
            col = new HashSet<Character>();
            block = new HashSet<Character>();
            for (int j = 0; j < n; j++) {
                //Check row
                if (!row.contains(board[i][j])) {
                    row.add(board[i][j]);
                } else if (board[i][j] != '.'){
                    return false;
                }
                //Check col
                if (!col.contains(board[j][i])) {
                    col.add(board[j][i]);
                } else if (board[j][i] != '.'){
                    return false;
                }
                //check block
                int c = j % 3 + 3 * (i % 3);//make use of how i and j increases
                int r = j / 3 + 3 * (i / 3);
                if (!block.contains(board[r][c])) {
                    block.add(board[r][c]);
                } else if (board[r][c] != '.') {
                    return false;
                }
            }
        }

        return true;
    }
}
