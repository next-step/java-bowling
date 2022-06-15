package bowling;

import bowling.domain.Board;
import bowling.domain.frame.Frame;
import bowling.domain.Player;
import bowling.presentation.Input;
import bowling.presentation.OutPut;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = Input.askName();
        Board board = Board.init();

        OutPut.board(board, player);

        int mayBeNextIndex = 1;
        do {
            Frame frame = board.frame(mayBeNextIndex);
            int score = frame.attempt();
            OutPut.score(frame, score);
            OutPut.board(board, player);
            mayBeNextIndex = frame.validateMoveToNextIndex();
        } while (finished(mayBeNextIndex));
    }

    private static boolean finished(int mayBeNextIndex) {
        return mayBeNextIndex < 11;
    }
}
