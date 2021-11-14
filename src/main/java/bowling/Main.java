package bowling;

import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.domain.userframeresult.BowlingGameResult;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int userSize = InputView.inputUserSize();

        BowlingGameResult bowlingGameResult = BowlingGameResult.init();
        IntStream.rangeClosed(1, userSize)
            .mapToObj(index -> User.of(InputView.inputUsername(index)))
            .forEach(bowlingGameResult::addUser);

        printBowlingGame(bowlingGameResult);
    }

    private static void printBowlingGame(BowlingGameResult bowlingGameResult) {
        while (!bowlingGameResult.allBowlingFinished()) {
            Pin pin = Pin.of(InputView.inputFrameShot(bowlingGameResult.nowTurnUser()));
            bowlingGameResult.bowlingNowTurnUser(pin);
            ResultView.printBoard(bowlingGameResult);
        }
    }

}
