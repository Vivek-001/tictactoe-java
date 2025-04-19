import Model.Board;
import Model.PieceType;
import Model.Player;
import Model.PlayingPiece;
import Utility.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacTaoGame {
    int board_size;
    Deque<Player> players;
    Board gameBoard;

    // default Constructor
    public TicTacTaoGame() {
    }

    //initialize the Game
    public void initialize() {
        players = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

       // System.out.println("There will be 2 player in this Game! ");
        System.out.println("Please Enter the size of the board: ");
        board_size = scanner.nextInt();
        Scanner sc  = new Scanner(System.in);

        System.out.println("Enter the First player Name: ");
        String firstPlayerName = sc.next();

        System.out.println("Enter the second player Name: ");
        String secondPlayerName = sc.next();

        /// we can take the pieceType Symbol of the player also , but here taken the inGeneral symbol and also no of player.
        PlayingPiece playingPiece1 = new PlayingPiece(PieceType.X);
        Player player1 = new Player(firstPlayerName, playingPiece1);

        PlayingPiece playingPiece2 = new PlayingPiece(PieceType.O);
        Player player2 = new Player(secondPlayerName, playingPiece2);

        players.add(player1);
        players.add(player2);

        gameBoard = new Board(board_size);
        // we can take an input as name piece_type of all player and also board size from user
        // and make for that
    }

    //  Start the Game
    public String start() {
        boolean nowinner = true;
        while (nowinner) {
            //print board
            gameBoard.printBoard();
            // check free cell available of not in board
            List<Pair<Integer, Integer>> list = gameBoard.getFreeCells();
            if (list.isEmpty()) {
                nowinner = false;
                continue;
            }
            //read the user input
            Player currentPlayer = players.removeFirst();
            Scanner scanner = new Scanner(System.in);
            // System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
            System.out.println("Hi " + currentPlayer.getName() + " Enter index for put your piece : ");
            String input = scanner.nextLine();
            String[] values = input.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);

            boolean isPieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputColumn, currentPlayer.getPlayingPiece());
            if (!isPieceAddedSuccessfully) {
                System.out.println("You have chosen the occupied index, Please chose the different index");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);
            boolean winner = isThereAnyWinner(inputRow, inputColumn, currentPlayer.getPlayingPiece());
            if (winner) {
                return currentPlayer.getName();
            }
        }
        return "tied";
    }

    boolean isThereAnyWinner(int row, int column, PlayingPiece playingPiece) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diaMatch = true;
        boolean revDiaMatch = true;

        ///check in row
        for (int j = 0; j < gameBoard.size; j++) {
            if (gameBoard.board[row][j] == null || gameBoard.board[row][j].pieceType != playingPiece.pieceType) {
                rowMatch = false;
            }
        }

        ///check in column
        for (int i = 0; i < gameBoard.size; i++) {
            if (gameBoard.board[i][column] == null || gameBoard.board[i][column].pieceType != playingPiece.pieceType) {
                colMatch = false;
            }
        }
        ///check in diagonal
        for (int i = 0, j = 0; i < gameBoard.size; i++, j++) {
            if(gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != playingPiece.pieceType) {
                diaMatch = false;
            }
        }
        ///check in Reverse_Diagonal
        for (int i = 0, j = gameBoard.size - 1; i < gameBoard.size && j>=0; i++, j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != playingPiece.pieceType) {
                revDiaMatch = false;
            }
        }
        return rowMatch || colMatch || diaMatch || revDiaMatch;
    }
}

