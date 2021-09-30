package bowling;

import static bowling.domain.frame.AbstractFrame.FINAL_ROUND;
import static bowling.domain.frame.AbstractFrame.FIRST_ROUND;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        Frame frame = NormalFrame.createFirstFrame();

        ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame));
        IntStream.range(FIRST_ROUND, FINAL_ROUND)
            .forEach(index -> normalView(user, frame));
        Frame finalFrame = frame.lastFrame();
        shootPinAndPrintFrame(user, frame, finalFrame);
        shootPinAndPrintFrame(user, frame, finalFrame);
        if (finalFrame.isScoreNextStorable()) {
            shootPinAndPrintFrame(user, frame, finalFrame);
        }
    }

    private static void normalView(User user, Frame frame) {
        Frame nowFrame = frame.lastFrame();
        shootPinAndPrintFrame(user, frame, nowFrame);
        if (nowFrame.isScoreNextStorable()) {
            shootPinAndPrintFrame(user, frame, nowFrame);
        }
        nowFrame.createNextFrame();
    }


    private static void shootPinAndPrintFrame(User user, Frame frame, Frame nowFrame) {
        Pin first = Pin.of(InputView.inputFrameShot(nowFrame.round()));
        nowFrame.updateScorePin(first);
        ResultView.printBoard(user, Frames.createFramesByFirstFrame(frame));
    }

}
