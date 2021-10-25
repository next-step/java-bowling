package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResults;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        Frame frame = NormalFrame.createFirstFrame();

        ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
        printBowlingGame(user, frame);
    }

    private static void printBowlingGame(User user, Frame frame) {
        while (!frame.lastFrame().isFinished()) {
            Pin pin = Pin.of(InputView.inputFrameShot(frame.lastFrame().round()));
            frame.lastFrame().bowling(pin);
            ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
        }
    }

}
