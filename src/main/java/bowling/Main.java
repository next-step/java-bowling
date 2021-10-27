package bowling;

import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.domain.userframeresult.UserFrameResults;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int userSize = InputView.inputUserSize();

        UserFrameResults userFrameResults = UserFrameResults.init();
        IntStream.rangeClosed(1, userSize)
            .mapToObj(index -> User.of(InputView.inputUsername(index)))
            .forEach(userFrameResults::addUser);

        printBowlingGame(userFrameResults);
    }

    private static void printBowlingGame(UserFrameResults userFrameResults) {
        while (!userFrameResults.allBowlingFinished()) {
            Pin pin = Pin.of(InputView.inputFrameShot(userFrameResults.nowTurnUser()));
            userFrameResults.bowlingNowTurnUser(pin);
            ResultView.printBoard(userFrameResults);
        }
    }

}
