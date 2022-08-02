package bowling2;

import bowling2.domain.Board;
import bowling2.domain.Player;
import bowling2.presentation.Input;
import bowling2.presentation.OutPut;

public class BowlingGame {
    public static void main(String[] args) {
        doBowling();
    }

    private static void doBowling() {
        Player player = Input.askName();
        Board board = Board.init();

        OutPut.printBoard(board, player);

        while(board.inProgress()) {
            int fallenPins = Input.askBowling(board.indexOfCurrentFrame());
            board.handleAfterTry(fallenPins);
            OutPut.printBoard(board, player);
            System.out.println();
        }
    }
}
