package bowling;

import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.domain.userframeresult.UserFrameResults;
import bowling.view.InputView;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int userSize = InputView.inputUserSize();

        UserFrameResults userFrameResults = UserFrameResults.init();
        IntStream.rangeClosed(1, userSize)
            .mapToObj(index -> User.of(InputView.inputUsername(index)))
            .forEach(userFrameResults::addUser);

        while (!userFrameResults.allBowlingFinished()) {
            Pin pin = Pin.of(InputView.inputFrameShot(userFrameResults.nowTurnUser()));
            userFrameResults.bowlingNowTurnUser(pin);
        }
//
//        ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
//        printBowlingGame(user, frame);
    }

//    private static void printBowlingGame(User user, Frame frame) {
//        while (!frame.lastFrame().isFinished()) {
//            Pin pin = Pin.of(InputView.inputFrameShot(frame.lastFrame().round()));
//            frame.lastFrame().bowling(pin);
//            ResultView.printBoard(user, FrameResults.createFrameResultsByFirstFrame(frame));
//        }
//    }

}
