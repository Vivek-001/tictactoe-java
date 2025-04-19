package Model;

import Utility.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int size;  //size should be public so that be accessible for outside of the class
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    // add piece in board if cell is available
    public Boolean addPiece(int row, int column, PlayingPiece piece) {
        if (board[row][column] != null) {
            return false;
        }
        board[row][column] = piece;
        return true;
    }

    // get the free cell from the board
    public List<Pair<Integer, Integer>> getFreeCells() {
        List<Pair<Integer, Integer>> freeCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    freeCells.add(new Pair<>(i, j));
                }
            }
        }
        return freeCells;
    }

    // print the board at user interface
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.toString());
                } else {
                    System.out.print(" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
