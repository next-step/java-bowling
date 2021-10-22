package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResults;
import bowling.domain.frame.NormalFrame;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

//    public static void main(String[] args) {
//        User user = User.of(InputView.inputUsername());
//        Frame frame = NormalFrame.createFirstFrame();
//
//        ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame));
//        IntStream.range(FIRST_ROUND, FINAL_ROUND)
//            .forEach(index -> normalView(user, frame));
//        Frame finalFrame = frame.lastFrame();
//        shootPinAndPrintFrame(user, frame, finalFrame);
//        shootPinAndPrintFrame(user, frame, finalFrame);
//        if (finalFrame.isScoreNextStorable()) {
//            shootPinAndPrintFrame(user, frame, finalFrame);
//        }
//    }
//
//    private static void normalView(User user, Frame frame) {
//        Frame nowFrame = frame.lastFrame();
//        shootPinAndPrintFrame(user, frame, nowFrame);
//        if (nowFrame.isScoreNextStorable()) {
//            shootPinAndPrintFrame(user, frame, nowFrame);
//        }
//        nowFrame.createNextFrame();
//    }
//
//
//    private static void shootPinAndPrintFrame(User user, Frame frame, Frame nowFrame) {
//        Pin first = Pin.of(InputView.inputFrameShot(nowFrame.round()));
//        nowFrame.updateScorePin(first);
//        ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame));
//    }
    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        Frame frame = NormalFrame.createFirstFrame();

        ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
    }

}
