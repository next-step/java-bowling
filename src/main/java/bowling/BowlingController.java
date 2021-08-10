package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.player.Bowler;
import bowling.domain.dto.BowlingPlayResultData;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Bowler bowler = Bowler.of(name);
        BowlingGame bowlingGame = BowlingGame.start(bowler);

        ResultView.printBowlingScore(BowlingPlayResultData.of(bowler));
        while (bowlingGame.isNotFinish()) {
            int pins = InputView.inputCountOfHitPins(BowlingPlayResultData.of(bowler));
            bowlingGame.play(pins);
            ResultView.printBowlingScore(BowlingPlayResultData.of(bowler));
        }
    }

}
