import board.Board;
import frame.Frame;
import frame.LastFrame;
import view.InputView;
import view.OutputView;

public class BowlingController {

    public static void main(String[] args) {

        Board board = Board.initBoard(InputView.inputName());

        while (!board.reachLastFrame()) {
            Frame nowFrame = board.getNowFrame();
            rollUntilLast(board, nowFrame);
        }

        //last frame
        LastFrame lastFrame = LastFrame.init();
        board.addLast(lastFrame);

        while (!lastFrame.isFull()) {
            rollLast(board, lastFrame);
        }

    }

    private static void rollUntilLast(Board board, Frame nowFrame) {
        if (board.reachLastFrame()) {
            return;
        }
        nowFrame.bowling(InputView.inputScore(board.getNowFrameNumber()));
        showBoard(board);
    }

    private static void rollLast(Board board, LastFrame lastFrame) {
        lastFrame.bowling(InputView.inputScore(board.getNowFrameNumber()));
        showBoard(board);
    }

    private static void showBoard(Board board) {
        //show
        OutputView.showBasic();
        OutputView.showName(board.getName());
        OutputView.showFrame(board.getFrames());
        OutputView.showLastFrame(board.getLastFrame());
    }
}
