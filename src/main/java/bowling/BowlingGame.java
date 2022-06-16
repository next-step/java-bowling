package bowling;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;
import bowling.presentation.Input;
import bowling.presentation.OutPut;

public class BowlingGame {
    public static void main(String[] args) {
        try {
            doBowling();
        } catch (BowlingException e) {
            System.out.println("게임 중 발생한 에러로 종료됐습니다. message : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 에러로 종료됐습니다. message : "+ e.getMessage());
        }
    }

    private static void doBowling() {
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
