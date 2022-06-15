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

        Frame frame = board.frame(1);
        int mayBeNextIndex;
        OutPut.board(board, player);

        do {
            int score = frame.attempt();
            OutPut.score(frame, score);
            OutPut.board(board,player);
            mayBeNextIndex = frame.validateMoveToNextIndex();
            frame = board.frame(mayBeNextIndex);
        } while (mayBeNextIndex < 11);
    }
}
