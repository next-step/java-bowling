package bowling;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingController {

    public static void main(String[] args) {
        List<String> names = InputView.inputPlayerNames();
        BowlingGame bowlingGame = BowlingGame.start(names);

        ResultView.printBowlingScore(bowlingGame.bowlerData());
        while (bowlingGame.isNotFinish()) {
            int pins = InputView.inputCountOfHitPins(bowlingGame.currentBowler());
            bowlingGame.play(pins);
            ResultView.printBowlingScore(bowlingGame.bowlerData());
        }
    }

}
