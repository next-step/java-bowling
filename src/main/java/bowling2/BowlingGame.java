package bowling2;

import bowling.domain.Player;
import bowling.presentation.Input;
import bowling2.domain.Board;

public class BowlingGame {

    private static void doBowling() {
        Player player = Input.askName();
        Board board = Board.init();

        // TODO(jack.comeback) : print empty board

        int fallenPins = Input.askBowling(board.indexOfCurrentFrame());
        board.handleAfterTry(fallenPins);
        // TODO(jack.comeback) : 점수 계산
        board.askCurrentFrame();


    }
}
