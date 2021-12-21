package bowling.domain.controller;

import bowling.domain.bowling.Bowling;
import bowling.domain.bowling.BowlingGame;
import bowling.domain.controller.view.InputView;
import bowling.domain.controller.view.OutputView;
import bowling.domain.pin.Pin;

import java.util.List;

public class BowlingController {

    private BowlingController() {
    }

    public static void main(String[] args) {
        List<Bowling> bowlings = Bowling.listOf(InputView.getNames());
        BowlingGame bowlingGame = new BowlingGame(bowlings);

        boolean continued = true;
        while (continued) {
            String numberOfFrame = bowlingGame.currentNameOfParticipant();
            int hitCount = InputView.getHitCount(numberOfFrame);
            continued = bowlingGame.pitch(Pin.from(hitCount));

            OutputView.showBowlingGame(bowlingGame);
        }
    }

}
