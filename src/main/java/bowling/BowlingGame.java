package bowling;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.presentation.Input;
import bowling.presentation.OutPut;

public class BowlingGame {
    public static void main(String[] args) {
        try {
            doBowling();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void doBowling() {
        Player player = Input.askName();
        Board board = Board.init();

        OutPut.printBoard(board, player);

        while (board.inProgress()) {
            int fallenPins = Input.askBowling(board.indexOfCurrentFrame());
            board.handleAfterTry(fallenPins);
            OutPut.printBoard(board, player);
            System.out.println();
        }
    }
}
