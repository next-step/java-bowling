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

        while (bowlingGame.isGameInProgress()) {
            String nameOfParticipant = bowlingGame.currentNameOfParticipant();
            int hitCount = InputView.getHitCount(nameOfParticipant);
            bowlingGame.pitch(Pin.from(hitCount));

            OutputView.showBowlingGame(bowlingGame);
        }
    }

}
