import board.Board;
import frame.Frame;
import view.InputView;

public class BowlingController {

    public static void main(String[] args) {

        Board board = Board.initBoard(InputView.inputName());

        while (!board.reachLastFrame()) {
            Frame nowFrame = board.getNowFrame();
            rollUntilLast(board, nowFrame);
        }


    }

    private static void rollUntilLast(Board board, Frame nowFrame) {
        if (board.reachLastFrame()) {
            return;
        }
        nowFrame.bowling(InputView.inputScore(board.getNowFrameNumber()));
        //show
    }
}
