package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Pins;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingMain {
    public static void main(final String[] args) {
        final String userName = InputView.plzEnterUserName();
        final User user = User.of(userName);
        
        final BowlingGame bowlingGame = BowlingGame.of(user);
        OutputView.printResult(bowlingGame.name(), bowlingGame.symbols());
        
        while (bowlingGame.isNotFinished()) {
            final int nowFrameNo = bowlingGame.nowFrameNo();
            final int fallenPins = InputView.plzEnterFallenPins(nowFrameNo);
            bowlingGame.pitch(Pins.of(fallenPins));
            OutputView.printResult(bowlingGame.name(), bowlingGame.symbols());
        }
    }
}
