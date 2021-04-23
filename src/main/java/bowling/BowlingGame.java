package bowling;

import bowling.domain.BowlingTurn;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGame {
    public static void main(String[] args) {
        BowlingTurn bowlingTurn = BowlingTurn.of(InputView.inputPlayerName());
        ResultView.printInitBowlingBoard(bowlingTurn);

        while(!bowlingTurn.isLastFrame()) {
            int currentFrameNumber = bowlingTurn.currentFrameNumber();
            bowlingTurn.play(InputView.inputBowlingPin(currentFrameNumber));
            ResultView.printBowlingBoard(bowlingTurn);
        }
    }
}
