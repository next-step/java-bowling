package bowling;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.BowlingException;
import bowling.presentation.Input;
import bowling.presentation.OutPut;

public class BowlingGame {
    private static final int FIRST_INDEX = 1;
    private static final int STOPPABLE_INDEX = 11;

    public static void main(String[] args) {
        try {
            doBowling();
        } catch (BowlingException e) {
            System.out.println("게임 중 발생한 에러로 종료됐습니다. message : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 에러로 종료됐습니다. message : " + e.getMessage());
        }
    }

    private static void doBowling() {
        Player player = Input.askName();
        Board board = Board.init();

        OutPut.board(board, player);

        Frame frame = new NormalFrame(FIRST_INDEX);
        board.addFrameIfAbsent(frame);
        do {
            int fallenPins = Input.askBowling(frame.index());
            frame.determineSpare(fallenPins);
            OutPut.board(board, player);
            frame = frame.validateMoveToNextFrame();
            board.addFrameIfAbsent(frame);
        } while (continuous(frame.index()));
    }

    private static boolean continuous(int mayBeNextIndex) {
        return mayBeNextIndex < STOPPABLE_INDEX;
    }
}
