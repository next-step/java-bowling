package bowling;

import bowling.domain.Board;
import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.presentation.Input;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = Input.askName();
        Board board = Board.init();

        Frame frame = board.frame(1);
        do {
            frame.score();
//  TODO(jack.comeback) : output 점수 출력
            frame = frame.validateMoveToNext(board);
        } while (frame.notLastFrame());

        System.out.println(board.toString());
    }
}
