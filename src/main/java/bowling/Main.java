package bowling;

import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameResults;
import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.domain.userframeresult.UserFrameResults;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        int userSize = InputView.inputUserSize();

        UserFrameResults userFrameResults = UserFrameResults.init();
        for (int i = 1; i <= userSize; i++) {
            User user = User.of(InputView.inputUsername(i));
            userFrameResults.addUser(user);
        }

//        User user = User.of(InputView.inputUsername());
//        Frame frame = NormalFrame.createFirstFrame();
//
//        ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
//        printBowlingGame(user, frame);
    }

    private static void printBowlingGame(User user, Frame frame) {
        while (!frame.lastFrame().isFinished()) {
            Pin pin = Pin.of(InputView.inputFrameShot(frame.lastFrame().round()));
            frame.lastFrame().bowling(pin);
            ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
        }
    }

}
